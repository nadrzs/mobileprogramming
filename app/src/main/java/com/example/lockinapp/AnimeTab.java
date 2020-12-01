package com.example.lockinapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.lockinapp.Fragment.TopAiringFragment;
import com.example.lockinapp.Fragment.TopAllAnimeFragment;
import com.example.lockinapp.Fragment.TopMovieFragment;
import com.example.lockinapp.Fragment.TopTvSeriesFragment;
import com.example.lockinapp.Fragment.TopUpcomingFragment;
import com.google.android.material.tabs.TabLayout;

public class AnimeTab extends Fragment {

    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public AnimeTab(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.anime_tab, container, false);

        viewPager = view.findViewById(R.id.ViewPagerAnime);
        tabLayout = view.findViewById(R.id.topAnimeTabLayout);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new TopAllAnimeFragment(), "Top All");
        adapter.addFragment(new TopAiringFragment(), "Top Airing");
        adapter.addFragment(new TopUpcomingFragment(), "Top Upcoming");
        adapter.addFragment(new TopTvSeriesFragment(), "Top TV Series");
        adapter.addFragment(new TopMovieFragment(), "Top Movie");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


}
