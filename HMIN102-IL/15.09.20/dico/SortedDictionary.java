package dico;

public class SortedDictionary extends AbstractDictionary {

	public SortedDictionary() {
		super(0);
	}
	
	public SortedDictionary(int n) {
		super(n);
	}

	protected int newIndexOf(Object key) {
		return this.size();
	}

	protected int indexOf(Object key) {
		
		return -1;
	}
	
	
	public static void main(String[] args) {
		String str1 = "A";
		String str2 = "z";
		System.out.println(str1.compareTo(str2));
	}
}