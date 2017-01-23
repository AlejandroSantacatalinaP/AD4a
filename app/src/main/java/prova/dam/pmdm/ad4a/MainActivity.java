package prova.dam.pmdm.ad4a;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText talumno;
    private EditText tprofesor;
    private Button balumno;
    private Button bprofesor;
    private Switch sborrar;
    private DBadapter dba;
    boolean borrar=true;
    private long aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        talumno = (EditText) findViewById(R.id.talumno);
        tprofesor = (EditText) findViewById(R.id.tprofesor);
        balumno = (Button) findViewById(R.id.balumno);
        bprofesor = (Button) findViewById(R.id.bprofesor);
        sborrar = (Switch) findViewById(R.id.sborrar);

        dba = new DBadapter(this);
        //dba.open();

        sborrar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!sborrar.isChecked()){
                    balumno.setText("AÑADIR ALUMNO");
                    bprofesor.setText("AÑADIR PROFESOR");
                    Log.d("ms","Debajo de set text");
                    balumno.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(talumno.getText().toString()!="") {
                                aux=dba.insertarAlumno(talumno.getText().toString());
                                Log.d("ms","Dentro de insertar");
                                if(aux==-1){
                                    Toast.makeText(MainActivity.this, "No se ha introducido correctamente el alumno "+talumno.getText().toString(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, "Se ha introducido correctamente el alumno "+talumno.getText().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(MainActivity.this, "Introduzca texto en el cuadro", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    bprofesor.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(tprofesor.getText().toString()!="") {
                                aux=dba.insertarProfesor(tprofesor.getText().toString());
                                if(aux==-1){
                                    Toast.makeText(MainActivity.this, "No se ha introducido correctamente el profesor "+tprofesor.getText().toString(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, "Se ha introducido correctamente el profesor "+tprofesor.getText().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(MainActivity.this, "Introduzca texto en el cuadro", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else if (sborrar.isChecked()){
                    balumno.setText("BORRAR ALUMNO");
                    bprofesor.setText("BORRAR PROFESOR");
                    balumno.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(talumno.getText().toString().isEmpty()) {
                                dba.borrarAlumno(Integer.parseInt(talumno.getText().toString()));
                            }else {
                                Toast.makeText(MainActivity.this, "Introduzca texto en el cuadro", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    bprofesor.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(tprofesor.getText().toString().isEmpty()) {
                                dba.borrarProfesor(Integer.parseInt(tprofesor.getText().toString()));
                            }else {
                                Toast.makeText(MainActivity.this, "Introduzca texto en el cuadro", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

            }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
