package com.deadly.orderapp.services;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.deadly.orderapp.models.response.BaseResponse;
import com.deadly.orderapp.models.response.auth.AuthenticationResponse;
import com.deadly.orderapp.models.response.auth.VerifyResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Executor;

import javax.security.auth.login.LoginException;

import lombok.AccessLevel;
import lombok.Getter;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private final Executor executor;
    @Getter(AccessLevel.PROTECTED)
    protected String _TOKEN = "";
    @Getter(AccessLevel.PUBLIC)
    protected String _UUID = "";
    @Getter(AccessLevel.PROTECTED)
    protected double _LIFETIME;
    @Getter(AccessLevel.PUBLIC)
    private final String BASE_URL;
    @Getter(AccessLevel.PUBLIC)
    private boolean _LOGGED_IN = false;
    private final ClientInterface _CLIENT;

    @Deprecated
    public Client(String BASE_URL, Executor executor) {
        this.BASE_URL = BASE_URL;
        this.executor = executor;

        _CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build()
                .create(ClientInterface.class);
    }

    public Client(String BASE_URL, String username, String password, Executor executor) throws RuntimeException, LoginException {
        this.BASE_URL = BASE_URL;
        this.executor = executor;

        Interceptor interceptor = chain -> {
            Request.Builder requestBuilder = chain.request().newBuilder();

            try {
                if (!Objects.equals(chain.request().headers("Authorize").get(0), "false")) {
                    requestBuilder.addHeader("Authorization", "Bearer " + _TOKEN);
                }
            } catch (IndexOutOfBoundsException ignored) {
                requestBuilder.addHeader("Authorization", "Bearer " + _TOKEN);
            }
            return chain.proceed(requestBuilder.build());
        };

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        OkHttpClient client = builder.build();

        _CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ClientInterface.class);

        HashMap<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);

        try {
            AuthenticationResponse response = _CLIENT.postLogin(body).execute().body();

            if (response == null) {
                _LOGGED_IN = false;
                _TOKEN = null;
                _UUID = null;
                return;
            }

            _LOGGED_IN = true;
            _TOKEN = response.login.token;
            _LIFETIME = response.login.lifetime;
            _UUID = response.login.uuid;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public int getHealth() {
        final int[] code = new int[1];
        executor.execute(() -> _CLIENT.getHealth().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                code[0] = response.code();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                code[0] = -1;
            }
        }));
        return code[0];
    }

    public LiveData<VerifyResponse> getAuthAdminTest() {
        MutableLiveData<VerifyResponse> result = new MutableLiveData<>();
        executor.execute(() -> _CLIENT.getAuthAdminTest().enqueue(new Callback<VerifyResponse>() {
            @Override
            public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VerifyResponse> call, Throwable t) {
                result.setValue(null);
            }
        }));
        return result;
    }
}
