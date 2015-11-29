package grupo8.altafidelidadipo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Zonas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas);
        Bundle bundle = getIntent().getExtras();
        String nombre=bundle.getString("comeFrom");
        getSupportActionBar().setTitle(nombre);
    }
}
