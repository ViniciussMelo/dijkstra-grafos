package application;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ConfiguracaoWindow extends JDialog {

    JLabel labelPasta, labelSucesso, labelError;
    JTextField fieldPasta, fieldSucesso, fieldError;
    JCheckBox checkBoxAutomatico;
    JButton buttonSalvar;

    public ConfiguracaoWindow() {
        setTitle("Configuracao do sistema");
        setSize(250, 250);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setLocationRelativeTo(null);
        criarComponentes();
        adicionarAtionListener();
    }

    private void criarComponentes() {
        labelPasta = new JLabel("Pasta: ");
        labelPasta.setBounds(10, 10, 50, 25);
        getContentPane().add(labelPasta);

        fieldPasta = new JTextField();
        fieldPasta.setBounds(70, 10, 160, 25);
        fieldPasta.setText(Principal.context.getPathConfig());
        getContentPane().add(fieldPasta);


        labelError = new JLabel("Error: ");
        labelError.setBounds(10, 45, 50, 25);
        getContentPane().add(labelError);

        fieldError = new JTextField();
        fieldError.setBounds(70, 45, 160, 25);
        fieldError.setText(Principal.context.getPathErro());
        getContentPane().add(fieldError);


        labelSucesso = new JLabel("Sucesso: ");
        labelSucesso.setBounds(10, 80, 60, 25);
        getContentPane().add(labelSucesso);

        fieldSucesso = new JTextField();
        fieldSucesso.setBounds(70, 80, 160, 25);
        fieldSucesso.setText(Principal.context.getPathSucesso());
        getContentPane().add(fieldSucesso);


        checkBoxAutomatico = new JCheckBox("Automatico?");
        checkBoxAutomatico.setBounds(75, 115, 160, 25);
        checkBoxAutomatico.setSelected(Principal.context.isAutomatico());
        getContentPane().add(checkBoxAutomatico);

        buttonSalvar = new JButton("Salvar");
        buttonSalvar.setBounds(7, 150, 230, 25);
        getContentPane().add(buttonSalvar);
    }

    private void adicionarAtionListener() {
        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationContext context = Principal.context;

                context.setAutomatico(checkBoxAutomatico.isSelected());
                context.setPathConfig(fieldPasta.getText());
                context.setPathErro(fieldError.getText());
                context.setPathSucesso(fieldSucesso.getText());
                
                try {
					context.recordPath();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao gravar caminho: \n"+e1.getMessage());
				}
            }
        });
    }

    public static void main(String[] args) {
        new ConfiguracaoWindow().setVisible(true);
    }

}
