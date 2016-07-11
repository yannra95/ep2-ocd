
public class ULA {
	
	
	public String calcula(String operacao, String op1, String op2){
		Integer ret = 0;
		String retorno = "";

		System.out.println("operacao: "+operacao);
		
		switch (operacao) {
		
		//add
		case "001":
			ret = Integer.parseInt(op1, 2) + Integer.parseInt(op2, 2);
			retorno = Integer.toBinaryString(ret);
			break;
		//sub
		case "010":
			ret = Integer.parseInt(op1, 2) - Integer.parseInt(op2, 2);
			retorno = Integer.toBinaryString(ret);
			break;
		//mul
		case "011":
			ret = Integer.parseInt(op1, 2) * Integer.parseInt(op2, 2);
			retorno = Integer.toBinaryString(ret);
			break;
		//div
		case "100":
			ret = Integer.parseInt(op1, 2) / Integer.parseInt(op2, 2);
			retorno = Integer.toBinaryString(ret);
			break;
		//inc
		case "101":
			ret = Integer.parseInt(op1, 2)+1;
			retorno = Integer.toBinaryString(ret);
			break;

		default:
			break;
		}
		
		return retorno;
	}
}
