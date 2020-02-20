package com.danhtran12797.thd.appnext_test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appnext.ads.fullscreen.RewardedVideo;
import com.appnext.ads.interstitial.Interstitial;
import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerListener;
import com.appnext.banners.BannerView;
import com.appnext.core.AppnextError;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.appnext.core.callbacks.OnVideoEnded;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnInterstitial, btnRewarded;
    BannerView bannerView;
    RewardedVideo rewarded_ad;
    Interstitial interstitial_Ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInterstitial = findViewById(R.id.btnInterstitial);
        btnRewarded = findViewById(R.id.btnRewarded);

        btnRewarded.setOnClickListener(this);
        btnInterstitial.setOnClickListener(this);

        /*Interstitial: You must load the ad before showing it*/
        interstitial_Ad = new Interstitial(this, getResources().getString(R.string.placement_id_appnext));
        interstitial_Ad.loadAd();

        /*Rewarded: You must load the ad before showing it*/
        rewarded_ad = new RewardedVideo(this, getResources().getString(R.string.placement_id_appnext));
        rewarded_ad.loadAd();

        bannerView = findViewById(R.id.banner);
        bannerView.loadAd(new BannerAdRequest());
        // Banner listener
        bannerView.setBannerListener(new BannerListener() {
            @Override
            public void onError(AppnextError error) {
                super.onError(error);
                Log.d("AAA", "Banner Error: " + error.getErrorMessage());
                Toast.makeText(MainActivity.this, "Banner Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLoaded(String s) {
                super.onAdLoaded(s);
                Log.d("AAA", "Banner load success");
                Toast.makeText(MainActivity.this, "Banner load success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void adImpression() {
                super.adImpression();
            }
            @Override
            public void onAdClicked() {
                super.onAdClicked();
                Log.d("AAA", "Banner clicked");
                Toast.makeText(MainActivity.this, "Banner clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Interstitial: Receive event callbacks from the SDK
        // Get callback for ad loaded
        interstitial_Ad.setOnAdLoadedCallback(new OnAdLoaded() {
            @Override
            public void adLoaded(String s) {
                Log.d("AAA", "Interstitial load success");
                Toast.makeText(MainActivity.this, "Interstitial load success", Toast.LENGTH_SHORT).show();
            }
        });
        interstitial_Ad.setOnAdOpenedCallback(new OnAdOpened() {
            @Override
            public void adOpened() {
                Log.d("AAA", "Interstitial oppened");
                Toast.makeText(MainActivity.this, "Interstitial oppened", Toast.LENGTH_SHORT).show();
            }
        });// Get callback for ad clicked
        interstitial_Ad.setOnAdClickedCallback(new OnAdClicked() {
            @Override
            public void adClicked() {
                Log.d("AAA", "Interstitial clicked");
                Toast.makeText(MainActivity.this, "Interstitial clicked", Toast.LENGTH_SHORT).show();
            }
        });// Get callback for ad closed
        interstitial_Ad.setOnAdClosedCallback(new OnAdClosed() {
            @Override
            public void onAdClosed() {
                Log.d("AAA", "Interstitial closed");
                Toast.makeText(MainActivity.this, "Interstitial closed", Toast.LENGTH_SHORT).show();
            }
        });// Get callback for ad error
        interstitial_Ad.setOnAdErrorCallback(new OnAdError() {
            @Override
            public void adError(String error) {
                Log.d("AAA", "Interstitial error");
                Toast.makeText(MainActivity.this, "Interstitial error", Toast.LENGTH_SHORT).show();
            }
        });

        // Rewarded: Receive event callbacks from the SDK
        // Get callback for ad loaded
        rewarded_ad.setOnAdLoadedCallback(new OnAdLoaded() {
            @Override
            public void adLoaded(String s) {
                Log.d("AAA", "Rewarded load success");
                Toast.makeText(MainActivity.this, "Rewarded load success", Toast.LENGTH_SHORT).show();
            }
        });
        // Get callback for ad opened
        rewarded_ad.setOnAdOpenedCallback(new OnAdOpened() {
            @Override
            public void adOpened() {
                Log.d("AAA", "Rewarded oppened");
                Toast.makeText(MainActivity.this, "Rewarded oppened", Toast.LENGTH_SHORT).show();
            }
        });
        // Get callback for ad clicked
        rewarded_ad.setOnAdClickedCallback(new OnAdClicked() {
            @Override
            public void adClicked() {
                Log.d("AAA", "Rewarded clicked");
                Toast.makeText(MainActivity.this, "Rewarded clicked", Toast.LENGTH_SHORT).show();
            }
        });
        // Get callback for ad closed
        rewarded_ad.setOnAdClosedCallback(new OnAdClosed() {
            @Override
            public void onAdClosed() {
                Log.d("AAA", "Rewarded closed");
                Toast.makeText(MainActivity.this, "Rewarded closed", Toast.LENGTH_SHORT).show();
            }
        });
        // Get callback for ad error
        rewarded_ad.setOnAdErrorCallback(new OnAdError() {
            @Override
            public void adError(String error) {
                Log.d("AAA", "Rewarded error: "+error);
                Toast.makeText(MainActivity.this, "Rewarded error: "+error, Toast.LENGTH_SHORT).show();
            }
        });
        // Get callback when the user saw the video until the end (video ended)
        rewarded_ad.setOnVideoEndedCallback(new OnVideoEnded() {
            @Override
            public void videoEnded() {
                Log.d("AAA", "Rewarded video ended");
                Toast.makeText(MainActivity.this, "Rewarded video ended", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bannerView.destroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInterstitial:
                if (interstitial_Ad.isAdLoaded()) {
                    interstitial_Ad.showAd();
                } else {
                    interstitial_Ad.loadAd();
                    Toast.makeText(this, "Load Interstitial...", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRewarded:
                if (rewarded_ad.isAdLoaded()) {
                    rewarded_ad.showAd();
                } else {
                    rewarded_ad.loadAd();
                    Toast.makeText(this, "Load Rewarded...", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
