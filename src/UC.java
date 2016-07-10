import java.util.ArrayList;

public class UC {
	String operacao;
	String operando1;
	String operando2;
	boolean op1Reg;
	boolean op1Ind;
	boolean op2Reg;
	boolean op2Ind;
	String palavraControle;
	String[] palavra;
	private Assembler assembler;
	
	public UC(Assembler assembler){
		this.assembler = assembler;
	}
	
	// Esse método retorna as palavras de controle envolvidas na linha de código digitada
	// Deve ser executado após a execução completa da linha de código anterior 
	public void lerInstrucao(Memoria memoria, int endereco) {
	 
		String opcode = memoria.get(endereco); // instrução a ser lida
		//Para log
		
		String instrucao = opcode.substring(0,4);
		String operando1 = opcode.substring(4,18);
		String operando2 = opcode.substring(18);

		geraPalavra(instrucao, operando1, operando2);
		
		String linhasControle;
		String condJump;
		String comandoUla;
		String readWrite;
		String indirecao;
	}	
		//Para log
	
	/** 
	20 bits para linhas de controle
	1 bit pra condição jump e mais 8 bits pro endereço pra onde pular
	2 bits ou menos pro comando pra ula
	1 bit pra dizer para a memoria se é read(1) ou write(0)
	1 bit pra dizer se é indireção
	00000000000000000000 0 00000000 00 0 0*/
	
	public ArrayList<Integer> recuperaEntrada(String operando, boolean indirecao) {
		ArrayList<Integer> portas = new ArrayList<Integer>();
		String reg = operando.substring(10);
		switch(reg) {
		case "00":
			portas.add(retornaPortaEntrada("AX"));
			break;
		case "01":
			portas.add(retornaPortaEntrada("BX"));
			break;
		case "10":
			portas.add(retornaPortaEntrada("CX"));
			break;
		case "11":
			portas.add(retornaPortaEntrada("DX"));
			break;
		}
		if (indirecao) {
			portas.add(retornaPortaEntrada("MAR"));
		}
		return portas;
	}
	
	public ArrayList<Integer> recuperaSaida(String operando, boolean indirecao) {
		ArrayList<Integer> portas = new ArrayList<Integer>();
		String reg = operando.substring(10);
		switch(reg) {
		case "00":
			portas.add(retornaPortaSaida("AX"));
			break;
		case "01":
			portas.add(retornaPortaSaida("BX"));
			break;
		case "10":
			portas.add(retornaPortaSaida("CX"));
			break;
		case "11":
			portas.add(retornaPortaSaida("DX"));
			break;
		}
		if (indirecao) {
			portas.add(retornaPortaSaida("MAR"));
		}
		return portas;
	}

	/* inst	reg		ind	op1				reg	ind	op2
	 * 0000	 0	 	 1	000000000001	 0	 0	000000000000*/
	
	public ArrayList<String> geraPalavra(String instrucao, String operando1, String operando2) {
		ArrayList<String> palavras = new ArrayList<String>();
		
		char ind1 = operando1.charAt(1); // op1 indireção
		char ind2 = operando2.charAt(1); // op2 indireção
		
		if (ind1 == '1') {
			recuperaEntrada(operando1, true);
			recuperaEntrada(, indirecao)
			
		}
		if (ind2 == '1') {
			
		}
		// "ADD BX,5"
		recuperaEntrada(operando1, op1Ind); // pega BX
		recuperaSaida(operando2); // pega 5

		
		switch(instrucao) {
		case "0100": // MOV
			break;
		case "0101": // ADD
			
				
			break;
		case "0110": // SUB
			break;
		case "0111": // MUL
			break;
		case "1000": // DIV
			break;
		case "1001": // JMP
			break;
		case "1010": // JZ
			break;
		case "1011": // JNZ
			break;
		case "1100": // JG
			break;
		case "1101": // JGE
			break;
		case "1110": // JL
			break;
		case "1111": // JLE
			break;
		default:
			// Para log > "Instrução inválida"
			break;
	}
		Palavra palavra1 = new Palavra(operando2, operando2, operando2, operando2, "1");
		
		//Para log
		return palavras;
	}
	
	public String geraSinal(ArrayList<String>portas) {
		String sinal;
		
		return sinal;
	}
	
	public String mandaSinal(){
		return palavraControle;
	}
	
	public int retornaPortaEntrada(String local) {
		switch(local) {
			case "PC":
				return 0;
			case "MAR":
				return 2;
			case "MBR":
				return 3;
			case "AX":
				return 5;
			case "BX":
				return 7;
			case "CX":
				return 9;
			case "DX":
				return 11;
			case "IR":
				return 13;
			case "ULA":
				return 15;
			case "X":
				return 16;
			case "AC":
				break; // TODO
			case "MEM":
				return 18;
			default:
				break; 
			}
		return -1;
	}
	
	public int retornaPortaSaida(String local) {
		switch(local) {
		case "PC":
			return 1;
		case "MAR":
			break; // TODO 
		case "MBR":
			return 4;
		case "AX":
			return 6;
		case "BX":
			return 8;
		case "CX":
			return 10;
		case "DX":
			return 12;
		case "IR":
			return 14;
		case "ULA":
			break; // TODO
		case "X":
			break; // TODO
		case "AC":
			return 17;
		case "MEM":
			return 19;
		default:
			break;
		} 
		return -1; //TODO
	}
	
	/**
	 * Prepara a instrução recebida para criar a palavra de controle
	 */
	/*public void separaInstrucao(String codInstrucao){
		operacao = codInstrucao.substring(0, 4);
		operando1 = codInstrucao.substring(4, 18);
		operando2 = codInstrucao.substring(18);
		System.out.println("operação: "+operacao+" operando1: "+operando1+" operando2: "+operando2);
		if(operando1.charAt(0) == '1')
			op1Reg = true;
		else
			op1Reg = false;
		if(operando1.charAt(1) == '1')
			op1Ind = true;
		else
			op1Ind = false;
		if(operando2.charAt(0) == '1')
			op2Reg = true;
		else
			op2Reg = false;
		if(operando2.charAt(1) == '1')
			op2Ind = true;
		else
			op2Ind = false;
		operando1 = operando1.substring(2);
		operando2 = operando2.substring(2);
		System.out.println("operação: "+operacao+" operando1: "+operando1+" operando2: "+operando2);
	}*/
	
	//Para log
	//Mostrar só o que for necessário

}
