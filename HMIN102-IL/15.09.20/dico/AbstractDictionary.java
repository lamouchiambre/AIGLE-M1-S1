
public abstract class AbstractDictionary implements IDictionary {
  // Attributes
  private Object[] keys;
  private Object[] values;

  // Constructor
  protected AbstractDictionary(int n) {
    keys = new Object[n];
    values = new Object[n];  
  } 
  
  // Abstract methods
  /* rend l'index si il y est, -1 sinon */
  abstract protected int indexOf(Object key); 
  
  /* si key n'est pas dans le dictionnaire, rend une index possible (appelée avant indexOf) */
  abstract protected int newIndexOf(Object key); 

  // Methods
  public int size() {
    return keys.length;
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