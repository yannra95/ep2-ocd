public class IR {
	private String instrucao;
	private String op1;
	private String op2;
	
	public IR(){
		instrucao = "";
		op2 = "";
		op1 = "";
	}
	
	public String getInstrucao() {
		return instrucao;
	}
	public void setInstrucao(String instrucao) {
		this.instrucao = instrucao;
	}
	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getOp2() {
		return op2;
	}
	public void setOp2(String op2) {
		this.op2 = op2;
	}
	
	

}
