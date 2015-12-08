package grupo8.altafidelidadipo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {

    public static Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabPrincipal);
        registerForContextMenu(fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Principal.this, "En construcción...", Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPrincipal);
        setSupportActionBar(toolbar);

        spinner = (Spinner) findViewById(R.id.spinnerPrincipal);

        ArrayList<String> casas = new ArrayList<>();
        casas.add("Sierra");
        casas.add("Ciudad");
        casas.add("Playa");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Principal.this, R.layout.spinner_item, casas);
        spinner.setAdapter(adapter);
        if(getIntent().hasExtra("newPosition")){
            spinner.setSelection(getIntent().getExtras().getInt("newPosition"));
        }

/****************** Como en principio es un spinner de relleno, no tiene implementada ninguna función ******************/
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//            }
//
//        });

        ImageButton riego = (ImageButton) findViewById(R.id.btRiego);
        registerForContextMenu(riego);
        ImageButton climatizacion = (ImageButton) findViewById(R.id.btClimatizacion);
        registerForContextMenu(climatizacion);
        ImageButton ventanas = (ImageButton) findViewById(R.id.btVentanas);
        registerForContextMenu(ventanas);
        ImageButton iluminacion = (ImageButton) findViewById(R.id.btIluminacion);
        registerForContextMenu(iluminacion);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.opciones) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()){
            case R.id.btRiego:
                menu.setHeaderTitle("Opciones Generales");
                menu.add(0, v.getId(), 0, R.string.general_riego_on);
                menu.add(0, v.getId(), 0, R.string.general_riego_off);
                break;
            case R.id.btClimatizacion:
                menu.setHeaderTitle("Opciones Generales");
                menu.add(0, v.getId(), 0, R.string.general_climatizacion_on);
                menu.add(0, v.getId(), 0, R.string.general_climatizacion_off);
                break;
            case R.id.btVentanas:
                menu.setHeaderTitle("Opciones Generales");
                menu.add(0, v.getId(), 0, R.string.general_ventanas_on);
                menu.add(0, v.getId(), 0, R.string.general_ventanas_off);
                menu.add(0, v.getId(), 0, R.string.general_persianas_on);
                menu.add(0, v.getId(), 0, R.string.general_persianas_off);
                break;
            case R.id.btIluminacion:
                menu.setHeaderTitle("Opciones Generales");
                menu.add(0, v.getId(), 0, R.string.general_iluminacion_on);
                menu.add(0, v.getId(), 0, R.string.general_iluminacion_off);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals(getString(R.string.general_riego_on))) {
            Toast.makeText(this, "Se han conectado todos los riegos", Toast.LENGTH_SHORT).show();
        }else if (item.getTitle().equals(getString(R.string.general_riego_off))) {
            Toast.makeText(this, "Se han desconectado todos los riegos", Toast.LENGTH_SHORT).show();
        }else if (item.getTitle().equals(getString(R.string.general_climatizacion_on))) {
            Toast.makeText(this, "Se han activado todos los termostatos", Toast.LENGTH_SHORT).show();
        }else if (item.getTitle().equals(getString(R.string.general_climatizacion_off))) {
            Toast.makeText(this, "Se han desactivado todos los termostatos", Toast.LENGTH_SHORT).show();
        }else if (item.getTitle().equals(getString(R.string.general_ventanas_on))) {
            Toast.makeText(this, "Se han abierto todas las ventanas", Toast.LENGTH_SHORT).show();
        }else if (item.getTitle().equals(getString(R.string.general_ventanas_off))) {
            Toast.makeText(this, "Se han cerrado todas las ventanas", Toast.LENGTH_SHORT).show();
        }else if (item.getTitle().equals(getString(R.string.general_persianas_on))) {
            Toast.makeText(this, "Se han subido todas las persianas", Toast.LENGTH_SHORT).show();
        }else if (item.getTitle().equals(getString(R.string.general_persianas_off))) {
            Toast.makeText(this, "Se han bajado todas las persianas", Toast.LENGTH_SHORT).show();
        }else if (item.getTitle().equals(getString(R.string.general_iluminacion_on))) {
            Toast.makeText(this, "Se han encendido todas las luces", Toast.LENGTH_SHORT).show();
            VariablesGlobales g = VariablesGlobales.getInstance();
            g.encenderLuces();
        }else if (item.getTitle().equals(getString(R.string.general_iluminacion_off))) {
            Toast.makeText(this, "Se han apagado todas las luces", Toast.LENGTH_SHORT).show();
            VariablesGlobales g = VariablesGlobales.getInstance();
            g.apagarLuces();
        }else{
            return false;
        }
        return true;
    }

    public void goToRiego (View view){
        Intent i = new Intent(Principal.this, ZonasRiego.class);
        i.putExtra("position", spinner.getSelectedItemPosition());
        startActivity(i);
    }

    public void goToClimatizacion (View view){
        Intent i = new Intent(Principal.this, ZonasClimatizacion.class);
        i.putExtra("position", spinner.getSelectedItemPosition());
        startActivity(i);
    }

    public void goToVentanas (View view){
        Intent i = new Intent(Principal.this, ZonasVentanas.class);
        i.putExtra("position", spinner.getSelectedItemPosition());
        startActivity(i);
    }

    public void goToIluminacion (View view) {
        Intent i = new Intent(Principal.this, ZonasIluminacion.class);
        i.putExtra("position", spinner.getSelectedItemPosition());
        startActivity(i);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);


    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
    }

}