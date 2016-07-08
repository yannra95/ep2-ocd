import java.util.ArrayList;
import java.util.List;

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
	private ArrayList<Integer> sinal;
	
	public Processador() {
		this.pc = new Registrador("0",0 , 1);
		this.mar = new Registrador("", 2, 3);
		this.mbr = new Registrador("", 4, 5);
		this.ir = new IR();
		
		this.ax = new Registrador("", 5, 6);
		this.bx = new Registrador("", 7, 8);
		this.cx = new Registrador("", 9, 10);
		this.dx = new Registrador("", 11, 12);
		
		//Vetor de boolan que representa as portas e seus estados (aberta ou fechada)
		this.barramentoDados = new boolean[23];
		//Vetor de int que representa quais portas um dado sinal pede que sejam abertas
		this.sinal = new ArrayList<Integer>();
	}
	
	public void cicloInstrucao(){
		
	}
	
	/**
	 * MAR <== PC
	 * Memoria <== MAR
	 * MBR <== Memoria
	 * IR <== MBR
	 */
	
	

	public IR getIr() {
		return ir;
	}

	public void setIr(IR ir) {
		this.ir = ir;
	}

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

	public ArrayList<Integer> getSinal() {
		return sinal;
	}

	public void setSinal(ArrayList<Integer> sinal) {
		this.sinal = sinal;
	}
	
	
	
}
