package application;

import java.util.*;

public class Vertex implements Comparable<Vertex> {

	private city city;
	private ArrayList<Edge> adj;
	private boolean known;
	private Vertex prev;
	private double distance;

	public Vertex(city city) {
		this.city = city;
		this.adj = new ArrayList<>();
	}

	public void addNeighbour(Edge edge) {
		this.adj.add(edge);
	}

	public city getcity() {
		return city;
	}

	public void setcity(city city) {
		this.city = city;
	}

	public ArrayList<Edge> getAdj() {
		return adj;
	}

	public void setAdj(ArrayList<Edge> adj) {
		this.adj = adj;
	}

	public boolean isKnown() {
		return known;
	}

	public void setKnown(boolean known) {
		this.known = known;
	}

	public Vertex getPrev() {
		return prev;
	}

	public void setPrev(Vertex prev) {
		this.prev = prev;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Vertex otherVertex) {
		return Double.compare(this.distance, otherVertex.getDistance());
	}

}
