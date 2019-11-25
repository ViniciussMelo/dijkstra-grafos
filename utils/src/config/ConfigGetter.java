package config;

import application.ApplicationContext;
import application.Principal;

import java.util.ArrayList;
import java.util.List;

public class ConfigGetter {

    private String separatorKeys = Config.separators.get("keys");
    private String separatorValues = Config.separators.get("values");

    private String line;
    private List<Config> configs;

    public ConfigGetter(String line) {
        this.line = line;
        this.configs = new ArrayList<>();
    }

    public ConfigGetter read() {
        String[] keys = line.split(separatorKeys);

        for (String keySplit : keys) {

            String key = getKey(keySplit);
            String value = getValue(keySplit);

            configs.add(new Config(value, key));
        }

        return this;
    }

    public List<Config> getConfigs() {
        return configs;
    }

    public ConfigGetter setInContext(ApplicationContext context) {
        for (Config config : configs) {

            switch (config.getKey()) {
                case Config.PATH_CONFIG: {
                    context.setPathConfig(config.getValue());
                    break;
                }

                case Config.PATH_ERROR: {
                    context.setPathErro(config.getValue());
                    break;
                }

                case Config.PATH_SUCCESS: {
                    context.setPathSucesso(config.getValue());
                    break;
                }
                
                case Config.IS_AUTOMATIC:{
                	context.setAutomatico(config.getValue().toLowerCase().equals("true"));
                    break;
                }
                
                case Config.PATH_ARQUIVOS:{
                	context.setPathArquivos(config.getValue());
                	break;
                }
            }

        }

        return this;
    }

    private String getKey(String keyValue) {
        String[] key = keyValue.split(separatorValues);
        return key[0];
    }

    private String getValue(String keyValue) {
        String[] key = keyValue.split(separatorValues);
        return key[1];
    }

    public static void main(String[] s) {
        StringBuilder cof = new StringBuilder();

        cof.append(Config.PATH_SUCCESS)
                .append(Config.separators.get("values"))
                .append("Success")
                .append(Config.separators.get("keys"));

        cof.append(Config.PATH_ERROR)
                .append(Config.separators.get("values"))
                .append("Error")
                .append(Config.separators.get("keys"));

        ConfigGetter getter = new ConfigGetter(cof.toString())
                .read()
                .setInContext(Principal.context);

        System.out.println(Principal.context.getPathSucesso() + " " + Principal.context.getPathErro());
    }
}
