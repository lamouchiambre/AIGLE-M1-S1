package com.company;

import java.util.ArrayList;

public class Graph {
	// Attributes
    protected ArrayList<Vertex> vertices;
    protected ArrayList<Edge> edges;
    protected int vProcessed;
    
    // Constructor
    public Graph() {
    	this.vertices = new ArrayList<Vertex>();
    	this.edges = new ArrayList<Edge>();
    	this.vProcessed = 0;
    }
    
    // Getters
    public ArrayList<Vertex> getVertices() {
    	return this.vertices;
    }
    public ArrayList<Edge> getEdges() {
    	return this.edges;
    }
    public int getVProcessed() {
    	return this.vProcessed;
    }
    
    // Setters
    public void setVProcessed(int vProcessed) {
    	this.vProcessed = vProcessed;
    }
    
    // Others methods  
    public int minDegree() {
    	int result = 2147483647;
    	for (Vertex v : this.getVertices()) {
    		if (v.isActive() && v.getDegree() <= result) {
    			result = v.getDegree();
    		}
    	}
    	return result;
    }
    public int maxDegree() {
    	int result = 0;
    	for (Vertex v : this.getVertices()) {
    		if (v.getDegree() > result) {
    			result = v.getDegree();
    		}
    	}
    	return result;
    }
    
    public void add(Edge e) {
    	if (e.isPreference()) { // pref
    		e.getV1().addPrefNeighbour(e.getV2());
    		e.getV2().addPrefNeighbour(e.getV1());
    	} else { // inter
    		e.getV1().addInterNeighbour(e.getV2());
    		e.getV2().addInterNeighbour(e.getV1());
    	}
    	e.setActive(true);
    	if (!this.getVertices().contains(e.getV1())) {
    		this.getVertices().add(e.getV1());
    	}
    	if (!this.getVertices().contains(e.getV1())) {
    		this.getVertices().add(e.getV1());
    	}
    	this.getEdges().add(e);
    }
    
    public void update(Vertex v) {
    	for (Edge e : this.getEdges()) {
    		if (v.isActive()) {
    			if (e.containsVertex(v) && e.getV1().isActive() && e.getV2().isActive()) {
    				e.setActive(true);
    			}
    		} else {
    			if (e.containsVertex(v) && e.getV1().isActive() || !e.getV2().isActive()) {
    				e.setActive(false);
    			}
    		}
    	}
    }
    
    public int chooseColor(Vertex v, int nbColor) {
    	boolean[] colors = new boolean[nbColor];
    	boolean[] prefColors = new boolean[nbColor];
    	int result = -1;
    	int j = 0;
    	
    	for (int i = 0; i < nbColor; i++) {
    		colors[i] = true;
    		prefColors[i] = false;
    	}
    	
    	for (Vertex ver : v.getListInter()) {
    		if (ver.getColor() != -1) {
    			colors[ver.getColor()] = false;
    		}
    	}
    	for (Vertex ver : v.getListPref()) {
    		if (ver.getColor() != -1) {
    			colors[ver.getColor()] = true;
    		}
    	}
    	
    	while (result == -1 && j < nbColor) {
    		if (colors[j]) {
    			result = j;
    		}
    		j++;
    	}
    	
    	return result;
    }
    
    public void print() {
    	System.out.println("Vertices:");
    	for (Vertex v : this.getVertices()) {
    		v.toString();
    	}
    	
    	System.out.println("Edges:");
    	for (Edge e : this.getEdges()) {
    		e.toString();
    	}
    }
    
    public void color(int nbColor) {
    	int index = -1;
    	int spiller = -1;
    	
    	for (Vertex v : this.getVertices()) {
    		if (v.isActive() && v.triviallyColorable(nbColor) && v.getDegree() == this.minDegree()) {
    			index = this.getVertices().indexOf(v);
    		}
    		if (v.isActive() && !v.triviallyColorable(nbColor) && v.getDegree() == this.maxDegree()) {
    			spiller = this.getVertices().indexOf(v);
    		}
    	}
    	
    	if (index > -1) {
    		this.getVertices().get(index).setActive(true);
    		this.getVertices().get(index).setOrder(vProcessed);
    		this.update(this.getVertices().get(index));
    		this.setVProcessed(this.getVProcessed() + 1);
    		this.color(nbColor);
    		this.getVertices().get(index).setActive(true);
    		this.update(this.getVertices().get(index));
    		this.getVertices().get(index).setColor(this.chooseColor(this.getVertices().get(index), nbColor));
    		
    		System.out.print(this.getVertices().get(index).getName() + " color : " + this.getVertices().get(index).getColor());
    		if (this.getVertices().get(index).hasBeenPrefColored()) {
    			System.out.print(" (pref coloored)");
    		}
    		System.out.println();
    	} else {
    		System.out.println("Spiller vertex : " + this.getVertices().get(spiller).getName());
    		this.getVertices().get(spiller).setActive(false);
    		this.getVertices().get(spiller).setOrder(-1);
    		this.update(this.getVertices().get(spiller));
    		this.setVProcessed(this.getVProcessed() + 1);
    		this.color(nbColor);
    		this.getVertices().get(spiller).setActive(true);
    		this.update(this.getVertices().get(spiller));
    		this.getVertices().get(spiller).setColor(this.chooseColor(this.getVertices().get(spiller), nbColor));
    		if (this.chooseColor(this.getVertices().get(spiller), nbColor) != -1) {
    			System.out.println(this.getVertices().get(spiller).getName() + " color : " 
    					+ this.getVertices().get(spiller).getColor() + "(optimistic version)");
    		} else {
    			System.out.println(this.getVertices().get(spiller).getName() + " color : uncolorable (optimistic version)");
    		}
    	}
    }
}