import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Log {
	
	private static ArrayList<String> historico;
	private static String timeStampLog;
	
	private static Assembler assembler;
	
	public static void inicializaLog(Assembler a){
		assembler = a;
		historico = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		String inicial = "Início da Execução: "+ dateFormat.format(cal.getTime()) +"\n";
		
		historico.add(inicial);
		
		DateFormat timeStampFormat = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
		timeStampLog = timeStampFormat.format(cal.getTime());
		
		assembler.atualizaTextAreLog(inicial);
	}
	
	
	public static void addTo(String s){
		String adicional;
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
		Calendar cal = Calendar.getInstance();
		adicional = dateFormat.format(cal.getTime()) + " - \t" + s;
		
		historico.add(adicional);
		assembler.atualizaTextAreLog(adicional);
	}
	
	public static String[] getAllFrom(){
		String[] retorno = (String[]) historico.toArray();
		return retorno;
	}
	
	public static String getLastFrom(){
		String retorno = historico.get(historico.size()-1);
		return retorno;
	}
	
	
	public static void save(){
		
		String identificadorLog = timeStampLog.replace(':', '-');
		identificadorLog = identificadorLog.replace('/', '-');
		
		BufferedWriter writer = null;
		
		try{
			FileWriter f = new FileWriter("log("+ identificadorLog +").txt");
			writer = new BufferedWriter(f);
			
			for(String s: historico){
				writer.write(s);
			}
			
		} catch(Exception e){
			System.out.println("Erro! Não foi possível salvar arquivo de log: "+ e);
		}
		finally{
		    try{
		        if ( writer != null)
		        writer.close( );
		    }
		    catch(Exception er){
		    	System.out.println("Erro! Não foi possível fechar o buffer do arquivo de log: "+ er);
		    }
		}
	}
	
}
