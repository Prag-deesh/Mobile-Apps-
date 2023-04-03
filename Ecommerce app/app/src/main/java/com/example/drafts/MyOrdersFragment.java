package com.example.drafts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.drafts.Adapters.MyOrdersAdapters;
import com.example.drafts.models.MyOrdersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MyOrdersFragment extends Fragment {

    RecyclerView Orderrec;
    List<MyOrdersModel> myOrdersModelList;
    MyOrdersAdapters myOrdersAdapters;
    FirebaseFirestore db;
    FirebaseAuth auth;

    public MyOrdersFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View root = inflater.inflate(R.layout.fragment_my_orders, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        Orderrec = root.findViewById(R.id.orders_rec);
        Orderrec.setLayoutManager(new LinearLayoutManager(getActivity()));

        myOrdersModelList = new ArrayList<>();
        myOrdersAdapters = new MyOrdersAdapters(getActivity(), myOrdersModelList);
        Orderrec.setAdapter(myOrdersAdapters);

        db.collection("AddtoCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                MyOrdersModel myOrdersModel = documentSnapshot.toObject(MyOrdersModel.class);
                                myOrdersModelList.add(myOrdersModel);
                                myOrdersAdapters.notifyDataSetChanged();
                            }
                        }
                    }
                });

        return root;
    }
}