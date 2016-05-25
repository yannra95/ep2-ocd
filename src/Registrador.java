public class Registrador {

	String conteudo;
	int portaEntrada;
	int portaSaida;
	String endereco;
	
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
	
	
	

}
