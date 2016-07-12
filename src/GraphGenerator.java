import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class GraphGenerator {

	public static Stack<Grafo> getRandomGraphs(int cantidad, double proba, int seed){
				
				Integer k = cantidad; 	// Generacion de instancias de los primeros k grafos "completos", en realidad las aristas van a estar o no con probabilidad p
				Double probabilidad = new Double(proba);
				Integer semilla = seed;

		        Random rnd = new Random(semilla);
		        Stack<Grafo> grafos = new Stack<>();
		        // Voy armando los grafos de i nodos hasta alcanzar k
	        	for (int i=2; i < k; i++){
	        		
	        		Integer nodos = i+1;
	        		// Inicialmente en cero y va aumentando a medida que agrego aristas
	        		Integer cantidadDeAristas = 0;  

	    	        Grafo grafo = new Grafo(nodos);
	    	        
	        		System.out.println("Cantidad de nodos: " + nodos);
	        		System.out.println("aristas:");
	        		//Escribo cada arista
	        		for (int j = 0;  j < nodos; j++) { //Por cada nodo
	        			//lo uno con todos los otros con probabilidad p
	        			//if(j<nodos){
	        				grafo.agregarNodo(j);
	        			//}*/
	        			for (int n = j + 1; n < nodos; n++){
	        				
	        				Double r = rnd.nextDouble();
	        				
	        				//System.out.println("random " + r);
	        				        				        				
	        				if (r.compareTo(probabilidad) < 0){
	        					// Agrego la arista

	        					grafo.agregarArista(j, n);

	        					cantidadDeAristas++;
	        					System.out.println(j +" " + n + " ");
	        				}
	        			}
	        		}
	        		System.out.println();
	        	
	        		grafos.push(grafo);
	
	        	}  
		        
	        	return grafos;
		}
	
	
	public static Grafo getRandomGraphsFromOthers(double proba, int seed,Grafo g){
		
		Integer k = g.getCantidadDeNodos()*2; 	
		Double probabilidad = new Double(proba);
		Integer semilla = seed;

        Random rnd = new Random(semilla);
        // Voy armando los grafos de i nodos hasta alcanzar k
    		
    		Integer nodos = g.getCantidadDeNodos();
    		// Inicialmente en cero y va aumentando a medida que agrego aristas
    		Integer cantidadDeAristas = 0;  

	        Grafo grafo = new Grafo(k);
	        
    		System.out.println("Cantidad de nodos: " + nodos);
    		System.out.println("aristas:");
    		//Escribo cada arista
    		for (int j = 0;  j < nodos; j++) { //Por cada nodo
    			//lo uno con todos los otros con probabilidad p
    			//if(j<nodos){
    				grafo.agregarNodo(j);
    			//}*/
    			for (int n = j + 1; n < nodos; n++){
    				
    				Double r = rnd.nextDouble();
    				boolean enG=false;
    				//System.out.println("random " + r);
    				if(n<g.getCantidadDeNodos() && j< g.getCantidadDeNodos()){
    					if(g.getMatrizAdyacencia()[n][j]==1)
    						enG=true;
    				}
    				        				        				
    				if (/*r.compareTo(probabilidad) < 0 ||*/ enG){
    					// Agrego la arista

    					grafo.agregarArista(j, n);

    					cantidadDeAristas++;
    					System.out.println(j +" " + n + " ");
    				}
    			}
    		}
    		System.out.println();
    		for(int i=nodos;i<nodos*2;i++){
    			grafo.agregarNodo(i);
    			if(i!=nodos){
    				grafo.agregarArista(i, nodos);
    			}
    		}
    		
    		
        
    	return grafo;
}
	
	
	public static Stack<Grafo> getCompleteGraphs(int cantidad){
		
		Integer k = cantidad; 				// Generacion de instancias de los primeros k grafos completos

        Stack<Grafo> grafos = new Stack<>();

    	for (int i=2; i < k; i++){
    		Integer nodos = i+1;
    		Integer aristas = (nodos * (nodos-1))/2; 
    	
    		System.out.println(nodos + " " + aristas);
	        Grafo grafo = new Grafo(nodos);

    		//Escribo cada arista
    		for (int j = 0;  j < nodos; j++) { //Por cada nodo
    			//lo uno con todos los otros
    			grafo.agregarNodo(j);
    			for (int n = j + 1; n < nodos; n++){
    				System.out.println(j +" " + n);
    				grafo.agregarArista(j, n);
    			}
    			
    		}
    		System.out.println();
    		grafos.push(grafo);

    	}  
    	return grafos;
	}
	
	public static Stack<Grafo> getCoGraph(int cantidad, int seed){
		Integer k = cantidad; 				// Generacion de instancias de los primeros k grafos completos

        Stack<Grafo> grafos = new Stack<>();
        Random rnd = new Random(seed);
        
    	for (int i=2; i < k; i++){
    		Integer nodos = i+1;
    		Integer aristas = (nodos * (nodos-1))/2; 
    	
    		System.out.println(nodos + " " + aristas);
	        Grafo grafo = new Grafo(nodos);

    		//Escribo cada arista
    		for (int j = 0;  j < nodos; j++) { //Por cada nodo
    			//lo uno con todos los otros
    			grafo.agregarNodo(j);
    			Double r = rnd.nextDouble();
    			if(r.compareTo(0.5) < 0){
	    			for (int n = 0; n < j; n++){
	    				System.out.println(j +" " + n);
	    				grafo.agregarArista(j, n);
	    			}
    			}
    		}
    		System.out.println();
    		grafos.push(grafo);

    	}  
    	return grafos;
	}

}
