package com.cst2335.finalproject.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cst2335.finalproject.models.CovidData;

import java.util.ArrayList;
import java.util.List;

//THIS CLASS EXTENDS SQLITEOPENHELPER
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =1 ;
    public static final String DATABASE_NAME = "COVIDDATA";
    public static final String TABLE_NAME = "COVID_DATA_TABLE";
    public static final String COLUMN_CITY_CODE = "CITY_CODE";
    public static final String COLUMN_STATUS = "STATUS";
    public static final String COLUMN_COUNTRY = "COUNTRY";
    public static final String COLUMN_LON = "LON";
    public static final String DATABASEID = "DATABASEID";
    public static final String COLUMN_CITY = "CITY";
    public static final String COLUMN_COUNTRY_CODE = "COUNTRY_CODE";
    public static final String COLUMN_PROVINCE = "PROVINCE";
    public static final String COLUMN_LAT = "LAT";
    public static final String COLUMN_CASES = "CASES";
    public static final String COLUMN_DATE = "DATE";
    public final static String COL_ID = "_id";



/*
    protected final static String DATABASE_NAME = "DATABASE";
    protected final static int VERSION_NUM = 1;
    public final static String TABLE_NAME = "COVIDDETAILS";
    public final static String COLUMN_COUNTRY = "COUNTRY";
    public final static String COLUMN_PROVINCE = "PROVINCE";
    public final static String COLUMN_CASES = "CASES" ;
    public final static String COLUMN_DATE = "DATE" ;
    public final static String COLUMN_ID = "_id";
*/
    public DatabaseHandler(Context ctx)
    {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//CREATE THE TABLE WITH THE SPECIFIED COLUMNS
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_COUNTRY + " text,"
                + COLUMN_PROVINCE + " text,"
                + COLUMN_CASES + " INTEGER,"
                + DATABASEID + " text,"
                + COLUMN_CITY_CODE + " text,"
                + COLUMN_STATUS + " text,"
                + COLUMN_LON + " text,"
                + COLUMN_CITY + " text,"
                + COLUMN_COUNTRY_CODE + " text,"
                + COLUMN_DATE+ " text,"
                + COLUMN_LAT + " text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        /*//Drop the old table:
      //  sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);
       // if (i != i1) {
            // Simplest implementation is to drop all old tables and recreate them*/

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);//DROP THE TABLE IF EXIST
            onCreate(sqLiteDatabase);//CREATE SQLITE DATABASE
       // }
    }
        /*//Create the new table:
        /*public boolean saveData(CovidData data) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_PROVINCE, data.getProvince());
            values.put(COLUMN_CASES, data.getCaseNumber());
            values.put(COLUMN_DATE, data.getDate());
            values.put(COLUMN_COUNTRY, data.getCountry());
            values.put(COLUMN_CITY_CODE, data.getCitycode());
            values.put(COLUMN_STATUS, data.getStatus());
            values.put(COLUMN_LON, data.getLon());
            values.put(COLUMN_COUNTRY_CODE, data.getCountryCode());
            values.put(COLUMN_LAT, data.getLat());
            values.put(COLUMN_CITY, data.getCity());
            Log.e("result_data","-->"+values);

            long result = db.insert(TABLE_NAME, null, values);
            Log.d("theS", "saveData: "+result +" "+values);
            db.close();
            return result != -1;
        }*/

    //THIS METHOD RETURN THE LIST OF DATA
    public List<CovidData> getAllData() {
        List<CovidData> dataList = new ArrayList<CovidData>();//CREATE NEW LIST OBJECT
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;//SELECT ALL DATA FROM THE TABLE
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                //CREATE NEW COVID DATA OBJECT
                CovidData data = new CovidData(cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(10),
                        cursor.getString(1),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(9),
                        cursor.getString(11),cursor.getString(8));
         /*data.setDate(cursor.getString(0));
                data.setCityCode(cursor.getString(1));
                data.setStatus(cursor.getString(2));
                data.setCountry(cursor.getString(3));
                data.setLon(cursor.getString(4));
                data.setCity(cursor.getString(5));
                data.setCountryCode(cursor.getString(6));
                data.setProvince(cursor.getString(7));
                data.setLon(cursor.getString(8));
                data.setCases(cursor.getInt(9));*/
                dataList.add(data);//ADD THE DATA IN TO DATA LIST
            } while (cursor.moveToNext());
        }
        return dataList;//RETURN THE LIST OF DATA
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)  {
        //DROP THE OLD TABLE
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);

        //CREATE DB
        onCreate(db);
    }
}