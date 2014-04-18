package learning.algorithms;

import learning.datastructure.Graph;
import learning.datastructure.Graph.*;
import java.util.List;

/*
 * DFS
 * 	DFS is Recursive call, Depth-first, implemented by list.
 * 
 *  use recursive to go to the end (bottom) of the tree.
 *  	if the graph connect well, it can recursive search all vertices by DFS one vertice.
 *  **/

public class DFS {
	public static void DFS(Graph g) {
		for (List<Vertice> vl : g.adjacencylist) {
			if (vl.get(0).getcolor() == -1) {
				//mark Vertice as under searching.
				vl.get(0).setcolor(0);
				
				//recursive go to deep.
				DFSVisit(g, vl);
				
				display(g);
				System.out.println();
			}
		}
	}
	
	//Recursive call in DFS
	public static void DFSVisit(Graph g, List<Vertice> vl) {
		//check all children.
		for (int i=1; i<vl.size(); i++) {
			if (vl.get(i) != null) {
				//recursive call DFSVisit on each child.
				if (vl.get(i).getcolor() == -1) {
					//mark Vertice as under searching.
					vl.get(0).setcolor(0);
					
					//recursive go to deep.
					List<Vertice> nvl = g.search(vl.get(i));
					DFSVisit(g, nvl);
				}
			} else {
				break;
			}
		}
		//mark as finish searching
		vl.get(0).setcolor(1);
		
	}
	
	
	public static void display(Graph g) {
		g.displaycolor();
	}
	
	public static void main(String[] args) {
		Graph g = Graph.buildGraph();
		display(g);
		System.out.println();
		DFS(g);
		
	}
}
