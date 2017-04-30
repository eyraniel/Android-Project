package ru.tinkoff.shishkova.tfsproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import ru.tinkoff.shishkova.tfsproject.ui.fragments.LoginFragment;
import ru.tinkoff.shishkova.tfsproject.ui.activities.LoginTask;
import ru.tinkoff.shishkova.tfsproject.R;
import ru.tinkoff.shishkova.tfsproject.ui.widgets.LoginField;
import ru.tinkoff.shishkova.tfsproject.ui.widgets.ProgressButton;

public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginListener {

    public static final String PENDING_INTENT = "pi";
    public static final String EXTRA_SUCCESS = "extra_success";
    public static final String CREDENTIALS = "credentials";
    public static final int LOGIN_REQUEST_CODE = 1;

    private LoginField loginField;
    private ProgressButton button;

    private LoginFragment loginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginField = (LoginField) findViewById(R.id.login_field);
        button = (ProgressButton) findViewById(R.id.btn_enter);

        if (savedInstanceState != null) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            loginFragment = (LoginFragment) supportFragmentManager.findFragmentByTag(LoginFragment.TAG);
            if (loginFragment != null) {

            } else {
                loginFragment = new LoginFragment();
                supportFragmentManager.beginTransaction().add(loginFragment, LoginFragment.TAG).commit();
            }
        } else {
            loginFragment = new LoginFragment();
            getSupportFragmentManager().beginTransaction().add(loginFragment, LoginFragment.TAG).commit();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgress();
                new LoginTask(loginFragment).execute();
            }
        });
    }

    public void showProgress() {
        button.showProgress();
    }

    public void hideProgress() {
        button.hideProgress();
    }

    @Override
    public void onResult(Boolean success) {
        if (success) {
            startNextScreen();
        } else {
            hideProgress();
            Toast.makeText(getApplicationContext(), "Что-то не так!", Toast.LENGTH_SHORT).show();
        }
    }

    void startNextScreen() {
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.putExtra("LOGIN", loginField.login.getText().toString());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

