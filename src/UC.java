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
	
	public UC(){
	}
	
	palavra = new String
	
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
