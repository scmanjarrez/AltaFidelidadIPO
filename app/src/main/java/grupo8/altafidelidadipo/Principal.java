package grupo8.altafidelidadipo;

//import android.content.Intent;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//public class Principal extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_principal);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(Principal.this, "En construcción...", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_principal, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//    public void goToRiego (View view){
//        Intent i = new Intent(Principal.this, ZonasRiego.class);
//        startActivity(i);
//    }
//
//    public void goToClimatizacion (View view){
//        Intent i = new Intent(Principal.this, ZonasClimatizacion.class);
//        startActivity(i);
//    }
//
//    public void goToVentanas (View view){
//        Intent i = new Intent(Principal.this, ZonasVentanas.class);
//        startActivity(i);
//    }
//
//    public void goToIluminacion (View view){
//        Intent i = new Intent(Principal.this, ZonasIluminacion.class);
//        startActivity(i);
//    }
//
//}
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.Menu;
        import android.view.MenuItem;

        import java.util.ArrayList;


public class Principal extends AppCompatActivity {
    private RecyclerView reciclador;
    private RecyclerView.LayoutManager lmanager;
    private RecyclerView.Adapter adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<EntidadZonas> datos = new ArrayList<EntidadZonas>();
        datos.add(new EntidadZonas(R.drawable.icono_zona_pasillo, "Pasillo"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_salon, "Salón"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_cocina, "Cocina"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_habitacion, "Habitación"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_bano, "Baño"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_garaje, "Garaje"));
        datos.add(new EntidadZonas(R.drawable.icono_zona_jardin, "Jardín"));


        reciclador = (RecyclerView) findViewById(R.id.reciclador);
        lmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reciclador.setLayoutManager(lmanager);

        adaptador = new ZonasAdaptador(datos);
        reciclador.setAdapter(adaptador);

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
