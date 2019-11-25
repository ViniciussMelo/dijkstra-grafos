package config;

import java.util.HashMap;

public class Config {

    public final static String PATH_ERROR = "PATH_ERROR";
    public final static String PATH_SUCCESS = "PATH_SUCCESS";
    public final static String PATH_CONFIG = "PATH_CONFIG";
    public final static String IS_AUTOMATIC = "IS_AUTOMATIC";
    public final static String PATH_ARQUIVOS = "PATH_ARQUIVOS";
    public final static HashMap<String, String> separators = new HashMap<>();

    private String value;
    private String key;

    static {
        separators.put("values", "=");
        separators.put("keys", ";");
    }

    public Config() {
    }

    public Config(String value, String key) {
        this.value = value;
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
