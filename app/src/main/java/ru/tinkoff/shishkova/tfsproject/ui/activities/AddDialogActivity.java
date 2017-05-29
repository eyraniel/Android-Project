package ru.tinkoff.shishkova.tfsproject.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ru.tinkoff.shishkova.tfsproject.R;
import ru.tinkoff.shishkova.tfsproject.firebase.OnTransactionComplete;
import ru.tinkoff.shishkova.tfsproject.firebase.dialog.DialogRepository;
import ru.tinkoff.shishkova.tfsproject.ui.items.DialogItem;

public class AddDialogActivity extends AppCompatActivity {

    private EditText title;
    private EditText desc;
    private DialogRepository dialogRepository = DialogRepository.getInstance();

    public static void start(Context context) {
        context.startActivity(new Intent(context, AddDialogActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dialog);
        title = (EditText) findViewById(R.id.title);
        desc = (EditText) findViewById(R.id.desc);
    }

    public void onAddClick(View view) {
        dialogRepository.addDialog(new DialogItem(title.getText().toString(), desc.getText().toString()), new OnTransactionComplete() {
            @Override
            public void onCommit(Object result) {
                finish();
            }

            @Override
            public void onAbort(Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}