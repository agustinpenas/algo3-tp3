import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


public class main {
	public static void main(String[] args){


		Grafo g1 = new Grafo(7);
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
		
		
		
		
		
		MCSExacto mcsexacto = new MCSExacto();
		MCSTaboo mcs = new MCSTaboo(); 
		MCSGreedy mcsgreedy = new MCSGreedy();
		MCSLocalSearch mcsLocal = new MCSLocalSearch();
		Grafo g3 = mcs.resolverTabu(g1, g2,1000).getGrafoSol();
		Grafo g4 = mcsexacto.resolver(g1, g2);
		Grafo g5 = mcsgreedy.resolver(g1, g2);
		Grafo g6 = mcsLocal.resolver(g1, g2);
		
		System.out.println("GREEDY: " + g5.getListaAdyacencia().toString());
		System.out.println("LOCAL: " + g6.getListaAdyacencia().toString());
		System.out.println("TABOO: " + g3.getListaAdyacencia().toString());
		System.out.println("EXACTO: " + g4.getListaAdyacencia().toString());
		System.out.println("GRAFO1: " + g1.getListaAdyacencia().toString());
		System.out.println("GRAFO2: " + g2.getListaAdyacencia().toString());
		
		/*Stack<Grafo> grafos = new Stack<>();
		
		GraphGenerator fabrica = new GraphGenerator();
		
		grafos = GraphGenerator.getRandomGraphs(5, 0.5, 100);
		
		for(Grafo g : grafos){
			System.out.println(g.getListaAdyacencia().toString());
			System.out.println(g.getNodos().toString());
		}*/
		
		int[] nodosV = new int[12];
		int[] nodosW = new int[12];
		int[] grados = new int[12];
		int[] grados2 = new int[12];
		for(int i =0; i<12;i++){
			nodosV[i]=i;
		}
		
		for(int i =0; i<12;i++){
			nodosW[i]=i;
		}
		
		grados[0]=5;
		grados[1]=6;
		grados[2]=3;
		grados[3]=8;
		grados[4]=11;
		grados[5]=1;
		grados[6]=4;
		grados[7]=13;
		grados[8]=2;
		grados[9]=9;
		grados[10]=10;
		grados[11]=1;
		
		grados2[0]=5;
		grados2[1]=6;
		grados2[2]=3;
		grados2[3]=8;
		grados2[4]=11;
		grados2[5]=1;
		grados2[6]=4;
		grados2[7]=13;
		grados2[8]=2;
		grados2[9]=9;
		grados2[10]=10;
		grados2[11]=56;
		
		MCSGreedy greedy = new MCSGreedy();
		Grafo V = new Grafo(12);
		Grafo W = new Grafo(12);
		for(int i =0; i<12;i++){
			V.agregarNodo(i);
			W.agregarNodo(i);
		}
		V.setGrados(grados);
		W.setGrados(grados2);;
		//List<Integer> pepe = greedy.MCSGreedy(V, W);
		
		//for(Integer i : pepe){
			//System.out.println(i);
		//}
		
	}
	

}
