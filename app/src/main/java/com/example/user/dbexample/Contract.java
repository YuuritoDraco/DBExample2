package com.example.user.dbexample;

import android.provider.BaseColumns;

/**
 * Created by user on 10/31/2017.
 */

public class Contract
{
    public static class SinhVien implements BaseColumns
    {
        public static final String TABLE_NAME = "SinhVien";
        public static final String COL_NAME = "name";
        public static final String COL_AGE = "age";
    }
}
