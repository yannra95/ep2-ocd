import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Interface {

	private JFrame frame;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblComando;
	private String instrucao;
	private String op1;
	private String op2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
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
	 * @return 
	 */
	public void getComando(String comando){
		instrucao = comando.split(" ")[0];
		op1 = comando.split(" ")[1].split(",")[0];
		op2 = comando.split(" ")[1].split(",")[1];
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 572, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
	            {
	                getComando(textField.getText());
	                System.out.println(instrucao + " " + op1 + " " + op2);
	                textField.setText("");
	            }
			}
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
	            {
	                getComando(textField.getText());
	                System.out.println(instrucao + " " + op1 + " " + op2);
	                textField.setText("");
	            }
			}
		});
		textField.setBounds(71, 11, 475, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 42, 556, 219);
		frame.getContentPane().add(scrollPane);

		String[] columnNames = { "PC", "MAR", "MBR", "IR", "ax", "bx", "cx", "dx", "==0", "overf", "null" };
		Object[][] data = {{}};

		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		
		lblComando = new JLabel("Comando: ");
		lblComando.setBounds(10, -3, 200, 49);
		frame.getContentPane().add(lblComando);
//		
//		Object[] data2 = {"e","i","","","o","","",""};
//		model.insertRow(0, data2);		
//		
//		table = new JTable(model);
	}
}
