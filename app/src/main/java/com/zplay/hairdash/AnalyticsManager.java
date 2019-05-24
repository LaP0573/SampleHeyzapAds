package com.zplay.hairdash;

public interface AnalyticsManager {
    // CURRENCIES
    String GEM = "gem";
    String HEART = "heart";

    String TYPE_PROGRESSION = "progression";
    // SOURCES
    String ID_LEVEL_COMPLETED = "levelCompleted";
    String ID_CHALLENGE_RANK_UP = "challengeRankUp";
    // SINKS
    String ID_CHALLENGE_COMPLETION_BOUGHT = "challengeCompletionBought";
    String ID_LEVEL_STARTED_ENERGY_COST = "levelStartedEnergyCost";

    String TYPE_DAILY_REWARD = "dailyReward";
    // SOURCES
    String ID_DAILY_CHEST = "dailyChest";

    String TYPE_IAP = "iap";
    // SOURCES
    String ID_GOLD_BOUGHT = "goldBought";

    String TYPE_COSMETIC = "cosmetic";
    // SINKS
    String ID_RANDOM_COSMETIC = "randomCosmetic";
    String ID_COSMETIC_SET = "cosmeticSet";

    String TYPE_ENERGY = "energy";
    // SINKS
    String ID_INFINITE_ENERGY_BOUGHT = "infiniteEnergyBought";
    // SOURCES
    String ID_WATCHED_AD_FOR_HEART = "adForHeart";
    String ID_HEART_REFILLED_THROUGH_TIMER = "timerForHeart";

    String TYPE_IN_GAME = "in_game";
    // SINKS
    String ID_REVIVE = "revive";
    String ID_SHOP_REFRESH = "shop_refresh";

    String SCREEN_MAIN_MENU = "MainMenu";
    String SCREEN_CUSTOMIZATION = "Customization";
    String SCREEN_STAGE_SELECT = "StageSelect";
    String SCREEN_LEVEL_SELECT = "LevelSelect";
    String SCREEN_LEVEL_INGAME = "LevelInGame";
    String SCREEN_LEVEL_PAUSE = "LevelPause";
    String SCREEN_LEVEL_GAME_OVER = "LevelGameOver";
    String SCREEN_LEVEL_VICTORY = "LevelVictory";
    String SCREEN_ROGUE_INGAME = "RogueInGame";
    String SCREEN_ROGUE_PAUSE = "RoguePause";
    String SCREEN_ROGUE_GAME_OVER = "RogueGameOver";

    void onScreenChanged(String newScreen);

    void onDeath();

    void onExceptionThrown(String exception);

    void onAdFailedOrCancelled(String tag);

    void onClickedDiscordLink();

    void onClickedTwitterLink();

    void onClickedFacebookLink();

    void onClickedWebsiteLink();

    // these two events are used to set GA to manual handling during the ad playing, so onPause isn't triggered and it doesn't interrupt
    // the GA session
    void onAdIsGonnaStart();

    void onAdOver();

    void onIapBought(String currency, int amount, String itemType, String itemId, String signature);

    void onEnergySpent();
    void onInfiniteEnergyPreventedEnergyCost();

    void onRandomCosmeticUnlocked(String cosmeticID, String type, String name);

    // GOLD SOURCES (other than level complete)
    void onChallengeRankLevelUp(int reward, int newLevel);

    void onDailyGiftOpened(int reward, boolean throughAd);

    void onGoldBought(int amount);

    // GOLD SINKS
    void onRandomCosmeticBought(int cost);

    void onSetBought(int cost, String id, float percentOwnedBeforePurchase);

    void onInfiniteLivesBought(int cost, int nbHeartsBeforePurchase);

    void onChallengePassed(int challengeID, int cost);

    // HEARTS SOURCES

    void onHeartsEarnedThroughAd(int amount);

    void onHeartEarnedThroughTimer();

    void onRogueStarted();

    void onRogueEnded(int score, int stage, float timer);

    void onLevelStarted(int level, int stage);
    /**
     * If the level wasn't 3-stars, goldReward = -1 and the event gold earned shouldn't be sent
     */
    void onLevelCompleted(int level, int stage, float time, int nbStars, int goldReward);
    void onLevelFailed(int level, int stage);

