package com.example.drafts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.drafts.Adapters.MyCartAdapters;
import com.example.drafts.Adapters.NavCategoryAdapters;
import com.example.drafts.models.MyCartModel;
import com.example.drafts.models.NavCategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MyCartsFragment extends Fragment {

    RecyclerView Cartrec;
    List<MyCartModel> myCartModelList;
    MyCartAdapters myCartAdapters;
    FirebaseFirestore db;
    FirebaseAuth auth;
    TextView totAmount;


    public MyCartsFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_my_carts, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        Cartrec = root.findViewById(R.id.cart_rec);
        Cartrec.setLayoutManager(new LinearLayoutManager(getActivity()));//, RecyclerView.VERTICAL, false));

        totAmount = root.findViewById(R.id.tot_Amount);
        LocalBroadcastManager.getInstance(getActivity())
                .registerReceiver(MessegeReciever, new IntentFilter("MyTotalAmount"));

        myCartModelList = new ArrayList<>();
        myCartAdapters = new MyCartAdapters(getActivity(), myCartModelList);
        Cartrec.setAdapter(myCartAdapters);

        db.collection("AddtoCart").document(auth.getCurrentUser().getUid())
                .collection("CurrentUser").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            for(DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class);
                                myCartModelList.add(myCartModel);
                                myCartAdapters.notifyDataSetChanged();
                            }
                        }
                    }
                });

        return root;
    }

    public BroadcastReceiver MessegeReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int totalBill = intent.getIntExtra("totalAmount",0);
            totAmount.setText("Total Bill : ₹ "+ totalBill);

        }
    };

}