public class Processador {
	private IR ir;
	private Registrador pc;
	private Registrador mbr;
	private Registrador mar;
	private Registrador ax;
	private Registrador bx;
	private Registrador cx;
	private Registrador dx;
	
	private boolean[] barramentoDados;
	private int[] sinal;
	
	public Processador() {
		this.pc = new Registrador("0",0 , 1);
		this.mar = new Registrador("", 2, 3);
		this.mbr = new Registrador("", 4, 5);
		this.ir = new IR();
		
		//Vetor de boolan que representa as portas e seus estados (aberta ou fechada)
		this.barramentoDados = new boolean[23];
		//Vetor de int que representa quais portas um dado sinal pede que sejam abertas
		this.sinal = new int[23];
	}
	
	public void abrePortas(){
		
		for (int i = 0; i < barramentoDados.length; i++) {
			for (int j = 0; j < sinal.length; j++) {
				
				//Se o sinal manda que a porta i do barramento seja aberta
				if (i == j)
					barramentoDados[i] = true;
			}
		}
	}
	
	public void fechaTodasPortas(){
		
		for (int i = 0; i < barramentoDados.length; i++) {
			barramentoDados[i] = false;
		}
	}
	
	/**
	 * MAR <== PC
	 * Memoria <== MAR
	 * MBR <== Memoria
	 * IR <== MBR
	 */
	public void cicloBuscaInstrucao(){
		
		//Mar <== PC
		sinal[0] = 1; sinal[1] = 2;
		abrePortas();
		if(barramentoDados[])
		
	}
	
}
