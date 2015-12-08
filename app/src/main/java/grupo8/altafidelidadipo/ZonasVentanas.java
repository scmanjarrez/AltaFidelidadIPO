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
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

import java.util.ArrayList;

public class ZonasVentanas extends AppCompatActivity {

    private RecyclerView reciclador;
    private RecyclerView.LayoutManager lmanager;
    private RecyclerView.Adapter adaptador;
    public static Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas_ventanas);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        ArrayList<EntidadZonas> datos = new ArrayList<EntidadZonas>();
        datos.add(new EntidadZonas(R.drawable.icono_zonas_general, getString(R.string.general)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_pasillo, getString(R.string.pasillo)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_salon, getString(R.string.salon)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_cocina, getString(R.string.cocina)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_habitacion, getString(R.string.habitacion)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_bano, getString(R.string.baño)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_garaje, getString(R.string.garaje)));


        reciclador = (RecyclerView) findViewById(R.id.recicladorVentanas);
        lmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reciclador.setLayoutManager(lmanager);


        adaptador = new ZonasAdaptador(datos);
        reciclador.setAdapter(adaptador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabZonasVentanas);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZonasVentanas.this, "En construcción...", Toast.LENGTH_SHORT).show();
            }
        });

        reciclador.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        switch (position) {
                            case 0:
                                opcGenerales(view);
                                break;
                        }
                    }
                })
        );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarVentanas);
        setSupportActionBar(toolbar);

        spinner = (Spinner) findViewById(R.id.spinnerVentanas);

        ArrayList<String> casas = new ArrayList<>();
        casas.add("Sierra");
        casas.add("Ciudad");
        casas.add("Playa");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ZonasVentanas.this, R.layout.spinner_item, casas);
        spinner.setAdapter(adapter);
        spinner.setSelection(position);

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
        }else if (item.getTitle().equals(getString(R.string.general_ventanas_off))) {
            functionOffVent(item.getItemId());
        }else if (item.getTitle().equals(getString(R.string.general_persianas_on))) {
            functionOnPers(item.getItemId());
        }else if (item.getTitle().equals(getString(R.string.general_persianas_off))) {
            functionOffPers(item.getItemId());
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
}
