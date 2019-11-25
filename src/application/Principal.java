package application;

import config.ConfigGetter;
import file.FileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Principal {

    public final static String PATH_CONFIG_FILE = "resources/config.txt";
    public final static ApplicationContext context = new ApplicationContext();

    public Principal() {
        loadConfigFromFile();
        criarTrayIcon();
        context.setPathConfig(PATH_CONFIG_FILE);
    }

    private void criarTrayIcon() {
        SystemTray systemTray = SystemTray.getSystemTray();
        PopupMenu popupMenu = new PopupMenu();
        TrayIcon trayIcon = new TrayIcon(
                new ImageIcon("icone.png").getImage(),
                "Sistema de menor caminho",
                popupMenu
        );

        MenuItem menuItemSair = new MenuItem("Sair");
        menuItemSair.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        MenuItem menuItemVisivel = new MenuItem("Visivel");
        menuItemVisivel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
					new VisivelWindow().setVisible(true);
				} catch (IOException e1) {
					
				}
            }
        });

        MenuItem menuItemConfiguracao = new MenuItem("Configuracao");
        menuItemConfiguracao.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConfiguracaoWindow(menuItemVisivel).setVisible(true);
            }
        });

        popupMenu.add(menuItemConfiguracao);
        /* Mexer no popupMenu.add(menuItemVisivel); / popupMenu.addSeparator(); */
        	popupMenu.addSeparator();
            popupMenu.add(menuItemVisivel); 
        	popupMenu.addSeparator();
            popupMenu.add(menuItemSair);

            menuItemVisivel.setEnabled(!context.isAutomatico());

        try {
            systemTray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void loadConfigFromFile() {
        try {
            FileReader fileConfig = new FileReader(Principal.PATH_CONFIG_FILE);
            String line = fileConfig.getLine(0);

            new ConfigGetter(line)
                    .read()
                    .setInContext(Principal.context);
        } catch (IOException e) {
            System.out.println("[INFO] Arquivo de configuracao nao criado.");
        }
    }

    public static void main(String[] args) {
        new Principal();
    }
}
