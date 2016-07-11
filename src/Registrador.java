public class Registrador {

	private String conteudo;
	private String[] conteudoIR = new String[3];
	private int portaEntrada;
	private boolean isEntradaAberta;
	private int portaSaida;
	private boolean isSaidaAberta;
	
	public Registrador(String conteudo, int portaEntrada, int portaSaida) {
		super();
		this.conteudo = conteudo;
		this.portaEntrada = portaEntrada;
		this.isEntradaAberta = false;
		this.portaSaida = portaSaida;
		this.isSaidaAberta = false;
	}
	
	//Se tiver apenas uma porta, por convençao, usaremos a de entrada
	public Registrador(String conteudo, int portaEntrada) {
		super();
		this.conteudo = conteudo;
		this.portaEntrada = portaEntrada;
		this.isEntradaAberta = false;
		this.portaSaida = -1;
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

	public String[] getConteudoIR() {
		return conteudoIR;
	}

	public void setConteudoIR(String[] conteudoIR) {
		this.conteudoIR = conteudoIR;
	}
	public boolean isEntradaAberta() {
		return isEntradaAberta;
	}
	public void setEntradaAberta(boolean isEntradaAberta) {
		this.isEntradaAberta = isEntradaAberta;
	}
	public boolean isSaidaAberta() {
		return isSaidaAberta;
	}
	public void setSaidaAberta(boolean isSaidaAberta) {
		this.isSaidaAberta = isSaidaAberta;
	}
	

}
