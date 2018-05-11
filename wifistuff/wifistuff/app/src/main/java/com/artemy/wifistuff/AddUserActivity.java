package com.artemy.wifistuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {

    EditText nickname,password,first_name,last_name,phone;
    UserHelper userHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        userHelper = new UserHelper(this);

        nickname = (EditText)this.findViewById(R.id.edit_text_nickname);
        password = (EditText)this.findViewById(R.id.edit_text_password);
        first_name = (EditText)this.findViewById(R.id.edit_text_first_name);
        last_name = (EditText)this.findViewById(R.id.edit_text_last_name);
        phone = (EditText)this.findViewById(R.id.edit_text_phone);

    }

    public void addUser(View view)
    {
        String nickname,password,first_name,last_name,phone;
        nickname = this.nickname.getText().toString();
        password = this.password.getText().toString();
        first_name = this.first_name.getText().toString();
        last_name = this.last_name.getText().toString();
        phone = this.phone.getText().toString();

        if(nickname.length()*password.length()*first_name.length()*last_name.length()*phone.length()==0)
        {
            Toast.makeText(this, "You have unfilled fields", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(nickname,password,first_name,last_name,phone);
        userHelper.insertUser(user);
    }

    public void clear_fields(View view)
    {
        nickname.setText("");
        password.setText("");
        first_name.setText("");
        last_name.setText("");
        phone.setText("");
    }

    public void  finish__add_user_activity(View view)
    {
        finish();
    }


}
