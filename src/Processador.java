import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Processador {
	private Registrador pc;
	private Registrador mbr;
	private Registrador mar;
	private Registrador ax;
	private Registrador bx;
	private Registrador cx;
	private Registrador dx;
	private Registrador ir;
	private Registrador ulaReg;
	private Registrador x;
	private Registrador ac;
	private Registrador mem;
	private Registrador[] registradores;

	// Flags em relação a zero
	private Flag fIgual;
	private Flag fDiferente;
	private Flag fMaiorOuIgual;
	private Flag fMaior;
	private Flag fMenorOuIgual;
	private Flag fMenor;
	private Flag[] flags;

	public Memoria memoria;
	public String palavraControle;
	private UC uc;
	private ULA ula;
	private Assembler assembler;

	private boolean[] barramentoDados;

	public Processador(Assembler assembler) {

		this.assembler = assembler;
		this.pc = new Registrador("0", 0, 1);
		this.mar = new Registrador("", 2);
		this.mbr = new Registrador("", 3, 4);
		this.ax = new Registrador("", 5, 6);
		this.bx = new Registrador("", 7, 8);
		this.cx = new Registrador("", 9, 10);
		this.dx = new Registrador("", 11, 12);
		this.ir = new Registrador("", 13, 14);
		this.ulaReg = new Registrador("", 15);
		this.x = new Registrador("", 16);
		this.ac = new Registrador("", 17);
		this.mem = new Registrador("", 18, 19);
		this.registradores = new Registrador[] { pc, mar, mbr, ir, ax, bx, cx,
				dx, ir, ulaReg, x, ac, mem };

		this.fIgual = new Flag(false, "igual a zero");
		this.fDiferente = new Flag(false, "diferente de zero");
		this.fMaiorOuIgual = new Flag(false, "maior ou igual a zero");
		this.fMaior = new Flag(false, "maior do que zero");
		this.fMenorOuIgual = new Flag(false, "menor ou igual a zero");
		this.fMenor = new Flag(false, "menor do que zero");
		this.flags = new Flag[] { fIgual, fDiferente, fMaiorOuIgual, fMaior,
				fMenorOuIgual, fMenor };

		this.memoria = new Memoria(assembler);
		this.ula = new ULA();
		this.palavraControle = "";

		// Vetor de boolean que representa as portas e seus estados (aberta ou
		// fechada)
		this.barramentoDados = new boolean[20];
	}

	public void cicloInstrucao() {

		cicloBusca();
		cicloExecucao();

	}

	/*
	 * portas endereço ula r/w ind00000000000000000000 00000000 000 0 00 19 20
	 * 27 28 30 31 32
	 */
	public void interpretaPalavra() {

		abrePortas();

		// Se a porta da de entrada da memoria estiver aberta
		if (registradores[12].isEntradaAberta()) {
			// Se for pra ler
			if (palavraControle.charAt(31) == '1') {
				// Coloca na memoria o endereço q deve ocorrer a busca
				registradores[12].setConteudo(memoria.get(Integer
						.parseInt(registradores[1].getConteudo())));
			}
			// Se for pra escrever
			else {
//				registradores[12].setConteudo(conteudo);
			}
		} else {

			// Se a porta de saida da memoria estiver aberta
			if (registradores[12].isSaidaAberta()) {
				// Se for pra ler
				if (palavraControle.charAt(31) == '1') {
					// Coloca no MBR o que a memoria retornar
					registradores[2].setConteudo(registradores[12]
							.getConteudo());
				}
				// Se for pra escrever
				else {

				}
			} else {

				copyReg2Reg();

				// Se a palavra tiver sinal pra ULA
				if (!palavraControle.substring(28, 31).equals("000")) {
					// Coloca no AC o resultado
					registradores[11].setConteudo(ula.calcula(
							palavraControle.substring(28, 31),
							registradores[10].getConteudo(),
							registradores[9].getConteudo()));
				} else {

				}
			}
		}

		fechaPortas();
	}

	/**
	 * Move o valor de um registrador para outro
	 */
	public void copyReg2Reg() {
		String regOut = null;
		for (int i = 0; i < registradores.length; i++)
			// Exceção: se for o registrador AC
			if (i == 11) {
				if (registradores[i].isEntradaAberta())
					regOut = registradores[i].getConteudo();
			} else {
				if (registradores[i].isSaidaAberta())
					regOut = registradores[i].getConteudo();
			}
		for (int i = 0; i < registradores.length; i++) {
			if (registradores[i].isEntradaAberta()) {
				registradores[i].setConteudo(regOut);
			}
		}
	}

	public void abrePortas() {
		String portas = palavraControle.substring(0, 20);
		for (int i = 0; i < barramentoDados.length; i++) {
			if (portas.charAt(i) == '1') {
				for (int j = 0; j < registradores.length; j++) {

					if (registradores[j].getPortaEntrada() == i) {
						registradores[j].setEntradaAberta(true);
					}
					if (registradores[j].getPortaSaida() == i) {
						registradores[j].setSaidaAberta(true);
					}
				}
			}
		}
		assembler.atualizaTabelaPortas();
	}

	public void fechaPortas() {
		for (int i = 0; i < barramentoDados.length; i++) {
			barramentoDados[i] = false;
		}
		for (int i = 0; i < registradores.length; i++) {
			registradores[i].setEntradaAberta(false);
			registradores[i].setSaidaAberta(false);
		}
		assembler.atualizaTabelaPortas();
	}

	/**
	 * Contrucaoo manual da palavra de controle representa a interpretacao da UC
	 */
	public void cicloBusca() {

		/*
		 * portas endereço ula r/w ind00000000000000000000 00000000 000 0 00 19
		 * 20 27 28 30 31 32
		 */

		Log.addTo("Ciclo de Busca");

		// MAR <- PC 1,2
		palavraControle = "011000000000000000000000000000000";
		Log.addTo("MAR <- PC 1,2: " + palavraControle);
		interpretaPalavra();
		assembler.atualizaTabela();

		// Memoria <- MAR 18
		palavraControle = "000000000000000000100000000000010";
		Log.addTo("Memoria <- MAR 18: " + palavraControle);
		interpretaPalavra();
		assembler.atualizaTabela();

		// MBR <- Memoria 4, 19
		palavraControle = "000100000000000000010000000000010";
		Log.addTo("MBR <- Memoria 4, 19: " + palavraControle);
		interpretaPalavra();
		assembler.atualizaTabela();

		// IR <- MBR 4,13
		palavraControle = "000010000000010000000000000000000";
		Log.addTo("IR <- MBR 4,13: " + palavraControle);
		interpretaPalavra();
		assembler.atualizaTabela();
		
		System.out.println("ir: "+registradores[0].getConteudo());

		// X (com inc) <- PC 1, 16
		palavraControle = "010000000000000010000000000010100";
		Log.addTo("X (com inc) <- PC 1, 16: " + palavraControle);
		interpretaPalavra();
		assembler.atualizaTabela();
		// PC <- AC 0, 17
		palavraControle = "100000000000000001000000000000000";
		interpretaPalavra();

	}

	public void cicloExecucao() {
		ArrayList<Palavra> palavras = uc.lerInstrucao(registradores[0].getConteudoIR());
		
		for (int i = 0; i < palavras.size(); i++) {
			interpretaPalavra();
		}
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

	public Flag getfIgual() {
		return fIgual;
	}

	public void setfIgual(Flag fIgual) {
		this.fIgual = fIgual;
	}

	public Flag getfDiferente() {
		return fDiferente;
	}

	public void setfDiferente(Flag fDiferente) {
		this.fDiferente = fDiferente;
	}

	public Flag getfMaiorOuIgual() {
		return fMaiorOuIgual;
	}

	public void setfMaiorOuIgual(Flag fMaiorOuIgual) {
		this.fMaiorOuIgual = fMaiorOuIgual;
	}

	public Flag getfMaior() {
		return fMaior;
	}

	public void setfMaior(Flag fMaior) {
		this.fMaior = fMaior;
	}

	public Flag getfMenorOuIgual() {
		return fMenorOuIgual;
	}

	public void setfMenorOuIgual(Flag fMenorOuIgual) {
		this.fMenorOuIgual = fMenorOuIgual;
	}

	public Flag getfMenor() {
		return fMenor;
	}

	public void setfMenor(Flag fMenor) {
		this.fMenor = fMenor;
	}

	public Flag[] getFlags() {
		return flags;
	}

	public void setFlags(Flag[] flags) {
		this.flags = flags;
	}

	public Assembler getAssembler() {
		return assembler;
	}

	public void setAssembler(Assembler assembler) {
		this.assembler = assembler;
	}

}
