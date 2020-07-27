package com.example.pc.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BaseHelper extends SQLiteOpenHelper{


    String tabla = "create table usuarios (id integer primary key, email text, password text)";
    String tabla2 = "create table notas (id integer primary key, titulo text, contenido text, fecha text)";

    public BaseHelper(Context context) {
        super(context,"Login.db", null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREO TABLA
        db.execSQL(tabla);
        db.execSQL(tabla2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //BORRO Y CREO TABLA
        db.execSQL("DROP TABLE USUARIOS");
        db.execSQL("DROP TABLE NOTAS");
        db.execSQL(tabla);
        db.execSQL(tabla2);
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


    //Insertando NOTA en base de datos

    public boolean insertarNota (String titulo, String contenido){

        //fecha
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("titulo",titulo);
        valores.put("contenido",contenido);
        valores.put("fecha",date);

        long ins = db.insert("notas",null,valores);
        if(ins==-1)
            return false;
        else
            return true;
    }



    //Chequeo si el email existe
    public boolean ckemail(String email){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from usuarios where email=?", new String[]{email});
       if(cursor.getCount()>0)
           return false;
       else
           return true;
    }

    //Chequeo si existe un usuario
    public boolean unicousuario(){
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from usuarios ",null);
        if(cursor.getCount()>0)
            return true;
        else
            return false;
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



//Cargo todo el listado
   public ArrayList<String> Listado(){

        ArrayList<String> datos = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select Id,Titulo, Contenido from notas";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()){
            do{
                String linea = c.getInt(0) +" "+c.getString(1)+" "+c.getString(2);
                datos.add(linea);
            }while(c.moveToNext());
        }
        db.close();
        return datos;
    }

    //Cargo solo el id y el titulo
    public ArrayList<String> segundoListado(){



        ArrayList<String> datos = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select Id,Titulo, Contenido,fecha from notas";
        Cursor c = db.rawQuery(sql,null);
        if(c.moveToFirst()){
            do{

                String linea = c.getInt(0) +" "+c.getString(1)+"\n   Fecha de Edicion: "+c.getString(3);
                datos.add(linea);
            }while(c.moveToNext());
        }
        db.close();
        return datos;
    }






    public void modificar(int Id, String titulo, String contenido){

        SQLiteDatabase db =this.getWritableDatabase();
        //DEBERIA PONER UN TRY CATCH LUEGO

        String sql ="update Notas set Titulo='"+titulo+"',Contenido='"+contenido+"'where Id="+Id;
        db.execSQL(sql);
        db.close();
    }



    //Insertando NOTA en base de datos

    public void eliminar (int Id){

        SQLiteDatabase db =this.getWritableDatabase();
        //DEBERIA PONER UN TRY CATCH LUEGO

        String sql ="delete from Notas where Id="+Id;

        db.execSQL(sql);
        db.close();
    }


}
