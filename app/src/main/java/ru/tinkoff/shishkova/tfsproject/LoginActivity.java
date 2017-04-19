package ru.tinkoff.shishkova.tfsproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import ru.tinkoff.shishkova.tfsproject.ui.widgets.LoginField;
import ru.tinkoff.shishkova.tfsproject.ui.widgets.ProgressButton;

public class LoginActivity extends AppCompatActivity {

    private LoginField loginField;
    private ProgressButton button;

    private LoginTask task = new LoginTask();
    static final String sharedLogin = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginField = (LoginField) findViewById(R.id.login_field);
        button = (ProgressButton) findViewById(R.id.btn_enter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.execute();
            }
        });
    }

    private void startNextScreen() {
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.putExtra(sharedLogin, loginField.login.getText().toString());
        startActivity(intent);
    }

    private class LoginTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            button.showProgress();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            button.hideProgress();
            startNextScreen();
        }
    }
}

