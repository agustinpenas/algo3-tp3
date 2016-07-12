
import java.util.ArrayList;
import java.util.List;


public class MCSLocalSearch {
	
	public static Solucion resolver(Grafo g1, Grafo g2){
		
		//List<Integer> resultado = new ArrayList<>();
		// Linea entrada es una instancia del problema

		Grafo grande;
		Grafo chico;
		
		if(g1.getCantidadDeNodos()>g2.getCantidadDeNodos()){
			grande = g1;
			chico = g2;
		}else{
			grande = g2;
			chico = g1;
		}
		
		//Grafo solucionParcial = getSolucionInicial(g1,g2);
		Solucion solInicial = getSolucionInicial(chico,grande);
		List<Integer> asignacionesSolucionParcial = solInicial.getAsignacion();
		Integer cantAristas = solInicial.getCantidadAristas();
		Grafo respuesta = solInicial.getGrafoSol();
		
		boolean aristasMaxAlcanzada=false;
		
		while(!aristasMaxAlcanzada){
			//Agregar
			aristasMaxAlcanzada = true;

			List<Solucion> vecinos = getAsignacionesDeVecindadN(asignacionesSolucionParcial, chico,grande);
			
			// Itero los nodos candidatos y veo si al agregarlo me mejora la frontera
			for (Solucion solPosible : vecinos) {
				
				if(solPosible.getCantidadAristas()>cantAristas){
					cantAristas=solPosible.getCantidadAristas();
					respuesta = solPosible.getGrafoSol();
					asignacionesSolucionParcial = solPosible.getAsignacion();
					aristasMaxAlcanzada = false;
				}
			}
			
			
		}
		
		Solucion fin = new Solucion();
		fin.setAsignacion(asignacionesSolucionParcial);
		fin.setCantidadAristas(cantAristas);
		fin.setGrafoSol(respuesta);
		return fin;
	}


	public static List<Solucion> getAsignacionesDeVecindadN(List<Integer> asignacion,Grafo g1, Grafo g2){
		List<Solucion> res = new ArrayList<>();
		for(int i =0; i<g1.getCantidadDeNodos();i++){
			for(int j=i+1; j<g2.getCantidadDeNodos();j++){
				java.util.Collections.swap(asignacion, i, j);
				Solucion vecino = new Solucion();
				vecino = armarSolucion(asignacion, g1, g2);
				res.add(vecino);
				java.util.Collections.swap(asignacion, i, j);
			}
		}
		return res;
	}
	
	public static List<Solucion> getAsignacionesDeVecindad2(List<Integer> asignacion,Grafo g1, Grafo g2){
		List<Solucion> res = new ArrayList<>();
		for(int k=0; k<g1.getCantidadDeNodos();k++){
			for(int i =k+1; i<g1.getCantidadDeNodos();i++){
				for(int j=i+1; j<g2.getCantidadDeNodos();j++){
					int nodo1, nodo2, nodo3;
					nodo1 = asignacion.get(k);
					nodo2 = asignacion.get(i);
					nodo3 = asignacion.get(j);
					
					asignacion.set(k, nodo3);
					asignacion.set(i, nodo1);
					asignacion.set(j, nodo2);
					
					Solucion vecino = new Solucion();
					vecino = armarSolucion(asignacion, g1, g2);
					res.add(vecino);
					
					asignacion.set(k, nodo1);
					asignacion.set(i, nodo2);
					asignacion.set(j, nodo3);
				}
			}
		}
		return res;
	}
	

	private static Solucion getSolucionInicial(Grafo V, Grafo W){
		// Inicialmente la solucion es el nodo de grado maximo
		Solucion res = new Solucion();
		int[] nodosV = new int[V.getCantidadDeNodos()];
		int[] nodosW = new int[W.getCantidadDeNodos()];
		List<Integer> solucion = new ArrayList<>();

		for(int i =0; i<V.getCantidadDeNodos();i++){
			nodosV[i]=i;
		}
		
		for(int i =0; i<W.getCantidadDeNodos();i++){
			nodosW[i]=i;
			solucion.add(i);
		}
		
		MergeSort sorter = new MergeSort();
		
		sorter.sort(nodosV, V.getGrados());
		sorter.sort(nodosW, W.getGrados());
		
		for(int i =0; i<nodosW.length; i++){
			if(i>=nodosV.length){
				solucion.set(i, nodosW[i]);
			}else{
				solucion.set(nodosV[i], nodosW[i]);
			}
		}
		
		res = armarSolucion(solucion, V, W);
		return res;
	}
	
	private static Solucion armarSolucion(List<Integer> asignacion, Grafo V, Grafo W){
		int cantAristasParcial=0;
		
		Grafo respuestaParcial = new Grafo(V.getCantidadDeNodos());
		for(int nodoDelChico=0; nodoDelChico<V.getCantidadDeNodos();nodoDelChico++){
			int nodoDelGrande= asignacion.get(nodoDelChico);
			respuestaParcial.agregarNodo(nodoDelChico);
			//este for tambien lo puedo hacer con chico.getAdyacentesANodo(nodoDelChico)
			for(Integer ady : V.getListaAdyacencia().get(nodoDelChico)){
				if(W.getListaAdyacencia().get(nodoDelGrande).contains(asignacion.get(ady))){
					cantAristasParcial++;
					
					respuestaParcial.agregarNodo(ady);
					respuestaParcial.agregarArista(nodoDelChico, ady);					
					}
			}	

		}
		Solucion sol = new Solucion();
		List<Integer> asign = new ArrayList<>();
		for(int i =0; i<asignacion.size();i++){
			asign.add(i, asignacion.get(i));
		}
		sol.setAsignacion(asign);
		sol.setCantidadAristas(cantAristasParcial);
		sol.setGrafoSol(respuestaParcial);
		return sol;
	}
	

}
