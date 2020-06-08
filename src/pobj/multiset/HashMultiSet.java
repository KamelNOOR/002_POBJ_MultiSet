package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>, Iterable<T>{
	
	HashMap<T, Integer> ensemble;
	
	public HashMultiSet() {
		ensemble = new HashMap<T, Integer>();
	}
	
	public HashMultiSet(Collection<T> c) {
		ensemble = new HashMap<T, Integer>();
		for (T t : c) {
			if (!ensemble.containsKey(t))
				ensemble.put(t, 1);
			else
				ensemble.replace(t,ensemble.get(t));
		}
	}
	
	@Override
	public boolean add(T e, int count) {
		if (!ensemble.containsKey(e))
			ensemble.put(e, count);
		else
			ensemble.replace(e,ensemble.get(e)+count);
		return true;
	}

	@Override
	public boolean add(T e) {
		if (!ensemble.containsKey(e))
			ensemble.put(e, 1);
		else
			ensemble.replace(e,ensemble.get(e)+1);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object e) {
		ensemble.replace((T) e,ensemble.get((T) e)-1);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object e, int count) {
		ensemble.replace((T) e,ensemble.get((T) e)-count);
		return true;
	}

	@Override
	public int count(T o) {
		if (!ensemble.containsKey(o))
			return 0;
		return ensemble.get(o);
	}

	@Override
	public void clear() {
		ensemble.clear();
	}

	@Override
	public int size() {
		int somme = 0;
		for (Integer i : ensemble.values()) {
			somme += i ;
		}
		return somme;
	}
	
	@Override
	public List<T> elements() {
		List<T> liste = new ArrayList<T>();
		for (T t : ensemble.keySet()) {
			liste.add(t);
		}
		return liste;
	}

	public Iterator<T> iterator(){
		return new HashMultiSetIterator();
	}
	
	private class HashMultiSetIterator implements Iterator<T>{
		
		private Set<T> key = ensemble.keySet();
		private int cpt=0;
		private int indice=0;
		@Override
		public boolean hasNext() {
			return indice<key.size();
		}

		@Override
		public T next() {
			cpt++;
			T t = (T) key.toArray()[indice];
			if (ensemble.get(t)==cpt) {
				cpt=0;
				indice++;
			}
			return t;
		}
	}



}
