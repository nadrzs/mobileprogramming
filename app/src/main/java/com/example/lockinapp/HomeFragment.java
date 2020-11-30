package com.example.lockinapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {


    View view;
    TabLayout tabLayout;
    ViewPager viewPager;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        tabLayout = view.findViewById(R.id.TabLayout);
        viewPager = view.findViewById(R.id.ViewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        //adding fragment
        adapter.addFragment(new AnimeTab(), "Anime");
        adapter.addFragment(new MangaTab(), "Manga");
        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }




}
