package com.example.krisha.lostandfound;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Return1 extends AppCompatActivity {

   private StorageReference mStorageRef;
   private DatabaseReference databaseReference;
   private ImageView imageView;

   public static final int CAMERA_REQUEST = 1;

    EditText Nametxt,Contacttxt,Objecttxt;
    private Uri imgUri;

    FirebaseDatabase firebaseDatabase;

    public static final String FB_STORAGE_PATH = "image/";
    public static final String FB_DATABASE_PATH = "image/";
    public static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return1);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        imageView = findViewById(R.id.imageViewC);
        Nametxt=(EditText) findViewById(R.id.editText3);
        Contacttxt=(EditText) findViewById(R.id.editText6);
        Objecttxt=(EditText) findViewById(R.id.editText7);


    }

    public void camera(View view)
    {
        Intent cameraintent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraintent,CAMERA_REQUEST);
        //File file = getFile();
        //cameraintent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(file));
    }



   /* private void addArrayList(){
        String name = Nametxt.getText().toString().trim();
        String contact = Contacttxt.getText().toString().trim();
        String object = Objecttxt.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(contact)){
            Toast.makeText(this, "Please enter contact number", Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(object)){
            Toast.makeText(this, "Please enter the name of the lost item", Toast.LENGTH_LONG).show();
        }else{
            String id= databaseReference.push().getKey();
            ObjectDetails objectDetails= new ObjectDetails(name,contact,object);
            databaseReference.child(id).child("name").setValue(name.toString());
            databaseReference.child(id).child("L&F department").setValue(contact.toString());
            databaseReference.child(id).child("object").setValue(object.toString());
            Toast.makeText(this, "Details Added", Toast.LENGTH_LONG).show();
            Cleartxt();
        }
    }
    private void Cleartxt(){
        Nametxt.setText("");
        Contacttxt.setText("");
        Objecttxt.setText("");
    } */

   public void btnBrowse_Click(View v){
       Intent intent = new Intent();
       intent.setType("image/*");
       intent.setAction(Intent.ACTION_GET_CONTENT);
       startActivityForResult(Intent.createChooser(intent, "Select image"), REQUEST_CODE);
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA_REQUEST)
        {
            Bitmap bitmap=(Bitmap)data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }

        else if(requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null){
            imgUri = data.getData();

            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), imgUri);
                imageView.setImageBitmap(bm);
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getImageExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @SuppressWarnings("VisibleForTests")
    public void btnUpload_Click(View v){
       if (imgUri!=null && (Nametxt.length())!=0 && (Contacttxt.length())!=0 && (Objecttxt.length())!=0){
           final ProgressDialog dialog = new ProgressDialog(this);
           dialog.setTitle("Uploading details");
           dialog.show();

           StorageReference ref = mStorageRef.child(FB_STORAGE_PATH + System.currentTimeMillis() +"."+getImageExt(imgUri));

           ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
               @Override
               public void onSuccess(Uri uri) {
                   String uri1 = imgUri.toString();
                   Log.d("TAG", uri1);
               }
           });


           //Add file to reference
           ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                   dialog.dismiss();

                   Toast.makeText(getApplicationContext(), "Details uploaded", Toast.LENGTH_SHORT).show();
                   ObjectDetails objectDetails= new ObjectDetails(Nametxt.getText().toString(), Contacttxt.getText().toString(), Objecttxt.getText().toString(), taskSnapshot.getUploadSessionUri().toString());


                   String id = databaseReference.push().getKey();
                   databaseReference.child(id).setValue(objectDetails);

                   /* String name = Nametxt.getText().toString().trim();
                   String contact = Contacttxt.getText().toString().trim();
                   String object = Objecttxt.getText().toString().trim();

                   if(TextUtils.isEmpty(name)){
                       Toast.makeText(this, "Please enter your name", Toast.LENGTH_LONG).show();
                   }else if(TextUtils.isEmpty(contact)){
                       Toast.makeText(this, "Please enter contact number", Toast.LENGTH_LONG).show();
                   }else if(TextUtils.isEmpty(object)){
                       Toast.makeText(this, "Please enter the name of the lost item", Toast.LENGTH_LONG).show();
                   }else{
                       databaseReference.child(id).child("name").setValue(Nametxt.toString());
                       databaseReference.child(id).child("contact").setValue(Contacttxt.toString());
                       databaseReference.child(id).child("object").setValue(Objecttxt.toString());
                       Toast.makeText(this, "Details Added", Toast.LENGTH_LONG).show();
                    } */


               }
           })
                   .addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {

                           dialog.dismiss();

                           Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();


                       }
                   })
                   .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                       @Override
                       public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                           double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                           dialog.setMessage("Uploaded " + (int)progress+ "%");

                       }
                   });
       }
       else {
           Toast.makeText(getApplicationContext(), "Please select image and fill in details", Toast.LENGTH_SHORT).show();
       }
    }
}









