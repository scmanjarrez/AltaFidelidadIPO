package grupo8.altafidelidadipo;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Principal.this, "En construcción...", Toast.LENGTH_LONG).show();
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void goToRiego (View view){
        Intent i = new Intent(Principal.this, ZonasRiego.class);
        i.putExtra("comeFrom","Riego");
        startActivity(i);
    }

    public void goToClimatizacion (View view){
        Intent i = new Intent(Principal.this, Zonas.class);
        i.putExtra("comeFrom","Climatización");
        startActivity(i);
    }

    public void goToVentanas (View view){
        Intent i = new Intent(Principal.this, Zonas.class);
        i.putExtra("comeFrom","Ventanas");
        startActivity(i);
    }

    public void goToIluminacion (View view){
        Intent i = new Intent(Principal.this, Zonas.class);
        i.putExtra("comeFrom","Iluminación");
        startActivity(i);
    }

}
