import java.util.ArrayList;

public class Memoria {
	private ArrayList<ComponenteMemoria> memoria;
	
	public Memoria(){
		this.memoria = new ArrayList<ComponenteMemoria>();
	}
	
	public void add(int endereco, String conteudo){
		
		memoria.add(new ComponenteMemoria(endereco, conteudo));
	}
	
//	public int get(int endereco){
//		
//		int conteudo = 0;
//		for (int i = 0; i < memoria.size(); i++) {
//			if(memoria.get(i).getConteudo() == endereco)
//				conteudo = memoria.get(i).getConteudo();
//			else conteudo = -1;
//		}
//		return conteudo;
//	}

}
