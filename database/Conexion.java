package kanji.database;

import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
//DEFINED CLASS
import kanji.core.Kanji;

public class Conexion{
	
	/*CLASS FIELDS*/
	private	LinkedList<Kanji> kanji_list;
	private LinkedList<String> all_lectures;
	private String file_path = "/home/unzero/kanji/database/data.dat";

	/*PUBLIC METHODS*/
	public Conexion() throws IOException{
		kanji_list = new LinkedList<Kanji>();
		all_lectures = new LinkedList<String>();
		read();
	}
	
	public void close() throws IOException{
		write();
	}

	public Kanji get(int i){
		return kanji_list.get(i);
	}

	public String get_lecture(int i){
		return all_lectures.get(i);
	}

	public int size(){
		return kanji_list.size();
	}

	public int total_lectures(){
		return all_lectures.size();
	}

	/*PRIVATE METHODS*/
	private void read() throws IOException {
		BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream(file_path),"UTF-8"));
		String line;
		while( (line = buf.readLine() ) != null){
			if(!line.equals("")){
				Kanji new_kanji = new Kanji(line);
				kanji_list.add( new_kanji );
				for(String el : new_kanji.get_lectures()){
					if(all_lectures.indexOf(el) == -1)all_lectures.add(el);
				}
			}
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
}
