package application;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.sun.xml.internal.bind.CycleRecoverable.Context;

import file.FileReader;

public class VisivelWindow extends JDialog{
	public final static String PATH_WAY_FILE = "resources/rotas.txt";
	
	JLabel lblBusca, lblCodOrigem, lblCidOrigem, lblCodDestino, lblCidDestino, lblDistancia, lblOrigem, lblDestino;
	JTextField fieldBusca, fieldCodOrigem, fieldCidOrigem, fieldCodDestino, fieldCidDestino, fieldDistancia;
	JButton buttonPesqusiar, buttonAdicionar, buttonSalvar, buttonProcessar;
	JTable table;
    
	String caminhoArquivo = "";

	public VisivelWindow() throws IOException {
		setTitle("Dijsktra - Busca por melhor caminho");
		setSize(800,690);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setModal(true);
		setLocationRelativeTo(null);
		criarComponentes();
		adicionarActionListener();
	}
	
	private void criarComponentes() throws IOException {
		// ------------- Busca ---------------- \\
		lblBusca = new JLabel("Buscar ");
		lblBusca.setBounds(10, 10, 50, 25);
		getContentPane().add(lblBusca);
		
		fieldBusca = new JTextField();
		fieldBusca.setBounds(70, 10, 300, 25);
		fieldBusca.enable(false);
		getContentPane().add(fieldBusca);
		
		buttonPesqusiar = new JButton("Buscar");
		buttonPesqusiar.setBounds(375, 10, 100, 25);
		getContentPane().add(buttonPesqusiar);
		// ---------------- -------------------- \\	
		
		// ------------- Origem ---------------- \\
		lblCodOrigem = new JLabel("C�digo: ");
		lblCodOrigem.setBounds(10, 70, 50, 25);
		getContentPane().add(lblCodOrigem);
		
		fieldCodOrigem = new JTextField();
		fieldCodOrigem.setBounds(70, 70, 80, 25);
		getContentPane().add(fieldCodOrigem);
		
		lblCidOrigem = new JLabel("Cidade: ");
		lblCidOrigem.setBounds(170, 70, 50, 25);
		getContentPane().add(lblCidOrigem);
		
		fieldCidOrigem = new JTextField();
		fieldCidOrigem.setBounds(230, 70, 180, 25);
		getContentPane().add(fieldCidOrigem);
		
		lblOrigem = new JLabel("(ORIGEM)");
		lblOrigem.setBounds(420, 70, 100, 25);
		getContentPane().add(lblOrigem);		
		// ---------------- -------------------- \\		
		
		// ------------- Destino ---------------- \\
		lblCodDestino = new JLabel("C�digo: ");
		lblCodDestino.setBounds(10, 120, 50, 25);
		getContentPane().add(lblCodDestino);
		
		fieldCodDestino = new JTextField();
		fieldCodDestino.setBounds(70, 120, 80, 25);
		getContentPane().add(fieldCodDestino);
		
		lblCidDestino = new JLabel("Cidade: ");
		lblCidDestino.setBounds(170, 120, 120, 25);
		getContentPane().add(lblCidDestino);
		
		fieldCidDestino = new JTextField();
		fieldCidDestino.setBounds(230, 120, 180, 25);
		getContentPane().add(fieldCidDestino);
		
		lblDestino = new JLabel("(DESTINO)");
		lblDestino.setBounds(420, 120, 100, 25);
		getContentPane().add(lblDestino);		
		// ---------------- -------------------- \\
		
		// ------------- Dist�ncia ---------------- \\
		lblDistancia = new JLabel("KM:");
		lblDistancia.setBounds(30, 170, 50, 25);
		getContentPane().add(lblDistancia);
		
		fieldDistancia = new JTextField();
		fieldDistancia.setBounds(70, 170, 50, 25);
		getContentPane().add(fieldDistancia);		
		// ---------------- -------------------- \\
		
		// ------------- Adicionar ---------------- \\
		buttonAdicionar = new JButton("+");
		buttonAdicionar.setBounds(725, 170, 50, 25);
		getContentPane().add(buttonAdicionar);
		// ---------------- -------------------- \\
		
		// ------------- Tabela ---------------- \\
		criaTabela();
		// ---------------- -------------------- \\
		
		// ------------- Footer ---------------- \\
		buttonSalvar = new JButton("SALVAR");
		buttonSalvar.setBounds(665, 620, 110, 25);
		getContentPane().add(buttonSalvar);
		
		buttonProcessar = new JButton("PROCESSAR");
		buttonProcessar.setBounds(545, 620, 110, 25);
		getContentPane().add(buttonProcessar);
		// ---------------- -------------------- \\
	}
	
	
	private void criaTabela() throws IOException {
		table = new JTable();
		JScrollPane js = new JScrollPane(table);
		js.setBounds(10, 210, 765, 405);
		getContentPane().add(js);
		configuraTabela();
	}
	
	private void configuraTabela() throws IOException {
		String [] colunas = {"C�digo Origem", "Cidade Origem", "C�digo Destino", "Cidade Destino", "Dist�ncia"};
		DefaultTableModel modelo = new DefaultTableModel();
		
		for (int i = 0; i < colunas.length; i++) {
			modelo.addColumn(colunas[i]);
		}
		table.setModel(modelo);
	}
	
