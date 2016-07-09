import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Processador {
	private Registrador pc;
	private Registrador mbr;
	private Registrador mar;
	private Registrador ir;
	private Registrador ax;
	private Registrador bx;
	private Registrador cx;
	private Registrador dx;
	public Memoria memoria;
	public String palavraControle;

	private boolean[] barramentoDados;

	public Processador() {

		this.pc = new Registrador("0", 0, 1);
		this.mar = new Registrador("", 2);
		this.mbr = new Registrador("", 3, 4);
		this.ir = new Registrador("", "", "", 13, 14);
		this.ax = new Registrador("", 5, 6);
		this.bx = new Registrador("", 7, 8);
		this.cx = new Registrador("", 9, 10);
		this.dx = new Registrador("", 11, 12);
		this.memoria = new Memoria();
		this.palavraControle = "";

		//Vetor de boolean que representa as portas e seus estados (aberta ou fechada)
		this.barramentoDados = new boolean[21];
	}

	public void cicloInstrucao() {
		
		cicloBusca();
		
	}
	
	public void interpretaSinal(){
		abrePortas();
		
		//Se as portas da ULA não tiverem abertas, operação envolve apenas transporte de dados
		if(!barramentoDados[15]){
			
		}
		
	}
	
	public void abrePortas(){
		String sinal = palavraControle.substring(0, 20);
		for (int i = 0; i < barramentoDados.length; i++) {
			if(sinal.charAt(i) == '1')
				barramentoDados[i] = true;
		}
	}
	
	public void fechaPortas(){
		for (int i = 0; i < barramentoDados.length; i++) {
			barramentoDados[i] = false;
		}
	}
	
	/**
	 * Contrução manual da palavra de controle representa a interpretação da UC
	 */
	public void cicloBusca() {
		
		//MAR <- PC 1,2
		palavraControle = "01100000000000000000 0 00000000 00 0 0";
		interpretaSinal();
		//Memoria <- MAR 18
		palavraControle = "00000000000000000010 0 00000000 00 1 1";
		
		//MBR <- Memoria 4, 19
		palavraControle = "00010000000000000001 0 00000000 00 1 1";
		
		//IR <- MBR 4,13
		palavraControle = "00001000000001000000 0 00000000 00 1 1";
	}
	
	public void cicloIndireção(){
		
	}

	public void cicloExecucao() {

	}

	/**
	 * MAR <== PC Memoria <== MAR MBR <== Memoria IR <== MBR
	 */

	public Registrador getPc() {
		return pc;
	}

	public void setPc(Registrador pc) {
		this.pc = pc;
	}

	public Registrador getMbr() {
		return mbr;
	}

	public void setMbr(Registrador mbr) {
		this.mbr = mbr;
	}

	public Registrador getMar() {
		return mar;
	}

	public void setMar(Registrador mar) {
		this.mar = mar;
	}

	public Registrador getAx() {
		return ax;
	}

	public void setAx(Registrador ax) {
		this.ax = ax;
	}

	public Registrador getBx() {
		return bx;
	}

	public void setBx(Registrador bx) {
		this.bx = bx;
	}

	public Registrador getCx() {
		return cx;
	}

	public void setCx(Registrador cx) {
		this.cx = cx;
	}

	public Registrador getDx() {
		return dx;
	}

	public void setDx(Registrador dx) {
		this.dx = dx;
	}

	public boolean[] getBarramentoDados() {
		return barramentoDados;
	}

	public void setBarramentoDados(boolean[] barramentoDados) {
		this.barramentoDados = barramentoDados;
	}

	public Registrador getIr() {
		return ir;
	}

	public void setIr(Registrador ir) {
		this.ir = ir;
	}
}
