package il_tp9_state;

public class EOperateur extends EtatCalculette {
	
	EOperateur(Calculette c) { 
		super(c); 
	}
	
	public int enter(String s) throws CalculetteException {
		calc.setOperateur(s);
		return(3);
	}
}
