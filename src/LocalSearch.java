
import java.util.ArrayList;
import java.util.List;


public class LocalSearch {
	
	public static Grafo resolver(Grafo g1, Grafo g2){
		
		List<Integer> resultado = new ArrayList<>();
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
		Solucion solInicial = getSolucionInicial(g1,g2);
		List<Integer> asignacionesSolucionParcial = solInicial.getAsignacion();
		Integer cantAristas = solInicial.getCantidadAristas();
		Grafo respuesta = new Grafo(chico.getCantidadDeNodos());
		boolean aristasMaxAlcanzada=false;
		
		while(!aristasMaxAlcanzada){
			//Agregar
			aristasMaxAlcanzada = true;

			List<List<Integer>> nodosCandidatosAAgregar = getAsignacionesDeVecindadN(asignacionesSolucionParcial, g1,g2);
			
			// Itero los nodos candidatos y veo si al agregarlo me mejora la frontera
			for (List<Integer> asignacion : nodosCandidatosAAgregar) {
				
				
				int cantAristasParcial=0;
				Grafo respuestaParcial = new Grafo(chico.getCantidadDeNodos());
				for(int nodoDelChico=0; nodoDelChico<chico.getCantidadDeNodos();nodoDelChico++){
					int nodoDelGrande= asignacion.get(nodoDelChico);
					//este for tambien lo puedo hacer con chico.getAdyacentesANodo(nodoDelChico)
					for(Integer ady : chico.getListaAdyacencia().get(nodoDelChico)){
						if(grande.getListaAdyacencia().get(nodoDelGrande).contains(asignacion.get(ady))){
							cantAristasParcial++;
							respuestaParcial.agregarNodo(nodoDelChico);
							respuestaParcial.agregarNodo(ady);
							respuestaParcial.agregarArista(nodoDelChico, ady);
							}
					}	

				}
				
				if(cantAristasParcial>cantAristas){
					cantAristas=cantAristasParcial;
					respuesta = respuestaParcial;
					resultado = asignacion;
					aristasMaxAlcanzada = false;
				}
			}
			
			
		}
		
			
		return respuesta;
	}


	public static List<List<Integer>> getAsignacionesDeVecindadN(List<Integer> asignacion,Grafo g1, Grafo g2){
		List<List<Integer>> res = new ArrayList<>();

		return res;
	}
	

	
	//TODO
	private static Solucion getSolucionInicial(Grafo g1, Grafo g2){
		// Inicialmente la solucion es el nodo de grado maximo
		Solucion res = new Solucion();
		
		return res;
	}
	

}
