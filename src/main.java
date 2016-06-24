import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class main {
	public static void main(String[] args){
		try {
		// Archivo de salida
		/*FileWriter fstreamexacto = new FileWriter("testExactoGrafoCompleto.txt");
        BufferedWriter archivoBufferexacto = new BufferedWriter(fstreamexacto);
        
		FileWriter fstreamgreedy = new FileWriter("testGreedyGrafo3.txt");
        BufferedWriter archivoBuffergreedy = new BufferedWriter(fstreamgreedy);*/
			
		List<Integer> lista = new ArrayList<>();
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		lista.add(6);
		lista.add(7);
		
		getAsignacionesDeVecindad2(lista, 4, 7);
        
		FileWriter fstreamlocal = new FileWriter("testLocalGrafo5.txt");
        BufferedWriter archivoBufferlocal = new BufferedWriter(fstreamlocal);
        
		/*FileWriter fstreamtaboo = new FileWriter("testTabooGrafo3.txt");
        BufferedWriter archivoBuffertaboo = new BufferedWriter(fstreamtaboo);*/
        
		/*FileWriter fstreamgreedycantAristas = new FileWriter("testGreedyGrafo3cantAristas.txt");
        BufferedWriter archivoBuffergreedycantAristas = new BufferedWriter(fstreamgreedycantAristas);*/
        
		FileWriter fstreamlocalcantAristas = new FileWriter("testLocalGrafo5cantAristas.txt");
        BufferedWriter archivoBufferlocalcantAristas = new BufferedWriter(fstreamlocalcantAristas);
        
		/*FileWriter fstreamtaboocantAristas = new FileWriter("testTabooGrafo3cantAristas.txt");
        BufferedWriter archivoBuffertaboocantAristas = new BufferedWriter(fstreamtaboocantAristas);*/
		
		
		/*Stack<Grafo> q1greedy = GraphGenerator.getRandomGraphs(50, 0.3, 100);
		Stack<Grafo> q2greedy = GraphGenerator.getRandomGraphs(50, 0.3, 150);*/
		
		Stack<Grafo> q1local = GraphGenerator.getRandomGraphs(50, 0.5, 100);
		Stack<Grafo> q2local = GraphGenerator.getRandomGraphs(50, 0.5, 150);
		/*
		Stack<Grafo> q1taboo = GraphGenerator.getRandomGraphs(50, 0.3, 100);
		Stack<Grafo> q2taboo = GraphGenerator.getRandomGraphs(50, 0.3, 150);*/
		
		
		while(!q1local.isEmpty()){
				  //LOCAL
//				  SalidaCMF solucion = new LocalSearch().resolverProblema(entrada);  
				  //EXACTO
//				  SalidaCMF solucion = new ExactSolver().resolverProblema(entrada);
				  //GOLOSO
//				  SalidaCMF solucion = new GreedyHeuristic().resolverProblema(entrada);
				  //TABU
			/*Grafo g1greedy = q1greedy.pop();
			Grafo g2greedy = q2greedy.pop();*/
			
			Grafo g1local = q1local.pop();
			Grafo g2local = q2local.pop();
			
			/*Grafo g1taboo = q1taboo.pop();
			Grafo g2taboo = q2taboo.pop();*/
			
				  int CantIteraciones =10;
				  double tabuListSize = 0.5;
				  MCSGreedy greedy = new MCSGreedy();
				  MCSLocalSearch local = new MCSLocalSearch();
				  MCSTaboo taboo = new MCSTaboo();
	
				 
				  long totalTiempo = 0, tiempoInicio = 0, tiempoPromedio = 0;
					int cantRepeticionesTest = 10;
					int cantAristas = 0;
					/*//greedy
					// **** se repite la misma prueba cantRepeticionesTest veces para buscar la media ****
					
					System.out.println("resolviendo greedy");
					for (int i = 0; i < cantRepeticionesTest; i++) {					
						tiempoInicio = System.nanoTime();
						cantAristas = greedy.resolver(g1greedy, g2greedy).getCantidadDeAristas();

						totalTiempo = System.nanoTime() - tiempoInicio;

						tiempoPromedio += totalTiempo;

						System.out.println("  -"+ i);
						System.out.println(tiempoPromedio);
						System.out.println(totalTiempo);
					}
					
					System.out.println("");
				  tiempoPromedio /= cantRepeticionesTest;
				  archivoBuffergreedy.write(tiempoPromedio+ " "+"\n" );
				  archivoBuffergreedycantAristas.write(cantAristas+ " " +"\n" );*/
				  
				  //local
				  System.out.println("resolviendo local");
				  tiempoPromedio = 0;
					// **** se repite la misma prueba cantRepeticionesTest veces para buscar la media ****
					for (int i = 0; i < cantRepeticionesTest; i++) {					
						tiempoInicio = System.nanoTime();
						cantAristas = local.resolver(g1local, g2local).getCantidadDeAristas();

						totalTiempo = System.nanoTime() - tiempoInicio;

						tiempoPromedio += totalTiempo;

						System.out.println("  -"+ i);
						System.out.println(tiempoPromedio);
						System.out.println(totalTiempo);
					}
					
					System.out.println("");
				  tiempoPromedio /= cantRepeticionesTest;
				  archivoBufferlocal.write(tiempoPromedio+ " "+"\n" );
				  archivoBufferlocalcantAristas.write(cantAristas+ " " +"\n" );
				  
				/*  //taboo
				  System.out.println("resolviendo taboo");
				  tiempoPromedio = 0;
					// **** se repite la misma prueba cantRepeticionesTest veces para buscar la media ****
					for (int i = 0; i < cantRepeticionesTest; i++) {					
						tiempoInicio = System.nanoTime();
						cantAristas = taboo.resolverTabu(g1taboo, g2taboo, CantIteraciones).getCantidadAristas();

						totalTiempo = System.nanoTime() - tiempoInicio;

						tiempoPromedio += totalTiempo;

						System.out.println("  -"+ i);
						System.out.println(tiempoPromedio);
						System.out.println(totalTiempo);
					}
					
					System.out.println("");
				  tiempoPromedio /= cantRepeticionesTest;
				  archivoBuffertaboo.write(tiempoPromedio+ " "+"\n" );
				  archivoBuffertaboocantAristas.write(cantAristas+ " " +"\n" );*/
				  
				  
		}
		/*archivoBufferexacto.close();
		archivoBuffergreedy.close();*/
		archivoBufferlocal.close();
		/*archivoBuffertaboo.close();
		archivoBuffergreedycantAristas.close();*/
		archivoBufferlocalcantAristas.close();
		/*archivoBuffertaboocantAristas.close();*/

	} catch (Exception e) {
		System.out.println("FATAL ERROR (" + e.getMessage() + ")");
		e.printStackTrace();
	}

		
		
	}

	
	public static List<Solucion> getAsignacionesDeVecindad2(List<Integer> asignacion, int cant1, int cant2){
		List<Solucion> res = new ArrayList<>();
		for(int k=0; k<cant1;k++){
			for(int i =k+1; i<cant1;i++){
				for(int j=i+1; j<cant2;j++){
					int nodo1, nodo2, nodo3;
					nodo1 = asignacion.get(k);
					nodo2 = asignacion.get(i);
					nodo3 = asignacion.get(j);
					
					asignacion.set(k, nodo3);
					asignacion.set(i, nodo1);
					asignacion.set(j, nodo2);
					
					System.out.println(asignacion.toString());
					
					asignacion.set(k, nodo1);
					asignacion.set(i, nodo2);
					asignacion.set(j, nodo3);
				}
			}
		}
		return res;
	}
}
