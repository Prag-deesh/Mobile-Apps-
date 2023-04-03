package com.example.drafts.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drafts.Adapters.NavCategoryAdapters;
import com.example.drafts.R;
import com.example.drafts.databinding.FragmentCategoryBinding;
import com.example.drafts.models.NavCategoryModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    private FragmentCategoryBinding binding;

    RecyclerView Catrec;
    List<NavCategoryModel> navCategoryModelList;
    NavCategoryAdapters navCategoryAdapters;
    FirebaseFirestore db;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Catrec = root.findViewById(R.id.cat_rec);
        db = FirebaseFirestore.getInstance();

//for category recycler view
        Catrec.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        navCategoryModelList = new ArrayList<>();
        navCategoryAdapters = new NavCategoryAdapters(getActivity(), navCategoryModelList);
        Catrec.setAdapter(navCategoryAdapters);


        db.collection("category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NavCategoryModel navCategoryModel = document.toObject(NavCategoryModel.class);
                                navCategoryModelList.add(navCategoryModel);
                                navCategoryAdapters.notifyDataSetChanged();

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
