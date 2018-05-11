package com.artemy.wifistuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditUserActivity extends AppCompatActivity  {

    EditText edit_text_nickname,edit_text_password,edit_text_fisrt_name,edit_text_last_name,edit_text_phone;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");

        edit_text_nickname = (EditText) this.findViewById(R.id.edit_text_nickname);
        edit_text_nickname.setText(user.getNickname());

        edit_text_password = (EditText) this.findViewById(R.id.edit_text_password);
        edit_text_password.setText(user.getPassword());

        edit_text_fisrt_name = (EditText) this.findViewById(R.id.edit_text_first_name);
        edit_text_fisrt_name.setText(user.getFirst_name());

        edit_text_last_name = (EditText) this.findViewById(R.id.edit_text_last_name);
        edit_text_last_name.setText(user.getLast_name());

        edit_text_phone = (EditText) this.findViewById(R.id.edit_text_phone);
        edit_text_phone.setText(user.getPhone());

    }


    public void submitClick(View view)
    {
        if (edit_text_nickname.getText().length() * edit_text_password.getText().length()*
                edit_text_fisrt_name.getText().length() * edit_text_last_name.getText().length() *edit_text_phone.getText().length() == 0)
        {
            Toast.makeText(this, "You have unfilled fields", Toast.LENGTH_SHORT).show();
            return;
        }

        user.setNickname(edit_text_nickname.getText().toString());
        user.setPassword(edit_text_password.getText().toString());
        user.setFirst_name(edit_text_fisrt_name.getText().toString());
        user.setLast_name(edit_text_last_name.getText().toString());
        user.setPhone(edit_text_phone.getText().toString());

        UserHelper userHelper = new UserHelper(this);
        userHelper.updateUser(user);

        Intent intent = new Intent();
        intent.putExtra("user",user);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onBackPressed()
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void finish_edit_user_activity(View view)
    {
        setResult(RESULT_CANCELED);
        finish();
    }



}
