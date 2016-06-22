import java.util.ArrayList;
import java.util.List;

public class MCSExacto {

	public Grafo resolver(Grafo g1, Grafo g2){
		Grafo grande;
		Grafo chico;
		
		if(g1.getCantidadDeNodos()>g2.getCantidadDeNodos()){
			grande = g1;
			chico = g2;
		}else{
			grande = g2;
			chico = g1;
		}
		
		ArrayList<Integer> asignaciones = new ArrayList<>();
		for(int i =0; i< grande.getCantidadDeNodos();i++){
			asignaciones.add(i);
		}
		Permuter p = new Permuter(asignaciones);
		int cantidadAristas =0;
		Grafo respuesta = new Grafo(chico.getCantidadDeNodos());
		List<Integer> asignacionFinal = new ArrayList<>();
		while(p.hasNextPermutation()){
			List<Integer> asignacion = p.getNextPermutation();
			int cantAristasParcial=0;
			Grafo respuestaParcial = new Grafo(chico.getCantidadDeNodos());
			for(int nodoDelChico=0; nodoDelChico<chico.getCantidadDeNodos();nodoDelChico++){
				int nodoDelGrande= asignacion.get(nodoDelChico);
				respuestaParcial.agregarNodo(nodoDelChico);
				//este for tambien lo puedo hacer con chico.getAdyacentesANodo(nodoDelChico)
				for(Integer ady : chico.getListaAdyacencia().get(nodoDelChico)){
					if(grande.getListaAdyacencia().get(nodoDelGrande).contains(asignacion.get(ady))){
						cantAristasParcial++;
						
						respuestaParcial.agregarNodo(ady);
						respuestaParcial.agregarArista(nodoDelChico, ady);					}
					// O
					/*if(grande.getMatrizAdyacencia()[nodoDelGrande][asignacion.get(ady)]==1){
						cantAristasParcial++;
					}*/
				}	

			}
			if(cantAristasParcial>cantidadAristas){
				cantidadAristas=cantAristasParcial;
				respuesta = respuestaParcial;
				asignacionFinal = asignacion;
			}
		}
		
		System.out.println(cantidadAristas);
		System.out.println(asignacionFinal.toString());
		return respuesta;
	}
}
