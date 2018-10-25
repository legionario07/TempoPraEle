package br.com.tempopraele;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class ConfiguracaoActivity extends AppCompatActivity {

    private  EditText inpTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);

        inpTimer = findViewById(R.id.inpTimer);

        SharedPreferences prefs = getSharedPreferences("POKER TEMPO",  MODE_PRIVATE);
        String valorTimer = String.valueOf(prefs.getLong("TIMER",30));

        inpTimer.setText(valorTimer);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Long total = 0l;
        try {
            total = Long.valueOf(inpTimer.getText().toString());

            SharedPreferences prefs = getSharedPreferences("POKER TEMPO",  MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong("TIMER", total);
            editor.commit();

            Log.i("TIMER", "OnDestroy"+total);

        }catch (Exception e){

            Toast.makeText(this, "Erro ao salvar os dados.",Toast.LENGTH_LONG).show();
        } finally {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }


    }

    @Override
    protected void onDestroy() {



        super.onDestroy();

    }
}
