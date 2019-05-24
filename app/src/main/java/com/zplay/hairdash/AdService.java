package com.zplay.hairdash;

public interface AdService {
    void showRewardedVideo(VideoAdObserver observer, AnalyticsManager analyticsManager);

    void showInterstitialAd(VideoAdObserver observer, AnalyticsManager analyticsManager);

    void adErrorToast(String text);

    boolean isVideoAdAvailableOrFetch();

    boolean isInterstitialAdAvailableOrFetch();
}
