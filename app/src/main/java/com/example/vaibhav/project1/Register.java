package com.example.vaibhav.project1;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.ExtractedText;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    Button reg,tc;
    EditText name,pass,user,confirmpass;
    CheckBox chk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_xml);
        reg=(Button)findViewById(R.id.register);
        name=(EditText)findViewById(R.id.name);
        user=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        confirmpass=(EditText)findViewById(R.id.confirmpassword);
        chk=(CheckBox)findViewById(R.id.check);
        tc=(Button)findViewById(R.id.tc);
        chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    reg.setEnabled(true);
                    reg.setTextColor(Color.BLUE);
                }
                else{
                    reg.setEnabled(false);
                    reg.setTextColor(Color.GRAY);
                }
            }
        });
        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b=new AlertDialog.Builder(Register.this);
                b.setMessage("Right now we are not having much of terms and conditions. This was given just to follow the egeneral norms. ")
                        .setCancelable(true)
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog a= b.create();
                a.setTitle("Terms and Conditions");
                a.show();
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHandler db=new DatabaseHandler(getApplicationContext());
                String n=name.getText().toString();
                String u=user.getText().toString();
                String p=pass.getText().toString();
                String cp=confirmpass.getText().toString();
                //Toast.makeText(getApplicationContext(),"Your details are \n"+name.getText().toString()+"\n"+u+"\n"+p+"\n"+cp, Toast.LENGTH_SHORT).show();
                if(n.trim().length()>0 && p.trim().length()>0 && cp.trim().length()>0 && u.trim().length()>0) {
                    if (cp.compareTo(p)==0) {
                        if (db.register(n, u, p) == 1) {
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else if (db.register(n, u, p) == 0) {
                            user.setError("Username already exists");
                            pass.setText("");
                            confirmpass.setText("");
                        }
                        else
                            Toast.makeText(getApplicationContext(), "Error in registration", Toast.LENGTH_SHORT).show();
                    } else {
                        //pass.setError("Passwords don't match");
                        confirmpass.setError("Passwords don't match");
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Enter valid details", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}