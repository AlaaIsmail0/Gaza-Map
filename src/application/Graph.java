package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Graph {
	public static ArrayList<Vertex> cities = new ArrayList<>();
	static double org_xMin = 34.1707489947603;
	static double org_xMax = 34.575060834817954;
	static double org_yMin = 31.208163033163977;
	static double org_yMax = 31.614521165206845;
	static int yourWidth;
	static int yourHeight;

	public void dijkstra(Vertex sourceVertex, Vertex target) {
		for (int i = 0; i < cities.size(); i++) {
			cities.get(i).setDistance(Double.MAX_VALUE);
			cities.get(i).setKnown(false);
			cities.get(i).setPrev(null);
		}

		sourceVertex.setDistance(0);
		MyPriorityQueue<Vertex> priorityQueue = new MyPriorityQueue<>(Comparator.comparingDouble(Vertex::getDistance));
		priorityQueue.add(sourceVertex);
		sourceVertex.setKnown(true);

		while (!priorityQueue.isEmpty()) {
			// Extract the vertex with the smallest distance
			Vertex currVertex = priorityQueue.poll();
			// Check if the target vertex is reached
			if (currVertex == target)
				break;

			// Process neighbors of the current vertex
			if (currVertex != null) {
				for (Edge edge : currVertex.getAdj()) {
					Vertex v = edge.getTargetVertex();
					// Check if the neighbor is not explored yet
					if (!v.isKnown()) {
						// Check if the path if shorter than the known distance to the neighbor
						if (currVertex.getDistance() + edge.getWeight() < v.getDistance()) {
							v.setDistance(currVertex.getDistance() + edge.getWeight());
							v.setPrev(currVertex);
							priorityQueue.add(v);

						}
					}
				}
				currVertex.setKnown(true);
			}
		}
	}

	public static ArrayList<Vertex> readcities(int size1, int size2) throws FileNotFoundException {
	    Scanner scanner = new Scanner(new File("C:\\Users\\EASY LIFE\\Eclipse IDE\\.metadata\\Gaza\\src\\application\\cities.txt"));

	    String line;
	    String tokens[];

	    while (scanner.hasNextLine()) {
	        line = scanner.nextLine();
	        tokens = line.split(",");
	        double lat = Double.parseDouble(String.format("%.3f", Double.parseDouble(tokens[1])));
	        double lon = Double.parseDouble(String.format("%.3f", Double.parseDouble(tokens[2])));

	        double mercatorX = (lon - org_xMin) / (org_xMax - org_xMin) * yourWidth;
	        double mercatorY = (lat - org_yMin) / (org_yMax - org_yMin) * yourHeight;

	        cities.add(new Vertex(new city(tokens[0], mercatorX, mercatorY, lat, lon)));
	    }

	    scanner.close();
	    readEdges();
	    return cities;
	}


	public static void readEdges() throws FileNotFoundException {
		Scanner scanner = new Scanner(
				new File("C:\\Users\\EASY LIFE\\Eclipse IDE\\.metadata\\Gaza\\src\\application\\data.txt"));

		String line;
		String tokens[];
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			tokens = line.split("-");

			Vertex v1 = Search(tokens[0]);
			Vertex v2 = Search(tokens[1]);
			Double distance = Double.parseDouble(String.format("%.3f", getDistance(v1, v2)));
			Edge e = new Edge(v1, v2, distance);
			v1.addNeighbour(e);
		}

	}

	public static Vertex Search(String name) throws FileNotFoundException {
		Vertex v = null;
		for (int i = 0; i < cities.size(); i++) {
			if (name.compareTo(cities.get(i).getcity().getName()) == 0) {
				v = cities.get(i);
			}
		}
		return v;
	}

	public ArrayList<String> PrintPath(Vertex source, Vertex targetVertex) {
		ArrayList<String> path = new ArrayList<>();
		if (source.getcity().getName().equals(targetVertex.getcity().getName())) {
			path.add(source.getcity().getName() + " " + source.getDistance());
		} else if (targetVertex.getPrev() == null) {
			path.add("No Path");
		} else {
			for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPrev()) {
				path.add(vertex.getcity().getName() + " " + vertex.getDistance());
			}
			Collections.reverse(path);
		}
		return path;
	}

	public static Double getDistance(Vertex A, Vertex B) {
		double lat1 = Math.toRadians(A.getcity().lat);
		double lat2 = Math.toRadians(B.getcity().lat);
		double lon1 = Math.toRadians(A.getcity().lon);
		double lon2 = Math.toRadians(B.getcity().lon);

		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.asin(Math.sqrt(a));
		double r = 6371;
		return (c * r);
	}
}
