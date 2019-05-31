package com.anas.pfav31;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private Socket sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lance(View view) {

        EditText txt = (EditText) findViewById(R.id.txt);
        final String str = txt.getText().toString();
        System.out.println("Anaaaas");
        Thread th =new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Saaaa7bi");
                try {
                    Socket sc = new Socket("10.0.2.2", 666);
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(sc.getOutputStream()));
                    // Write output
                    writer.write(str);
                    writer.flush();
                    writer.close();
                    sc.close();

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
    }

}