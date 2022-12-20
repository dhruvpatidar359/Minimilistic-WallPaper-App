package com.example.wallpaperapp.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import androidx.annotation.Nullable;

import com.example.wallpaperapp.models.imageModel;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    final static String name = "Database.db";
    final static int DBverion = 10;

    public DBHelper(@Nullable Context context) {
        super(context, name, null, DBverion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table imageDownloading" + "(" + "address text," + "preview_images text," + "image_name text," + "isFav text default 'false')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists imageloading");
        onCreate(sqLiteDatabase);

    }

    public boolean insertFuction(String address, String image_name) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("image_name", image_name);
        values.put("preview_images", address);

        long check = sqLiteDatabase.insert("imageDownloading", null, values);
        return check > 0;
    }

    public boolean insertFav(String name) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("isFav", "true");

        long check = sqLiteDatabase.update("imageDownloading", values, "image_name=?", new String[]{name});
        sqLiteDatabase.close();

        return check > 0;


    }

    public String getFav(String name) {

        SQLiteDatabase database = this.getWritableDatabase();

//        Cursor cursor=database.query("imageDownloading",new String[]{"isFav"},"isFav" + "=" + "true",null,null,null,null);
        Cursor cursor = database.rawQuery("Select isFav from imageDownloading where image_name = " + "\"" + name + "\"", null);

        cursor.moveToFirst();

        return cursor.getString(0);
    }

    public boolean insertPreview(String address, String image_name) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues values = new ContentValues();
        Log.d("This",address);
        Log.d("This",image_name);


        values.put("address", address);

        long check = sqLiteDatabase.update("imageDownloading", values, "image_name=?", new String[]{image_name});
        Log.d("This",Long.toString(check));

        return check > 0;
    }

    public boolean removeFav(String name) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("isFav", "false");
        long check = sqLiteDatabase.update("imageDownloading", values, "image_name=?", new String[]{name});
        sqLiteDatabase.close();

        return check > 0;
    }

    public ArrayList<imageModel> getOrders() {
        ArrayList<imageModel> items = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from imageDownloading", null);

        if (cursor.moveToFirst()) {
            while (true) {
                imageModel model = new imageModel();
                model.setTags(cursor.getString(2));
                model.setWebformatURL(cursor.getString(1));
                model.setLargeImageURL(cursor.getString(0));


                items.add(model);

                if (!cursor.moveToNext()) break;
            }

        }
        cursor.close();
        database.close();
        return items;
    }

    public ArrayList<imageModel> getOrdersFav() {
        ArrayList<imageModel> items = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from imageDownloading where isFav = " + "\"" + "true" + "\"", null);

        if (cursor.moveToFirst()) {
            while (true) {
                imageModel model = new imageModel();
                model.setTags(cursor.getString(2));
                model.setWebformatURL(cursor.getString(1));
                model.setLargeImageURL(cursor.getString(0));


                items.add(model);

                if (!cursor.moveToNext()) break;
            }

        }
        cursor.close();
        database.close();
        return items;
    }


}
