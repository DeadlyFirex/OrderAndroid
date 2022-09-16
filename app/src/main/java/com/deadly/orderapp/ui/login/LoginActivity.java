package com.deadly.orderapp.ui.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.deadly.orderapp.databinding.ActivityLoginBinding;
import com.deadly.orderapp.models.response.auth.VerifyResponse;
import com.deadly.orderapp.services.Client;
import com.deadly.orderapp.ui.dashboard.DashboardActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    public ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.loginButton;

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                // ignore
            }
        };

        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);

        loginButton.setOnClickListener(v -> {
            try {
                Client client = new Client("http://10.8.0.1:5000",
                        usernameEditText.getText().toString(), passwordEditText.getText().toString(), executorService);

                LiveData<VerifyResponse> result = client.getAuthTest();
                result.observe(LoginActivity.this, verifyResponse -> {
                    if (verifyResponse == null) {
                        Snackbar.make(passwordEditText, "stringId", BaseTransientBottomBar.LENGTH_SHORT).show();
                    } else {
                        Intent goToNextActivity = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(goToNextActivity);
                    }
                });
            } catch (RuntimeException e) {
                throw new RuntimeException(":P");
            }
        });
    }
}