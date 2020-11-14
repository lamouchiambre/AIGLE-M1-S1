package il_tp8;

import java.util.Scanner;
public class Node {

	protected String label = "";
	
	protected Color color = Color.colorGreen;
	protected Shape shape = Shape.boxShape;

	public String getLabel() { return label; }
	public void setLabel(String label) { this.label = label; }

	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }

	public Shape getShape() { return shape; }
	public void setShape(Shape shape) { this.shape = shape; }

	
	private void createNewNode() {
		
		Node n = new Node();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("> Please give a label : ");
		n.setLabel(sc.nextLine());

		//#if color
		System.out.print("> Please select a color [");
		//#if colorRed
		System.out.print("r - red , ");
		//#endif
		//#if colorGreen
//@		System.out.print("g - green , ");
		//#endif
		System.out.print("n - none] : ");

		userColor = sc.newLine();
		//if ()
		n.setColor(sc.newLine());
		
		//#endif

        //A COMPLETER ICI 
        return n;
	}
        
	@Override
	public String toString() {
		String s = "";
                
		s += label + " [ ";
//#if color
		s += "color=" + this.color + ",";
//#endif
		s += "shape=" + this.shape;
		s += "]\n";
                
		return s;
	}
}
