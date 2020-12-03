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
import com.example.lockinapp.Data.DataRetrofit;
import com.example.lockinapp.Model.AnimItem;
import com.example.lockinapp.Model.Anime;
import com.example.lockinapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopUpcomingFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter layoutAdapter;
    private ProgressBar Loading;
    private SwipeRefreshLayout Refresh;

    public TopUpcomingFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_top_upcoming, container, false);

        recyclerView = view.findViewById(R.id.AnimeRecycler);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Loading = view.findViewById(R.id.Loading);
        Refresh = view.findViewById(R.id.Refresh);

        ResponseData();
        Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ResponseData();
                Loading.setVisibility(View.GONE);
            }
        });

        return view;
    }

    private void ResponseData(){
        ApiService apiService = DataRetrofit.getData().create(ApiService.class);
        Loading.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        apiService.getTopUpcoming()
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
                        Toast.makeText(getActivity(), "Low Connection", Toast.LENGTH_SHORT).show();
                        Loading.setVisibility(View.GONE);
                        Refresh.setRefreshing(false);
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