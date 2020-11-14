package dico;

//import java.lang.*;

public class FastDictionary extends AbstractDictionary{

	protected FastDictionary() {
		super(0);
	}
	
	protected FastDictionary(int n) {
		super(n);
	}

	@Override
	protected int indexOf(Object key) {
		int cmp = 0;
		int hash = key.hashCode();
		if (this.getKeys().length != 0) {
			hash = Math.abs(hash % this.getKeys().length);
		} else {
			return -1;
		}

		while (this.getKeys()[hash] != key && cmp < this.getKeys().length) {
			hash += 1;
			hash %= this.getKeys().length;
			cmp += 1;
		}
		if (cmp == this.getKeys().length ) {
			return -1;
		}
		return hash;
	}

	@Override
	protected int newIndexOf(Object key) {
		int hash = 0;
		
		if (this.mustGrow()) {
			this.grow();
		}
		if (this.getKeys().length != 0) {
			hash = Math.abs(key.hashCode() % this.getKeys().length);	
		
			while (this.getKeys()[hash] != null) {
				hash += 1;
				hash %= this.getKeys().length;
			} 
		}
		
		return hash;
	}

	public boolean mustGrow() {
		return (this.getKeys().length == 0 
				|| ((float) this.size() / this.getKeys().length) >= 0.75);
	}

	@Override
	protected void grow() {
		Object[] oldKeys = this.getKeys().clone();
		Object[] oldValues = this.getValues().clone();
		int n = oldKeys.length;

		if (n == 0) {
			n++;
		}
		this.setKeys(new Object[(int) (n * 2)]);
		this.setValues(new Object[(int) (n * 2)]);

		for (int i = 0; i < oldKeys.length; i++) {
			if (oldKeys[i] != null) {
				this.put(oldKeys[i], oldValues[i]);
			}
		}
	}
	
	public String toString() {
		return "Fast" + super.toString();
	}

//	public static void main(String[] args) {
//		FastDictionary D = new FastDictionary();
//		D.put("un", 1);
//		D.put("deux", 2);
//		D.put("trois", 3);
//		D.put("quatre", 4);
//
//		long startTime = System.nanoTime();
//		D.indexOf("un");
//		long endTime = System.nanoTime();
//		System.out.println("That took " + (endTime - startTime) + " nanoseconds");
//		
//		long startTime2 = System.nanoTime();
//		D.indexOf("deux");
//		long endTime2 = System.nanoTime();
//		System.out.println("That took " + (endTime2 - startTime2) + " nanoseconds");
//		
//		long startTime3 = System.nanoTime();
//		D.indexOf("trois");
//		long endTime3 = System.nanoTime();
//		System.out.println("That took " + (endTime3 - startTime3) + " nanoseconds");
//		
//		long startTime4 = System.nanoTime();
//		D.indexOf("quatre");
//		long endTime4 = System.nanoTime();
//		System.out.println("That took " + (endTime4 - startTime4) + " nanoseconds");
//	}
}