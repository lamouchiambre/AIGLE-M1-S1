package dico;

import java.util.Arrays;


public class SortedDictionary extends OrderedDictionary {

	public SortedDictionary() {
		super(0);
	}

	public SortedDictionary(int n) {
		super(n);
	}

	protected int compareTo(Object o1, Object o2) {
		String str1 = String.valueOf(o1);
		String str2 = String.valueOf(o2);

		return str1.compareTo(str2);
	}

	//	public int indexOf(Object key) {
	//		Comparable ckey = (Comparable) key;
	//		
	//		for (int i = 0; i < this.size(); i++) {
	//			if (compareTo(this.getKeys()[i], this.indexOf(key)) == 0) {
	//				return i;
	//			}
	//		}
	//
	//		return -1;
	//	}

	// (Comparable<Object> keys[i]) compareTo(key)

	@SuppressWarnings("unchecked")
	//	protected int newIndexOf(Object key) {
	//		int i = 0;
	//		this.grow();
	//		while (i < this.getKeys().length && (this.compareTo(key, this.getKeys()[i]) > 0)) {
	//			i++;
	//		}
	//		for (int j = this.getKeys().length - 1 ; j > i; j--) {
	//			//compareTo(this.getKeys()[i], this.indexOf(key)
	//			this.getKeys()[j] = this.getKeys()[j-1];
	//		}
	//		
	//		return i;
	//	}
	protected int newIndexOf(Object key) {
		//this.grow();
		if (this.size() == 0) {
			return 0;
		}

		Object[] tkeys = this.getKeys().clone();
		int deb = 0;
		int fin = this.getKeys().length;
		int mid;

		if (this.size() == this.getKeys().length) {
			this.grow();
		}

		while (deb < fin) {
			mid = (deb + fin) / 2;
			if (this.compareTo(key, tkeys[mid]) > 0) {
				deb = mid + 1;
			} else {
				fin = mid;
			}
		}

		for (int j = this.getKeys().length - 1; j > deb; j--) {
			//compareTo(this.getKeys()[i], this.indexOf(key)
			this.getKeys()[j] = this.getKeys()[j - 1];
			this.getValues()[j] = this.getValues()[j - 1];
		}

		return deb;
	}

	public static void main(String[] args) {
		SortedDictionary sd = new SortedDictionary(1);
//		Comparable un = 1;
//		System.out.println(un.compareTo(2));
		sd.put("a", "value1");
		sd.put("c", "value3");
		sd.put("b", "value2");
		System.out.println(sd);
		//		System.out.println(sd.compareTo(
		//				sd.getKeys()[sd.indexOf("a")], 
		//				sd.getKeys()[sd.indexOf("b")]));
		//		System.out.println(sd.compareTo(
		//				sd.getValues()[sd.indexOf("a")], 
		//				sd.getValues()[sd.indexOf("b")]));
		//		System.out.println(sd);
	}
}