package ru.tinkoff.shishkova.tfsproject.ui.widgets;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;

import ru.tinkoff.shishkova.tfsproject.ui.activities.LoginActivity;

import static android.app.Activity.RESULT_OK;

/**
 * @author Sergey Boishtyan
 */
public class LoginService extends IntentService {


    public LoginService() {
        super("LoginService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(3000);
            Intent result = new Intent().putExtra(LoginActivity.EXTRA_SUCCESS, false);
            PendingIntent pendingIntent = intent.getParcelableExtra(LoginActivity.PENDING_INTENT);
            try {
                pendingIntent.send(this, RESULT_OK, result);
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopSelf();
    }
}
