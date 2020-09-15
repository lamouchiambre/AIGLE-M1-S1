import java.util.ArrayList;

public abstract class Dico implements IDico {
  private Object[] keys;
  private Object[] values;
  
  abstract protected int indexOf(Object key); // rend index si il y est, -1 sinon
  abstract protected int newIndexOf(Object key); // rend index pourrait être, appelée avant indexOf si key pas dans le dictionnaire

  public Object get(Object key) {
    int i = this.indexOf(key);
    if (this.isEmpty()) {
      return null;
    } else {
      if (i == (-1)) {
        return null;
      } else {
        return values[i];
      }
    }
  }

  abstract public IDico put(Object key, Object value);
  // {
  //   keys.add(index,key);
  //   values.add(index,value);
  //   return this;
  // }

  public boolean isEmpty() {
    return (this.keys == null) && (this.values == null)
    || (this.keys.length == 0) && (this.values.length == 0); 
  }

  public boolean containsKey(Object key) {
    for (int i = 0; i < this.keys.length; i++) {
      if (key == keys[i]) {
        return true;
      }
    }
    return false;
  }

 }