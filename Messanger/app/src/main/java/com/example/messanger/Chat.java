package com.example.messanger;
 import android.support.v7.app.AppCompatActivity;
 import android.os.Bundle;
 import android.view.View;
 import android.widget.EditText;
 import android.widget.TextView;
 import com.google.gson.Gson;
 import com.neovisionaries.ws.client.WebSocket;



public class  Chat extends AppCompatActivity
{
    private static WebSocket ws;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        Connection.addListener(MyActivity.contactNumber1, new Connection.ChatListener() {
            @Override
            public void onMessage(MessageDto message)
            {
                TextView textView1 = ( TextView) findViewById(R.id.textView10);
                textView1.setText("Собеседник:"+ message);
            }
        });
    }
   public void sending(View view)
    {
        MessageDto messagedto=new MessageDto();
        Gson gson = new Gson();
        String json = gson.toJson(messagedto);
        Connection.send(json);
        int i = 0;
        while (true)
        {
            i++;
            TextView textView = ( TextView) findViewById(R.id.textView9);
            textView.setText("Я:"+json);
        }
    }
}


