public class UC {
	private boolean[] portas;
	
	
	public UC(){
		portas = new boolean[24];
	}
	
	public void separaInstrucao(String codInstrucao){
		
		IR ir = new IR();
		String operacao = codInstrucao.substring(0, 4);
		String operando1 = codInstrucao.substring(4, 18);
		String operando2 = codInstrucao.substring(18);
		
		System.out.println("operação: "+operacao+" operando1: "+operando1+" operando2: "+operando2);
	}
}
