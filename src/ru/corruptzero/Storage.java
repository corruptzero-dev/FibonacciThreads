package ru.corruptzero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Storage {
    private static Storage INSTANCE = null;
    private long ms;
    private final Map<Integer, Long> msMap = new HashMap<>();

    public static Storage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Storage();
        }
        return INSTANCE;
    }

    public long getMs() {
        return ms;
    }

    public void setMs(long ms) {
        this.ms = ms;
    }

    public void addToMsMap(int i, long ms){
        msMap.put(i,ms);
    }

    public Map<Integer, Long> getMsMap() {
        return msMap;
    }
}
