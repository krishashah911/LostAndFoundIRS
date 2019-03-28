package com.example.krisha.lostandfound;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.returnitem:
                Intent intent1 = new Intent(this, ReturnItem.class);
                this.startActivity(intent1);
                return true;
            case R.id.finditem:
                Intent intent2 = new Intent(this, FindItem.class);
                this.startActivity(intent2);
                return true;
             default:
                 return super.onOptionsItemSelected(item);

        }
    }

    public void helpbtn(View view){
        Intent helpintent = new Intent(getBaseContext(), Department.class);
        startActivity(helpintent);
    }
}
