package com.example.krisha.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db=new DatabaseHelper(this);

        mTextUsername=(EditText)findViewById(R.id.edittext_username);
        mTextPassword=(EditText)findViewById(R.id.edittext_password);
        mButtonLogin=(Button)findViewById(R.id.button_login);
        mTextViewRegister=(TextView)findViewById(R.id.textview_register);
        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent=new Intent(Main2Activity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String user=mTextUsername.getText().toString().trim();
                String pwd=mTextPassword.getText().toString().trim();
                Boolean res=db.checkUser(user,pwd);

                if (user.length()==0 || pwd.length()==0)
                {
                    Toast.makeText(Main2Activity.this,"Enter Username and Password",Toast.LENGTH_SHORT).show();
                }

                else {
                    if (res == true) {
                        Intent Homepage = new Intent(Main2Activity.this, Main3Activity.class);
                        startActivity(Homepage);
                    } else {
                        Toast.makeText(Main2Activity.this, "Login error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
