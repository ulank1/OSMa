package com.example.ulan.osm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Админ on 06.01.2017.
 */

public class DataHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "mydatabase.db";

    public static final String TABLE_ROUTE ="Route";
    public static final String ROUTE_LOC_COLUMN="loc_route";
    public static final String ROUTE_LAT_COLUMN="lat_route";
    public static final String ROUTE_LONG_COLUMN="long_route";
    public static final String ROUTE_NUMBER_COLUMN="number_route";


    public static final String TABLE_DATE ="Date";
    public static final String DATE_D_COLUMN="date_d";




    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_ROUTE + "(" +
                BaseColumns._ID + " integer primary key autoincrement," +
                ROUTE_LAT_COLUMN + " real," +
                ROUTE_LOC_COLUMN + " text," +
                ROUTE_NUMBER_COLUMN + " text," +
                ROUTE_LONG_COLUMN + " real);");
        db.execSQL("create table " + TABLE_DATE + "(" +

                DATE_D_COLUMN + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public  void addRoute(double lat, double longt, String name, String loc){
        ContentValues values = new ContentValues();

        values.put(ROUTE_LAT_COLUMN, lat);
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
                null, null, null);
    }
    public void deleteRoute() {
        getWritableDatabase().delete(TABLE_ROUTE, null, null);
    }



}