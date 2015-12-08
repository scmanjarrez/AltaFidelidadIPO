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
import android.widget.Toast;

import java.util.ArrayList;

public class ZonasIluminacion extends AppCompatActivity {

    private RecyclerView reciclador;
    private RecyclerView.LayoutManager lmanager;
    private RecyclerView.Adapter adaptador;
    public static Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas_iluminacion);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        ArrayList<EntidadZonas> datos = new ArrayList<EntidadZonas>();
        datos.add(new EntidadZonas(R.drawable.icono_zonas_general, getString(R.string.general)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_pasillo, getString(R.string.pasillo)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_salon, getString(R.string.salon)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_cocina, getString(R.string.cocina)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_habitacion, getString(R.string.habitacion)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_bano, getString(R.string.bano)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_garaje, getString(R.string.garaje)));
        datos.add(new EntidadZonas(R.drawable.icono_zona_jardin, getString(R.string.jardin)));


        reciclador = (RecyclerView) findViewById(R.id.recicladorIluminacion);
        lmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reciclador.setLayoutManager(lmanager);


        adaptador = new ZonasAdaptador(datos);
        reciclador.setAdapter(adaptador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabZonasIluminacion);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZonasIluminacion.this, "En construcción...", Toast.LENGTH_SHORT).show();
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
                                Intent i = new Intent(ZonasIluminacion.this, IluminacionPasillo.class);
                                i.putExtra("position", spinner.getSelectedItemPosition());
                                startActivity(i);
                                break;
                            case 2:
                                Intent j = new Intent(ZonasIluminacion.this, IluminacionSalon.class);
                                j.putExtra("position", spinner.getSelectedItemPosition());
                                startActivity(j);
                                break;
                            case 3:
                                Intent k = new Intent(ZonasIluminacion.this, IluminacionCocina.class);
                                k.putExtra("position", spinner.getSelectedItemPosition());
                                startActivity(k);
                                break;
                            case 4:
                                Intent l = new Intent(ZonasIluminacion.this, IluminacionHabitacion.class);
                                l.putExtra("position", spinner.getSelectedItemPosition());
                                startActivity(l);
                                break;
                            case 5:
                                Intent m = new Intent(ZonasIluminacion.this, IluminacionBano.class);
                                m.putExtra("position", spinner.getSelectedItemPosition());
                                startActivity(m);
                                break;
                            case 6:
                                Intent n = new Intent(ZonasIluminacion.this, IluminacionGaraje.class);
                                n.putExtra("position", spinner.getSelectedItemPosition());
                                startActivity(n);
                                break;
                            case 7:
                                Intent o = new Intent(ZonasIluminacion.this, IluminacionJardin.class);
                                o.putExtra("position", spinner.getSelectedItemPosition());
                                startActivity(o);
                                break;
                        }
                    }
                })
        );

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarIluminacion);
        setSupportActionBar(toolbar);

        spinner = (Spinner) findViewById(R.id.spinnerIluminacion);

        ArrayList<String> casas = new ArrayList<>();
        casas.add("Sierra");
        casas.add("Ciudad");
        casas.add("Playa");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ZonasIluminacion.this, R.layout.spinner_item, casas);
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
        menu.add(0, v.getId(), 0, R.string.general_iluminacion_on);
        menu.add(0, v.getId(), 0, R.string.general_iluminacion_off);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().equals(getString(R.string.general_iluminacion_on))) {
            functionOn(item.getItemId());
        }else if (item.getTitle().equals(getString(R.string.general_iluminacion_off))) {
            functionOff(item.getItemId());
        }else{
            return false;
        }
        return true;
    }
    public void functionOn(int id) {
        Toast.makeText(this, "Se han encendido todas las luces", Toast.LENGTH_SHORT).show();
        VariablesGlobales g = VariablesGlobales.getInstance();
        g.encenderLuces();
    }
    public void functionOff(int id) {
        Toast.makeText(this, "Se han apagado todas las luces", Toast.LENGTH_SHORT).show();
        VariablesGlobales g = VariablesGlobales.getInstance();
        g.apagarLuces();
    }

    public void opcGenerales(View v) {
        registerForContextMenu(v);
        openContextMenu(v);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ZonasIluminacion.this, Principal.class);
        i.putExtra("newPosition",spinner.getSelectedItemPosition());
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
