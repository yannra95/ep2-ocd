import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Interface {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;
	private String instrucao;
	private String op1;
	private String op2;
	private DefaultTableModel model;
	private ArrayList<String> memoria = new ArrayList<String>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
	public Interface() {
		initialize();
	}
	
	/**
	 * Posição 0 do vetor: instrução
	 * Posição 1 do vetor: 1º operador
	 * Posição 2 do vetor: 2º operador
	 * @param comando
	 */
	public void getComando(String comando){
		instrucao = comando.split(" ")[0];
		op1 = comando.split(" ")[1].split(",")[0];
		op2 = comando.split(" ")[1].split(",")[1];
	}
	
	/**
	 * Adiciona uma nova linha na 1ª posição da tabela
	 * @param data
	 */
	public void addRow(Object[] data){
		model.insertRow(0, data);		
		table = new JTable(model);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(100, 100, 936, 514);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 11, 556, 453);
		frame.getContentPane().add(scrollPane);

		String[] columnNames = { "PC", "MAR", "MBR", "IR", "ax", "bx", "cx", "dx", "==0", "overf", "null" };
		Object[][] data = {{}};

		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 11, 334, 422);
		frame.getContentPane().add(textArea);
		
		JButton btnExecutar = new JButton("Executar");
		btnExecutar.setBounds(20, 441, 89, 23);
		frame.getContentPane().add(btnExecutar);
	}
}
