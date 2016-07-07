public class UC {
	private boolean[] portas;
	
	
	public UC(){
		portas = new boolean[24];
	}
	
	public void decodificaInstrucao(String codInstrucao){
		
		IR ir = new IR();
		String operacao = codInstrucao.substring(0, 4);
		String operando1 = codInstrucao.substring(4, 18);
		String operando2 = codInstrucao.substring(18);
		
		
	}
}
