package com.zplay.hairdash;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import com.heyzap.sdk.ads.HeyzapAds;
import com.heyzap.sdk.ads.IncentivizedAd;
import com.heyzap.sdk.ads.InterstitialAd;

class AndroidAdService implements AdService {
    private Activity activity;
    private AnalyticsManager analyticsManager;
    private boolean listenerRegistered = false;
    private Runnable onAdCompleted = Utility.nullRunnable();
    private Runnable onAdFailed = Utility.nullRunnable();

    AndroidAdService(Activity activity) {
        this.activity = activity;

        HeyzapAds.start("", activity, HeyzapAds.DISABLE_AUTOMATIC_FETCH);
        HeyzapAds.startTestActivity(activity);
        HeyzapAds.setThirdPartyVerboseLogging(true);
        IncentivizedAd.fetch();
        InterstitialAd.fetch();
    }

    @Override
    public void showRewardedVideo(final VideoAdObserver observer, AnalyticsManager analyticsManager) {
        System.out.println("######### SHOW REWARDED ##########");
        this.analyticsManager = analyticsManager;

        if (!isVideoAdAvailableOrFetch()) {
            observer.onVideoCancelled();
            return;
        }

        if (!listenerRegistered) {
            registerListener();
        }

        onAdCompleted = observer::onVideoCompleted;
        onAdFailed = observer::onVideoCancelled;
        analyticsManager.onAdIsGonnaStart();
        IncentivizedAd.display(activity);
        isVideoAdAvailableOrFetch();
        System.out.println("###################### STARTING AD #######################");
    }

    @Override
    public void showInterstitialAd(VideoAdObserver observer, AnalyticsManager analyticsManager) {
        System.out.println("######### SHOW INTERSTITIAL ##########");
        this.analyticsManager = analyticsManager;
        if (!isInterstitialAdAvailableOrFetch()) {
            observer.onInterstitialCancelled();
            return;
        }

        if (!listenerRegistered) {
            registerListener();
        }

        onAdCompleted = observer::onInterstitialCompleted;
        onAdFailed = observer::onInterstitialCancelled;

        analyticsManager.onAdIsGonnaStart();
        InterstitialAd.display(activity);

    }

    public void adErrorToast(String text) {
        activity.runOnUiThread(() -> Toast.makeText(activity, text, Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean isVideoAdAvailableOrFetch() {
        if (!isNetworkAvailable()) {
            adErrorToast("No internet connection. Connect to the Internet and try again.");
            return false;
        }


        if (!IncentivizedAd.isAvailable()) {
            adErrorToast("No ads were found, please check your network.");
            IncentivizedAd.fetch();
            return false;
        }
        return true;
    }

    @Override
    public boolean isInterstitialAdAvailableOrFetch() {
        if (!isNetworkAvailable()) {
            adErrorToast("No internet connection. Connect to the Internet and try again.");
            System.out.println("NO NETWORK");
            return false;
        }

        if (!InterstitialAd.isAvailable()) {
            adErrorToast("No ads were found, please check your network.");
            System.out.println("NO INTERSTITIAL");
            InterstitialAd.fetch();
            return false;
        }

        return true;
    }

    boolean onBackPressed() {
        return HeyzapAds.onBackPressed();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void registerListener() {
        IncentivizedAd.setOnStatusListener(new HeyzapAds.OnStatusListener() {
            @Override
            public void onShow(String s) {
                System.out.println("INCENTIVIZEDADLOGGINGSTATUS onShow");
            }

            @Override
            public void onClick(String s) {
                System.out.println("INCENTIVIZEDADLOGGINGSTATUS onClick");
            }

            @Override
            public void onHide(String s) {
                System.out.println("INCENTIVIZEDADLOGGINGSTATUS onHide");

            }

            @Override
            public void onFailedToShow(String s) {
                System.out.println("INCENTIVIZEDADLOGGINGSTATUS onFailedToShow");

            }

            @Override
            public void onAvailable(String s) {
                System.out.println("INCENTIVIZEDADLOGGINGSTATUS onAvailable");

            }

            @Override
            public void onFailedToFetch(String s) {
                System.out.println("INCENTIVIZEDADLOGGINGSTATUS onFailedToFetch");

            }

            @Override
            public void onAudioStarted() {
                System.out.println("INCENTIVIZEDADLOGGINGSTATUS onAudioStarted");

            }

            @Override
            public void onAudioFinished() {
                System.out.println("INCENTIVIZEDADLOGGINGSTATUS onAudioFinished");
            }
        });
        IncentivizedAd.setOnIncentiveResultListener(new HeyzapAds.OnIncentiveResultListener() {
            @Override
            public void onComplete(String tag) {
                System.out.println("###################### INCENTIVIZEDADLOGGINGINCENTIVERESULT COMPLETE AD #######################");
                analyticsManager.onAdOver();
                onAdCompleted.run();
                onAdCompleted = Utility.nullRunnable();
                IncentivizedAd.fetch();
            }

            @Override
            public void onIncomplete(String tag) {
                System.out.println("###################### INCENTIVIZEDADLOGGINGINCENTIVERESULT INCOMPLETE AD #######################");
                analyticsManager.onAdOver();
                adErrorToast("Video has been cancelled, no reward.");
                analyticsManager.onAdFailedOrCancelled(tag);
                onAdFailed.run();
                onAdFailed = Utility.nullRunnable();
                IncentivizedAd.fetch();
            }
        });
        InterstitialAd.setOnStatusListener(new HeyzapAds.OnStatusListener() {
            @Override
            public void onShow(String s) {
                System.out.println("INTERSTITIALADLOGGING: Onshow");
            }

            @Override
            public void onClick(String s) {
                System.out.println("INTERSTITIALADLOGGING: onClick");
            }

            @Override
            public void onHide(String s) {
                System.out.println("INTERSTITIALADLOGGING: onHide");
                analyticsManager.onAdOver();
                onAdCompleted.run();
                onAdCompleted = Utility.nullRunnable();
                InterstitialAd.fetch();
            }

            @Override
            public void onFailedToShow(String s) {
                adErrorToast("There was an error trying to show the ad, please try again.");
                System.out.println("INTERSTITIALADLOGGING: onFailedToShow");
                analyticsManager.onAdOver();
                analyticsManager.onAdFailedOrCancelled(s);
                onAdFailed.run();
                onAdFailed = Utility.nullRunnable();
                InterstitialAd.fetch();
            }

            @Override
            public void onAvailable(String s) {
                System.out.println("INTERSTITIALADLOGGING: onAvailable");
            }

            @Override
            public void onFailedToFetch(String s) {
                adErrorToast("There was an error fetching the ad, please try again.");
                System.out.println("INTERSTITIALADLOGGING: onFailedToFetch");
                analyticsManager.onAdFailedOrCancelled(s);
                analyticsManager.onAdOver();
                onAdFailed.run();
                onAdFailed = Utility.nullRunnable();
                InterstitialAd.fetch();
            }

            @Override
            public void onAudioStarted() {
                System.out.println("INTERSTITIALADLOGGING: onAudioStarted");
            }

            @Override
            public void onAudioFinished() {
                System.out.println("INTERSTITIALADLOGGING: onAudioFinished");
            }
        });

        listenerRegistered = true;
    }
}
