package com.deadly.orderapp.ui.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.deadly.orderapp.R;
import com.deadly.orderapp.databinding.ActivityLoginBinding;
import com.deadly.orderapp.models.response.auth.VerifyResponse;
import com.deadly.orderapp.services.Client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.security.auth.login.LoginException;

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

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Client client = new Client("http://10.8.0.1:5000",
                            usernameEditText.getText().toString(), passwordEditText.getText().toString(), executorService);

                    LiveData<VerifyResponse> result = client.getAuthTest();
                    result.observe(LoginActivity.this, verifyResponse -> {
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle(null)
                                .setMessage(verifyResponse.status + " : " + verifyResponse.message + "as: " + verifyResponse.login.uuid)

                                // Specifying a listener allows you to take an action before dismissing the dialog.
                                // The dialog is automatically dismissed when a dialog button is clicked.
                                .setPositiveButton(android.R.string.yes, null)

                                // A null listener allows the button to dismiss the dialog and take no further action.
                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();

                    });
                } catch (LoginException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}