	private void adicionarActionListener() {
		buttonAdicionar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String codOrigem, cidOrigem, codDestino, cidDestino, distancia;
				ArrayList<String> campos = new ArrayList<String>();
				ArrayList<String> camposNumericos = new ArrayList<String>();
				
				codOrigem  = fieldCodOrigem.getText();
				cidOrigem  = fieldCidOrigem.getText();
				codDestino = fieldCodDestino.getText();
				cidDestino = fieldCidDestino.getText();
				distancia  = fieldDistancia.getText();
				
				//Passar os campos obrigat�rios para um arraylist
				campos.add(codOrigem);
				campos.add(cidOrigem);
				campos.add(codDestino);
				campos.add(cidDestino);
				campos.add(distancia);
				
				//Passar os campos num�ricos para um arraylist
				camposNumericos.add(codOrigem);
				camposNumericos.add(codDestino);
				camposNumericos.add(distancia);				
				
				if(!verificaCamposNumericos(camposNumericos)){
					JOptionPane.showMessageDialog(null, "Os campos 'c�digo' e 'dist�ncia' s� aceitam n�meros!'");
				}else if(!verificaCamposObrigatorios(campos)){
					JOptionPane.showMessageDialog(null, "Deve-se preencher todos os campos!");					
				}else {
					Object[] obj = {codOrigem, cidOrigem , codDestino, cidDestino, distancia};
					inserirElemento(obj);
					limpaCampos();
				}
			}
		});
		
		//SALVAR
		buttonSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				int sizeTabela = modelo.getDataVector().size();
				if(sizeTabela > 0) {
					try {
						gravaDadosTabela(modelo, sizeTabela);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Erro ao gravar rotas! \n"+ e1.getMessage());
					}
				}			
			}
		});
		
		//PROCESSAR
		buttonProcessar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		//PESQUISAR
		buttonPesqusiar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfPastas = new JFileChooser(Principal.context.getPathArquivos());
				jfPastas.setDialogTitle("Importar arquivos");
				jfPastas.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfPastas.showOpenDialog(null);

				File arquivo = jfPastas.getSelectedFile();
				caminhoArquivo = arquivo.toString();

				fieldBusca.setText(caminhoArquivo);

				try {
					insereDadosTabela(arquivo);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao carregar os dados na tabela!\n"+e1.getMessage());
				}
			}
		});
	}
	
	public void gravaDadosTabela(DefaultTableModel modelo, int sizeTabela) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		String texto;
		int maxNum = 0;

		for (int i = 0; i < sizeTabela; i++) {
			texto = "";
			for (int j = 0; j < modelo.getColumnCount(); j++) {
				if("".equals(texto)) {
					texto = (String) modelo.getValueAt(i, j);
				}
				else {
					texto += ";" + (String) modelo.getValueAt(i, j);;
				}
			}
			list.add(texto);
		}
		if("".equals(caminhoArquivo)) {
			File teste = new File(Principal.context.getPathArquivos());
			String[] arquivos = teste.list();

			if(arquivos.length > 0) {
				ArrayList<Integer> arqRotas = new ArrayList<Integer>();

				for (int i = 0; i < arquivos.length; i++) {
					if(arquivos[i].contains("rota")) {
						arqRotas.add(Integer.parseInt(arquivos[i].substring(arquivos[i].indexOf("-")+1, arquivos[i].indexOf("."))));
					}
				}

				if(arqRotas.size() > 0) {
					arqRotas.sort(null);
					maxNum = arqRotas.get(arqRotas.size() - 1) + 1;
				}
				else {
					maxNum = 0;
				}

				caminhoArquivo = Principal.context.getPathArquivos() +"\\rota-"+ maxNum + ".txt";
			}
			else {
				caminhoArquivo = Principal.context.getPathArquivos() +"\\rota-0.txt";
			}
		}
		Principal.context.recordWay(list, caminhoArquivo);
	}
	
	public void limpaCampos() {
		fieldCodOrigem.setText("");
		fieldCidOrigem.setText("");
		fieldCodDestino.setText("");
		fieldCidDestino.setText("");
		fieldDistancia.setText("");
	}
	
	public boolean verificaCamposObrigatorios(ArrayList<String> campos) {
		boolean retorno = true;
		for (int i = 0; i < campos.size(); i++) {
			if(campos.get(i).length() == 0) {
				retorno = false;
				break;
			}
		}
		return retorno;
	}
	
	public void inserirElemento(Object[] obj) {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.addRow(obj);
	}
	
	public boolean verificaCamposNumericos(ArrayList<String> camposNumericos) {
		boolean retorno = true;
		for (int i = 0; i < camposNumericos.size(); i++) {
			if(!isValidNumber(camposNumericos.get(i))) {
				retorno = false;
			}
		}	
		return retorno;
	}
    
    public boolean isValidNumber(String texto) {
    	boolean retorno = true;
    	
    	if(texto.length() != 0) {
    		try {
    			int valor = Integer.parseInt(texto);
    		}catch(NumberFormatException nfe){
    			retorno = false;
    		}
    	}
    	return retorno;
    }
	
    public void insereDadosTabela(File arq) throws IOException {
    	FileReader file = new FileReader(arq.toString());
    	ArrayList<String> list = new ArrayList<String>();
    	DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		String[] array = new String[5];
		String codOrigem, cidOrigem, codDestino, cidDestino, distancia;

		list = file.getAllLines();

		for (int i = 0; i < modelo.getRowCount(); i++) {
			modelo.removeRow(i);
		}

		for (int i = 0; i < list.size(); i++) {
			array = list.get(i).split(";");
			if(array.length == 5) {
				codOrigem  = array[0];
				cidOrigem  = array[1];
				codDestino = array[2];
				cidDestino = array[3];
				distancia  = array[4];
				modelo.addRow(new Object[] {codOrigem, cidOrigem, codDestino, cidDestino, distancia});
			}
		}
    }

	public static void main(String[] args) throws IOException {
        new VisivelWindow().setVisible(true);
    }
}
