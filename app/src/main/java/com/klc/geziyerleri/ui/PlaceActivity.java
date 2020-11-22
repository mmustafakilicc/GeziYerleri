package com.klc.geziyerleri.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.klc.geziyerleri.R;
import com.klc.geziyerleri.constants.Constants;
import com.klc.geziyerleri.data.Place;

public class PlaceActivity extends AppCompatActivity {

    private Place place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
    }

    private void initialize(){
        place = (Place) getIntent().getSerializableExtra(Constants.IntentExtras.PLACE_EXTRA);
        if (place == null || place.getId() < 1) {
            showErrorAndExit(getString(R.string.error), getString(R.string.null_extra));
            return;
        }
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