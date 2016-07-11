import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Assembler {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JTable table2;
	private Processador processador;
	private JTextArea textAreaLog;
	
	private Assembler mySelf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assembler window = new Assembler();
					// window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Assembler() {
		processador = new Processador(this);
		mySelf = this;
		initialize();
	}

	public String completaZerosEsquerda(String s){
		int tamBitsOperand = 12;
		String retorno = "";
		
		int lim = tamBitsOperand - s.length();
		char[] aux = new char[tamBitsOperand];
		
		for (int i = 0; i < lim; i++) {
			aux[i] = '0';
		}
		
		for (int k = 0; k < s.length(); k++) {
			aux[lim+k] = s.charAt(k);
		}
		
		retorno = String.valueOf(aux);
		return retorno;
	}

	/**
	 * Devolve o OPCODE para dada instru��o, para ser guardado na memoria
	 * 
	 * @return opCode
	 */
	public String getOpCode(String instrucao) {
		String opCode = "";
		String aux;
		switch (instrucao) {
		case "ax":
			opCode = "10000000000000";
			break;
		case "[ax]":
			opCode = "11000000000000";
			break;
		case "bx":
			opCode = "10000000000001";
			break;
		case "[bx]":
			opCode = "11000000000001";
			break;
		case "cx":
			opCode = "10000000000010";
			break;
		case "[cx]":
			opCode = "11000000000010";
			break;
		case "dx":
			opCode = "10000000000011";
			break;
		case "[dx]":
			opCode = "11000000000011";
			break;
		case "mov":
			opCode = "0100";
			break;
		case "add":
			opCode = "0101";
			break;
		case "sub":
			opCode = "0110";
			break;
		case "mul":
			opCode = "0111";
			break;
		case "div":
			opCode = "1000";
		case "jmp":
			opCode = "1001";
			break;

		default:
			break;
		}

		// Se tiver um numero na instru��o
		if (instrucao.matches(".*\\d+.*")) {
			// Se for uma indire��o
			if (instrucao.contains("[")) {
				aux = "01";
				opCode = aux.concat(completaZerosEsquerda(Integer.toBinaryString(Integer
						.parseInt(instrucao.substring(1, 2)))));
			} else {
				aux = "00";
				opCode = aux.concat(completaZerosEsquerda(Integer.toBinaryString(Integer
						.parseInt(instrucao))));
			}
		}

		// Pra ver se dentro da string tem um inteiro
		// matches(".*\\d+.*")
		return opCode;
	}

	/**
	 * Retorna em um vetor a instru��o e os operadores separadamentes
	 * resultado[0]: instru��o; resultado[1]: op1; resultado[2]: op2
	 * 
	 * @param comando
	 * @return
	 */
	public String[] getInsOp1Op2(String comando) {
		String[] resultado = new String[3];
		int i = 0;
		while (i < comando.length()) {

			resultado[0] = comando.split(" ")[0];

			// Se tiver uma v�rgula, ou seja, se houverem 2 operadores
			if (comando.indexOf(',') > 0) {
				resultado[1] = comando.split(" ")[1].split(",")[0];
				resultado[2] = comando.split(" ")[1].split(",")[1];
			} else {
				resultado[1] = comando.split(" ")[1];
				resultado[2] = null;
			}
			i++;
		}

		return resultado;
	}

	/**
	 * Retorna uma String com a concatena��o dos opCodes da intru��o e dos
	 * operadores
	 * 
	 * @param componentesLinha
	 * @return
	 */
	public String concatInstrucao(String[] componentesLinha) {

		return getOpCode(componentesLinha[0]).concat(
				getOpCode(componentesLinha[1]).concat(
						getOpCode(componentesLinha[2])));
	}

	/**
	 * Adiciona uma nova linha na 1� posi��o da tabela
	 * 
	 * @param data
	 */
	public void addRow(Object[] data) {
		model.insertRow(0, data);
		table = new JTable(model);
	}

	/**
	 * Adiciona as linhas de codigo a partir do endere�o da memoria especificado
	 * 
	 * @param linhasComando
	 * @param enderecoInicial
	 */
	public void addLinhasMemoria(String[] linhasComando, int enderecoInicial) {

		String[] componentesLinha;

		// Cada linha � adicionada � mem�ria
		for (int i = 0; i < linhasComando.length; i++) {

			componentesLinha = getInsOp1Op2(linhasComando[i]);

			processador.memoria.put(enderecoInicial + i, concatInstrucao(componentesLinha));
		}
	}

	/**
	 * Inicializa os componentes do Frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(false);
		frame.setBounds(100, 100, 936, 631);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 11, 556, 269);
		frame.getContentPane().add(scrollPane);

		String[] columnNames = { "PC", "MAR", "MBR", "IR", "ax", "bx", "cx",
				"dx", "==0", "<>0", ">=0", ">0", "<=0", "<0" };

		model = new DefaultTableModel();
		table = new JTable(model);
		model.setColumnIdentifiers(columnNames);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 300, 556, 269);
		frame.getContentPane().add(scrollPane);

		String[] columnNamesPortas = { "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" };

		model2 = new DefaultTableModel();
		table2 = new JTable(model2);
		model2.setColumnIdentifiers(columnNamesPortas);
		table2.setEnabled(false);
		scrollPane.setViewportView(table2);
		

		JTextArea textArea = new JTextArea();
		
		textAreaLog = new JTextArea();
		textAreaLog.setEditable(false);
		textAreaLog.setBounds(10, 300, 334, 269);
		frame.getContentPane().add(textAreaLog);

		// O que acontece quando "F9" � apertado
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {
					Log.inicializaLog(mySelf);

					// Cria um vetor onde cada posi��o � uma linha do bloco de
					// codigo
					String[] linhasComando = textArea.getText().split(
							"\r\n|\r|\n");

					// Coloca na memoria as linhas de codigo digitadas
					addLinhasMemoria(linhasComando, 0);
					processador.memoria.printMemoria();
				}
			}
		});
		
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F6) {
					processador.cicloInstrucao();
				}
			}
		});
		textArea.setBounds(10, 11, 334, 269);
		frame.getContentPane().add(textArea);
	}
	
	public void atualizaTabela(){
		String[] data = new String[14];
				
		data[0] = processador.getPc().getConteudo();
		data[1] = processador.getMar().getConteudo();
		data[2] = processador.getMbr().getConteudo();
		data[3] = processador.getIr().getConteudo();
		data[4] = processador.getAx().getConteudo();
		data[5] = processador.getBx().getConteudo();
		data[6] = processador.getCx().getConteudo();
		data[7] = processador.getDx().getConteudo();
		//==0
		data[8] = String.valueOf(processador.getfIgual().isFlagValue()? 1 : 0);
		//<>0
		data[9] = String.valueOf(processador.getfDiferente().isFlagValue()? 1 : 0);
		//>=0
		data[10] = String.valueOf(processador.getfMaiorOuIgual().isFlagValue()? 1 : 0);
		//>0
		data[11] = String.valueOf(processador.getfMaior().isFlagValue()? 1 : 0);
		//<=0
		data[12] = String.valueOf(processador.getfMenorOuIgual().isFlagValue()? 1 : 0);
		//<0
		data[13] = String.valueOf(processador.getfMenor().isFlagValue()? 1 : 0);
		
		model.addRow(data);
		model.fireTableDataChanged();
		
	}
	
	public void atualizaTabelaPortas(){
		String[] data = new String[19];
		
		data[0] = String.valueOf(processador.getPc().isEntradaAberta()? 1 : 0);
		data[1] = String.valueOf(processador.getPc().isSaidaAberta()? 1 : 0);
		
		data[2] = String.valueOf(processador.getMar().isEntradaAberta()? 1 : 0);
		
		data[3] = String.valueOf(processador.getMbr().isEntradaAberta()? 1 : 0);
		data[4] = String.valueOf(processador.getMbr().isSaidaAberta()? 1 : 0);
		
		data[5] = String.valueOf(processador.getAx().isEntradaAberta()? 1 : 0);
		data[6] = String.valueOf(processador.getAx().isSaidaAberta()? 1 : 0);
		
		data[7] = String.valueOf(processador.getBx().isEntradaAberta()? 1 : 0);
		data[8] = String.valueOf(processador.getBx().isSaidaAberta()? 1 : 0);
		
		data[9] = String.valueOf(processador.getCx().isEntradaAberta()? 1 : 0);
		data[10] = String.valueOf(processador.getCx().isSaidaAberta()? 1 : 0);
		
		data[11] = String.valueOf(processador.getDx().isEntradaAberta()? 1 : 0);
		data[12] = String.valueOf(processador.getDx().isSaidaAberta()? 1 : 0);
		
		data[13] = String.valueOf(processador.getIr().isEntradaAberta()? 1 : 0);
		data[14] = String.valueOf(processador.getIr().isSaidaAberta()? 1 : 0);
		
		//data[15] = String.valueOf(processador.getULA().isEntradaAberta());
		
		//data[16] = String.valueOf(processador.getX().isEntradaAberta());
		
		//data[17] = String.valueOf(processador.getAc().isSaidaAberta());
		
		//data[18] = String.valueOf(processador.getMemoria().isEntradaAberta());
		//data[19] = String.valueOf(processador.getMemoria().isSaidaAberta());
		
		
		model2.addRow(data);
		model2.fireTableDataChanged();
		
	}

	public JTextArea getTextAreaLog() {
		return textAreaLog;
	}
	
	public void atualizaTextAreLog(String[] s){
		
		textAreaLog.setText("");
		for(String str : s){
			textAreaLog.append(str + "\n");
		}
	}
	
	public void atualizaTextAreLog(String s){
		textAreaLog.append(s);
	}
}
