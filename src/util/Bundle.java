package util;

import java.util.HashMap;

public class Bundle {
    private HashMap<String, Object> map;

    private boolean isEmpty;

    public Bundle() {
        map = new HashMap<>();
        isEmpty = false;
    }

    private Bundle(boolean empty) {
        this();
        isEmpty = true;
    }

    public static Bundle emptyBundle(){
        return new Bundle(true);
    }

    public Bundle put(String key, Object obj) {
        map.put(key, obj);
        return this;
    }

    public <T> T get(String key) {
        return (T) map.get(key);
    }

    public int getSize() {
        return map.size();
    }

    public void clear(){
        map.clear();
    }
}