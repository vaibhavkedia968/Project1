package com.example.vaibhav.project1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button log,reg;
    EditText username,pass;
    static String currentuser="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log=(Button)findViewById(R.id.login);
        reg=(Button)findViewById(R.id.register);
        username=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        //DatabaseHandler db=new DatabaseHandler(getApplicationContext());
        //currentuser=db.activeUser();
        if(currentuser.length()>0){
            Intent i=new Intent(MainActivity.this,Welcome.class);
            Bundle b=new Bundle();
            b.putString("username",currentuser);
            i.putExtras(b);
            startActivity(i);
            finish();
        }
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Register.class);
                startActivity(i);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unm,p;
                unm = username.getText().toString();
                p = pass.getText().toString();
                if(unm.trim().length()>0 && p.trim().length()>0)
                {
                    DatabaseHandler db=new DatabaseHandler(getApplicationContext());
                    if(db.verify(unm,p))
                    {
                        Intent i=new Intent(MainActivity.this,Welcome.class);
                        Bundle b=new Bundle();
                        b.putString("username",unm);
                        i.putExtras(b);
                        currentuser=unm;
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    if(unm.trim().length()==0)
                        username.setError("Enter correct username");
                    else if(p.trim().length()==0)
                        pass.setError("Enter correct password");
                    else
                        Toast.makeText(getApplicationContext(), "Click Register to create a new account", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}




