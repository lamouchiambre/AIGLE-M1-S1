package com.company;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int nbCol1 = 0;
		while (nbCol1 <= 0) {
			System.out.println("Veuillez saisir un nombre de couleur pour le graphe 1 (diamant)");
			nbCol1 = new Scanner(System.in).nextInt();
		}
		
		Vertex X1 = new Vertex("X");
		Vertex Y1 = new Vertex("Y");
		Vertex T1 = new Vertex("T");
		Vertex Z1 = new Vertex("Z");
		
		Edge e1 = new Edge(X1, T1, false);
		Edge e2 = new Edge(X1, Y1, false);
		Edge e3 = new Edge(Y1, Z1, false);
		Edge e4 = new Edge(T1, Z1, false);
		Edge e5 = new Edge(Z1, X1, true);
		
		System.out.println("Graphe 1: ");
		Graph G1 = new Graph();
		G1.add(e1);
		G1.add(e2);
		G1.add(e3);
		G1.add(e4);
		G1.add(e5);
		
		G1.print();
		System.out.println();
		G1.color(nbCol1);
		System.out.println();
		
		int nbCol2 = 0;
		while (nbCol2 <= 0) {
			System.out.println("Veuillez saisir un nombre de couleur pour le graphe 2 (cours)");
			nbCol2 = new Scanner(System.in).nextInt();
		}
		
		Graph G2 = new Graph();
		Vertex X = new Vertex("X");
		Vertex Y = new Vertex("Y");
		Vertex U = new Vertex("U");
		Vertex V = new Vertex("V");
		Vertex T = new Vertex("T");
		Vertex Z = new Vertex("Z");
		
		G2.add(new Edge(X, Y, false));
		G2.add(new Edge(X, U, false));
		G2.add(new Edge(X, V, false));
		G2.add(new Edge(Y, U, false));
		G2.add(new Edge(T, V, false));
		G2.add(new Edge(V, Z, false));
		G2.add(new Edge(U, T, true));
		G2.add(new Edge(Y, T, false));
		
		System.out.println();
		System.out.println("Graphe 2:");
		G2.print();
		System.out.println();
		G2.color(nbCol2);
	}

}
