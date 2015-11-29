package grupo8.altafidelidadipo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class Zonas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas);
        Bundle bundle = getIntent().getExtras();
        String nombre=bundle.getString("comeFrom");
        getSupportActionBar().setTitle(nombre);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Zonas.this, "En construcción...", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    public void goToLucesPasillo (View view){
        Intent i = new Intent(Zonas.this, Luces.class);
        i.putExtra("zona","Pasillo");
        startActivity(i);
    }
    public void goToLucesSalon (View view){
        Intent i = new Intent(Zonas.this, Luces.class);
        i.putExtra("zona","Salón");
        startActivity(i);
    }
}
