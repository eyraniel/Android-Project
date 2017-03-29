package ru.tinkoff.shishkova.tfsproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class DialogActivity extends AppCompatActivity {

    final List<MessageItem> list = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private Button sendButton;
    //private Button backButton;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        message = (EditText) findViewById(R.id.message);
        sendButton = (Button) findViewById(R.id.btn_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (message.getText().length() > 0) {
                    list.add(0, new MessageItem(message.getText().toString(), 0));
                    adapter.notifyItemInserted(0);
                    recyclerView.scrollToPosition(0);
                }
            }
        });
        /*backButton = (Button) findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backScreen();
            }
        });*/
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_dialog);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MessageAdapter(createDataset());
        recyclerView.setAdapter(adapter);
    }

    private List<MessageItem> createDataset() {
        List<MessageItem> list = new ArrayList<>();
        list.add(new MessageItem("blabla", 1));
        list.add(new MessageItem("blablabla", 0));
        list.add(new MessageItem("blabla", 0));
        list.add(new MessageItem("blablablablabla", 1));
        list.add(new MessageItem("bla", 0));
        list.add(new MessageItem("blabla", 1));
        return list;
    }

    /*
    private void backScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }*/
}
