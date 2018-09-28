package com.example.messanger;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import com.neovisionaries.ws.client.WebSocket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, About.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed()
    {
             openQuitDialog();
    }

    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                MainActivity.this);
        quitDialog.setTitle("Выйти: Вы уверены?");

        quitDialog.setPositiveButton("Да", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет, я продолжу общение", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
            }
        });
        quitDialog.show();
    }
    public void onClick1(View view) {
        Intent intent;
        intent = new Intent(MainActivity.this, Inputlogin.class);
        startActivity(intent);
    }
}
