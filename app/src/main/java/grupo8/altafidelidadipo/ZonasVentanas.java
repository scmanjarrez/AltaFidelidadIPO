package grupo8.altafidelidadipo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ZonasVentanas extends AppCompatActivity {

    private RecyclerView reciclador;
    private RecyclerView.LayoutManager lmanager;
    private RecyclerView.Adapter adaptador;
    public static Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_zona_fuera);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        ArrayList<EntidadZonas> datos = new ArrayList<EntidadZonas>();
        datos.add(new EntidadZonas(R.drawable.icono_zonas_general, getString(R.string.general)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_salon, getString(R.string.salon)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_cocina, getString(R.string.cocina)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_habitacion, getString(R.string.habitacion)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_bano, getString(R.string.bano)));


        reciclador = (RecyclerView) findViewById(R.id.recicladorRecyclerViewF);
        lmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reciclador.setLayoutManager(lmanager);


        adaptador = new ZonasAdaptador(datos);
        reciclador.setAdapter(adaptador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabRecyclerViewF);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ZonasVentanas.this, VisionGeneral.class);
                i.putExtra("caller", "ZonasVentanas");
                i.putExtra("position", spinner.getSelectedItemPosition());
                startActivity(i);
            }
        });

        reciclador.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        switch (position) {
                            case 0:
                                opcGenerales(view);
                                break;
                            case 1:
                                Intent i = new Intent(ZonasVentanas.this, VentanasSalon.class);
                                i.putExtra("position", spinner.getSelectedItemPosition());
                                startActivity(i);
                                break;
                            case 2:
                                Intent j = new Intent(ZonasVentanas.this, VentanasCocina.class);
                                j.putExtra("position", spinner.getSelectedItemPosition());
                                startActivity(j);
                                break;
                            case 3:
                                Intent k = new Intent(ZonasVentanas.this, VentanasHabitacion.class);
                                k.putExtra("position", spinner.getSelectedItemPosition());
                                startActivity(k);
                                break;
                            case 4:
                                Intent l = new Intent(ZonasVentanas.this, VentanasBano.class);
                                l.putExtra("position", spinner.getSelectedItemPosition());
                                startActivity(l);
                                break;
                        }
                    }
                })
        );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarRecyclerViewF);
        setSupportActionBar(toolbar);

        TextView titulo = (TextView) findViewById(R.id.tituloRecyclerViewF);
        titulo.setText(R.string.title_activity_ventanas);

        spinner = (Spinner) findViewById(R.id.spinnerRecyclerViewF);

        ArrayList<String> casas = new ArrayList<>();
        casas.add("Sierra");
        casas.add("Ciudad");
        casas.add("Playa");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ZonasVentanas.this, R.layout.spinner_item, casas);
        spinner.setAdapter(adapter);
        spinner.setSelection(position);
        if(getIntent().hasExtra("newPosition")){
            spinner.setSelection(getIntent().getExtras().getInt("newPosition"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Opciones Generales");
        menu.add(0, v.getId(), 0, R.string.general_ventanas_on);
        menu.add(0, v.getId(), 0, R.string.general_ventanas_off);
        menu.add(0, v.getId(), 0, R.string.general_persianas_on);
        menu.add(0, v.getId(), 0, R.string.general_persianas_off);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals(getString(R.string.general_ventanas_on))) {
            functionOnVent(item.getItemId());
            VariablesGlobales g = VariablesGlobales.getInstance();
            g.abrirVentanas();
        }else if (item.getTitle().equals(getString(R.string.general_ventanas_off))) {
            functionOffVent(item.getItemId());
            VariablesGlobales g = VariablesGlobales.getInstance();
            g.cerrarVentanas();
        }else if (item.getTitle().equals(getString(R.string.general_persianas_on))) {
            functionOnPers(item.getItemId());
            VariablesGlobales g = VariablesGlobales.getInstance();
            g.subirPersianas();
        }else if (item.getTitle().equals(getString(R.string.general_persianas_off))) {
            functionOffPers(item.getItemId());
            VariablesGlobales g = VariablesGlobales.getInstance();
            g.bajarPersianas();
        }else{
            return false;
        }
        return true;
    }
    public void functionOnVent(int id) {
        Toast.makeText(this, "Se han abierto todas las ventanas", Toast.LENGTH_SHORT).show();
    }
    public void functionOffVent(int id) {
        Toast.makeText(this, "Se han cerrado todas las ventanas", Toast.LENGTH_SHORT).show();
    }
    public void functionOnPers(int id) {
        Toast.makeText(this, "Se han subido todas las persianas", Toast.LENGTH_SHORT).show();
    }
    public void functionOffPers(int id) {
        Toast.makeText(this, "Se han bajado todas las persianas", Toast.LENGTH_SHORT).show();
    }

    public void opcGenerales(View v) {
        registerForContextMenu(v);
        openContextMenu(v);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ZonasVentanas.this, Principal.class);
        i.putExtra("newPosition",spinner.getSelectedItemPosition());
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.opciones) {
            Intent i = new Intent(this, Opciones.class);
            i.putExtra("caller", "ZonasVentanas");
            i.putExtra("position", spinner.getSelectedItemPosition());
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void backArrow(View v){
        onBackPressed();
    }
}
