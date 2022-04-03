package com.cst2335.finalproject.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    protected final static String DATABASE_NAME = "DATABASE";
    protected final static int VERSION_NUM = 1;
    public final static String TABLE_NAME = "COVIDDETAILS";
    public final static String COLUMN_COUNTRY = "COUNTRY";
    public final static String COLUMN_PROVINCE = "PROVINCE";
    public final static String COLUMN_CASES = "CASES" ;
    public final static String COLUMN_DATE = "DATE" ;
    public final static String COLUMN_ID = "_id";

    public DatabaseHandler(Context ctx)
    {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_COUNTRY + " text,"
                + COLUMN_PROVINCE + " text,"
                + COLUMN_CASES + " text,"
                + COLUMN_DATE + " text);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {   //Drop the old table:
        sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);

        //Create the new table:
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)  {   //Drop the old table:
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);

        //Create the new table:
        onCreate(db);
    }
}
