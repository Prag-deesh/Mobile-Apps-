package com.example.drafts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.drafts.models.MyOrdersModel;
import com.example.drafts.models.NavNewpModel;
import com.example.drafts.models.PopularModel;
import com.example.drafts.models.RecommendedModel;
import com.example.drafts.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    TextView quantity;
    int totQuantity = 1;
    int totPrice = 0;

    FirebaseFirestore fstore;
    FirebaseAuth fAuth;

    ImageView det_img;
    TextView det_name,det_desc,det_price,det_type;
    ImageView add_item,rem_item;
    Button add_to_cart;

    ViewAllModel viewAllModel = null;
    NavNewpModel navNewpModel = null;
    PopularModel popularModel = null;
    RecommendedModel recommendedModel = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        final Object obj = getIntent().getSerializableExtra("detail");
        if(obj instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) obj;
        }
        else if(obj instanceof NavNewpModel) {
            navNewpModel = (NavNewpModel) obj;
        }
        else if(obj instanceof PopularModel) {
            popularModel = (PopularModel) obj;
        }
        else if(obj instanceof RecommendedModel) {
            recommendedModel = (RecommendedModel) obj;
        }




        quantity = findViewById(R.id.quantity);

        det_img = findViewById(R.id.detpro_img);
        det_name = findViewById(R.id.detpro_name);
        det_price = findViewById(R.id.detpro_price);
        det_type = findViewById(R.id.detpro_type);
        det_desc = findViewById(R.id.detpro_desc);
        add_item = findViewById(R.id.add_item);
        rem_item = findViewById(R.id.minus_item);
        add_to_cart = findViewById(R.id.addtocart);

        if(viewAllModel != null) {   // viewall
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(det_img);
            det_name.setText(viewAllModel.getName());
            det_desc.setText("About : \n"+viewAllModel.getDescription());
            det_type.setText(viewAllModel.getType());
            det_price.setText(" ₹ "+String.valueOf(viewAllModel.getPrice()));

            totPrice = viewAllModel.getPrice() * totQuantity;

            add_item.setOnClickListener(new View.OnClickListener() { //viewalladditem
                @Override
                public void onClick(View v) {

                    if(totQuantity < 10) {
                        totQuantity++;
                        quantity.setText(String.valueOf(totQuantity));
                        totPrice = viewAllModel.getPrice() * totQuantity;
                    }

                }
            });
            rem_item.setOnClickListener(new View.OnClickListener() { //viewallremitem
                @Override
                public void onClick(View v) {

                    if(totQuantity > 0) {
                        totQuantity--;
                        quantity.setText(String.valueOf(totQuantity));
                        totPrice = viewAllModel.getPrice() * totQuantity;
                    }

                }
            });

            add_to_cart.setOnClickListener(new View.OnClickListener() { //viewalladdtocart
                @Override
                public void onClick(View v) {
                    viewalladdedtocart();
                }
            });
        }

         if(navNewpModel != null) {   //newpro
             Glide.with(getApplicationContext()).load(navNewpModel.getImg_url()).into(det_img);
             det_name.setText(navNewpModel.getName());
             det_desc.setText("About : \n"+navNewpModel.getDescription());
             det_type.setText(navNewpModel.getType());
             det_price.setText(" ₹ "+String.valueOf(navNewpModel.getPrice()));

             totPrice = navNewpModel.getPrice() * totQuantity;

             add_item.setOnClickListener(new View.OnClickListener() { //newproadditem
                 @Override
                 public void onClick(View v) {

                     if(totQuantity < 10) {
                         totQuantity++;
                         quantity.setText(String.valueOf(totQuantity));
                         totPrice = navNewpModel.getPrice() * totQuantity;
                     }

                 }
             });
             rem_item.setOnClickListener(new View.OnClickListener() { //newproremitem
                 @Override
                 public void onClick(View v) {

                     if(totQuantity > 0) {
                         totQuantity--;
                         quantity.setText(String.valueOf(totQuantity));
                         totPrice = navNewpModel.getPrice() * totQuantity;
                     }

                 }
             });

             add_to_cart.setOnClickListener(new View.OnClickListener() { //newproaddtocart
                 @Override
                 public void onClick(View v) {
                     newpaddedtocart();
                 }
             });
        }

        if(popularModel != null) {  //popular
            Glide.with(getApplicationContext()).load(popularModel.getImg_url()).into(det_img);
            det_name.setText(popularModel.getName());
            det_desc.setText("About : \n"+popularModel.getDescription());
            det_type.setText(popularModel.getType());
            det_price.setText(" ₹ "+String.valueOf(popularModel.getPrice()));

            totPrice = popularModel.getPrice() * totQuantity;

            add_item.setOnClickListener(new View.OnClickListener() { //popadditem
                @Override
                public void onClick(View v) {

                    if(totQuantity < 10) {
                        totQuantity++;
                        quantity.setText(String.valueOf(totQuantity));
                        totPrice = popularModel.getPrice() * totQuantity;
                    }

                }
            });
            rem_item.setOnClickListener(new View.OnClickListener() { //popremitem
                @Override
                public void onClick(View v) {

                    if(totQuantity > 0) {
                        totQuantity--;
                        quantity.setText(String.valueOf(totQuantity));
                        totPrice = popularModel.getPrice() * totQuantity;
                    }

                }
            });

            add_to_cart.setOnClickListener(new View.OnClickListener() { //popaddtocart
                @Override
                public void onClick(View v) {
                    popaddedtocart();
                }
            });
        }

        if(recommendedModel != null) {
            Glide.with(getApplicationContext()).load(recommendedModel.getImg_url()).into(det_img);
            det_name.setText(recommendedModel.getName());
            det_desc.setText("About : \n"+recommendedModel.getDescription());
            det_type.setText(recommendedModel.getType());
            det_price.setText(" ₹ "+String.valueOf(recommendedModel.getPrice()));

            totPrice = recommendedModel.getPrice() * totQuantity;

            add_item.setOnClickListener(new View.OnClickListener() { //recomadditem
                @Override
                public void onClick(View v) {

                    if(totQuantity < 10) {
                        totQuantity++;
                        quantity.setText(String.valueOf(totQuantity));
                        totPrice = recommendedModel.getPrice() * totQuantity;
                    }

                }
            });
            rem_item.setOnClickListener(new View.OnClickListener() { //recomremitem
                @Override
                public void onClick(View v) {

                    if(totQuantity > 0) {
                        totQuantity--;
                        quantity.setText(String.valueOf(totQuantity));
                        totPrice = recommendedModel.getPrice() * totQuantity;
                    }

                }
            });

            add_to_cart.setOnClickListener(new View.OnClickListener() { //recomaddtocart
                @Override
                public void onClick(View v) {
                    recomaddedtocart();
                }
            });
        }

    }
    private void viewalladdedtocart() {  //viewallcart

        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put(("productImg"),viewAllModel.getImg_url());
        cartMap.put("productName",viewAllModel.getName());
        cartMap.put("productDesc",viewAllModel.getDescription());
        cartMap.put("productPrice",det_price.getText().toString());
        cartMap.put("productType",viewAllModel.getType());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalprice",totPrice);

        fstore.collection("AddtoCart").document(fAuth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        Toast.makeText(DetailedActivity.this, "Added to Cart ", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });
    }

    private void newpaddedtocart() {   //newprocart

        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put(("productImg"),navNewpModel.getImg_url());
        cartMap.put("productName",navNewpModel.getName());
        cartMap.put("productDesc",navNewpModel.getDescription());
        cartMap.put("productPrice",det_price.getText().toString());
        cartMap.put("productType",navNewpModel.getType());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalprice",totPrice);

        fstore.collection("AddtoCart").document(fAuth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        Toast.makeText(DetailedActivity.this, "Added to Cart ", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });
    }

    private void popaddedtocart() {   //popularcart

        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put(("productImg"),popularModel.getImg_url());
        cartMap.put("productName",popularModel.getName());
        cartMap.put("productDesc",popularModel.getDescription());
        cartMap.put("productPrice",det_price.getText().toString());
        cartMap.put("productType",popularModel.getType());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalprice",totPrice);

        fstore.collection("AddtoCart").document(fAuth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        Toast.makeText(DetailedActivity.this, "Added to Cart ", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });
    }

    private void recomaddedtocart() {  //recommendedcart

        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();
        cartMap.put(("productImg"),recommendedModel.getImg_url());
        cartMap.put("productName",recommendedModel.getName());
        cartMap.put("productDesc",recommendedModel.getDescription());
        cartMap.put("productPrice",det_price.getText().toString());
        cartMap.put("productType",recommendedModel.getType());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalprice",totPrice);

        fstore.collection("AddtoCart").document(fAuth.getCurrentUser().getUid())
                .collection("CurrentUser").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {

                        Toast.makeText(DetailedActivity.this, "Added to Cart ", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });
    }

}