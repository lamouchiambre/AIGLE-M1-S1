package dico;

import java.util.Arrays;

public abstract class AbstractDictionary implements IDictionary {
	// Attributes
	private Object[] keys;
	private Object[] values;

	// Constructor
	protected AbstractDictionary(int n) {
		keys = new Object[n];
		values = new Object[n];  
	} 

	protected void setKeys(Object[] keys) {
		this.keys = keys;
	}

	protected void setValues(Object[] values) {
		this.values = values;
	}

	public Object[] getKeys() {
		return keys;
	}

	public Object[] getValues() {
		return values;
	}

	// Abstract methods
	/* rend l'index si il y est, -1 sinon */
	abstract protected int indexOf(Object key); 

	/* si key n'est pas dans le dictionnaire, rend une index possible (appelée avant indexOf) */
	abstract protected int newIndexOf(Object key); 

	// Methods
	public int size() {
		int nb = 0;
		for (Object i : this.keys) {
			if (i != null) {
				nb += 1 ;
			}
		}
		return nb;
	}    

	@Override
	public String toString() {
		return "AbstractDictionary [keys=" + Arrays.toString(keys) + ", values=" + Arrays.toString(values) + "]";
	}

	public Object get(Object key) {
		int i = this.indexOf(key);

		if (this.isEmpty()) {
			return null;
		} else if (i == (-1)) { 
			return null;
		} else {
			return values[i];
		}
	}
	protected void grow() {
		Object[] newKeys = Arrays.copyOf(this.getKeys(), this.getKeys().length + 1);
		Object[] newValues = Arrays.copyOf(this.getValues(), this.getKeys().length + 1);
		this.setKeys(newKeys);
		this.setValues(newValues); 	
	}
	public IDictionary put(Object key, Object value) {
		int i = indexOf(key);

		if (i == -1) {
			int j = this.newIndexOf(key);
			keys[j] = key;
			values[j] = value;   
		} else {
			values[i] = value; 
		} 

		return this;
	}

	public boolean isEmpty() {
		return this.size() == 0; 
	}

	public boolean containsKey(Object key) {
		return indexOf(key) != -1;
	}
}