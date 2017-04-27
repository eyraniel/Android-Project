package ru.tinkoff.shishkova.tfsproject;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class DialogLoader extends AsyncTaskLoader<List<MessageItem>> {

    private List<MessageItem> data;

    public DialogLoader(Context context) {
        super(context);
    }

    @Override
    public List<MessageItem> loadInBackground() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return createDataset();
    }

    @Override
    public void deliverResult(List<MessageItem> newData) {

        data = newData;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(isStarted()){
            super.deliverResult(newData);
        }

    }

    @Override
    protected void onStartLoading() {
        if(data != null){
            deliverResult(data);
        }

        if(takeContentChanged() || data == null){
            forceLoad();
        }

    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    public void onCanceled(List<MessageItem> data) {
        super.onCanceled(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        data = null;
    }

    private List<MessageItem> createDataset() {
        List<MessageItem> list = new ArrayList<>();
        list.add(new MessageItem("blabla", "Captain America", 2));
        list.add(new MessageItem("blablabla"));
        list.add(new MessageItem("blabla"));
        list.add(new MessageItem("blablablablabla", "Iron Man", 2));
        list.add(new MessageItem("bla"));
        list.add(new MessageItem("blabla", "Loki", 2));
        return list;
    }
}
