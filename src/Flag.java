
public class Flag{
	private boolean flagValue;
	private String descricao;
	
	public Flag(boolean f, String s){
		flagValue = f;
		descricao = s;
	}

	public boolean isFlagValue() {
		return flagValue;
	}

	public void setFlagValue(boolean flagValue) {
		this.flagValue = flagValue;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String toString(){
		return ("Flag "+ descricao +" = "+ flagValue);
	}
	
}
