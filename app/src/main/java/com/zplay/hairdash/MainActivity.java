package com.zplay.hairdash;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.heyzap.sdk.ads.HeyzapAds;

public class MainActivity extends AppCompatActivity implements VideoAdObserver {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        AndroidAdService adService = new AndroidAdService(this);
        fab.setOnClickListener(view -> {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            adService.showInterstitialAd(this, AnalyticsManager.NULL_INSTANCE());
            toolbar.setTitle("Showing ad");
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        System.out.println("ONBACKPRESSED");
        if (HeyzapAds.onBackPressed()) {
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void onVideoCompleted() {
        runOnUiThread(() -> {
            toolbar.setTitle("V.ad successful");
        });
    }

    @Override
    public void onVideoCancelled() {
        runOnUiThread(() -> {
            toolbar.setTitle("V.ad unsuccessful");
        });
    }

    @Override
    public void onInterstitialCompleted() {
        runOnUiThread(() -> {
            toolbar.setTitle("I.ad successful");
        });
    }

    @Override
    public void onInterstitialCancelled() {
        runOnUiThread(() -> {
            toolbar.setTitle("I.ad unsuccessful");
        });
    }
}
