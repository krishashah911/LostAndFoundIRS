package com.example.krisha.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db= new DatabaseHelper(this);

        mTextUsername=(EditText)findViewById(R.id.edittext_username);
        mTextPassword=(EditText)findViewById(R.id.edittext_password);
        mTextCnfPassword=(EditText)findViewById(R.id.edittext_cnf_password);
        mButtonRegister=(Button)findViewById(R.id.button_register);
        mTextViewLogin=(TextView)findViewById(R.id.textview_login);

        mTextViewLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent LoginIntent = new Intent(RegisterActivity.this,Main2Activity.class);
                startActivity(LoginIntent);
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=mTextUsername.getText().toString().trim();
                String pwd=mTextPassword.getText().toString().trim();
                String cnf_pwd=mTextCnfPassword.getText().toString().trim();

                if (user.length()==0)
                {
                    Toast.makeText(RegisterActivity.this,"Please enter appropriate username", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (pwd.length() == 0) {
                        Toast.makeText(RegisterActivity.this, "Please enter appropriate password", Toast.LENGTH_SHORT).show();
                    } else {

                        if (pwd.equals(cnf_pwd) && (pwd.length()) != 0 && (user.length()) != 0) {
                            long val = db.addUser(user, pwd);
                            if (val > 0) {
                                Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                                Intent moveToLogin = new Intent(RegisterActivity.this, Main2Activity.class);
                                startActivity(moveToLogin);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(RegisterActivity.this, " passsword is not matching", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
        });
    }
}

