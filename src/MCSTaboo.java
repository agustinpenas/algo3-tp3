import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MCSTaboo {
	
	private CriterioTerminacion criterioTerminacion;

	public Solucion resolverTabu(Grafo g1, Grafo g2 , int cantIteraciones) {

		this.criterioTerminacion = new CriterioTerminacion(cantIteraciones);
		ContextoEjecucion contextoEjecucion = new ContextoEjecucion();
		
		Grafo grande;
		Grafo chico;
		
		if(g1.getCantidadDeNodos()>g2.getCantidadDeNodos()){
			grande = g1;
			chico = g2;
		}else{
			grande = g2;
			chico = g1;
		}
		
		//ComparadorSoluciones comparadorSoluciones = new ComparadorSolucionesImpl();


		Solucion result = new Solucion();
		List<Solucion> neighborhood = new LinkedList<Solucion>();
		List<Solucion> mejoresResultados = new LinkedList<Solucion>();
		Queue<List<List<Integer>>> tabuNeighborhood = new LinkedList<List<List<Integer>>>();
		List<List<Integer>> tabuQueue = new ArrayList<List<Integer>>();

		/*** Linea entrada es una instancia del problema ***/

		Solucion solucionActual = getSolucionInicial(chico, grande);

		Solucion mejorSolucionEncontrada = solucionActual;
		
		
		while (!criterioTerminacion.cumplido(contextoEjecucion)) {
			
			neighborhood = getAsignacionesDeVecindadTabu(mejorSolucionEncontrada ,chico, grande,tabuNeighborhood, tabuQueue);				
			
			if (neighborhood.isEmpty()){
				break; 
			}
			
			solucionActual = evaluarMejorVecino(neighborhood);
			System.out.println(neighborhood.size());
			//System.out.println(solucionActual.getCantidadAristas());
			/*** Si solucionActual es mejor que la mejorSolucionEncontrada hasta el momento. ***/
			if (solucionActual.getCantidadAristas()>mejorSolucionEncontrada.getCantidadAristas()) {
				mejorSolucionEncontrada = solucionActual;				
			}
			/*** Si el mejor del neighborhood "no" es mejor que la solucion que habia, tomo ese como mejor para empeorar la solucion. ***/
			else if ((solucionActual != null) && (mejorSolucionEncontrada != null)){
				mejoresResultados.add(mejorSolucionEncontrada);
				mejorSolucionEncontrada = solucionActual;
			}
			
			contextoEjecucion.update();
			neighborhood = null;
		}
		
		mejoresResultados.add(mejorSolucionEncontrada);
		/*** De entre todas las mejores soluciones me quedo con la de mayor frontera. ***/
		// TODO: Mejora mejorSolucionEncontrada sea una cola de prioridad y tomar el tope.
		mejorSolucionEncontrada = evaluarMejorVecino(mejoresResultados);
		
		/*** se setea el result ***/
		result.setAsignacion(mejorSolucionEncontrada.getAsignacion());
		result.setCantidadAristas(mejorSolucionEncontrada.getCantidadAristas());
		result.setGrafoSol(mejorSolucionEncontrada.getGrafoSol());
		System.out.println(mejorSolucionEncontrada.getCantidadAristas());
		System.out.println(mejorSolucionEncontrada.getAsignacion().toString());
		return result;			
	}
	
	public static List<Solucion> getAsignacionesDeVecindadTabu(Solucion sol,Grafo g1, Grafo g2, Queue<List<List<Integer>>> tabuNeighborhood, List<List<Integer>> tabuQueue){
		List<Solucion> res = new ArrayList<>();
		if(tabuQueue.size()>10000){
			//tabuQueue.poll();
		}
		List<Integer> asign = new ArrayList<>();
		for(int i =0; i<sol.getAsignacion().size();i++){
			asign.add(i, sol.getAsignacion().get(i));
		}
		tabuQueue.add(asign);
		List<List<Integer>> asignaciones = new ArrayList<>();
		List<Integer> asignacion = sol.getAsignacion().subList(0, sol.getAsignacion().size());
		for(int i =0; i<g1.getCantidadDeNodos();i++){
			for(int j=i+1; j<g2.getCantidadDeNodos();j++){
				java.util.Collections.swap(asignacion, i, j);
				Solucion vecino = new Solucion();
				vecino = armarSolucion(asignacion, g1, g2);
				if(!tabuQueue.contains(vecino.getAsignacion())){
					res.add(vecino);
					asignaciones.add(vecino.getAsignacion());
				}
				java.util.Collections.swap(asignacion, i, j);
			}
		}
		
		if(tabuNeighborhood.contains(asignaciones)){
			res.clear();
		}else{
			if(tabuNeighborhood.size() >= 10000){
				tabuNeighborhood.poll();
			}
			tabuNeighborhood.add(asignaciones);
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
	
	private static Solucion evaluarMejorVecino(List<Solucion> neighborhood){
		Solucion parcial = new Solucion();
		Integer cantAristasParcial =0;
		for(Solucion vecino :neighborhood){
			if(cantAristasParcial<=vecino.getCantidadAristas()){
				cantAristasParcial=vecino.getCantidadAristas();
				parcial = vecino;
			}
		}
		return parcial;
	}
}
