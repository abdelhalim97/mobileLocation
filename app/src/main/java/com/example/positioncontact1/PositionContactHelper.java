package com.example.positioncontact1;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PositionContactHelper extends SQLiteOpenHelper {
    public static final String filename="mesposition.dp";
    public static int version=2;
    public static final String table_position="PositionContact";
    public static final String col_id="identifiant";
    public static final String col_numero="Numero";
    public static final String col_pseudo="Pseudo";
    public static final String col_longtitude="Langtitude";
    public static final String col_latitude="latitude";

    public PositionContactHelper(@Nullable Context context,
                                 @Nullable String name,
                                 @Nullable SQLiteDatabase.CursorFactory factory,
                                 int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String req="create table "+table_position+" (" +
                col_id+" integer primary key autoincrement," +
                col_numero+" text not null," +
                col_pseudo+" text not null," +
                col_longtitude+" text not null," +
                col_latitude+" text not null)";
        db.execSQL(req);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table "+table_position);
        onCreate(sqLiteDatabase);
    }
}


