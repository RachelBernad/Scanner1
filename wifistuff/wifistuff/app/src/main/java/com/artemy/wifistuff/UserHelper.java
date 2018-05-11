package com.artemy.wifistuff;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by natal on 14/02/2018.
 */

public class UserHelper extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase database;

    public static final String DATABASENAME = "user.db";
    public static final String TABLE_USER = "table_user";
    public static final int DATABASEVERSION = 4;

    public static final String COLUMN_ID = "userId";
    public static final String COLUMN_NICKNAME = "nickname";
    public static final String COLUMN_PASSWORD = "username";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PHONE = "phone";


    public UserHelper(Context context)
    {
        super(context, DATABASENAME, null, DATABASEVERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+ TABLE_USER + " ("+COLUMN_ID+" integer primary key autoincrement, "+ COLUMN_NICKNAME + " text, " +
                COLUMN_PASSWORD + " text, " + COLUMN_FIRST_NAME + " text,"+ COLUMN_LAST_NAME + " text, "+ COLUMN_PHONE + " text)");
        Log.i("data2", "Table user created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void open()
    {
        database = getWritableDatabase();
        Log.i("data3", "Database connection open");
    }

    public void close() {
        super.close();
    }


    public void updateUser(User user)
    {

        String sql = String.format("update " + TABLE_USER + " set "  + COLUMN_NICKNAME + "='%s' , " + COLUMN_PASSWORD + "='%s' , "
                + COLUMN_FIRST_NAME + "='%s' , " + COLUMN_LAST_NAME + "='%s' , " + COLUMN_PHONE + "='%s' where " + COLUMN_ID + "=%d",user.getNickname(),
                user.getPassword(),user.getFirst_name(),user.getLast_name(),user.getPhone(),user.getId());

        open();
        database.execSQL(sql);
        close();

    }

    public void deleteUser(User user)
    {
        open();
        String sql = String.format("delete from " + TABLE_USER + " where " + COLUMN_ID + "=%d",user.getId());
        database.execSQL(sql);
        close();
    }


    public void insertUser(User user)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NICKNAME, user.getNickname());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_FIRST_NAME, user.getFirst_name());
        values.put(COLUMN_LAST_NAME, user.getLast_name());
        values.put(COLUMN_PHONE, user.getPhone());
        long insertId = (long) database.insert(TABLE_USER, null, values);
        user.setId(insertId);

        close();
    }

    public ArrayList<User> getAllUsers() {

        open();
        ArrayList<User> all_users = new ArrayList<>();

//        String orderBy = COLUMN_NICKNAME + " desc";
        String orderBy = COLUMN_NICKNAME + " asc";

        Cursor cursor = database.query(TABLE_USER,null,null,null,null,null,orderBy);


        while (cursor.moveToNext())
        {
            long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
            String nickname = cursor.getString(cursor.getColumnIndex(COLUMN_NICKNAME));
            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String first_name = cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME));
            String last_name = cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME));
            String phone = cursor.getString(cursor.getColumnIndex(COLUMN_PHONE));
            User user = new User(nickname, password,first_name,last_name,phone,id);
            all_users.add(user);
        }

        cursor.close();
        close();
        return all_users;
    }

    public boolean isExist(String user_to_search)
    {
        open();
        Cursor cursor = database.rawQuery("select * from " + TABLE_USER + " where " + COLUMN_NICKNAME + " = ? ",new String[]{user_to_search});
        int count = cursor.getCount();
        close();
        if (count==0)
        {
            return false;
        }
        else
            return true;
    }


}
