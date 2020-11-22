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
import com.klc.geziyerleri.adapter.PlaceAdapter;
import com.klc.geziyerleri.constants.Constants;
import com.klc.geziyerleri.data.City;
import com.klc.geziyerleri.data.Place;
import com.klc.geziyerleri.network.viewmodel.PlaceViewModel;

import java.util.List;

public class CityActivity extends AppCompatActivity {

    //region variables
    private PlaceViewModel placeViewModel;
    private LiveData<List<Place>> liveDataPlace;
    private LiveData<Boolean> liveDataIsLoading;
    private LiveData<Throwable> liveDataError;

    private ContentLoadingProgressBar progressBarCAIsLoading;

    private RecyclerView recyclerViewCARegion;

    private LinearLayout linearLayoutCAErrorMessage;

    private TextView textViewCAErrorMessage;

    private City city;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        initialize();
    }

    private void initialize() {

        city = (City) getIntent().getSerializableExtra(Constants.IntentExtras.CITY_EXTRA);

        if (city == null || city.getId() < 1) {
            showErrorAndExit(getString(R.string.error), getString(R.string.null_extra));
            return;
        }

        placeViewModel = new ViewModelProvider(this).get(PlaceViewModel.class);
        liveDataError = placeViewModel.getError();
        liveDataIsLoading = placeViewModel.getIsLoading();
        liveDataPlace = placeViewModel.getPlaces();

        progressBarCAIsLoading = findViewById(R.id.progressBarCAIsLoading);

        recyclerViewCARegion = findViewById(R.id.recyclerViewCARegion);

        linearLayoutCAErrorMessage = findViewById(R.id.linearLayoutCAErrorMessage);

        textViewCAErrorMessage = findViewById(R.id.textViewCAErrorMessage);

        setObservers();
    }

    private void setObservers() {

        liveDataError.observe(this, throwable -> {
            if (throwable == null) {
                linearLayoutCAErrorMessage.setVisibility(View.GONE);
                textViewCAErrorMessage.setText(R.string.ok);
            } else {
                linearLayoutCAErrorMessage.setVisibility(View.VISIBLE);
                textViewCAErrorMessage.setText(throwable.getMessage());
            }
        });

        liveDataIsLoading.observe(this, isLoading -> {
            if (isLoading) {
                progressBarCAIsLoading.setVisibility(View.VISIBLE);
            } else {
                progressBarCAIsLoading.setVisibility(View.GONE);
            }
        });

        liveDataPlace.observe(this, places -> {
            if (places == null) {
                recyclerViewCARegion.setVisibility(View.GONE);
            } else {
                setupList(places);
            }
        });

        placeViewModel.loadCityPlaces(city.getId());
    }

    private void setupList(List<Place> placeList) {

        PlaceAdapter adapter = new PlaceAdapter(this, placeList, this::startPlaceActivity);

        recyclerViewCARegion.setAdapter(adapter);

        recyclerViewCARegion.setVisibility(View.VISIBLE);

    }

    private void startPlaceActivity(Place place) {
        Intent intent = new Intent(this, PlaceActivity.class);
        intent.putExtra(Constants.IntentExtras.PLACE_EXTRA, place);
        startActivity(intent);
    }

    public void back(View view) {
        finish();
    }

    public void refresh(View view) {
        placeViewModel.loadCityPlaces(city.getId());
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