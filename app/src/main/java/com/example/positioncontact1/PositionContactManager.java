package com.example.positioncontact1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class PositionContactManager {
    SQLiteDatabase db;
    public PositionContactManager(Context con){
        PositionContactHelper helper =
                new PositionContactHelper(con,PositionContactHelper.filename,
                        null,PositionContactHelper.version);
        db = helper.getWritableDatabase();

    }
    public long inserer ( String num, String ps, String lon , String lat){
        long a=0;
        ContentValues v = new ContentValues();
        v.put(PositionContactHelper.col_numero,num);
        v.put(PositionContactHelper.col_pseudo,ps);
        v.put(PositionContactHelper.col_longtitude,lon);
        v.put(PositionContactHelper.col_latitude,lat);
        a= db.insert(PositionContactHelper.table_position,null,v);
        return a;
    }
    public  ArrayList<PositionContact> getAllPosition()
    {
        ArrayList<PositionContact> data = new ArrayList<PositionContact>();
        Cursor cr=db.query(PositionContactHelper.table_position,new String[]{
                PositionContactHelper.col_id,
                PositionContactHelper.col_numero,
                PositionContactHelper.col_pseudo,
                PositionContactHelper.col_longtitude,
                PositionContactHelper.col_latitude},null,null,null,null,null );
        cr.moveToFirst();
        while ((!cr.isAfterLast())){
            int id=cr.getInt(0);
            String num=cr.getString(1);
            String ps=cr.getString(2);
            String lon=cr.getString(3);
            String lat=cr.getString(4);

            data.add(new PositionContact(id,num,ps,lon,lat));
            cr.moveToNext();
        }

        return  data;
        /**
         * select id , numero, pseudo..
         * from nom table
         * where numero =123
         * group by
         * having
         * les methode eli yekhdmou al base de donne insere methode supprimer,
         */


    }



    public  long supprimerSelonId(int id)
    {
        long a=0;
        a=db.delete(PositionContactHelper.table_position,PositionContactHelper.col_id+"="+id,
                null);
        return a;
    }
    public  long supprimerSelonnom(String num)
    {
        long a;
        a=db.delete(PositionContactHelper.table_position,PositionContactHelper.col_numero+"="+num,
                null);
        return a;//
    }
    long modifier ( int id,String num)
    {
        int a=-1;
        ContentValues v=new ContentValues();

        v.put(PositionContactHelper.col_numero,num);
        a=db.update(PositionContactHelper.table_position,
                v, PositionContactHelper.col_id+"="+id,null);
        return a;
    }


    }
