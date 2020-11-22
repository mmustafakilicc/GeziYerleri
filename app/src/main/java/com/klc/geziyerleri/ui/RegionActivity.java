package com.klc.geziyerleri.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.klc.geziyerleri.R;
import com.klc.geziyerleri.adapter.CityAdapter;
import com.klc.geziyerleri.constants.Constants;
import com.klc.geziyerleri.data.City;
import com.klc.geziyerleri.data.Region;
import com.klc.geziyerleri.network.viewmodel.CityViewModel;

import java.util.List;

public class RegionActivity extends AppCompatActivity {

    //region variables
    private Region region;

    private CityViewModel cityViewModel;
    private LiveData<List<City>> liveDataCity;
    private LiveData<Throwable> liveDataError;
    private LiveData<Boolean> liveDataIsLoading;

    private ContentLoadingProgressBar progressBarRAIsLoading;

    private RecyclerView recyclerViewRARegion;

    private LinearLayout linearLayoutRAErrorMessage;

    private TextView textViewRAErrorMessage;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);

        initialize();
    }

    private void initialize() {

        region = (Region) getIntent().getSerializableExtra(Constants.IntentExtras.REGION_EXTRA);

        if (region == null || region.getId() < 1) {
            showErrorAndExit(getString(R.string.error), getString(R.string.null_extra));
            return;
        }

        cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);
        liveDataCity = cityViewModel.getRegionCities();
        liveDataError = cityViewModel.getError();
        liveDataIsLoading = cityViewModel.getIsLoading();

        TextView textViewRATitle = findViewById(R.id.textViewRATitle);
        textViewRATitle.setText(region.getName());

        progressBarRAIsLoading = findViewById(R.id.progressBarRAIsLoading);

        recyclerViewRARegion = findViewById(R.id.recyclerViewRARegion);

        linearLayoutRAErrorMessage = findViewById(R.id.linearLayoutRAErrorMessage);

        textViewRAErrorMessage = findViewById(R.id.textViewRAErrorMessage);

        setObservers();
    }

    private void setObservers() {

        liveDataIsLoading.observe(this, isLoading -> {
            if (isLoading) {
                progressBarRAIsLoading.setVisibility(View.VISIBLE);
            } else {
                progressBarRAIsLoading.setVisibility(View.GONE);
            }
        });

        liveDataError.observe(this, throwable -> {
            if (throwable == null) {
                linearLayoutRAErrorMessage.setVisibility(View.GONE);
            } else {
                linearLayoutRAErrorMessage.setVisibility(View.VISIBLE);
                textViewRAErrorMessage.setText(throwable.getMessage());
            }
        });

        liveDataCity.observe(this, cities -> {
            if (cities == null || cities.size() == 0) {
                recyclerViewRARegion.setVisibility(View.GONE);
                showErrorAndExit(getString(R.string.info), getString(R.string.empty_city_list));
            } else {
                setupList(cities);
            }
        });

        cityViewModel.loadRegionCities(region.getId());
    }

    private void setupList(List<City> cityList) {

        CityAdapter adapter = new CityAdapter(this, cityList, this::startCityActivity);

        recyclerViewRARegion.setAdapter(adapter);

        recyclerViewRARegion.setVisibility(View.VISIBLE);

    }

    private void startCityActivity(City city) {
        Intent intent = new Intent(this, CityActivity.class);
        intent.putExtra(Constants.IntentExtras.CITY_EXTRA, city);
        startActivity(intent);
    }

    public void refresh(View view) {
        cityViewModel.loadRegionCities(region.getId());
    }

    public void back(View view) {
        finish();
    }

    private void showErrorAndExit(String title, String message) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setPositiveButton(R.string.ok, (dialog, which) -> {
            dialog.dismiss();
            finish();
        });
        builder.setTitle(title);
        builder.setMessage(message);
        builder.create().show();
    }
}