package kanji.core;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Random;
import java.util.StringTokenizer;
import java.io.*;
//DEFINED CLASS
import kanji.gui.ventana_central;
import kanji.database.Conexion;
import kanji.core.Kanji;

public class app{

private final static int options = 4;

	Scanner reader;
	Random random_generator;
	Conexion conexion;

	public app() throws IOException {
		reader = new Scanner(System.in);
		random_generator = new Random();
		conexion = new Conexion();
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

	private void insert(){
		System.out.println("Por favor ingrese el kanji");
		System.out.println("Formato: Kanji lectura1 lectura2 ... lecturaN");
		String line = read_line();
		//create_kanji(line);
	}

	private void run(){
		int ok = 0;
		String wrong = "";
		for(int x=0;x<10;++x){
			System.out.println("|-------------------------------------------|");
			Kanji in_study = conexion.get( random_generator.nextInt(conexion.size()) );
			int loc = random_generator.nextInt(options);
			System.out.println("Para el Kanji: "+in_study.get_kanji()+" la lectura correcta es:");
			for(int i=0;i<options;++i){
				String nx;
				if(loc == i){
					nx = in_study.get_lecture( random_generator.nextInt(in_study.size()) );
				}else{
					do{
						nx = conexion.get_lecture( random_generator.nextInt(conexion.total_lectures()) );
					}while( in_study.indexOf(nx) != -1);
				}
				System.out.println(i+". "+nx);
			}
			System.out.print("Por favor ingrese la lectura correcta: ");
			int read = read_int();
			if( read == loc ) ok++;
			else wrong += in_study.get_kanji()+" ";
			//7437400
			System.out.print("Las lecturas son: "+in_study.toString());
			System.out.println("|-------------------------------------------|");
		}
		System.out.println(ok+"/10");
		if( !wrong.equals("")){
			System.out.println("Deberias repasar: ");
			System.out.println(wrong);
		}
	}

	private void menu(){
		System.out.println(" 0: Para salir");
		System.out.println(" 1: Para insertar en el mapa");
		System.out.println(" 2: Para evaluacion rapida");
	}

	private void decided(int op){
		if(op == 1){
//			insert();
		}else if(op == 2){
			run();
		}else if(op != 0){

		}
	}

	public void start() throws IOException {
		System.out.println("Bienvenido por favor ingrese su opción");
		int i = 0;
		do{
			menu();
			i = read_int();
			decided(i);
		}while(i != 0);

	}

	public static void main(String[] args){
		try{
			app app_running = new app();
			app_running.start();
		}catch(IOException ex){
			System.out.println("Error al iniciar el programa!");
			ex.printStackTrace();
		}
		
	}
}

