package il_tp10_command_bidon;

//import java.util.List;
import java.util.Stack;
//import java.util.ArrayList;
//import java.util.HashMap;



// Command interface
interface ICommand {
	void execute();
	void undo(); 
}

// Command class
public abstract class CommandBidon implements ICommand {
	private double voldeplace;

	protected double getVolDeplace() { return voldeplace; }
	public void setVolDeplace(double voldeplace) { this.voldeplace = voldeplace; }
}

// Invoker class
class Lanceur {
	protected Stack<ICommand> history;

	public boolean hasNextUndo() {
		return !history.isEmpty();
	}

	// execute la commande c et la sauve dans l'historique
	public void pushCommand(ICommand c) {
		// gérer les exceptions
		c.execute();
		history.push(c);
	}

	// annule la commande la plus recente et la retir de l'hisorique
	public void popLastCommand() {
		history.lastElement().undo();
		history.pop();
	}
}

// ConcreteCommand class
class Bidon extends CommandBidon {
	protected double volume;
	protected double volumeMax;

	public Bidon(double volumeMax) {
		this.volumeMax = volumeMax;
	}

	public void execute() {

	}

	public double getVolume() { return this.volume; }
	public double getVolumeMax() { return this.volumeMax; }
	public void setVolume(double volume) { this.volume = volume; }

	public void vider() {
		this.setVolume(0);
	}
	public void remplir() {
		this.setVolume(volumeMax);
	}
	public double transvaser(Bidon dest) {
		double vdeplace;
		double sum = this.getVolume() + dest.getVolume();

		if (sum > dest.getVolumeMax()) {
			vdeplace = dest.getVolumeMax() - dest.getVolume();
			dest.setVolume(dest.getVolumeMax());
			this.setVolume(this.getVolume() - vdeplace);
		} else {
			vdeplace = this.getVolume();
			dest.setVolume(sum);
			this.vider();
		}

		return vdeplace;
	}

	@Override
	public void undo() {

	}
}

// ConcreteCommand class
class RemplirBidon extends CommandBidon {
	private Bidon b;

	public RemplirBidon(Bidon b) {
		this.b = b;
	}

	public void execute() {
		// pour prépare un éventuel undo
		this.setVolDeplace(b.getVolumeMax() - b.getVolume());
		// exécution de la commande promrement dite
		b.remplir();
	}

	public void undo() {
		b.setVolume(b.getVolume() - this.getVolDeplace());
	}
}

// ConcreteCommand class
class TransvaserBidon extends CommandBidon {
	protected Bidon source, destination;

	public TransvaserBidon(Bidon src, Bidon dst) {					
		source = src;
		destination = dst;
	}

	@Override
	public void execute() {
		this.setVolDeplace(source.transvaser(destination));
	}

	@Override
	public void undo() {
		source.setVolume(source.getVolume() + this.getVolDeplace());
		destination.setVolume(destination.getVolume() - this.getVolDeplace());
	}
}

public class Joueur {
	public static void main(String[] args) {
		Bidon b1 = new Bidon(1);
		Bidon b2 = new Bidon(2);
		Bidon b3 = new Bidon(3);
	}
}