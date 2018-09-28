package com.example.messanger;
import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;

public class Inputlogin extends Activity {

    public static String login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputlogin);
    }
    public void logIN(View view)
    {
        Intent intent;
        intent = new Intent(Inputlogin.this, MyActivity.class);
        startActivity(intent);
        login = ((EditText)findViewById(R.id.edittextlogin)).getText().toString();
        Log.d("Inputlogin", login);
        WebSocket ws;
        try
        {
            ws = Connection.connect(login);
        }
        catch (Exception e)
        {
            Log.d("Inputlogin", "IO " + e.getMessage());
            e.printStackTrace();
        }
    }
}