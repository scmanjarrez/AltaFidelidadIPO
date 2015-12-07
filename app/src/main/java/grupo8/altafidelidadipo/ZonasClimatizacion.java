package grupo8.altafidelidadipo;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ZonasClimatizacion extends AppCompatActivity {

    private RecyclerView reciclador;
    private RecyclerView.LayoutManager lmanager;
    private RecyclerView.Adapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas_climatizacion);

        ArrayList<EntidadZonas> datos = new ArrayList<EntidadZonas>();
        datos.add(new EntidadZonas(R.drawable.icono_zona_pasillo, "Pasillo"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_salon, "Salón"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_cocina, "Cocina"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_habitacion, "Habitación"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_bano, "Baño"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_garaje, "Garaje"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_jardin, "Jardín"));


        reciclador = (RecyclerView) findViewById(R.id.recicladorClimatizacion);
        lmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reciclador.setLayoutManager(lmanager);


        adaptador = new ZonasAdaptador(datos);
        reciclador.setAdapter(adaptador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabZonasClimatizacion);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ZonasClimatizacion.this, "En construcción...", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }
}
