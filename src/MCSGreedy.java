import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

public class MCSGreedy {

	public static Solucion resolver( Grafo V/*chico*/, Grafo W/*grande*/){
		
		int[] nodosV = new int[V.getCantidadDeNodos()];
		int[] nodosW = new int[W.getCantidadDeNodos()];
		int[] gradosV= new int[V.getCantidadDeNodos()];
		int[] gradosW= new int[W.getCantidadDeNodos()];
		
		List<Integer> solucion = new ArrayList<>();

		for(int i =0; i<V.getCantidadDeNodos();i++){
			nodosV[i]=i;
		}
		
		for(int i =0; i<W.getCantidadDeNodos();i++){
			nodosW[i]=i;
			solucion.add(i);
		}
		
		for(int i =0; i<V.getCantidadDeNodos();i++){
			gradosV[i]=V.getGrados()[i];
		}
		
		for(int i =0; i<W.getCantidadDeNodos();i++){
			gradosW[i]=W.getGrados()[i];
		}
		
		MergeSort sorter = new MergeSort();
		
		sorter.sort(nodosV, gradosV);
		sorter.sort(nodosW, gradosW);
		
		for(int i =0; i<nodosW.length; i++){
			if(i>=nodosV.length){
				solucion.set(i, nodosW[i]);
			}else{
				solucion.set(nodosV[i], nodosW[i]);
			}
		}

		int cantAristasParcial=0;
		Grafo respuestaParcial = new Grafo(V.getCantidadDeNodos());
		for(int nodoDelChico=0; nodoDelChico<V.getCantidadDeNodos();nodoDelChico++){
			int nodoDelGrande= solucion.get(nodoDelChico);
			respuestaParcial.agregarNodo(nodoDelChico);
			//este for tambien lo puedo hacer con chico.getAdyacentesANodo(nodoDelChico)
			for(Integer ady : V.getListaAdyacencia().get(nodoDelChico)){
				if(W.getListaAdyacencia().get(nodoDelGrande).contains(solucion.get(ady))){
					cantAristasParcial++;
					
					respuestaParcial.agregarNodo(ady);
					respuestaParcial.agregarArista(nodoDelChico, ady);					
					}
			}	

		}
		
		
		Solucion fin = new Solucion();
		fin.setAsignacion(solucion);
		fin.setCantidadAristas(cantAristasParcial);
		fin.setGrafoSol(respuestaParcial);
		return fin;
	}
	

}


