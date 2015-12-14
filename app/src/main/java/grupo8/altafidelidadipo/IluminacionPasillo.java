package grupo8.altafidelidadipo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class IluminacionPasillo extends AppCompatActivity {


    private RecyclerView reciclador;
    private RecyclerView.LayoutManager lmanager;
    private RecyclerView.Adapter adaptador;
    public static Spinner spinner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_zona_dentro);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabRecyclerViewD);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IluminacionPasillo.this, VisionGeneral.class);
                i.putExtra("caller", "IluminacionPasillo");
                i.putExtra("position", spinner.getSelectedItemPosition());
                startActivity(i);
            }
        });

        VariablesGlobales estado = VariablesGlobales.getInstance();

        ArrayList<EntidadLuces> datos = new ArrayList<EntidadLuces>();
        datos.add(new EntidadLuces("Luz","swPasillo1", estado.isSwPasillo1()));

        reciclador = (RecyclerView) findViewById(R.id.recicladorRecyclerViewD);
        lmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reciclador.setLayoutManager(lmanager);
        reciclador.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        adaptador = new LucesAdaptador(datos);
        reciclador.setAdapter(adaptador);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarRecyclerViewD);
        setSupportActionBar(toolbar);

        TextView titulo = (TextView) findViewById(R.id.tituloRecyclerViewD);
        titulo.setText(R.string.pasillo);

        TextView funcion = (TextView) findViewById(R.id.tvFuncionRecyclerViewD);
        funcion.setText(R.string.title_activity_iluminacion);

        spinner = (Spinner) findViewById(R.id.spinnerRecyclerViewD);

        ArrayList<String> casas = new ArrayList<>();
        casas.add("Sierra");
        casas.add("Ciudad");
        casas.add("Playa");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(IluminacionPasillo.this, R.layout.spinner_item, casas);
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
    public void onBackPressed() {
        Intent i = new Intent(IluminacionPasillo.this, ZonasIluminacion.class);
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
            i.putExtra("caller", "IluminacionPasillo");
            i.putExtra("position", spinner.getSelectedItemPosition());
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void backArrow(View v){
        onBackPressed();
    }

}
