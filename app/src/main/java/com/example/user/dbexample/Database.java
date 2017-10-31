package com.example.user.dbexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.ParcelUuid;

import java.util.ArrayList;

/**
 * Created by USER on 10/31/2017.
 */

public class Database extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "MARKS";

    private static final String SQL_SV_CREATE = "CREATE TABLE IF NOT EXISTS " + Contract.SinhVien.TABLE_NAME + " (" +
            Contract.SinhVien._ID + " INTEGER PRIMARY KEY," +
            Contract.SinhVien.COL_NAME + " TEXT, " +
            Contract.SinhVien.COL_AGE + " INTEGER)";

    public Database(Context context)
    {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                //+ COL_1 + "INTEGER PRIMARY KEY, "
                + COL_2 + "TEXT, "
                + COL_3 + "INTEGER)";
        db.execSQL(CREATE_TABLE);
        db.execSQL(SQL_SV_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Contract.SinhVien.TABLE_NAME);
        onCreate(db);
    }

    public boolean InsertDatabase(String name, String mark)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            //contentValues.put(COL_1, 1);
            contentValues.put(COL_2, name);
            contentValues.put(COL_3, Integer.valueOf(mark));
            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public boolean InsertSinhVien(String name, String age)
    {
        try
        {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Contract.SinhVien.COL_NAME, name);
            contentValues.put(Contract.SinhVien.COL_AGE, Integer.valueOf(age));
            long result = db.insert(Contract.SinhVien.TABLE_NAME, null, contentValues);
            if (result == -1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Cursor> getData(String Query)
    {
        //get writable database
        String[] columns = new String[]{"mesage"};
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2 = new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try
        {
            SQLiteDatabase    sqlDB = this.getWritableDatabase();
            String maxQuery = Query;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[]{"Success"});

            alc.set(1, Cursor2);
            if (null != c && c.getCount() > 0)
            {
                alc.set(0, c);
                c.moveToFirst();

                return alc;
            }
            return alc;
        }
        catch (SQLException sqlEx)
        {
            //Log.e("printing exception", sqlEx.getMessage());
            sqlEx.printStackTrace();
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + sqlEx.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
        catch (Exception ex)
        {
            //Log.e("printing exception", ex.getMessage());
            ex.printStackTrace();
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[]{"" + ex.getMessage()});
            alc.set(1, Cursor2);
            return alc;
        }
    }
}
