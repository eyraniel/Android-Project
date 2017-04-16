package ru.tinkoff.shishkova.tfsproject.ui.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import ru.tinkoff.shishkova.tfsproject.R;

public class SendMessageButton extends LinearLayout {

    public EditText message;
    public Button button;

    public SendMessageButton(Context context) {
        super(context);
    }

    public SendMessageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SendMessageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_send_message_button, this);
        message = (EditText) findViewById(R.id.message);
        button = (Button) findViewById(R.id.btn_send);
        button.setEnabled(false);

        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 0){
                    button.setEnabled(true);
                }
                else{
                    button.setEnabled(false);
                }
            }
        });
    }
}
