package com.anas.pfav31;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    public String sendStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void lance(View view) {
        Switch simpleSwitch = (Switch) findViewById(R.id.switch2);
        boolean isDiag = simpleSwitch.isChecked();
        final String diag;
        if (isDiag) diag = "1";
        else diag = "0";
        EditText txt = findViewById(R.id.txt);
        final String str = txt.getText().toString();
        EditText ip = findViewById(R.id.ip);
        final String ip_a = ip.getText().toString();
        EditText sz = findViewById(R.id.size);
        final String size = String.valueOf(sz.getText());
        EditText end = findViewById(R.id.end);
        final String str_end = end.getText().toString();
        EditText start = findViewById(R.id.start);
        final String str_start = start.getText().toString();
        EditText spd = findViewById(R.id.speed);
        final String speed = start.getText().toString();
        boolean reg;
        reg = str.isEmpty() || ip_a.isEmpty() || size.isEmpty() || str_end.isEmpty() || str_start.isEmpty() || speed.isEmpty();
        if (reg) Toast.makeText(getApplicationContext(), "Champs vide !", Toast.LENGTH_SHORT).show();
        else { Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        Socket sc = new Socket(ip_a, 666);
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(sc.getOutputStream()));
                        sendStr = size + ";" + str + ";" + str_start + ";" + str_end + ";" + diag + ";" + speed;
                        writer.write(sendStr);
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

}