package com.example.vaibhav.project1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Welcome extends AppCompatActivity {
    Spinner category;
    Button go;
    boolean cat;
    TextView wu;
    int timelimit=60;
    String[] c={"Select your category","Aptitude","Science","Current Affairs"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_xml);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        category=(Spinner)findViewById(R.id.category);
        go=(Button)findViewById(R.id.cont);
        wu=(TextView)findViewById(R.id.welcomeuser);
        Bundle b=getIntent().getExtras();
        wu.setText("WELCOME "+b.getString("username"));
        MainActivity.currentuser=b.getString("username");
        loadCategory();

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                {
                    cat=true;
                    Toast.makeText(parent.getContext(), "You selected: " + c[position], Toast.LENGTH_LONG).show();
                }
                else
                    cat = false;
                if (cat)
                    go.setEnabled(true);
                else
                    go.setEnabled(false);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cont();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id)
        {
            case R.id.myprofile:
                Toast.makeText(getApplication(),"My Profile",Toast.LENGTH_SHORT).show();
                myProfile();
                break;

            case R.id.signout:
                Toast.makeText(getApplication(),"Sign Out",Toast.LENGTH_SHORT).show();
                signout();
                break;

            case R.id.about:
                Toast.makeText(getApplication(),"About",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Welcome.this,AboutActivity.class);
                startActivity(i);
                break;

            case R.id.contact:
                Toast.makeText(getApplication(),"Contact Us",Toast.LENGTH_SHORT).show();
                contact();
                break;

            case R.id.exit:
                Toast.makeText(getApplication(),"Exit",Toast.LENGTH_SHORT).show();
                exit();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    public void myProfile()
    {
        Intent i=new Intent(Welcome.this,ProfileActivity.class);
        startActivity(i);
    }
    public void signout()
    {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setMessage("")
                .setCancelable(false)
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent j=new Intent(Welcome.this,MainActivity.class);
                        MainActivity.currentuser="";
                        startActivity(j);
                        finish();
                    }
                });
        AlertDialog a= b.create();
        a.setTitle("Are you sure you want to sign out?");
        a.show();
    }
    public void exit()
    {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog a= b.create();
        a.setTitle("Confirm exit");
        a.show();
    }
    public void contact()
    {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setMessage("here we can give our number/email or\nopen an activity providing our details.")
                .setCancelable(true)
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog a= b.create();
        a.setTitle("Contact Us");
        a.show();
    }
    public void onBackPressed()
    {
        exit();
    }
    public void loadCategory()
    {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, c);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        category.setAdapter(dataAdapter);
    }

    public void cont()
    {
        AlertDialog.Builder b=new AlertDialog.Builder(this);
        b.setMessage("Once started, you'll have only "+timelimit+" seconds to complete your quiz.")
                .setCancelable(false)
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Can't connect right now.", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog a= b.create();
        a.setTitle("Start the quiz?");
        a.show();
    }
}


