package application;

import config.Config;
import config.ConfigBuilder;
import file.FileReader;
import file.FileWrite;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;

public class ApplicationContext {

    private boolean automatico = false;
    private String pathErro = "";
    private String pathSucesso = "";
    private String pathConfig = "";
    private String pathArquivos = "";

    public boolean isAutomatico() {
        return automatico;
    }

    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }

    public String getPathErro() {
        return pathErro;
    }

    public void setPathErro(String pathErro) {
        this.pathErro = pathErro;
    }

    public String getPathSucesso() {
        return pathSucesso;
    }

    public void setPathSucesso(String pathSucesso) {
        this.pathSucesso = pathSucesso;
    }

    public String getPathConfig() {
        return pathConfig;
    }

    public void setPathConfig(String pathConfig) {
        this.pathConfig = pathConfig;
    }
    
    public void setPathArquivos(String pathArquivos) {
    	this.pathArquivos = pathArquivos;
    }
    
    public String getPathArquivos() {
    	return this.pathArquivos;
    }
    
    public void recordPath() throws IOException {
    	FileWrite file = new FileWrite(pathConfig);
    	String config = new ConfigBuilder()
                .addConfig(Config.PATH_CONFIG, pathConfig)
                .addConfig(Config.PATH_SUCCESS, pathSucesso)
                .addConfig(Config.PATH_ERROR, pathErro)
                .addConfig(Config.IS_AUTOMATIC, isAutomatico() ? "true" : "false")
                .addConfig(Config.PATH_ARQUIVOS, pathArquivos)
                .toString();

    	file.write(config);
    }
    
    public void recordWay(ArrayList<String> list, String path) throws IOException {
    	FileWrite file = new FileWrite(path);
    	//Limpar o arquivo e inserir os dados
    	file.write("");
    	
    	for (int i = 0; i < list.size(); i++) {
			file.append(list.get(i));
			file.append("\n");
		}
    }
}
