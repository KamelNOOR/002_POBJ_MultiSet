package pobj.multiset;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import pobj.util.Chrono;


public class WordCount {

	public static void wordcount(MultiSet<String> ms) {
		
		String file = "data/WarAndPeace.txt";
		BufferedReader br;
		try {
			String line;
			br = new BufferedReader(new FileReader(file));
			
			while((line = br.readLine()) != null) {
				for (String word : line.split("\\P{L}+")) {
					if (word.equals(""))
						continue;
					ms.add(word);
				}
			}
			br.close();
			
			class MultiSetComparator<T> implements Comparator<T> {
				
				MultiSet<T> ms;
				public MultiSetComparator(MultiSet<T> ms) {
					this.ms = ms;
				}
				public int compare(T o1, T o2) {
					return Integer.compare(ms.count(o2), ms.count(o1));
				}
			}
			
			Comparator<String> c = new MultiSetComparator<String>(ms);
			
			List<String> sorted = ms.elements();
			Collections.sort(sorted, c);
			for (int i=0; i<10; i++) {
				String e = sorted.get(i);
				System.out.println(e + " : " + ms.count(e));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String args[]) {
		
		HashMultiSet<String> ms = new HashMultiSet<String>();
		NaiveMultiSet<String> ne = new NaiveMultiSet<>();
		Chrono chrono =new Chrono();
		wordcount(ne);
		chrono.stop();
		Chrono chrono1 =new Chrono();
		wordcount(ms);
		chrono1.stop();
		
		
	}
}