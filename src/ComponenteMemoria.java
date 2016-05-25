public class ComponenteMemoria {
	private int endereco;
	private String conteudo;

	public ComponenteMemoria(int endereco, String conteudo) {
		this.endereco = endereco;
		this.conteudo = conteudo;
	}

	public int getEndereco() {
		return endereco;
	}

	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	
	
}
