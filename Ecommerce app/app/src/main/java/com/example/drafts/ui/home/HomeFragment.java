package com.example.drafts.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drafts.Adapters.CategoryAdapters;
import com.example.drafts.Adapters.PopularAdapters;
import com.example.drafts.Adapters.RecommendedAdapters;
import com.example.drafts.Adapters.ViewAllAdapters;
import com.example.drafts.MainActivity;
import com.example.drafts.R;
import com.example.drafts.databinding.FragmentHomeBinding;
import com.example.drafts.models.CategoryModel;
import com.example.drafts.models.PopularModel;
import com.example.drafts.models.RecommendedModel;
import com.example.drafts.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    RecyclerView Poprec,Catrec,Recomrec;
    ProgressBar progressBar;

    FirebaseFirestore db;

    //popular items
    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;

    //category items
    List<CategoryModel> categoryModelList;
    CategoryAdapters categoryAdapters;

    //recom items
    List<RecommendedModel> recommendedModelList;
    RecommendedAdapters recommendedAdapters;

    ////////////////search
    EditText search_box;
    List<ViewAllModel> viewAllModelList;
    ViewAllAdapters viewAllAdapters;
    RecyclerView recyclerViewSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Poprec = root.findViewById(R.id.pop_rec);
        Catrec = root.findViewById(R.id.cat_rec);
        Recomrec = root.findViewById(R.id.recom_rec);

        progressBar = root.findViewById(R.id.progressbar);

        db = FirebaseFirestore.getInstance();

        progressBar.setVisibility(View.VISIBLE);

        //popular recycler view
        Poprec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(),popularModelList);
        Poprec.setAdapter(popularAdapters);

        db.collection("Products").whereEqualTo("popular","yes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapters.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);

                              //  progressBar.setVisibility(View.GONE);
                              //  scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //for category
        Catrec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapters = new CategoryAdapters(getActivity(),categoryModelList);
        Catrec.setAdapter(categoryAdapters);

        db.collection("category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapters.notifyDataSetChanged();

                                //  progressBar.setVisibility(View.GONE);
                                //  scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//Recommended recycler view
        Recomrec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedModelList = new ArrayList<>();
        recommendedAdapters = new RecommendedAdapters(getActivity(),recommendedModelList);
        Recomrec.setAdapter(recommendedAdapters);

        db.collection("Products").whereEqualTo("recommended","yes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RecommendedModel recommendedModel = document.toObject(RecommendedModel.class);
                                recommendedModelList.add(recommendedModel);
                                recommendedAdapters.notifyDataSetChanged();

                                //  progressBar.setVisibility(View.GONE);
                                //  scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //search view
        search_box = root.findViewById(R.id.search_box);
        recyclerViewSearch = root.findViewById(R.id.search_rec);
       viewAllModelList = new ArrayList<>();
       viewAllAdapters = new ViewAllAdapters(getContext(),viewAllModelList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearch.setAdapter(viewAllAdapters);
        recyclerViewSearch.setHasFixedSize(true);
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(s.toString().isEmpty()) {
                    viewAllModelList.clear();
                    viewAllAdapters.notifyDataSetChanged();
                }
                else {
                    searchproduct(s.toString());
                }

            }
        });



        return root;
    }

    private void searchproduct(String type) {

        if(!type.isEmpty()) {

            db.collection("Products").whereEqualTo("type",type).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful() && task.getResult() != null) {

                                viewAllModelList.clear();;
                                viewAllAdapters.notifyDataSetChanged();

                                for(DocumentSnapshot doc : task.getResult().getDocuments()) {
                                    ViewAllModel viewAllModel = doc.toObject(ViewAllModel.class);
                                    viewAllModelList.add(viewAllModel);
                                    viewAllAdapters.notifyDataSetChanged();
                                }

                            }

                        }
                    });
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}