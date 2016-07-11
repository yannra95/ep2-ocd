
public class ULA {
	
	
	public String calcula(String operacao, String op1, String op2){
		Integer ret = 0;
		String retorno = "";

		System.out.println("operacao: "+operacao);
		
		switch (operacao) {
		
		//add
		case "001":
			
			break;
		//sub
		case "010":
			
			break;
		//mul
		case "011":
			
			break;
		//div
		case "100":
			
			break;
		//inc
		case "101":
			ret = Integer.parseInt(op1)+1;
			retorno = ret.toString();
			break;

		default:
			break;
		}
		
		return retorno;
	}
}
