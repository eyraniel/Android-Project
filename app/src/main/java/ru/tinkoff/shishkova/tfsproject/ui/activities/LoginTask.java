package ru.tinkoff.shishkova.tfsproject.ui.activities;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import ru.tinkoff.shishkova.tfsproject.ui.fragments.LoginFragment;

/**
 * @author Sergey Boishtyan
 */
class LoginTask extends AsyncTask<String[], Void, Boolean> {

    private WeakReference<LoginFragment> loginFragment;

    public LoginTask(LoginFragment loginFragment) {
        this.loginFragment = new WeakReference<>(loginFragment);
    }

    @Override
    protected Boolean doInBackground(String[]... credentials) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        LoginFragment loginFragment = this.loginFragment.get();
        loginFragment.setSuccess(success);
    }
}
