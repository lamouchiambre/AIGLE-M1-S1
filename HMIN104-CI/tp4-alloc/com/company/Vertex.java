package com.company;

import java.util.ArrayList;

public class Vertex {

    protected String name;
    protected int color;
    protected ArrayList<Vertex> listInter;
    protected ArrayList<Vertex> listPref;
    boolean isActive;
    protected int order;

    public Vertex(String name) {
        this.name = name;
        this.color = -1;
        this.listInter = new ArrayList<>();
        this.listPref = new ArrayList<>();
        this.isActive = true;
    }

    // Getters
    public String getName() {
        return this.name;
    }
    public int getColor() {
        return this.color;
    }
    public int getDegree(){
        return this.listInter.size();
    }
    public ArrayList<Vertex> getListInter() {
        return this.listInter;
    }
    public ArrayList<Vertex> getListPref() {
        return this.listPref;
    }
    public boolean isActive() {
        return this.isActive;
    }
    public int getOrder() {
    	return this.order;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public void setActive(boolean active) {
    	this.isActive = active;
    }
    public void setOrder(int order) {
    	this.order = order;
    }

    // Others methods
    public void addInterNeighbour(Vertex neighbours) {
        if(!this.listInter.contains(neighbours))
            this.listInter.add(neighbours);
    }
    public void addPrefNeighbour(Vertex neighbours) {
        if(!this.listPref.contains(neighbours))
            this.listPref.add(neighbours);
    }

    public void removeInterNeighbour(Vertex neighbour) {
        this.listInter.remove(neighbour);
    }
    public void removePrefNeighbour(Vertex neighbour) {
        this.listPref.remove(neighbour);
    }

    public boolean hasBeenPrefColored() {
        boolean res = false;
        for (Vertex vertice : listPref) {
            if (vertice.getColor() == this.color) {
                res = true;
            }
        }
        return res;
    }

    public boolean triviallyColorable(int seuil){
        return getDegree() < seuil;
    }

    @Override
    public String toString(){
        //TODO a finir
        String affichage = "Sommet " + this.getName() + " ";
        if (this.getColor() == 0)
            return affichage;
        affichage += "couleur: " + this.getColor() ;

        return affichage;
    }
}