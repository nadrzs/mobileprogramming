package com.example.lockinapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lockinapp.AnimeAdapter;
import com.example.lockinapp.Data.ApiService;
import com.example.lockinapp.Data.DataBaseHelper;
import com.example.lockinapp.Data.DataRetrofit;
import com.example.lockinapp.Model.AnimItem;
import com.example.lockinapp.Model.Anime;
import com.example.lockinapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopAllAnimeFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter layoutAdapter;
    private int page;
    private ProgressBar Loading;
    private SwipeRefreshLayout Refresh;

    public TopAllAnimeFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_top_all, container, false);
        recyclerView = view.findViewById(R.id.AnimeRecycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Loading = view.findViewById(R.id.Loading);
        Refresh = view.findViewById(R.id.Refresh);
        page = 1;

        ResponseData(page);
        Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ResponseData(1);
                Loading.setVisibility(View.GONE);
            }
        });


        return view;
    }

    private void ResponseData(int page){
        ApiService apiService = DataRetrofit.getData().create(ApiService.class);
        Loading.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        apiService.getAllAnime(page)
                .enqueue(new Callback<Anime>() {
                    @Override
                    public void onResponse(Call<Anime> call, Response<Anime> response) {
                        List<AnimItem> items = response.body().getTopAnime();

                        Refresh.setRefreshing(false);
                        Loading.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        if(items != null){
                            layoutAdapter = new AnimeAdapter(getContext(), items);
                            recyclerView.setAdapter(layoutAdapter);
                        }else {
                            Toast.makeText(getActivity(), "Failed Get Dat !", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Anime> call, Throwable t) {

                        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());

                        List<AnimItem> items2 = new ArrayList<>(dataBaseHelper.getAllRecord());

                        Refresh.setRefreshing(false);
                        Loading.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        if(items2 != null){
                            layoutAdapter = new AnimeAdapter(getContext(), items2);
                            recyclerView.setAdapter(layoutAdapter);
                        }else{
                            Toast.makeText(getActivity(), "Database Is Empty", Toast.LENGTH_SHORT).show();
                        }

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(getView(), "Check Connection", Snackbar.LENGTH_LONG).show();
                            }
                        },4000);
                    }
                });
    }

}