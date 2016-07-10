public class Palavra {
	
	public Palavra(String linhasDeControle, String condJump, String comandoUla, String readWrite, String indirecao) {
		String[] palavra = new String[5];
		palavra[0] = linhasDeControle;
		palavra[1] = condJump;
		palavra[2] = comandoUla;
		palavra[3] = readWrite;
		palavra[4] = indirecao;		
	}
}
