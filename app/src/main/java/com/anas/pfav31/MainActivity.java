package com.anas.pfav31;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private Button connect;
    private EditText txt;
    private EditText ip;
    private EditText port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        connect = findViewById(R.id.connect);
        txt = findViewById(R.id.txt);
        ip = findViewById(R.id.ip);
        port = findViewById(R.id.port);


        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String value = txt.getText().toString();
                System.out.println("Out of it");
                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("Inside");
                            Socket socket = new Socket(ip.getText().toString(), Integer.parseInt(port.getText().toString()));
                            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
                            DOS.writeChars(value);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
                th.start();
            }
        });

    }

}