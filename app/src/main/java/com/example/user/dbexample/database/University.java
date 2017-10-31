package com.example.user.dbexample.database;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by user on 10/31/2017.
 */

public class University extends SugarRecord
{
    //@Unique: tu khoa dung de nhap khong duoc trung
    public String name;
    public String address;

    public University()
    {
        super();
    }
}
