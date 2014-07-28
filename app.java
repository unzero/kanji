import java.util.*;
import java.io.*;

public class app{

	Scanner reader;
	Random random_generator;
	LinkedList<Kanji> kanji_list;
	LinkedList<String> all_lectures;
	String file_path = "./data.dat";
	private final static int options = 4;

	public app() throws IOException {
		reader = new Scanner(System.in);
		random_generator = new Random();
		kanji_list = new LinkedList<Kanji>();
		all_lectures = new LinkedList<String>();
		read();
	}

	private int read_int(){
		while(true){
			String ln = reader.nextLine();
			try{
				return Integer.parseInt(ln);
			}catch(NumberFormatException ex){}
		}
	}

	private String read_line(){
		return reader.nextLine();
	}

	private void create_kanji(String line){
		Kanji new_kanji = new Kanji();
		StringTokenizer tok = new StringTokenizer(line,"　");
		new_kanji.kanji = tok.nextToken();
		while(tok.hasMoreTokens() ){
			String ln = tok.nextToken();
			new_kanji.lectures.add(ln);
			if(all_lectures.indexOf(ln) == -1)all_lectures.add(ln);
		}
		kanji_list.add(new_kanji);
	}

	private void read() throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(file_path),"UTF-8"));
		String line;
		while( (line = buf.readLine() ) != null){
			create_kanji(line);
		}
		buf.close();
	}

	private void write() throws IOException {
		BufferedWriter buf = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_path),"UTF-8"));
		for(int x=0;x<kanji_list.size();++x){
			buf.write(kanji_list.get(x).toString());
		}
		buf.close();
	}

	private void insert(){
		System.out.println("Por favor ingrese el kanji");
		System.out.println("Formato: Kanji lectura1 lectura2 ... lecturaN");
		String line = read_line();
		create_kanji(line);
	}

	private void run(){
		int ok = 0;
		String wrong = "";
		for(int x=0;x<10;++x){
			Kanji in_study = kanji_list.get( random_generator.nextInt(kanji_list.size()) );
			int loc = random_generator.nextInt(options);
			System.out.println("Para el Kanji: "+in_study.kanji+" la lectura correcta es:");
			for(int i=0;i<options;++i){
				String nx;
				if(loc == i){
					nx = in_study.lectures.get( random_generator.nextInt(in_study.lectures.size()) );
				}else{
					do{
						nx = all_lectures.get( random_generator.nextInt(all_lectures.size()) );
					}while( in_study.lectures.indexOf(nx) != -1);
				}
				System.out.println(i+". "+nx);
			}
			System.out.print("Por favor ingrese la lectura correcta: ");
			int read = read_int();
			if( read == loc ) ok++;
			else wrong += in_study.kanji+" ";
			//7437400	
		}
		System.out.println(ok+"/10");
		if( !wrong.equals("")){
			System.out.println("Deberias repasar: ");
			System.out.println(wrong);
		}
	}

	private void menu(){
		System.out.println("-1: Para salir");
		System.out.println(" 1: Para insertar en el mapa");
		System.out.println(" 2: Para evaluacion rapida");
	}

	private void decided(int op){
		if(op == 1){
			insert();
		}else if(op == 2){
			run();
		}else if(op != -1){

		}
	}

	public void start() throws IOException {
		System.out.println("Bienvenido por favor ingrese su opción");
		int i = 0;
		do{
			menu();
			i = read_int();
			decided(i);
		}while(i != -1);
		write();
	}

	public static void main(String[] args){
		try{
			app app_running = new app();
			app_running.start();
		}catch(IOException ex){
			System.out.println("Error al iniciar el programa!");
		}
	}
}

class Kanji{
	public LinkedList<String> lectures;
	public String kanji;
	public Kanji(){
		lectures = new LinkedList<String>();
	}

	@Override
		public String toString(){
			String ret = new String();
			ret += kanji;
			ret += "　";
			for(String el : lectures){
				ret += el + "　";
			}
			ret += "\n";
			return ret;
		}
}
