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
	 * 0000	 0	 	 0	000000000000	 0	 0	000000000000
	 */
	
	public Palavra[] lerInstrucao(Memoria memoria, int endereco) {
		String opcode = memoria.get(endereco); // instrução a ser lida
		
		String instrucao = opcode.substring(0,4);
		String operando1 = opcode.substring(4,18);
		String operando2 = opcode.substring(18);
		
		/** 
		20 bits para linhas de controle
		1 bit pra condição jump e mais 8 bits pro endereço pra onde pular
		2 bits ou menos pro commando pra ula
		1 bit pra dizer para a memoria se é read(1) ou write(0)
		1 bit pra dizer se é indireção
		00000000000000000000 0 00000000 00 0 0*/
		
		
		if (operando1.charAt(0) == '1') { // se há uma indireção no op1
			
			
		}
		if (operando2.charAt(0) == '1') { // se há uma indireção no op2
			
		}
		
		String linhasControle = opcode.substring(0, 20);
		String condJump = opcode.substring(20,21);
		String comandoUla = opcode.substring(21,23);
		String readWrite = opcode.substring(23,24);
		String indirecao = opcode.substring(24,25);
		
		Palavra[] palavrasDeControle;
		
		if (indirecao.charAt(0) == '1') {
			// Retornar mais de uma palavra
			palavrasDeControle = new Palavra[2];
		} else {
			// Palavra simples
			palavrasDeControle = new Palavra[1];
			Palavra palavra = new Palavra(linhasControle,condJump,comandoUla,readWrite,indirecao);
			palavrasDeControle[0] = palavra;
		}
		return palavrasDeControle;
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
	
	public String setLinhasControle(){
		
// 000000000000000000000
		
//		mov:	0100
//		add:	0101
//		sub:	0110
//		mul:	0111
//		div:	1000
//		jmp:	1001
		switch (operacao) {
		case "0100":
			
			break;
		case "0101":
			
			break;
		case "0110":
			
			break;
		case "0111":
			
			break;
		case "1000":
			
			break;
		case "1001":
			
			break;

		default:
			break;
		}
		
		String retorno = "";
		
		return retorno;
	}
	
	public void criaPalavraControle(){
		
		String retorno = "";
		
		palavraControle = retorno;
	}

	public String mandaSinal(){
		return palavraControle;
	}
}
