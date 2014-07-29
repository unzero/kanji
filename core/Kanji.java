package kanji.core;

import java.util.StringTokenizer;
import java.util.LinkedList;

public class Kanji{

	/*FIELDS*/
	private LinkedList<String> lectures;
	private String kanji;

	public Kanji(String line){
		lectures = new LinkedList<String>();
		StringTokenizer tok = new StringTokenizer(line,"　");
		kanji = tok.nextToken();
		while(tok.hasMoreTokens() ){
			lectures.add(tok.nextToken());
		}
	}
	
	public int size(){
		return lectures.size();
	}

	public String get_lecture(int i){
		return lectures.get(i);
	}

	public String get_kanji(){
		return kanji;
	}
	
	public LinkedList<String> get_lectures(){
		return lectures;
	}

	public int indexOf(String el){
	return lectures.indexOf(el);
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
