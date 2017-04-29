package ru.tinkoff.shishkova.tfsproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import ru.tinkoff.shishkova.tfsproject.ui.widgets.SendMessageButton;

public class DialogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<MessageItem>> {

    private List<MessageItem> list = new ArrayList<>();
    private static List<MessageItem> data = new ArrayList<>();
    private static final int LOADER_ID = 1;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private SendMessageButton sendButton;
    private ImageButton backButton;
    private DialogLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        loader = new DialogLoader(this);
        getSupportLoaderManager().initLoader(LOADER_ID, null, this);

        list = data;

        sendButton = (SendMessageButton) findViewById(R.id.send_msg_btn);
        backButton = (ImageButton) findViewById(R.id.btn_back);

        sendButton.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sendButton.message.getText().toString().length() > 0) {
                    list.add(0, new MessageItem(sendButton.message.getText().toString()));
                    loader.deliverResult(list);
                    sendButton.message.setText("");
                    adapter.notifyItemInserted(0);
                    recyclerView.scrollToPosition(0);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backScreen();
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_dialog);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        if (list.isEmpty()) {
            list = loader.loadInBackground();
        }
        adapter = new MessageAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void backScreen() {
        finish();
    }

    @Override
    public Loader<List<MessageItem>> onCreateLoader(int id, Bundle args) {
        if (id == LOADER_ID) {
            return new DialogLoader(this);
        } else{
            throw new IllegalArgumentException("Unknown loader id = " + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<List<MessageItem>> loader, List<MessageItem> data) {
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void onLoaderReset(Loader<List<MessageItem>> loader) {
        adapter.notifyDataSetChanged();
    }

    public static class DialogLoader extends AsyncTaskLoader<List<MessageItem>> {

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

            data = createDataset();
            return data;
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
    }

    private static List<MessageItem> createDataset() {
        List<MessageItem> initData = new ArrayList<>();
        initData.add(new MessageItem("blabla", "Captain America", 2));
        initData.add(new MessageItem("blablabla"));
        initData.add(new MessageItem("blabla"));
        initData.add(new MessageItem("blablablablabla", "Iron Man", 2));
        initData.add(new MessageItem("bla"));
        initData.add(new MessageItem("blabla", "Loki", 2));
        return initData;
    }
}
