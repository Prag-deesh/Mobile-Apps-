package com.example.drafts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.drafts.Adapters.NavCategoryAdapters;
import com.example.drafts.Adapters.NavNewpAdapters;
import com.example.drafts.Adapters.PopularAdapters;
import com.example.drafts.databinding.FragmentCategoryBinding;
import com.example.drafts.databinding.FragmentNewProductsBinding;
import com.example.drafts.models.NavCategoryModel;
import com.example.drafts.models.NavNewpModel;
import com.example.drafts.models.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class NewProductsFragment extends Fragment {

    private @NonNull FragmentNewProductsBinding binding;

    RecyclerView Newprec;
    List<NavNewpModel> navNewpModelList;
    NavNewpAdapters navNewpAdapters;
    FirebaseFirestore db;


    public NewProductsFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNewProductsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Newprec = root.findViewById(R.id.newp_rec);
        db = FirebaseFirestore.getInstance();


        //for new products recycler view
        Newprec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        navNewpModelList = new ArrayList<>();
        navNewpAdapters = new NavNewpAdapters(getActivity(),navNewpModelList);
        Newprec.setAdapter(navNewpAdapters);

        db.collection("Products").whereEqualTo("newpro","yes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NavNewpModel navNewpModel = document.toObject(NavNewpModel.class);
                                navNewpModelList.add(navNewpModel);
                                navNewpAdapters.notifyDataSetChanged();

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
