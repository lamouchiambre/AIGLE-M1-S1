package dico;

public class SortedDictionary extends AbstractDictionary {

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
	
	public int indexOf(Object key) {
		for (int i = 0; i < this.size(); i++) {
			if (compareTo(this.getKeys()[i], this.indexOf(key)) == 0) {
				return i;
			}
		}

		return -1;
	}
	
	// (Comparable<Object> keys[i]) compareTo(key)
	
	protected int newIndexOf(Object key) {
		int i = 0;
		
		if (this.getKeys().length == this.size()) {
			this.grow();
		}		
		
		for (int j = 0; j < this.getKeys().length; j++) {
			if (compareTo(this.getKeys()[i], this.indexOf(key)) >= 0) {
				
			}
		}
		
		return ;
	}

	public static void main(String[] args) {
		SortedDictionary sd = new SortedDictionary(2);
		sd.put("a", "value1");
		sd.put("b", "value3");
		System.out.println(sd.compareTo(
				sd.getKeys()[sd.indexOf("a")], 
				sd.getKeys()[sd.indexOf("b")]));
		System.out.println(sd.compareTo(
				sd.getValues()[sd.indexOf("a")], 
				sd.getValues()[sd.indexOf("b")]));
		System.out.println(sd);
	}
}