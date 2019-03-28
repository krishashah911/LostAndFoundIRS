package com.example.krisha.lostandfound;


import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class FindItem extends AppCompatActivity {


    private DatabaseReference databaseReference;
    private ListView iv;
    private List<ObjectDetails> imgList;
    private ImageListAdapter adapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_item);

        imgList = new ArrayList<>();
        iv = (ListView) findViewById(R.id.ListViewImage) ;

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait loading details...");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference(Return1.FB_DATABASE_PATH);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Fetch image data from firebase database
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //Default constructor required in ObjectDetails.java
                    ObjectDetails img = snapshot.getValue(ObjectDetails.class);
                    imgList.add(img);
                }

                adapter = new ImageListAdapter(FindItem.this, R.layout.image_item, imgList);

                iv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

    }

}