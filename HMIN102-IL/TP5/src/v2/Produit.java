package v2;

public class Produit {
	double prixBase;
	double TVA = 19.6;
	double marge = 1.10;
	String titre;
	
	public Produit(String titre, double pb) {
		this.titre = titre;
		this.prixBase = pb;
	}
	
	protected double prixHT() { return prixBase * marge; }
	
	double prixVente() { return (this.prixHT() * (1 + TVA)); }
	
	double prixLocation() {
		return (this.prixVente() * 5) / 100;
	}

	public static void main(String[] args) {
		Produit lgv = new Produit("La grande vadrouille", 10.0);
		Client cl = new Client("Dupont");
//		Compte cmt = new Compte(cl);
//		cmt.prixLocation(lgv);
		
		// double dispatch - etape 1 - envoi de message à un Compte
//		System.out.println("Compte : " + cmt.prixLocation(lgv));
		
		Compte cmt2 = new CompteAvecReduction(cl);
		System.out.println("CompteReduction : " + cmt2.prixLocation(lgv));
		
		Compte cmt3 = new CompteAvecSeuil(cl);
		System.out.println("CompteSeuil : " + cmt3.prixLocation(lgv));
		
		Produit r = new ProduitSolde("RockyIV", 10.0);
//		System.out.println("Compte+ProduitSolde : " + cmt.prixLocation(r));
		System.out.println("CompteReduction+ProduitSolde : " + cmt2.prixLocation(r));
	}
}

class ProduitSolde extends Produit {
	public ProduitSolde(String titre, double pb) {
		super(titre, pb);
	}
	
	double prixVente() { return (super.prixVente() / 2); }
}

class Client {
	protected String nom;
	protected Compte compte;
	public void setCompte(Compte c) { compte = c; }
	public Compte getCompte() { return compte; }
	
	public Client(String nom) {
		this(nom, null);
	}
	
	public Client(String nom, Compte c) {
		this.nom = nom;
		this.compte = c;
	}
}

class Compte {
	public double prixLocation(Produit p) {
		return p.prixLocation();
	}
}

class CompteBasique extends Compte {
	static int nbComptes = 0;
	int numero;
	Client titulaire;
	
	public CompteBasique(Client cl) {
		titulaire = cl;
		cl.setCompte(this);
		this.numero = ++nbComptes;
	}
}

class Forfait extends Compte {
	Compte decore;
	public Forfait(Compte c) {
		decore = c;
	}
	
	public double prixLocation(Produit p) {
		return decore.prixLocation(p);
	}
}

class ForfaitReduction extends Forfait {
	// donne une reduction de 10% sur chaque location
	double reduction = 0.10;
	
	public ForfaitReduction(Compte c) { super(c); }
	
	public double prixLocation(Produit p) {
		return (super.prixLocation(p) * (1 - reduction)); 
	}
}

class CompteAvecReduction extends CompteBasique {
	// donne une reduction de 10% sur chaque location
	double reduction = 0.10;
	
	public CompteAvecReduction(Client cl) { super(cl); }
	
	public double prixLocation(Produit p) {
		return (super.prixLocation(p) * (1 - reduction));
	}
}


class CompteAvecSeuil extends CompteBasique {
	// donne une location gratuite apr_s $compteur payantes
	static int init = 2;
	int compteur = init;
	// le compteur est incrémenté à chaque fois que le produit est loué
	// (méthode non écrire)
	
	public CompteAvecSeuil(Client cl) { super(cl); }
	
	public double prixLocation(Produit p) {
		if (--compteur == 0) {
			compteur = init;
			return 0.0;
		} else {
			return super.prixLocation(p);
		}
	}
}
