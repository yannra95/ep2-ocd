public class UC {
	String operacao;
	String operando1;
	String operando2;
	boolean op1Reg;
	boolean op1Ind;
	boolean op2Reg;
	boolean op2Ind;
	
	public UC(){
	}
	
	/**
	 * Prepara a instrução recebida para criar a palavra de controle
	 */
	public void decodificaInstrucao(String codInstrucao){

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
	
	public abrePortas(){
		
	}
	
	public fechaPortas(){
		
	}
}
