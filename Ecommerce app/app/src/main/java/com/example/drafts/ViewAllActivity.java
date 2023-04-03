package com.example.drafts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.drafts.Adapters.ViewAllAdapters;
import com.example.drafts.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ViewAllAdapters viewAllAdapters;
    List<ViewAllModel> viewAllModelList;
    FirebaseFirestore fstore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        fstore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllModelList = new ArrayList<>();
        viewAllAdapters = new ViewAllAdapters(this,viewAllModelList);
        recyclerView.setAdapter(viewAllAdapters);

        if(type != null && type.equalsIgnoreCase("halfsleeve tees")) {
            fstore.collection("Products").whereEqualTo("type", "halfsleeve tees").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapters.notifyDataSetChanged();
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("fullsleeve tees")) {
            fstore.collection("Products").whereEqualTo("type", "fullsleeve tees").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapters.notifyDataSetChanged();
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("hoodies")) {
            fstore.collection("Products").whereEqualTo("type", "hoodies").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapters.notifyDataSetChanged();
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("shirts")) {
            fstore.collection("Products").whereEqualTo("type", "shirts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapters.notifyDataSetChanged();
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("shorts")) {
            fstore.collection("Products").whereEqualTo("type", "shorts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapters.notifyDataSetChanged();
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("joggers")) {
            fstore.collection("Products").whereEqualTo("type", "joggers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapters.notifyDataSetChanged();
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("pants")) {
            fstore.collection("Products").whereEqualTo("type", "pants").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapters.notifyDataSetChanged();
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("slippers")) {
            fstore.collection("Products").whereEqualTo("type", "slippers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapters.notifyDataSetChanged();
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("shoes")) {
            fstore.collection("Products").whereEqualTo("type", "shoes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapters.notifyDataSetChanged();
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("caps")) {
            fstore.collection("Products").whereEqualTo("type", "caps").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapters.notifyDataSetChanged();
                    }
                }
            });
        }
        if(type != null && type.equalsIgnoreCase("socks")) {
            fstore.collection("Products").whereEqualTo("type", "socks").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapters.notifyDataSetChanged();
                    }
                }
            });
        }

    }
}