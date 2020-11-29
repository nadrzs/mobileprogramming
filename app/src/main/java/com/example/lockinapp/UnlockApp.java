package com.example.lockinapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UnlockApp extends Fragment {

    View view;
    RecyclerView recyclerView;

    public UnlockApp(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.unlock_app, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.LockRecycler);
        recyclerView.setHasFixedSize(true);
        AppAdapter recyclerViewAdapater = new AppAdapter(getContext(), getAllApps());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerView.setAdapter(recyclerViewAdapater);

        return view;
    }

        private List<AppItem> getAllApps(){

        List<AppItem> appList = new ArrayList<>();

        PackageManager pk = getActivity().getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveInfoList = pk.queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : resolveInfoList){
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            appList.add(new AppItem(activityInfo.loadIcon(pk), activityInfo.loadLabel(pk).toString(), activityInfo.packageName));

        }

        return appList;
    }
}
