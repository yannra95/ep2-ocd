package Memoria;
import java.util.ArrayList;

public class Memoria {
	private ArrayList<ComponenteMemoria> memoria;
	
	public Memoria(){
		this.memoria = new ArrayList<ComponenteMemoria>();
	}
	
	public void printMemoria() {
		for (int j = 0; j < memoria.size(); j++) {
			System.out.println("endereço: " + memoria.get(j).getEndereco()
					+ "\t conteudo: " + memoria.get(j).getConteudo());
		}
	}
	
	
	public void add(int endereco, String conteudo){
		// TODO Mudar tipo da memoria para HashMap
		memoria.add(new ComponenteMemoria(endereco, conteudo));
	}
	
	public String get(int endereco){
		
		String conteudo = null;
		for (int i = 0; i < memoria.size(); i++) {
			if(memoria.get(i).getEndereco() == endereco )
				conteudo = memoria.get(i).getConteudo();
			else conteudo = null;
		}
		return conteudo;
	}

}
