package prova.dam.pmdm.ad4a;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends Activity {
    private EditText talumno;
    private EditText tprofesor;
    private Button balumno;
    private Button bprofesor;
    private Switch sborrar;
    private DBadapter dba;
    private EditText res;
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
        res = (EditText) findViewById(R.id.result);

        dba = new DBadapter(this);
        //dba.open();

        if(sborrar.isActivated()){
            balumno.setText("BORRAR ALUMNO");
            bprofesor.setText("BORRAR PROFESOR");
            balumno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dba.borrarAlumno(Integer.parseInt(talumno.getText().toString()));
                }
            });
            bprofesor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dba.borrarProfesor(Integer.parseInt(tprofesor.getText().toString()));
                }
            });
        }

        balumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux = dba.insertarAlumno(talumno.getText().toString());
                res.setText(String.valueOf(aux));
            }
        });
        bprofesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aux = dba.insertarProfesor(tprofesor.getText().toString());
                res.setText(String.valueOf(aux));
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
