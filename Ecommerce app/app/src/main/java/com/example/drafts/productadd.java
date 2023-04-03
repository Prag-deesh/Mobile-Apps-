package com.example.drafts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class productadd extends AppCompatActivity {

    ImageView inputprodimg;
    EditText inputprodname,inputproddesc,inputprodtype,inputprodprice;
    RadioButton recomprod,popprod;
    Button addprod;
    String pname,pdesc,ptype,ispop,isrecom;
    int pprice;
    public Uri imgUri;
    String savecurrdate,savecurrtime;

    private String productRandomKey,dowloadImageURL;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productadd);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("product images");
        fstore = FirebaseFirestore.getInstance();

        inputprodimg = findViewById(R.id.prod_img);
        inputprodname = findViewById(R.id.prod_name);
        inputproddesc = findViewById(R.id.prod_desc);
        inputprodtype = findViewById(R.id.prod_type);
        inputprodprice = findViewById(R.id.prod_price);
        recomprod = findViewById(R.id.isrecomproduct);
        popprod = findViewById(R.id.ispopproduct);
        addprod  = findViewById(R.id.addprod);


        inputprodimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectimage();
            }
        });

        addprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateproduct();
            }
        });


    }

    private void validateproduct() {

        pname = inputprodname.getText().toString();
        pdesc = inputproddesc.getText().toString();
        ptype = inputprodtype.getText().toString();
        pprice = Integer.parseInt(inputprodprice.getText().toString());

        if(imgUri == null)
        {
            Toast.makeText(this, "Image is empty", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pname)) {
            Toast.makeText(this, "Name is empty ,Please write the name of the product", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pdesc)) {
            Toast.makeText(this, "Description is empty ,Please write the description of the product", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(ptype)) {
            Toast.makeText(this, "Type is empty ,Please write the type of the product", Toast.LENGTH_SHORT).show();
        }
        else if(pprice == 0 ) {
            Toast.makeText(this, "Price is empty ,Please write the price of the product", Toast.LENGTH_SHORT).show();
        }
        else {
            StoreprodInfo();
        }



    }

    private void StoreprodInfo() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentdate = new SimpleDateFormat("MMM dd, yyyy");
        savecurrdate = currentdate.format(calendar.getTime());

        SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss a");
        savecurrtime = currenttime.format(calendar.getTime());

        productRandomKey = savecurrdate + savecurrtime;

        StorageReference filepath = storageReference.child(imgUri.getLastPathSegment() + productRandomKey + ".jpg");

        final UploadTask uploadTask = filepath.putFile(imgUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String msg = e.toString();
                Toast.makeText(productadd.this, "Error : " + msg, Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(productadd.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful()) {
                            throw task.getException();
                        }

                        dowloadImageURL = filepath.getDownloadUrl().toString();
                        return filepath.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()) {
                            dowloadImageURL = task.getResult().toString();
                            Toast.makeText(productadd.this, "product image url saved sucessfully..", Toast.LENGTH_SHORT).show();

                        saveProductInfotodatabase();
                        }
                    }
                });

            }
        });



    }

    private void saveProductInfotodatabase() {

        final HashMap<String,Object> prod = new HashMap<>();

        prod.put("name",pname);
        prod.put("description",pdesc);
        prod.put("img_url",dowloadImageURL);
        prod.put("type",ptype);
        prod.put("price",pprice);

        fstore.collection("Products").add(prod).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(productadd.this, "Product added to database ", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


    }

    private void selectimage() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,11);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==11 && resultCode==RESULT_OK && data!=null && data.getData()!=null) {
            imgUri = data.getData();
            inputprodimg.setImageURI(imgUri);

        }
    }
}