package com.example.ulan.osm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Админ on 06.01.2017.
 */

public class DataHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mydatabase.db";

    public static final String TABLE_ROUTE ="Route";
    public static final String ROUTE_LOC_COLUMN="loc_route";
    public static final String ROUTE_LAT_COLUMN="lat_route";
    public static final String ROUTE_JSON_ID_COLUMN="id_route";
    public static final String ROUTE_LONG_COLUMN="long_route";
    public static final String ROUTE_NUMBER_COLUMN="number_route";

    public static final String TABLE_SEARCH ="Search";
    public static final String SEARCH_LOC_COLUMN="loc_search";
    public static final String SEARCH_LAT_COLUMN="lat_search";
    public static final String SEARCH_JSON_ID_COLUMN="id_search";
    public static final String SEARCH_LONG_COLUMN="long_search";
    public static final String SEARCH_NUMBER_COLUMN="number_search";


    public static final String TABLE_DATE ="Date";
    public static final String DATE_D_COLUMN="date_d";




    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_ROUTE + "(" +
                ROUTE_LAT_COLUMN + " real," +
                ROUTE_LOC_COLUMN + " text," +
                ROUTE_JSON_ID_COLUMN + " integer," +
                ROUTE_NUMBER_COLUMN + " text," +
                ROUTE_LONG_COLUMN + " real);");

        db.execSQL("create table " + TABLE_SEARCH + "(" +
                SEARCH_LAT_COLUMN + " real," +
                SEARCH_LOC_COLUMN + " text," +
                SEARCH_JSON_ID_COLUMN + " integer," +
                SEARCH_NUMBER_COLUMN + " text," +
                SEARCH_LONG_COLUMN + " real);");

        db.execSQL("create table " + TABLE_DATE + "(" +

                DATE_D_COLUMN + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public  void addRoute(double lat, double longt, String name, String loc, int id){
        ContentValues values = new ContentValues();

        values.put(ROUTE_LAT_COLUMN, lat);
        values.put(ROUTE_JSON_ID_COLUMN, id);
        values.put(ROUTE_LOC_COLUMN, loc);
        values.put(ROUTE_LONG_COLUMN, longt);
        values.put(ROUTE_NUMBER_COLUMN, name);


        getWritableDatabase().insert(TABLE_ROUTE, null, values);
    }

    public Cursor getDataRoute() {
        return getReadableDatabase().query(TABLE_ROUTE,
                null, null, null,
                null, null, null);
    }

    public Cursor getDataRouteByNumber(String number) {
        return getReadableDatabase().query(TABLE_ROUTE,
                null,  ROUTE_NUMBER_COLUMN+ " = ? ", new String[]{String.valueOf(number)},
                null, null, ROUTE_JSON_ID_COLUMN+" ASC");
    }
    public void deleteRoute() {
        getWritableDatabase().delete(TABLE_ROUTE, null, null);
    }


    public void readDataRoute(String number){
        Cursor cursor=getDataRouteByNumber(number);
        if (cursor.getCount()!=0){
            while (cursor.moveToNext()){
                Log.e("TAG_GAND",cursor.getString(cursor.getColumnIndex(ROUTE_LAT_COLUMN))+" "+cursor.getString(cursor.getColumnIndex(ROUTE_LONG_COLUMN)));
            }
        }
        Log.e("TAG_GAND","GAND");

    }


    public  void addSearch(double lat, double longt, String name, String loc, int id){
        ContentValues values = new ContentValues();

        values.put(SEARCH_LAT_COLUMN, lat);
        values.put(SEARCH_JSON_ID_COLUMN, id);
        values.put(SEARCH_LOC_COLUMN, loc);
        values.put(SEARCH_LONG_COLUMN, longt);
        values.put(SEARCH_NUMBER_COLUMN, name);


        getWritableDatabase().insert(TABLE_SEARCH, null, values);
    }

    public Cursor getDataSearch() {
        return getReadableDatabase().query(TABLE_SEARCH,
                null, null, null,
                null, null, null);
    }

    public Cursor getDataSearchByNumber(String number) {
        return getReadableDatabase().query(TABLE_SEARCH,
                null,  SEARCH_NUMBER_COLUMN+ " = ? ", new String[]{String.valueOf(number)},
                null, null, SEARCH_JSON_ID_COLUMN+" ASC");
    }
    public void deleteSearch() {
        getWritableDatabase().delete(TABLE_SEARCH, null, null);
    }


    public void readDataSearch(String number){
        Cursor cursor=getDataSearchByNumber(number);
        if (cursor.getCount()!=0){
            while (cursor.moveToNext()){
                Log.e("TAG_GANDOOOOOOD",cursor.getString(cursor.getColumnIndex(SEARCH_LAT_COLUMN))+" "+cursor.getString(cursor.getColumnIndex(SEARCH_LONG_COLUMN)));
            }
        }
        Log.e("TAG_GAND","GAND");

    }


}