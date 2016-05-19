import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Interface {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<String> memoria = new ArrayList<String>();
	private IR ir = new IR();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					//window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
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
	 * IR <== MBR
	 * @param comando
	 */
	public void setIR(String comando){
		int i = 0;
		while(i < comando.length()){
			
			ir.setInstrucao(comando.split(" ")[0]);
			
			//Se tiver uma vírgula, ou seja, se houverem 2 operadores
			if(comando.indexOf(',') > 0){
				ir.setOp1(comando.split(" ")[1].split(",")[0]);
				ir.setOp2(comando.split(" ")[1].split(",")[1]);
			}else{
				ir.setOp1(comando.split(" ")[1]);
				ir.setOp2(null);
			}
			i++;
		}
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
		textArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_F9){
					String linhas[] = textArea.getText().split("\r\n|\r|\n");
					
					for (int i = 0; i < linhas.length; i++) {
						memoria.add(linhas[i]);
					}
					System.out.println(memoria);
					
					setIR(memoria.get(1));
					
					System.out.println(ir.getInstrucao());
				}
			}
		});
		textArea.setBounds(10, 11, 334, 453);
		frame.getContentPane().add(textArea);
	}
}
