import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import Memoria.Memoria;

public class Assembler {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private Memoria memoria;
	private Processador processador;

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
		processador = new Processador();
		memoria = new Memoria();
		initialize();
	}

	/**
	 * Devolve o OPCODE para dada instrução, para ser guardado na memoria
	 * 
	 * @return opCode
	 */
	public String getOpCode(String instrucao) {
		String opCode;
		switch (instrucao) {
		case "mov":
			opCode = "0";
			break;
		case "add":
			opCode = "1";
			break;
		case "sub":
			opCode = "2";
			break;
		case "mul":
			opCode = "3";
			break;
		case "div":
			opCode = "4";
		case "jmp":
			opCode = "5";
			break;
		case "ax":
			opCode = "6";
			break;
		case "[ax]":
			opCode = "0x6";
			break;
		case "bx":
			opCode = "0x7";
		case "[bx]":
			opCode = "0x7";
			break;
		case "cx":
			opCode = "8";
		case "[cx]":
			opCode = "0x8";
			break;
		case "dx":
			opCode = "9";
			break;
		case "[dx]":
			opCode = "x9";
			break;

		default:
			opCode = "";
			break;
		}
		
		//Pra ver se dentro da string tem um inteiro
//		matches(".*\\d+.*")

		return opCode;
	}

	/**
	 * Retorna em um vetor a instrução e os operadores separadamentes
	 * resultado[0]: instrução; resultado[1]: op1; resultado[2]: op2
	 * 
	 * @param comando
	 * @return
	 */
	public String[] getInsOp1Op2(String comando) {
		String[] resultado = new String[3];
		int i = 0;
		while (i < comando.length()) {

			resultado[0] = comando.split(" ")[0];

			// Se tiver uma vírgula, ou seja, se houverem 2 operadores
			if (comando.indexOf(',') > 0) {
				resultado[1] = comando.split(" ")[1].split(",")[0];
				resultado[2] = comando.split(" ")[1].split(",")[1];
			} else {
				resultado[1] = comando.split(" ")[1];
				resultado[2] = null;
			}
			i++;
		}
		
		System.out.println("comando: "+resultado[0]+" "+resultado[1]+" "+resultado[2]);
		
		return resultado;
	}

	/**
	 * Retorna uma String com a concatenação dos opCodes da intrução e dos
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
	 * Adiciona uma nova linha na 1ª posição da tabela
	 * 
	 * @param data
	 */
	public void addRow(Object[] data) {
		model.insertRow(0, data);
		table = new JTable(model);
	}

	/**
	 * Adiciona as linhas de codigo a partir do endereço da memoria especificado
	 * 
	 * @param linhasComando
	 * @param enderecoInicial
	 */
	public void addLinhasMemoria(String[] linhasComando, int enderecoInicial) {

		String[] componentesLinha;

		// Cada linha é adicionada à memória
		for (int i = 0; i < linhasComando.length; i++) {

			componentesLinha = getInsOp1Op2(linhasComando[i]);

			memoria.put(enderecoInicial+i, concatInstrucao(componentesLinha));
		}
	}

	/**
	 * Inicializa os componentes do Frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 936, 631);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 11, 556, 269);
		frame.getContentPane().add(scrollPane);

		String[] columnNames = { "PC", "MAR", "MBR", "IR", "ax", "bx", "cx",
				"dx", "==0", "overf", "null" };
		Object[][] data = { {} };

		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.setEnabled(false);
		scrollPane.setViewportView(table);

		JTextArea textArea = new JTextArea();
		
		// O que acontece quando "F9" é apertado
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F9) {

					// Cria um vetor onde cada posição é uma linha do bloco de
					// codigo
					String[] linhasComando = textArea.getText().split(
							"\r\n|\r|\n");
					
					//Coloca na memoria as linhas de codigo digitadas
					addLinhasMemoria(linhasComando, 0);
					memoria.printMemoria();
					//processador.startProcess()
				
				}
			}
		});
		textArea.setBounds(10, 11, 334, 269);
		frame.getContentPane().add(textArea);
	}
}
