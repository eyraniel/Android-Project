package ru.tinkoff.shishkova.tfsproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

import ru.tinkoff.shishkova.tfsproject.ui.widgets.SendMessageButton;

public class DialogActivity extends AppCompatActivity {

    private List<MessageItem> list;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private SendMessageButton sendButton;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        sendButton = (SendMessageButton) findViewById(R.id.send_msg_btn);
        backButton = (ImageButton) findViewById(R.id.btn_back);

        sendButton.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sendButton.message.getText().toString().length() > 0) {
                    if (!list.isEmpty()) {
                        list.add(0, new MessageItem(sendButton.message.getText().toString()));
                        sendButton.message.setText("");
                        adapter.notifyItemInserted(0);
                        recyclerView.scrollToPosition(0);
                    }
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
        adapter = new MessageAdapter(createDataset());
        recyclerView.setAdapter(adapter);
    }

    private List<MessageItem> createDataset() {
        list = new ArrayList<>();
        list.add(new MessageItem("blabla", "Captain America", 2));
        list.add(new MessageItem("blablabla"));
        list.add(new MessageItem("blabla"));
        list.add(new MessageItem("blablablablabla", "Iron Man", 2));
        list.add(new MessageItem("bla"));
        list.add(new MessageItem("blabla", "Loki", 2));
        return list;
    }

    private void backScreen() {
        finish();
    }
}
