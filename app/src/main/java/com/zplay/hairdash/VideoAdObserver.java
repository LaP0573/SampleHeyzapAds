package com.zplay.hairdash;

public interface VideoAdObserver {
    void onVideoCompleted();

    void onVideoCancelled();

    void onInterstitialCompleted();

    void onInterstitialCancelled();

    static VideoAdObserver NULL_INSTANCE() {
        return new VideoAdObserver() {
            @Override
            public void onVideoCompleted() {
                System.out.println("VIDEOADOBSERVER : oncompleted");
            }

            @Override
            public void onVideoCancelled() {
                System.out.println("VIDEOADOBSERVER : oncancelled");
            }

            @Override
            public void onInterstitialCompleted() {
                System.out.println("VIDEOADOBSERVER : onInterstitialcompleted");
            }

            @Override
            public void onInterstitialCancelled() {
                System.out.println("VIDEOADOBSERVER : onInterstitialcancelled");
            }
        };
    }
}
