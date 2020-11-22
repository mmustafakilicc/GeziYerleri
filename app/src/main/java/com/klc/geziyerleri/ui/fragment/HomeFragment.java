package com.klc.geziyerleri.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.klc.geziyerleri.R;
import com.klc.geziyerleri.adapter.RegionAdapter;
import com.klc.geziyerleri.constants.Constants;
import com.klc.geziyerleri.data.Region;
import com.klc.geziyerleri.network.viewmodel.RegionViewModel;
import com.klc.geziyerleri.ui.RegionActivity;

import java.util.List;

public class HomeFragment extends Fragment {

    RegionViewModel viewModelRegion;
    LiveData<List<Region>> liveDataRegion;
    LiveData<Throwable> liveDataError;
    LiveData<Boolean> liveDataIsLoading;

    RecyclerView recyclerViewHFRegion;
    ContentLoadingProgressBar progressBarHFIsLoading;
    MaterialButton materialButtonHFRefresh;
    TextView textViewHFErrorMessage;
    LinearLayout linearLayoutHFErrorMessage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        initialize(root);

        return root;
    }

    private void initialize(View view) {

        viewModelRegion = new ViewModelProvider(this).get(RegionViewModel.class);
        liveDataError = viewModelRegion.getError();
        liveDataIsLoading = viewModelRegion.getIsLoading();
        liveDataRegion = viewModelRegion.getRegions();

        recyclerViewHFRegion = view.findViewById(R.id.recyclerViewHFRegion);

        linearLayoutHFErrorMessage = view.findViewById(R.id.linearLayoutHFErrorMessage);

        textViewHFErrorMessage = view.findViewById(R.id.textViewHFErrorMessage);

        progressBarHFIsLoading = view.findViewById(R.id.progressBarHFIsLoading);

        materialButtonHFRefresh = view.findViewById(R.id.materialButtonHFRefresh);
        materialButtonHFRefresh.setOnClickListener(this::refresh);

        setObservers();
    }

    private void setObservers() {

        liveDataRegion.observe(getViewLifecycleOwner(), regions -> {
            if (regions != null) {
                setUpList(regions);
            } else {
                recyclerViewHFRegion.setVisibility(View.INVISIBLE);
            }
        });

        liveDataIsLoading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                progressBarHFIsLoading.setVisibility(View.VISIBLE);
            } else {
                progressBarHFIsLoading.setVisibility(View.INVISIBLE);
            }
        });

        liveDataError.observe(getViewLifecycleOwner(), throwable -> {
            if (throwable == null) {
                linearLayoutHFErrorMessage.setVisibility(View.INVISIBLE);
            } else {
                linearLayoutHFErrorMessage.setVisibility(View.VISIBLE);
                textViewHFErrorMessage.setText(throwable.getMessage());
            }
        });

        viewModelRegion.loadRegions();
    }

    private void setUpList(List<Region> regionList) {
        RegionAdapter adapter = new RegionAdapter(getContext(), regionList, this::startRegionCities);

        recyclerViewHFRegion.setAdapter(adapter);

        recyclerViewHFRegion.setVisibility(View.VISIBLE);
    }

    private void startRegionCities(Region region) {

        Intent intent = new Intent(getActivity(), RegionActivity.class);
        intent.putExtra(Constants.IntentExtras.REGION_EXTRA, region);

        startActivity(intent);

    }

    private void refresh(View view) {
        viewModelRegion.loadRegions();
    }
}