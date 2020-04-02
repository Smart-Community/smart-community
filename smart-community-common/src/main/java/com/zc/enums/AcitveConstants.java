package com.zc.enums;

/**
 * @author 小帅气
 * @create 2020-03-30-19:56
 */
public enum AcitveConstants {
    ACTIVE_KEY("SM-Active:"), JOIN_KEY("JOIN"), LOCK_KEY("LOCK"), NUM_KEY("NUM");

    private String key;

    AcitveConstants(String s) {
        this.key = s;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
