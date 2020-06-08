package pobj.multiset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.AbstractCollection;
import java.util.ArrayList;


public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {

	private List<T> list;
	
	public NaiveMultiSet() {
		list = new ArrayList<T>();
	}
	
	
	public boolean add(T e, int count) {
		for (int i=0; i<count; i++) {
			list.add(e);
		}
		return true;
	}
	
	public boolean add(T e) {
		list.add(e);
		return true;
	}
	
	public boolean remove(Object e) {
		T e1 = (T) e;
		if (count(e1) != 0) {
			for (int i=0; i<list.size(); i++) {
				if (list.get(i).equals(e)) {
					list.remove(i);
					break;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean remove(Object e, int count) {
		if (count<=0) {
			return false;
		}
		int cpt=count;
		while (cpt !=0) {
			remove(e);
			cpt--;
		}
		return true;
	}
	
	public int count(T o) {
		int cpt = 0;
		for (T e : list) {
			if (e.equals(o)) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public void clear() {
		list.clear();
	}
	
	public int size() {
		return list.size();
	}
	
	public List<T> elements() {
		List<T> elements = new ArrayList<T>();
		for (T t : list) {
			if (!elements.contains(t)) {
				elements.add(t);
			}
		}
		return elements;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new NaiveMultiSetIterator();
	}
	
	private class NaiveMultiSetIterator implements Iterator<T> {
		
		private int index = 0;
		
		@Override
		public boolean hasNext() {
			return index < size();
		}
		
		@Override
		public T next() {
			return list.get(index++);
		}
	}
}