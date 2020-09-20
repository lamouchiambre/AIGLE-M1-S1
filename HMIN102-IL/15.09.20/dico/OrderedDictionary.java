package tp1_2;

//import java.util.Arrays;

public class OrderedDictionary extends AbstractDictionary {
	//attributs
	//private int nb = -1;

	protected OrderedDictionary() {
		super(0);
		// TODO Auto-generated constructor stub
	}
	
	protected OrderedDictionary(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	protected int newIndexOf(Object key) {
		if (this.getKeys().length == this.size()) {
			this.grow();
		}		
		return this.size();
	}

	protected int indexOf(Object key) {
		int i = 0;
		while (i < this.size()) {
			if (this.getKeys()[i] == key) {
				return i;
			}
			i += 1;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		OrderedDictionary od = new OrderedDictionary(3);
		//OrderedDictionary od0 = new OrderedDictionary();
		od.put("alex", "amoureux");
		od.put("ambre", "amoureuse");
		od.put("alexambre", "coeur");
		od.put("10", "amoureux");
		System.out.println(od.indexOf("10"));
		Object[] t1 = new Object[2];
		t1[0] = 1;
		t1[1] = 2;
		//Object[] t2 = new Object[t1.length]; 
		//Object[] t2 = Arrays.copyOf(t1, t1.length +1); 
		System.out.println(od);

	}
}