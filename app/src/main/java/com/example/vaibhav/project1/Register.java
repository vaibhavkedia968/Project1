package com.example.vaibhav.project1;

/**
 * Created by SIVANGI on 12-01-2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    Button reg;
    EditText name,email,phone,pass;
    @Override
    protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.register_xml);

        reg = (Button)findViewById(R.id.registerMain);
        name = (EditText)findViewById(R.id.Name);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        pass = (EditText)findViewById(R.id.pass);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String e = email.getText().toString();
                String ph = phone.getText().toString();
                String ps = pass.getText().toString();

                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                db.register(n,e,ph,ps);
                Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
