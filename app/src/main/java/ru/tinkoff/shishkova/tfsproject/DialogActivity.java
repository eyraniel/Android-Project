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

        sendButton = (SendMessageButton) findViewById(R.id.send_msg_btn);
        backButton = (ImageButton) findViewById(R.id.btn_back);

        sendButton.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sendButton.message.getText().toString().length() > 0) {
                    list.add(0, new MessageItem(sendButton.message.getText().toString()));
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
        adapter = new MessageAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    /*private List<MessageItem> createDataset() {
        list = new ArrayList<>();
        list.add(new MessageItem("blabla", "Captain America", 2));
        list.add(new MessageItem("blablabla"));
        list.add(new MessageItem("blabla"));
        list.add(new MessageItem("blablablablabla", "Iron Man", 2));
        list.add(new MessageItem("bla"));
        list.add(new MessageItem("blabla", "Loki", 2));
        return list;
    }*/

    private void backScreen() {
        finish();
    }

    public static class DialogLoader extends AsyncTaskLoader<List<MessageItem>> {

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

            List<MessageItem> data = new ArrayList<>();
            data.add(new MessageItem("blabla", "Captain America", 2));
            data.add(new MessageItem("blablabla"));
            data.add(new MessageItem("blabla"));
            data.add(new MessageItem("blablablablabla", "Iron Man", 2));
            data.add(new MessageItem("bla"));
            data.add(new MessageItem("blabla", "Loki", 2));
            return data;
        }

        @Override
        public void deliverResult(List<MessageItem> newData) {

            data = newData;

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
        this.list.addAll(0, data);
        adapter.notifyItemRangeInserted(0, data.size());
        recyclerView.scrollToPosition(0);
    }

    @Override
    public void onLoaderReset(Loader<List<MessageItem>> loader) {
        adapter.notifyDataSetChanged();
    }
}
