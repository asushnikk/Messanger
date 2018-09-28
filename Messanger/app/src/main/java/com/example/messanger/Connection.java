package com.example.messanger;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketExtension;
import com.neovisionaries.ws.client.WebSocketFactory;

public class Connection extends AppCompatActivity
{
    public static  String url ="ws://192.168.0.173:49300/ws?Id=";
    private static final int TIMEOUT = 5000;
    private static WebSocket ws;

    public static WebSocket connect(String login) throws IOException, WebSocketException
    {
        ws = new WebSocketFactory()
                .setConnectionTimeout(TIMEOUT)
                .createSocket(url + login)
                .addListener(new WebSocketAdapter()
                {   // A text message arrived from the server.
                    public void onTextMessage(WebSocket websocket, String message)
                    {
                        Gson gson = new Gson();
                        MessageDto msgDto = gson.fromJson(message, MessageDto.class);

                        ChatListener listener = listeners.get(msgDto.getFrom());
                        if(listener != null){
                            listener.onMessage(msgDto);
                        }
                    }
                })
                .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                .connect();
        return null;
    }

    public static void addListener(String login, ChatListener listener){
        ChatListener existsListener = listeners.get(login);
        if(existsListener != null){
            listeners.remove(login);
        }

        listeners.put(login, listener);
    }


    private static HashMap<String, ChatListener> listeners = new HashMap<String, ChatListener>();

    public interface ChatListener
    {
        void onMessage(MessageDto message);
    }


    public static void send(String message)
    {
         ws.sendText(message,true);
    }
}
