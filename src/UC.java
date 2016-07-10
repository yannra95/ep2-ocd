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
	
	/*
	 * inst	reg		ind	op1				reg	ind	op2
	 * 0000	 0	 	 1	000000000001	 0	 0	000000000000
	 * 
	 */
	
	
	// Esse método retorna as palavras de controle envolvidas na linha de código digitada
	// Deve ser executado após a execução completa da linha de código anterior 
	public void lerInstrucao(Memoria memoria, int endereco) {
		String opcode = memoria.get(endereco); // instrução a ser lida
		//Para log
		
		String instrucao = opcode.substring(0,4);
		String operando1 = opcode.substring(4,18);
		String operando2 = opcode.substring(18);
		
		String linhasControle;
		String condJump;
		String comandoUla;
		String readWrite;
		String indirecao;
		/** 
		20 bits para linhas de controle
		1 bit pra condição jump e mais 8 bits pro endereço pra onde pular
		2 bits ou menos pro commando pra ula
		1 bit pra dizer para a memoria se é read(1) ou write(0)
		1 bit pra dizer se é indireção
		00000000000000000000 0 00000000 00 0 0*/
		
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
			default:
				System.out.println("Operação inválida");
				break;
		}
		
		//Para log
		// colocar num arrayList
		
		/**ax: 	0000
		bx: 	0001
		cx: 	0010
		dx: 	0011 */		
		
		if (operando1.charAt(1) == '1') { // se há uma indireção no op1
			if (operando1.charAt(0) == '1') { // se é um registrador
			}
			
		}
		if (operando2.charAt(1) == '1') { // se há uma indireção no op2
			
		}
		
			
	}
	
	public Palavra geraPalavra() {

		
		Palavra palavra1 = new Palavra(operando2, operando2, operando2, operando2, "1");
		
		
		
		
		//Para log
		return palavra1;
		/** talvez adicionar a um arrayList e zerar esse arrayList 
			antes da próxima linha de código ser executada */
	}
	
	
	/**
	 * Prepara a instrução recebida para criar a palavra de controle
	 */
	public void separaInstrucao(String codInstrucao){

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
	}

	public String mandaSinal(){
		return palavraControle;
	}
	
	public String retornaPortaEntrada(String local) {
		switch(local) {
			case "PC":
				return "0";
			case "MAR":
				return "2";
			case "MBR":
				return "3";
			case "AX":
				return "5";
			case "BX":
				return "7";
			case "CX":
				return "9";
			case "DX":
				return "11";
			case "IR":
				return "13";
			case "ULA":
				return "15";
			case "X":
				return "16";
			case "AC":
				return null;
			case "MEM":
				return "18";
			default:
				return null;
		}
	}
	
	public String retornaPortaSaida(String local) {
		switch(local) {
		case "PC":
			return "1";
		case "MAR":
			return null; 
		case "MBR":
			return "4";
		case "AX":
			return "6";
		case "BX":
			return "8";
		case "CX":
			return "10";
		case "DX":
			return "12";
		case "IR":
			return "14";
		case "ULA":
			return null;
		case "X":
			return null;
		case "AC":
			return "17";
		case "MEM":
			return "19";
		default:
			return null;
		} 
	}
	
	/**
	 * public String retornaPortaEntrada(String local) {
		switch(local) {
			case "PC":
				return "00000000000000000000";
			case "MAR":
				return "00000000000000000010";
			case "MBR":
				return "00000000000000000011";
			case "AX":
				return "00000000000000000101";
			case "BX":
				return "00000000000000000111";
			case "CX":
				return "00000000000000001001";
			case "DX":
				return "00000000000000001011";
			case "IR":
				return "00000000000000001101";
			case "ULA":
				return "00000000000000001111";
			case "X":
				return "00000000000000010000";
			case "AC":
				return null;
			case "MEM":
				return "00000000000000010010";
			default:
				return null;
		}
	}
	
	public String retornaPortaSaida(String local) {
		switch(local) {
		case "PC":
			return "00000000000000000001";
		case "MAR":
			return null; 
		case "MBR":
			return "00000000000000000100";
		case "AX":
			return "00000000000000000110";
		case "BX":
			return "00000000000000001000";
		case "CX":
			return "00000000000000001010";
		case "DX":
			return "00000000000000001100";
		case "IR":
			return "00000000000000001110";
		case "ULA":
			return null;
		case "X":
			return null;
		case "AC":
			return "00000000000000010001";
		case "MEM":
			return "00000000000000010011";
		default:
			return null;
		} 
	}*/
}
