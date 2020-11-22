package com.klc.geziyerleri.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.klc.geziyerleri.R;

public class CollapsingToolbarActivity extends AppCompatActivity {

    AppBarLayout appBarLayout;

    CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar);

        appBarLayout = findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

            }
        });

        collapsingToolbar = findViewById(R.id.collapsingToolbar);
        collapsingToolbar.setTitleEnabled(true);
        collapsingToolbar.setTitle("Köri Soslu Tavuk");

    }
}