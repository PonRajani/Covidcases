package com.cst2335.finalproject.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cst2335.finalproject.models.CovidData;

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DATABASE";
    private static final String TABLE_NAME = "covid_19_data";
    private static final String COLUMN_CITY_CODE = "CITY_CODE";
    private static final String COLUMN_STATUS = "STATUS";
    private static final String COLUMN_COUNTRY = "COUNTRY";
    private static final String COLUMN_LON = "LON";
    private static final String COLUMN_CITY = "CITY";
    private static final String COLUMN_COUNTRY_CODE = "COUNTRY_CODE";
    private static final String COLUMN_PROVINCE = "PROVINCE";
    private static final String COLUMN_LAT = "LAT";
    private static final String COLUMN_CASES = "CASES";
    private static final String COLUMN_ID = "DATE";
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

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_COUNTRY + " text,"
                + COLUMN_PROVINCE + " text,"
                + COLUMN_CASES + " INTEGER,"
                + COLUMN_CITY_CODE + " text,"
                + COLUMN_STATUS + " text,"
                + COLUMN_LON + " text,"
                + COLUMN_CITY + " text,"
                + COLUMN_COUNTRY_CODE + "text,"
                + COLUMN_LAT + "text);");
        sqLiteDatabase.execSQL(TABLE_NAME);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {   //Drop the old table:
      //  sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);
        if (i != i1) {
            // Simplest implementation is to drop all old tables and recreate them
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
        //Create the new table:
        public boolean saveData(CovidData data) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_CITY_CODE, data.getCitycode());
            values.put(COLUMN_STATUS, data.getStatus());
            values.put(COLUMN_COUNTRY, data.getCountry());
            values.put(COLUMN_LON, data.getLon());
            values.put(COLUMN_CITY, data.getCity());
            values.put(COLUMN_COUNTRY_CODE, data.getCountryCode());
            values.put(COLUMN_PROVINCE, data.getProvince());
            values.put(COLUMN_LAT, data.getLat());
            values.put(COLUMN_CASES, data.getCaseNumber());
            values.put(COLUMN_ID, data.getDate());
            long result = db.insert(TABLE_NAME, null, values);
            Log.d("theS", "saveData: "+result +" "+values);
            db.close();
            return result != -1;
        }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)  {   //Drop the old table:
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);

        //Create the new table:
        onCreate(db);
    }
}
