package prova.dam.pmdm.ad4a;

import android.content.ContentValues;
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
    private static final String NOTA = "nota";

    private static final String DATABASE_CREATE_ESTUDIANTES = "CREATE TABLE " + DATABASE_TABLE_ESTUDIANTES + " " +
            "(_id integer primary key autoincrement, name text, age integer, ciclo text," +
            " curso text, nota integer);";
    private static final String DATABASE_CREATE_PROFESORES = "CREATE TABLE " + DATABASE_TABLE_PROFESORES + " " +
            "(_id integer primary key autoincrement, name text, age integer, ciclo text," +
            " curso text, despacho text);";

    private static final String DATABASE_DROP_PROFESORES = "DROP TABLE IF EXISTS " + DATABASE_TABLE_PROFESORES + ";";
    private static final String DATABASE_DROP_ESTUDIANTES = "DROP TABLE IF EXISTS " + DATABASE_TABLE_ESTUDIANTES + ";";

    private static final String DROP_DATABASE = "DROP DATABASE " + DATABASE_NAME + ";";

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

    public void borrarAlumno (int i){

        db.execSQL("DELETE FROM "+DATABASE_TABLE_ESTUDIANTES+" WHERE id="+i);

    }

    public void borrarProfesor (int i){

        db.execSQL("DELETE FROM "+DATABASE_TABLE_PROFESORES+" WHERE id="+i);


    }

    public long insertarAlumno(String n) {
        long aux;
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores en este caso todos serán null o vacío menos el nombre
        newValues.put(NAME, n);
        newValues.put(AGE, 0);
        newValues.put(CICLO, 0);
        newValues.put(CURSOT, 0);
        newValues.put(DESPACHO, 0);
        aux = db.insert(DATABASE_TABLE_PROFESORES, null, newValues);
        return aux;
    }

    public long insertarProfesor(String n){
        long aux;
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores en este caso todos serán null o vacío menos el nombre
        newValues.put(NAME,n);
        newValues.put(AGE,0);
        newValues.put(CICLO,0);
        newValues.put(CURSO,0);
        newValues.put(NOTA,0);
        aux = db.insert(DATABASE_TABLE_ESTUDIANTES,null,newValues);
        return aux;
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
