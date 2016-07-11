public class Palavra {
	
	private String[] palavraControle;
	
	public Palavra(String linhasDeControle, String endereco, String comandoUla, String readWrite, String indirecao) {
		palavraControle = new String[5];
		palavraControle[0] = linhasDeControle;
		palavraControle[1] = endereco;
		palavraControle[2] = comandoUla;
		palavraControle[3] = readWrite;
		palavraControle[4] = indirecao;		
	}

	public String getLinhasDeControle() {
		return palavraControle[0];
	}
	
	public String getEndereco() {
		return palavraControle[1];
	}
	
	public String getComandoUla() {
		return palavraControle[2];
	}
	
	public String getReadWrite() {
		return palavraControle[3];
	}
	
	public String getIndirecao() {
		return palavraControle[4];
	}
	
}
