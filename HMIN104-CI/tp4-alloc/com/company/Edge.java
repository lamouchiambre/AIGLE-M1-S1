package com.company;

public class Edge {
	// Attributes
    Vertex v1;
    Vertex v2;
    boolean preference;
    boolean isActive;

    // Constructor
    public Edge(Vertex v1, Vertex v2, boolean preference) {
    	this.v2 = v2;
    	this.preference = preference;
    	this.isActive = false;
    }
    
    // Getters
    public Vertex getV1() {
        return v1;
    }
    public Vertex getV2() {
        return v2;
    }
    public boolean isPreference() {
        return preference;
    }
    public boolean isActive(){
        return isActive;
    }
    
    // Setters
    public void setV1(Vertex v1) {
        this.v1 = v1;
    }
    public void setV2(Vertex v2) {
        this.v2 = v2;
    }
    public void setPreference(boolean preference) {
        this.preference = preference;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;

        if (!preference) {
            //interference
            if (isActive) {
                //arrete vivante
                this.v1.addInterNeighbour(this.getV2());
                this.v2.addInterNeighbour(this.getV1());
            } else {
                //arrete morte
                this.v1.removeInterNeighbour(this.getV2());
                this.v2.removeInterNeighbour(this.getV1());
            }
        } else {
            //preference
            if (isActive) {
                //  arrete vivante
                this.v1.addPrefNeighbour(this.getV2());
                this.v2.addPrefNeighbour(this.getV1());
            } else {
                // arrete morte
                this.v1.removePrefNeighbour(this.getV2());
                this.v2.removePrefNeighbour(this.getV1());
            }
        }
    }

    // Others Methods
    public void updateEdge(Vertex v) {

        if (this.containsVertex(v)) {
            if (!v.isActive() && this.isActive) {
                this.isActive = false;
            } else if (this.getV1().isActive() && this.getV2().isActive()) {
                this.isActive = true;
            }
        }
    }

    public boolean containsVertex(Vertex v) {
        return this.getV1().equals(v) || this.getV2().equals(v);
    }
}