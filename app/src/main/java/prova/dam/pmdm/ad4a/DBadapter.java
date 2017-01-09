package prova.dam.pmdm.ad4a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alejandro on 22/12/16.
 */
public class DBadapter {

    //Definiciones y constantes necesarias, adaptadas a estudiante o profesor
    private static final String DATABASE_NAME = "colegio.db";
    private static final String DATABASE_TABLE_ESTUDIANTES = "estudiantes";
    private static final String DATABASE_TABLE_PROFESORES = "profesores";
    private static final int DATABASE_VERSION = 1;

    private static final String NAME = "name";
    private static final String AGE = "age";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String CURSOT = "cursot";
    private static final String DESPACHO = "despacho";

    private static final String DATABASE_CREATE_ESTUDIANTES = "CREATE TABLE " + DATABASE_TABLE_ESTUDIANTES + " " +
            "(_id integer primary key autoincrement, name text, age integer, ciclo text," +
            " curso text);";
    private static final String DATABASE_CREATE_PROFESORES = "CREATE TABLE " + DATABASE_TABLE_PROFESORES + " " +
            "(_id integer primary key autoincrement, name text, age integer, ciclo text," +
            " curso text);";

    private static final String DATABASE_DROP_PROFESORES = "DROP TABLE IF EXISTS " + DATABASE_TABLE_PROFESORES + ";";
    private static final String DATABASE_DROP_ESTUDIANTES = "DROP TABLE IF EXISTS " + DATABASE_TABLE_ESTUDIANTES + ";";

    //Contexto ose la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public DBadapter(Context c) {
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {

        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException e) {
            db = dbHelper.getReadableDatabase();
        }
    }

    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
}
