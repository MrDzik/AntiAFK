package com.antiafk.objects;

public class Config {
    private long timeToKick;

    public long getTimeToKick() {
        return this.timeToKick;
    }
    public void setTimeToKick(long timeToKick) {
        this.timeToKick = timeToKick;
    }
    public static Config getDefault(){
        Config config = new Config();
        config.setTimeToKick(180);
        return config;
    }
}
