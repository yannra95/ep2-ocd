public class Registrador {

	private String conteudo;
	private int portaEntrada;
	private int portaSaida;
	private String endereco;
	
	public Registrador(String conteudo, int portaEntrada, int portaSaida,
			String endereco) {
		super();
		this.conteudo = conteudo;
		this.portaEntrada = portaEntrada;
		this.portaSaida = portaSaida;
		this.endereco = endereco;
	}
	public Registrador(String conteudo, int portaEntrada, int portaSaida) {
		super();
		this.conteudo = conteudo;
		this.portaEntrada = portaEntrada;
		this.portaSaida = portaSaida;
	}
	
	
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	//Não possui set pois só pode ser definido na instância do objeto
	public int getPortaEntrada() {
		return portaEntrada;
	}
	//Não possui set pois só pode ser definido na instância do objeto
	public int getPortaSaida() {
		return portaSaida;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	

}
