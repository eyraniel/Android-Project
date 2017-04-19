package ru.tinkoff.shishkova.tfsproject.ui.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import ru.tinkoff.shishkova.tfsproject.R;

public class LoginField extends LinearLayout{

    public EditText login;
    public EditText password;

    public LoginField(Context context) {
        super(context);
    }

    public LoginField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public LoginField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_login_field, this);
        login = (EditText) findViewById(R.id.edit_text_login);
        password = (EditText) findViewById(R.id.edit_text_password);
        password.setEnabled(false);

        login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 0){
                    password.setEnabled(true);
                }
                else{
                    password.setEnabled(false);
                }
            }
        });
    }
}
