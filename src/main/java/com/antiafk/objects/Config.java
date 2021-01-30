package com.antiafk.objects;

public class Config {
    private long secondsToKick;

    public long getSecondsToKick() {
        return this.secondsToKick;
    }
    public void setSecondsToKick(long secondsToKick) {
        this.secondsToKick = secondsToKick;
    }
    public static Config getDefault(){
        Config config = new Config();
        config.setSecondsToKick(180);
        return config;
    }
}
