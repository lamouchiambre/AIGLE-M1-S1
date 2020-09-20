package tp1_2;

import java.lang.*;

public class FastDictionary extends AbstractDictionary{

	protected FastDictionary(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int indexOf(Object key) {
		// TODO Auto-generated method stub
		int cmp = 0;
		int hash = key.hashCode();
		hash = Math.abs(hash%this.getKeys().length);
		
		while(this.getKeys()[hash] != key && cmp < this.getKeys().length) {
			hash += 1;
			hash %= this.getKeys().length;
			cmp += 1;
		}
		if(cmp == this.getKeys().length ) {
			return -1;
		}
		return hash;
	}

	@Override
	protected int newIndexOf(Object key) {
		// TODO Auto-generated method stub
		if (this.mustGrow()) {
			this.grow();
		}
		int hash = Math.abs(key.hashCode()%this.getKeys().length);
		while (this.getKeys()[hash] != null) {
			hash += 1;
			hash %= this.getKeys().length;
		} 
		
		return hash;
	}
	
	public boolean mustGrow() {
		System.out.println(((float) this.size()/this.getKeys().length));
		return ((float) this.size()/this.getKeys().length) >= 0.75;
	}
	
	@Override
	protected void grow(){
		Object[] oldKeys = this.getKeys().clone();
		Object[] oldValues = this.getValues().clone();
		
		this.setKeys(new Object[(int) (oldKeys.length * 2)]);
		this.setValues(new Object[(int) (oldValues.length * 2)]);
		
		for(int i = 0; i < oldKeys.length ; i++) {
			if (oldKeys[i] != null) {
			this.put(oldKeys[i], oldValues[i]);
			}
		}
		
	}
	
	public static void main(String[] args) {
		FastDictionary D = new FastDictionary(4);
		System.out.println("test");
		D.put("un", 1);
		D.put("deux", 2);
		D.put("trois", 3);
		D.put("quatre", 4);
		System.out.println(D);
		System.out.println("end");
		
	}

}
