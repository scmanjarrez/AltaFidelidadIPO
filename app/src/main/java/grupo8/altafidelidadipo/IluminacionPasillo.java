package grupo8.altafidelidadipo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
        setContentView(R.layout.activity_recycler_view);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabRecyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(IluminacionPasillo.this, "En construcción...", Toast.LENGTH_LONG).show();
            }
        });

        VariablesGlobales estado = VariablesGlobales.getInstance();

        ArrayList<EntidadLuces> datos = new ArrayList<EntidadLuces>();
        datos.add(new EntidadLuces("Luz 1","swPasillo1", estado.isSwPasillo1()));

        reciclador = (RecyclerView) findViewById(R.id.recicladorRecyclerView);
        lmanager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        reciclador.setLayoutManager(lmanager);
        reciclador.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        adaptador = new LucesAdaptador(datos);
        reciclador.setAdapter(adaptador);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarRecyclerView);
        setSupportActionBar(toolbar);

        TextView titulo = (TextView) findViewById(R.id.tituloRecyclerView);
        titulo.setText(R.string.pasillo);

        spinner = (Spinner) findViewById(R.id.spinnerRecyclerView);

        ArrayList<String> casas = new ArrayList<>();
        casas.add("Sierra");
        casas.add("Ciudad");
        casas.add("Playa");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(IluminacionPasillo.this, R.layout.spinner_item, casas);
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
    public void onBackPressed() {
        Intent i = new Intent(IluminacionPasillo.this, ZonasIluminacion.class);
        i.putExtra("newPosition",spinner.getSelectedItemPosition());
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

}
