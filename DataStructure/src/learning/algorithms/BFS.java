package learning.algorithms;

import java.util.List;

import learning.datastructure.Queue;
import learning.datastructure.Graph;
import learning.datastructure.Graph.*;


/*
 * BFS
 * 	BFS is Iteration, breadth-first, implemented by queue.
 * 
 *  cannot use recursive, cannot recall itself.
 *  
 *  ***/

public class BFS {
	public static void BFS(Graph g, Vertice s) {
		//queue stores all edge children for future searching.
		Queue q = new Queue();
		s.setcolor(0);
		
		List<Vertice> vl = g.search(s);
		//mark all edge children to under searching, and add into queue for later searching.	
		for (Vertice v : vl) {
			if (v.getcolor() == -1) {
				q.Equeue(v.getname());
				v.setcolor(0);
			}
		}
		
		s.setcolor(1);
		
		while (!q.isEmpty()) {	
			//grep each for BFS.
			char name = (char)q.Dqueue();
			List<Vertice> vlu = g.searchbyname(name);
			Vertice u = vlu.get(0);

			//mark all edge children to under searching, and add into queue for later searching.	
			for (Vertice v : vlu) {
				if (v.getcolor() == -1) {
					q.Equeue(v.getname());
					v.setcolor(0);
				}
			}
			u.setcolor(1);
		}
		
		display(g);
	}
	
	public static void display(Graph g) {
		g.displaycolor();
	}

	
	
	public static void main(String[] args) {
		Graph g = Graph.buildGraph();
		BFS(g, g.adjacencylist[0].get(0));
	}
}
