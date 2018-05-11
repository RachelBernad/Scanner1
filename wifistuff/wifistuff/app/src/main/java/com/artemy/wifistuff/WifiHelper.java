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

public class WifiHelper extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase database;

    public static final String DATABASENAME = "user.db";
    public static final String TABLE_WIFI = "table_wifi";
    public static final int DATABASEVERSION = 4;

    public static final String COLUMN_ID = "wifiId";
    public static final String COLUMN_WIFI_NAME = "nickname";
    public static final String COLUMN_WIFI_PASS = "username";
    public static final String COLUMN_WIFI_PWTYPE = "password";
    public static final String COLUMN_BSSID = "bssid";
    public static final String COLUMN_MONGO_ID = "mongo_id";


//    private static final String CREATE_TABLE_WIFI = "CREATE TABLE IF NOT EXISTS " +
//            TABLE_USER + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NICKNAME + " TEXT," + COLUMN_PASSWORD + " TEXT,"
//            + COLUMN_FIRST_NAME + " TEXT," + COLUMN_BSSID + "TEXT " + COLUMN_MONGO_ID + "TEXT "  + ");";
//

    public WifiHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+ TABLE_WIFI + " ("+COLUMN_ID+" integer primary key autoincrement, "+ COLUMN_WIFI_NAME + " text, " + COLUMN_WIFI_PASS + " text, " + COLUMN_WIFI_PWTYPE + " text, " + COLUMN_BSSID + " text, " + COLUMN_MONGO_ID + " text )");
        Log.i("data1", "Table user created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WIFI);
        onCreate(db);
    }

    public void open()
    {
        database = getWritableDatabase();
        Log.i("data", "Database connection open");
    }

    public void close() {
        super.close();
    }


    public void updateWifi(Wifi wifi)
    {

        String sql = String.format("update " + TABLE_WIFI + " set "  + COLUMN_WIFI_NAME + "='%s' , " + COLUMN_WIFI_PASS + "='%s' , "
                + COLUMN_WIFI_PWTYPE + "='%s' , " + COLUMN_BSSID + "='%s' , " + COLUMN_MONGO_ID + "='%s' where " + COLUMN_ID + "=%d",wifi.getWifi_name(),wifi.getWifi_pass(),wifi.getWifi_pwtype(),wifi.getBssid(),wifi.getMongo_id(),wifi.getId());

        open();
        database.execSQL(sql);
        close();

    }

    public void deleteWifi(Wifi wifi)
    {
        open();
        String sql = String.format("delete from " + TABLE_WIFI + " where " + COLUMN_ID + "=%d",wifi.getId());
        database.execSQL(sql);
        close();
    }


    public void insertWifi(Wifi wifi)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WIFI_NAME, wifi.getWifi_name());
        values.put(COLUMN_WIFI_PASS, wifi.getWifi_pass());
        values.put(COLUMN_WIFI_PWTYPE, wifi.getWifi_pwtype());
        values.put(COLUMN_BSSID, wifi.getBssid());
        values.put(COLUMN_MONGO_ID, wifi.getMongo_id());
        long insertId = (long) database.insert(TABLE_WIFI, null, values);
        wifi.setId(insertId);

        close();
    }

    public ArrayList<Wifi> getAllWifies() {

        open();
        ArrayList<Wifi> all_wifies = new ArrayList<>();

//        String orderBy = COLUMN_NICKNAME + " desc";
        String orderBy = COLUMN_WIFI_NAME + " asc";

        Cursor cursor = database.query(TABLE_WIFI,null,null,null,null,null,orderBy);


        while (cursor.moveToNext())
        {
            long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
            String wifi_name = cursor.getString(cursor.getColumnIndex(COLUMN_WIFI_NAME));
            String wifi_pass = cursor.getString(cursor.getColumnIndex(COLUMN_WIFI_PASS));
            String wifi_pwtype = cursor.getString(cursor.getColumnIndex(COLUMN_WIFI_PWTYPE));
            String bssid = cursor.getString(cursor.getColumnIndex(COLUMN_BSSID));
            String mongo_id = cursor.getString(cursor.getColumnIndex(COLUMN_MONGO_ID));
            Wifi wifi = new Wifi(id,wifi_name, wifi_pass, wifi_pwtype,bssid,mongo_id);
            all_wifies.add(wifi);
        }

        cursor.close();
        close();
        return all_wifies;
    }

    public boolean isExist(String wifi_to_search)
    {
        open();
        Cursor cursor = database.rawQuery("select * from " + TABLE_WIFI + " where " + COLUMN_WIFI_NAME + " = ? ",new String[]{wifi_to_search});
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
