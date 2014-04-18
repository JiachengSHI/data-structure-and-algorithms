package learning.datastructure;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/*
 * Graph
 * 	implemented by adjacencylist, which contains Vertices and edges.
 * 	
 *  Vertice: color, name, value, ......
 *  Edge: weight, start Vertice, end Vertice.
 *  
 *  
 *  addVertice(), add Vertice into Graph.
 *  removeVertice(), remove Vertice and its connected edges from Graph.
 *  
 *  addEdge(), add Edge into Graph.
 *  removeEdge(), remove Edge from Graph.
 *  
 *  searchbyname(), search Vertice in Graph by its name.
 *  search(), search Vertice in Graph by itself.
 *  
 *  ***/

public class Graph {
	public class Vertice {
		//mark in BFS or DFS.
		private int color;
		//leave an interface.
		private int value;
		private char name;
		
		public Vertice(char name) {
			//default color = -1, under search color = 0, finish search color = 1.
			this.color = -1;
			this.name = name;
		}
		
//******//Encapsulation, only can access local variable by public method, rather than access directly, to protect program from others.
		public void setcolor(int color) {
			this.color = color;
		}
		
		public void setvalue(int value) {
			this.value = value;
		}
		
		public int getcolor() {
			return this.color;
		}
		
		public int getvalue() {
			return this.value;
		}
		
		//careful to return type, which will cause cast problem and bugs.
		public char getname() {
			return this.name;
		}
		
		public void display() {
			System.out.print(this.name + " ");
		}
		
		public void displaycolor() {
			System.out.print("name: "+this.name + " color: " + this.color + " ");
		}
	}
	
	public class Edge {
		//determine whether its a weighted or unweighted Graph
		private int weight;
		private Vertice s;
		private Vertice e;
		
		public Edge(Vertice u, Vertice v, int weight) {
			this.weight = weight;
			this.s = u;
			this.e = v;
		}
		
		//Encapsulation
		public void setweight(int weight) {
			this.weight = weight;
		}
		
		public int getweight() {
			return this.weight;
		}
	}
	
	//current size
	protected int size;
	//adjacency list
	public List<Vertice>[] adjacencylist;
	
	public Graph(int size) {
		this.size = 0;
		this.adjacencylist = (List<Vertice>[])new List[size];
	}
	
	//add & remove Vertice
	public void addVertice(Vertice v) {
		if (this.search(v) == null) {
			if (this.size < this.adjacencylist.length) {
				this.adjacencylist[size] = new ArrayList<Vertice>();
				this.adjacencylist[size].add(v);
				this.size++;
			} else {
				System.err.println("graph overflow, cannot add additional vertice.");
			}
		} else {
			System.err.println("vertice exists, cannot add vertice again.");
		}
	}
	
	public void removeVertice(Vertice v) {
		List<Vertice> vl = this.search(v);
		if (vl != null) {
			//remove all edge contains this vertice in other connected vertices.
			for (int i=1; i<vl.size(); i++) {
				if (vl.get(i) != null) {
					this.search(vl.get(i)).remove(vl.get(0));
				} else {
					break;
				}
			}

			//remove Vertice and its own edges.
			List<List<Vertice>> tmp = Arrays.asList(this.adjacencylist);
			tmp.remove(vl);
			this.adjacencylist = (List<Vertice>[]) tmp.toArray();
		} else {
			System.err.println("vertice does not exist, cannot remove vertice.");
		}
	}
	
	
	//add & remove edge
	//determine whether its a directed or undirected Graph.
	//undirected
	public void addEdge(Edge e) {
		Vertice start = e.s;
		Vertice end = e.e;
		List<Vertice> vls = this.search(start);
		List<Vertice> vle = this.search(end);
		if (vls != null && vle != null) { 
			if (!vls.contains(end) && !vle.contains(start)) {
				vls.add(end);
				vle.add(start);
			} else {
				System.err.println("edge exists, cannot add edge again.");
			}
		} else {
			System.err.println("vertice does not exist, cannot add edge.");
		}
	}

	public void removeEdge(Edge e) {
		Vertice start = e.s;
		Vertice end = e.e;
		List<Vertice> vls = this.search(start);
		List<Vertice> vle = this.search(end);
		if (vls != null && vle != null) { 
			if (vls.contains(end) && vle.contains(start)) {
				vls.remove(end);
				vle.remove(start);
			} else {
				System.err.println("edge does not exist, cannot remove edge.");
			}
		} else {
			System.err.println("vertice does not exist, cannot remove edge.");
		}
	}
	
	//search method
	//search certain vertice by name in Graph.
		public List<Vertice> searchbyname(char s) {
			if (this.size != 0) {
				for (List<Vertice> vl : this.adjacencylist) {
					if (vl!= null) {
						//only search the exist Vertice list.
						if (vl.get(0).getname() == s) {
							return vl;
						}
					} else {
						//stop at list tail.
						break;
					}
				}
			}
			return null;
		}
	
	
	//search vertice in Graph.
	public List<Vertice> search(Vertice s) {
		if (this.size != 0) {
			for (List<Vertice> vl : this.adjacencylist) {
				if (vl!= null) {
					//only search the exist Vertice list.
					if (vl.get(0) == s) {
						return vl;
					}
				} else {
					//stop at list tail.
					break;
				}
			}
		}
		return null;
	}
	
	//display Graph
	public void display() {
		for (List<Vertice> vl : this.adjacencylist) {
			for (Vertice v : vl) {
				v.display();
			}
			System.out.println();
		}
	}
	
	//display Graph
	public void displaycolor() {
		for (List<Vertice> vl : this.adjacencylist) {
			for (Vertice v : vl) {
				v.displaycolor();
			}
			System.out.println();
		}
	}
	
	
	
	public static void main(String[] args) {
		Graph g = new Graph(10);
		
		for (int i=0; i<10; i++) {
			//inner class must declare outer class "g."
			Vertice v = g.new Vertice((char)(i+97));
			v.setvalue(i);
			g.addVertice(v);
		}
		
		for (int i=0; i<10; i++) {
			for (int j=0; j< 10; j++) {
				Edge e = g.new Edge(g.adjacencylist[i].get(0), g.adjacencylist[j].get(0), j);
				g.addEdge(e);
			}
		}
		
		g.display();
		g.displaycolor();
	}
	
	
	//build a undirected weighted Graph
	public static Graph buildGraph() {
		Graph g = new Graph(10);
		
		for (int i=0; i<10; i++) {
			//inner class must declare outer class "g."
			Vertice v = g.new Vertice((char)(i+97));
			v.setvalue(i);
			g.addVertice(v);
		}
		
		for (int i=0; i<10; i++) {
			for (int j=0; j< 10; j++) {
				Edge e = g.new Edge(g.adjacencylist[i].get(0), g.adjacencylist[j].get(0), j);
				g.addEdge(e);
			}
		}
		
		return g;
	}
}
