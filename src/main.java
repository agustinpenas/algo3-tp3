import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class main {
	public static void main(String[] args){

	/*	Grafo g1 = new Grafo(7);
		Grafo g2 = new Grafo(8);
		g1.agregarNodo(0);
		g1.agregarNodo(1);
		g1.agregarNodo(2);
		g1.agregarNodo(3);
		g1.agregarNodo(4);
		g1.agregarNodo(5);
		g1.agregarNodo(6);
		g1.agregarArista(0, 6);
		g1.agregarArista(0, 1);
		g1.agregarArista(1, 4);
		g1.agregarArista(1, 2);
		g1.agregarArista(2, 5);
		g1.agregarArista(2, 3);
		g1.agregarArista(4, 3);
		g1.agregarArista(3, 5);
		g2.agregarNodo(0);
		g2.agregarNodo(1);
		g2.agregarNodo(2);
		g2.agregarNodo(3);
		g2.agregarNodo(4);
		g2.agregarNodo(5);
		g2.agregarNodo(6);
		g2.agregarNodo(7);
		g2.agregarArista(0, 1);
		g2.agregarArista(0, 4);
		g2.agregarArista(1, 4);
		g2.agregarArista(1, 7);
		g2.agregarArista(1, 2);
		g2.agregarArista(4, 6);
		g2.agregarArista(4, 3);
		g2.agregarArista(3, 5);
		g2.agregarArista(2, 3);
		MCSExacto mcs = new MCSExacto();
		Grafo g3 = mcs.resolver(g1, g2);
		
		System.out.println(g3.getListaAdyacencia().toString());
		System.out.println(g1.getListaAdyacencia().toString());
		System.out.println(g2.getListaAdyacencia().toString());*/
		
		Stack<Grafo> grafos = new Stack<>();
		
		GraphGenerator fabrica = new GraphGenerator();
		
		grafos = GraphGenerator.getRandomGraphs(5, 0.5, 100);
		
		for(Grafo g : grafos){
			System.out.println(g.getListaAdyacencia().toString());
			System.out.println(g.getNodos().toString());
		}
	}
}
