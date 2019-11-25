package config;

public class ConfigBuilder {

    private StringBuilder configs;

    public ConfigBuilder() {
        configs = new StringBuilder();
    }

    public ConfigBuilder addConfig(String key, String value) {
        configs.append(key)
                .append(Config.separators.get("values"))
                .append(value)
                .append(Config.separators.get("keys"));

        return this;
    }

    public ConfigBuilder addConfig(Config config) {
        configs.append(config.getKey())
                .append(Config.separators.get("values"))
                .append(config.getValue())
                .append(Config.separators.get("keys"));

        return this;
    }

    public String toString() {
        return configs.toString();
    }
}
