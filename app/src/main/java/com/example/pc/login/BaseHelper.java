package com.example.pc.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BaseHelper extends SQLiteOpenHelper{


    String tabla = "create table usuarios (id integer primary key, email text, password text)";

    public BaseHelper(Context context) {
        super(context,"Login.db", null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREO TABLA
        db.execSQL(tabla);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //BORRO Y CREO TABLA
        db.execSQL("DROP TABLE PERSONAS");
        db.execSQL(tabla);
    }

    //Insertando en base de datos

    public boolean insertar (String email, String password){

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("email",email);
        valores.put("password",password);
        long ins = db.insert("usuarios",null,valores);
        if(ins==-1)
            return false;
            else
                return true;
    }


    //Chequeo si el email es verdadero
    public boolean ckemail(String email){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from usuarios where email=?", new String[]{email});
       if(cursor.getCount()>0)
           return false;
       else
           return true;
    }

    // chequeo el email y el password

    public boolean ckemailpass (String email, String password){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from usuarios where email=? and password=?", new String[]{email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