    void onLevelCompletedWithRetriesCount(int levelCompleted, int playedStage, int nbRetriesForLevel);
    void onLevelThreeStarsWithRetriesCount(int levelCompleted, int playedStage, int nbThreeStarsRetriesForLevel);

    void onChallengeCompleted(int challengeId);

    void onShopOpenedBecauseNoMoney();

    void onShopOpenedManually();

    void onRevivedWithGold(int price);

    void onRevivedWithAd();

    void onEndlessShopRefreshed(int price);

    void onEarnedGoldWithAd();

    void onGameStarted();

    void update(float delta);

    void onExitedShop();

    void onEnteredShop();

    void onAdventureButtonClicked();

    void onHordeButtonClicked();

    void onEquipmentEquipped(String id, String type, String name);

    void onRewardedAdStarted();

    static AnalyticsManager NULL_INSTANCE() {
        return new AnalyticsManager() {
            @Override
            public void onScreenChanged(String newScreen) {

            }

            @Override
            public void onDeath() {

            }

            @Override
            public void onExceptionThrown(String exception) {

            }

            @Override
            public void onAdFailedOrCancelled(String tag) {

            }

            @Override
            public void onClickedDiscordLink() {

            }

            @Override
            public void onClickedTwitterLink() {

            }

            @Override
            public void onClickedFacebookLink() {

            }

            @Override
            public void onClickedWebsiteLink() {

            }

            @Override
            public void onAdIsGonnaStart() {

            }

            @Override
            public void onAdOver() {

            }

            @Override
            public void onIapBought(String currency, int amount, String itemType, String itemId, String signature) {

            }

            @Override
            public void onEnergySpent() {

            }

            @Override
            public void onInfiniteEnergyPreventedEnergyCost() {

            }

            @Override
            public void onRandomCosmeticUnlocked(String cosmeticID, String type, String name) {

            }

            @Override
            public void onChallengeRankLevelUp(int reward, int newLevel) {

            }

            @Override
            public void onDailyGiftOpened(int reward, boolean throughAd) {

            }

            @Override
            public void onGoldBought(int amount) {

            }

            @Override
            public void onRandomCosmeticBought(int cost) {

            }

            @Override
            public void onSetBought(int cost, String id, float percentOwnedBeforePurchase) {

            }

            @Override
            public void onInfiniteLivesBought(int cost, int nbHeartsBeforePurchase) {

            }

            @Override
            public void onChallengePassed(int challengeID, int cost) {

            }

            @Override
            public void onHeartsEarnedThroughAd(int amount) {

            }

            @Override
            public void onHeartEarnedThroughTimer() {

            }

            @Override
            public void onRogueStarted() {

            }

            @Override
            public void onRogueEnded(int score, int stage, float timer) {

            }

            @Override
            public void onLevelStarted(int level, int stage) {

            }

            @Override
            public void onLevelCompleted(int level, int stage, float time, int nbStars, int goldReward) {

            }

            @Override
            public void onLevelFailed(int level, int stage) {

            }

            @Override
            public void onLevelCompletedWithRetriesCount(int levelCompleted, int playedStage, int nbRetriesForLevel) {

            }

            @Override
            public void onLevelThreeStarsWithRetriesCount(int levelCompleted, int playedStage, int nbThreeStarsRetriesForLevel) {

            }

            @Override
            public void onChallengeCompleted(int challengeId) {

            }

            @Override
            public void onShopOpenedBecauseNoMoney() {

            }

            @Override
            public void onShopOpenedManually() {

            }

            @Override
            public void onRevivedWithGold(int price) {

            }

            @Override
            public void onRevivedWithAd() {

            }

            @Override
            public void onEndlessShopRefreshed(int price) {

            }

            @Override
            public void onEarnedGoldWithAd() {

            }

            @Override
            public void onGameStarted() {

            }

            @Override
            public void update(float delta) {

            }

            @Override
            public void onExitedShop() {

            }

            @Override
            public void onEnteredShop() {

            }

            @Override
            public void onAdventureButtonClicked() {

            }

            @Override
            public void onHordeButtonClicked() {

            }

            @Override
            public void onEquipmentEquipped(String id, String type, String name) {

            }

            @Override
            public void onRewardedAdStarted() {

            }
        };
    }
}
