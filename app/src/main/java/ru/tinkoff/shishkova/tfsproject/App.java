package ru.tinkoff.shishkova.tfsproject;

import android.app.Application;

import ru.tinkoff.shishkova.tfsproject.model.db.ChatDbHelper;

/**
 * @author Sergey Boishtyan
 */
public class App extends Application {

    private static ChatDbHelper chatDbHelper;

    public static ChatDbHelper getDbhelper() {
        return chatDbHelper;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        chatDbHelper = new ChatDbHelper(this);
    }
}
