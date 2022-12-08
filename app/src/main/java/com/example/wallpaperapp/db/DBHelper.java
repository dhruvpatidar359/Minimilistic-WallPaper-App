package com.example.wallpaperapp.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.wallpaperapp.models.imageModel;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    final static String name = "Database.db";
    final static int DBverion = 1;

    public DBHelper(@Nullable Context context) {
        super(context, name, null, DBverion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table imageDownloading" + "(" + "address text,"+"image_name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists imageloading");
        onCreate(sqLiteDatabase);

    }
    public boolean insertFuction(String address , String image_name){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("image_name",image_name);
        values.put("address",address);

        long check = sqLiteDatabase.insert("imageDownloading",null,values);
        return check > 0;
    }

    public ArrayList<imageModel> getOrders(){
        ArrayList<imageModel> items = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from imageDownloading",null);

        if(cursor.moveToFirst()){
            while(true){
                imageModel model  = new imageModel();
                model.setImage_name(cursor.getString(1));
                model.setDownloadableImage(cursor.getString(0));


                items.add(model);

                if(!cursor.moveToNext()) break;
            }

        }
        cursor.close();
        database.close();
        return items;
    }


}
