package com.example.lockinapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.lockinapp.Data.ApiService;
import com.example.lockinapp.Data.DataRetrofit;
import com.example.lockinapp.Fragment.TopAiringFragment;
import com.example.lockinapp.Fragment.TopAllAnimeFragment;
import com.example.lockinapp.Fragment.TopAllMangaFragment;
import com.example.lockinapp.Fragment.TopMovieFragment;
import com.example.lockinapp.Fragment.TopNovelsFragment;
import com.example.lockinapp.Fragment.TopOneshotsFragment;
import com.example.lockinapp.Fragment.TopTvSeriesFragment;
import com.example.lockinapp.Fragment.TopUpcomingFragment;
import com.example.lockinapp.Model.Manga;
import com.example.lockinapp.Model.MangaItem;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MangaTab extends Fragment {

    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public MangaTab(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.manga_tab, container, false);

        viewPager = view.findViewById(R.id.ViewPagerManga);
        tabLayout = view.findViewById(R.id.topMangaTabLayout);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new TopAllMangaFragment(), "Top Manga");
        adapter.addFragment(new TopNovelsFragment(), "Top Novels");
        adapter.addFragment(new TopOneshotsFragment(), "Top One-shots");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }


}
