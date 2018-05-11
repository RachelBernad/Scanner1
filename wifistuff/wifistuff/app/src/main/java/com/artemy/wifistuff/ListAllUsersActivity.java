package com.artemy.wifistuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAllUsersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    ListView list_view_all_users;
    ArrayList<User> all_users = new ArrayList<User>();
    AdapterUser adapterUser;
    UserHelper userHelper;
    EditText user_nickname;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_users);
        list_view_all_users = (ListView) findViewById(R.id.list_view_all_users);
        user_nickname = (EditText)findViewById(R.id.user_nickname);
        userHelper = new UserHelper(this);
        all_users = userHelper.getAllUsers();
        adapterUser = new AdapterUser(this, all_users);
        list_view_all_users.setAdapter(adapterUser);
        list_view_all_users.setOnItemClickListener(this);
        list_view_all_users.setOnItemLongClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        userHelper.deleteUser(all_users.get(position));
        all_users.remove(position);
        adapterUser.notifyDataSetChanged();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l)
    {
          Intent intent = new Intent(this,EditUserActivity.class);
          intent.putExtra("user", all_users.get(position));
          startActivityForResult(intent,1);
          return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode==RESULT_OK) {
            all_users.set(position, (User) data.getSerializableExtra("user"));
            adapterUser.notifyDataSetChanged();
        }
    }

    public void Search(View view)
    {
        String nickname = user_nickname.getText().toString();
        if(userHelper.isExist(nickname))
        {
            Toast.makeText(this, "There is a user with nickname : " + nickname, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "There is not a user with nickname : " + nickname, Toast.LENGTH_SHORT).show();
        }
    }

    public void finish__list_all_users_activity(View view)
    {
        finish();
    }

}
