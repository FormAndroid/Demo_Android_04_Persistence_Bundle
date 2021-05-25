package be.bxl.formation.demo_04_persistence_bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final String SAVE_MSG = "Je sauvegarde le message titre !";

    TextView tvTitle;
    EditText etMsg;
    Button btnUpdate;

    String demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tv_main_msg);
        etMsg = findViewById(R.id.et_main_msg);
        btnUpdate = findViewById(R.id.btn_main_update);

        btnUpdate.setOnClickListener(v -> updateTitle());


        Log.d("DEMO BUNDLE", "On Create -> " + demo);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("DEMO BUNDLE", "On Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("DEMO BUNDLE", "On Destroy");
    }

    private void updateTitle() {
        // Recuperation du nouveau titre
        String msg = etMsg.getText().toString();

        // Mise a jours du titre
        tvTitle.setText(msg);

        // Effacer l'EditText
        etMsg.setText("");

        demo = msg; // -> Cette donner sera perdu lors par exemple d'une rotation d'ecran
        Log.d("DEMO BUNDLE", "Update -> " + msg);
    }


    // Appeler automatique par le systeme quand il a besoin de sauvegarder l'état de l'activité
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("DEMO BUNDLE", "On Save !");

        // Recuperation de la valeur
        String msg = tvTitle.getText().toString();

        // Sauvegarde de valeur dans le bundle
        outState.putString(SAVE_MSG, msg);


        // Le bundle peut être récuperer :
        //  - onCreate
        //  - onRestoreInstanceState
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d("DEMO BUNDLE", "On Restore");

        // Recuperation des données sauvées
        String msg = savedInstanceState.getString(SAVE_MSG);

        // Mise a jours du visuel de l'activité
        tvTitle.setText(msg);
    }
}