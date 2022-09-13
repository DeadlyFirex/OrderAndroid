package com.deadly.orderapp.services;

import android.util.NoSuchPropertyException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.deadly.orderapp.models.response.BaseResponse;
import com.deadly.orderapp.models.response.auth.AuthenticationResponse;
import com.deadly.orderapp.models.response.auth.VerifyResponse;
import com.deadly.orderapp.models.response.event.EventListResponse;
import com.deadly.orderapp.models.response.event.EventResponse;
import com.deadly.orderapp.models.response.generic.ChangedResponse;
import com.deadly.orderapp.models.response.generic.VersionResponse;
import com.deadly.orderapp.models.response.product.ProductListResponse;
import com.deadly.orderapp.models.response.product.ProductResponse;
import com.deadly.orderapp.models.response.user.UserListResponse;
import com.deadly.orderapp.models.response.user.UserResponse;

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

        executor.execute(() -> {
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
        });
    }

    // Generics
    public LiveData<BaseResponse> getHealth() {
        MutableLiveData<BaseResponse> result = new MutableLiveData<>();
        executor.execute(() -> _CLIENT.getHealth().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                result.setValue(null);
            }
        }));
        return result;
    }

    public LiveData<VersionResponse> getVersion() {
        MutableLiveData<VersionResponse> result = new MutableLiveData<>();
        executor.execute(() -> _CLIENT.getVersion().enqueue(new Callback<VersionResponse>() {
            @Override
            public void onResponse(Call<VersionResponse> call, Response<VersionResponse> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<VersionResponse> call, Throwable t) {
                result.setValue(null);
            }
        }));
        return result;
    }

    public LiveData<ChangedResponse> getLastChanged() {
        MutableLiveData<ChangedResponse> result = new MutableLiveData<>();
        executor.execute(() -> _CLIENT.getLastChanged().enqueue(new Callback<ChangedResponse>() {
            @Override
            public void onResponse(Call<ChangedResponse> call, Response<ChangedResponse> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ChangedResponse> call, Throwable t) {
                result.setValue(null);
            }
        }));
        return result;
    }

    // Authentication

    public LiveData<VerifyResponse> getAuthTest() {
        MutableLiveData<VerifyResponse> result = new MutableLiveData<>();
        executor.execute(() -> _CLIENT.getAuthTest().enqueue(new Callback<VerifyResponse>() {
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

    // Users

    public LiveData<UserListResponse> getAllUsers() {
        MutableLiveData<UserListResponse> result = new MutableLiveData<>();
        executor.execute(() -> _CLIENT.getAllUsers().enqueue(new Callback<UserListResponse>() {
            @Override
            public void onResponse(Call<UserListResponse> call, Response<UserListResponse> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserListResponse> call, Throwable t) {
                result.setValue(null);
            }
        }));
        return result;
    }

    public LiveData<UserResponse> getUserByUuid(String uuid) {
        MutableLiveData<UserResponse> result = new MutableLiveData<>();
        executor.execute(() -> _CLIENT.getUserByUuid(uuid).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                result.setValue(null);
            }
        }));
        return result;
    }

    @Deprecated
    public LiveData<?> getUsersLastChanged() {
        throw new NoSuchPropertyException("Unimplemented");
    }
    
    // Product

    public LiveData<ProductListResponse> getAllProducts() {
        MutableLiveData<ProductListResponse> result = new MutableLiveData<>();
        executor.execute(() -> _CLIENT.getAllProducts().enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {
                result.setValue(null);
            }
        }));
        return result;
    }

    public LiveData<ProductResponse> getProductByUuid(String uuid) {
        MutableLiveData<ProductResponse> result = new MutableLiveData<>();
        executor.execute(() -> _CLIENT.getProductByUuid(uuid).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                result.setValue(null);
            }
        }));
        return result;
    }

    @Deprecated
    public LiveData<?> getEventsLastChanged() { throw new NoSuchPropertyException("Unimplemented"); }

    // Events

    public LiveData<EventListResponse> getAllEvents() {
        MutableLiveData<EventListResponse> result = new MutableLiveData<>();
        executor.execute(() -> _CLIENT.getAllEvents().enqueue(new Callback<EventListResponse>() {
            @Override
            public void onResponse(Call<EventListResponse> call, Response<EventListResponse> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<EventListResponse> call, Throwable t) {
                result.setValue(null);
            }
        }));
        return result;
    }

    public LiveData<EventResponse> getEventByUuid(String uuid) {
        MutableLiveData<EventResponse> result = new MutableLiveData<>();
        executor.execute(() -> _CLIENT.getEventByUuid(uuid).enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<EventResponse> call, Throwable t) {
                result.setValue(null);
            }
        }));
        return result;
    }

    @Deprecated
    public LiveData<?> getProductsLastChanged() { throw new NoSuchPropertyException("Unimplemented"); }
}
