package br.com.tempopraele;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtTimer;
    private ProgressBar progressBar;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Tempooooo...");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSetting);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ConfiguracaoActivity.class);
                startActivity(i);
            }
        });

        txtTimer = findViewById(R.id.txtTimer);
        progressBar = findViewById(R.id.progressBar);
        start();

    }

    private void start(){

        SharedPreferences prefs = getSharedPreferences("POKER TEMPO",  MODE_PRIVATE);
        long total = prefs.getLong("TIMER", 30);
        Log.i("TIMER", "start"+total);

        progressBar.setMax((int)total);
        countDownTimer = new CountDownTimer(total * 1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("TIME",String.valueOf(millisUntilFinished));
                txtTimer.setText(""+millisUntilFinished/1000);
                progressBar.setProgress((int) millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                txtTimer.setText("0");
                if(countDownTimer !=null){
                    countDownTimer.cancel();
                    countDownTimer=null;
                }

                finish();
            }



        };

        countDownTimer.start();

    }

}
