
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

public class Grafo {



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aristas == null) ? 0 : aristas.hashCode());
		result = prime
				* result
				+ ((cantidadDeAristas == null) ? 0 : cantidadDeAristas
						.hashCode());
		result = prime * result
				+ ((cantidadDeNodos == null) ? 0 : cantidadDeNodos.hashCode());
		result = prime * result + Arrays.hashCode(grados);
		result = prime * result + Arrays.hashCode(matrizAdyacencia);
		result = prime * result + ((nodos == null) ? 0 : nodos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grafo other = (Grafo) obj;
		if (aristas == null) {
			if (other.aristas != null)
				return false;
		} else if (!aristas.equals(other.aristas))
			return false;
		if (cantidadDeAristas == null) {
			if (other.cantidadDeAristas != null)
				return false;
		} else if (!cantidadDeAristas.equals(other.cantidadDeAristas))
			return false;
		if (cantidadDeNodos == null) {
			if (other.cantidadDeNodos != null)
				return false;
		} else if (!cantidadDeNodos.equals(other.cantidadDeNodos))
			return false;
		if (!Arrays.equals(grados, other.grados))
			return false;
		if (!Arrays.deepEquals(matrizAdyacencia, other.matrizAdyacencia))
			return false;
		if (nodos == null) {
			if (other.nodos != null)
				return false;
		} else if (!nodos.equals(other.nodos))
			return false;
		return true;
	}

	private Integer cantidadDeNodos;
	private Integer cantidadDeAristas;
	private Set<Arista> aristas;
	private Set<Integer> nodos;
	private List<List<Integer>> listaAdyacencia;
	
	private int[][] matrizAdyacencia;
	private int[] grados; 
	private boolean[] visitados;
	
	
	public Grafo(Integer cantidadDeNodos, Integer cantidadDeAristas,
			Set<Arista> aristas, Set<Integer> nodos, 
			int[][] matrizAdyacencia, int[] grados) {

			this.cantidadDeNodos = cantidadDeNodos;
			this.cantidadDeAristas = cantidadDeAristas;
			this.aristas = new HashSet<Arista>(aristas);
			this.nodos = new HashSet<Integer>(nodos);
			this.matrizAdyacencia = matrizAdyacencia;
			this.grados = grados;
			for(int i =0; i<cantidadDeNodos;i++){
				visitados[i]=false;
			}
			}

	
	
	
	
	
	
	public Grafo (int cantNodos) {
		this.matrizAdyacencia= new int[cantNodos][cantNodos];
		this.listaAdyacencia = new ArrayList<>(cantNodos);
		this.visitados = new boolean[cantNodos];
		this.nodos = new HashSet<Integer>();
		this.aristas = new HashSet<Arista>();
		this.grados = new int[cantNodos];
		
		for (int i = 0; i < cantNodos; i++) {
			grados[i]=0;
			this.listaAdyacencia.add( new ArrayList<>());
			for (int j = 0; j < cantNodos; j++) {
				matrizAdyacencia [i][j] =0;
			}
		}
		for(int i =0; i<cantNodos;i++){
			visitados[i]=false;
		}
		this.cantidadDeAristas=0;
		this.cantidadDeNodos=0;
	}
	
	
	/**
	 * Devuelve el nodo con mayor grado
	 * @return int nro de nodo de mayor grado.
	 */
	public int getNodoMax(){
		// itera sobre el vector de grados y me da el maximo.
		Iterator iterNodos = this.getNodos().iterator();
		int nodoMax = 0;
		int nodo;
		while(iterNodos.hasNext()){
			nodo = (int) iterNodos.next();
			if(this.grados[nodoMax] < this.grados[nodo])nodoMax = nodo;
		}
		return nodoMax;
	}
	
	/**
	 * Devuelve el nodo de menor grado
	 * @return int nro de nodo de menor grado.
	 */
	public int getNodoMin(){
		// itera sobre el vector de grados y me da el maximo.
		Iterator iterNodos = this.getNodos().iterator();
		int nodoMin = 0;
		int nodo;
		while(iterNodos.hasNext()){
			nodo = (int) iterNodos.next();
			if(this.grados[nodoMin] > this.grados[nodo])nodoMin = nodo;
		}
		return nodoMin;
	}	
	
	/**
	 * Itera los grados y saca un promedio. Toma el nodo que difiera menos del promedio.
	 * @return int nro de nodo de grado promedio. 
	 */
	public int getNodoPromedio(){
		// Obtengo el promedio de los grados
		int promedio = 0;
		Iterator iterPromedio = this.getNodos().iterator();
		int nodo;
		while(iterPromedio.hasNext()){
			nodo = (int) iterPromedio.next();
			promedio += this.grados[nodo];
		}
		
		promedio = promedio/this.getCantidadDeNodos();
		
		// itera sobre el vector de grados y me da el mas cercano al promedio.
		Iterator iterNodos = this.getNodos().iterator();
		int nodoPromedio = 0;
		int nodoIterado;
		int menorDiferencia = promedio;
		
		while(iterNodos.hasNext()){
			nodoIterado = (int) iterNodos.next();
			int gradoNodoIterado = this.grados[nodoIterado];
			int diferenciaConPromedio = Math.abs(promedio - gradoNodoIterado);
			if( diferenciaConPromedio < menorDiferencia){
				menorDiferencia = diferenciaConPromedio;
				nodoPromedio = nodoIterado;
			}
		}
		return nodoPromedio;
	}
	
	
	public Set<Integer> getAdyacentesAConjuntoDeNodos(Set<Integer> nodos){
		// itera sobre los nodos del conjunto y devuelve solo los adyacentes a ellos (sin incluir los que estan en el conjunto inicial).
		Set<Integer> res= new HashSet<Integer>();
	
		if(!nodos.isEmpty()){
			// itero por los nodos del conjunto que me pasaron como parametro
			for (Iterator iterator = nodos.iterator(); iterator.hasNext();) {
				Integer nodo = (Integer) iterator.next();
				// utilizo la funcion de getAdyacentesANodo y agrego con ADDALL
				res.addAll(this.getAdyacentesANodo(nodo));
			}
			
			for (Iterator iterator = nodos.iterator(); iterator.hasNext();) {
				Integer nodo = (Integer) iterator.next();
				// elimino los nodos que pertenecen al conjunto que me pasaron como parametro
				res.remove(nodo);
			}
		}
		else{
			return this.nodos;
		}
		
		return res;
	}

	public Set<Integer> getAdyacentesANodo(int nodo){
		// itera sobre los nodos del conjunto y devuelve solo los adyacentes a ellos (sin incluir los que estan en el conjunto inicial).
		Set<Integer> res= new HashSet<Integer>();
		for (int i = 0; i < this.cantidadDeNodos ; i++) {
//			if(sonAdyacentes(nodo,i) ){
			if(sonAdyacentes(nodo,i) && i>nodo ){
				res.add(i);
			}
		}
		return res;
	}

	
	public int[] getGrados() {
		return this.grados;
	}

	public int[][] getMatrizAdyacencia() {
		return this.matrizAdyacencia;
	}
	
	public List<List<Integer>> getListaAdyacencia(){
		return this.listaAdyacencia;
	}

	public Set<Arista> getAristas() {
		return this.aristas;
	}
	public Set<Integer> getNodos() {
		return this.nodos;
	}

	public void setGrados(int[] grados) {
		this.grados = grados;
	}

	public void setMatrizAdyacencia(int[][] matrizAdyacencia) {
		this.matrizAdyacencia = matrizAdyacencia;
	}

	public void setAristas(Set<Arista> aristas ) {
		this.aristas = aristas;
	}
	public void setNodos(Set<Integer> nodos) {
		this.nodos = nodos;
	}

	public Integer getCantidadDeNodos() {
		return cantidadDeNodos;
	}
	public Integer getCantidadDeAristas() {
		return cantidadDeAristas;
	}
	public void setCantidadDeNodos(Integer cantidadDeNodos) {
		this.cantidadDeNodos = cantidadDeNodos;
	}
	public void setCantidadDeAristas(Integer cantidadDeAristas) {
		this.cantidadDeAristas = cantidadDeAristas;
	}
	
	public void agregarNodo(Integer nodo) {
		if(!this.nodos.contains(nodo)){
			this.nodos.add(nodo);
			this.cantidadDeNodos++;
		}
	}

	public void agregarArista(int nodoU,int nodoV) {
		

    	
    	if(	!(this.matrizAdyacencia[nodoU][nodoV] == 1 &&
		this.matrizAdyacencia[nodoV][nodoU] == 1)){
    		
        	Arista aristaTemporal = new Arista();
        	aristaTemporal.setNroDeArista(this.cantidadDeAristas++); 
        	aristaTemporal.setNodoU(nodoU);
        	aristaTemporal.setNodoV(nodoV);
    	
			this.aristas.add(aristaTemporal);
	
			// aumenta el grado de los nodos de la arista
	    	this.grados[nodoU]++;
	    	this.grados[nodoV]++;
	
			this.matrizAdyacencia[nodoU][nodoV] = 1;
			this.matrizAdyacencia[nodoV][nodoU] = 1;
			
			this.listaAdyacencia.get(nodoU).add(nodoV);
			this.listaAdyacencia.get(nodoV).add(nodoU);
    	}
	}

	public void sacarNodo(int nodo){
		this.nodos.remove(nodo);
		this.cantidadDeNodos--;
	}

	public boolean sonAdyacentes(int nodoU,int nodoV) {

		if (this.matrizAdyacencia[nodoU][nodoV] ==1) return true;
		
		return false; 
	}
	
	public Set<Integer> getComponenteConexaDeNodo(int nodo){

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(nodo);
		Set<Integer> componente = new HashSet<Integer>();
		while(!queue.isEmpty()) {
			Integer node = queue.remove();
			componente.add(node);
			Integer child=null;
			while((child=this.getUnvisitedAdyNode(node))!=null) {
				this.visitados[child]=true;
				componente.add(child);
				queue.add(child);
			}
		}
		for(int i=0; i<this.cantidadDeNodos;i++){
			this.visitados[i]=false;
		}

		return componente;
	}
	
	public CoTree getCoTree(){
		return armarCoTree(this, this.getNodos(), this.cantidadDeNodos);
	}
	
	private static CoTree armarCoTree(Grafo g, Set<Integer> nodos, int cantNodos){
		Grafo subGrafo = new Grafo(cantNodos);
		

		
		for(Integer nodo: nodos){
			subGrafo.agregarNodo(nodo);
		}
		
		for(Integer nodo: nodos){
			for(Integer ady:g.getListaAdyacencia().get(nodo)){
				if(nodos.contains(ady)){
					subGrafo.agregarArista(nodo, ady);
				}
			}
		}
		
		Integer nodo = nodos.iterator().next();
		if(nodos.size() == 1){
			return new CoTree(nodo);
		}
	//Obtengo una componente conexa del subgrafo
		
		Set<Integer> componente = new HashSet<>(); 
		Set<Integer> otraComponente = new HashSet<Integer>();
		CoTree respuesta;
		componente.addAll(subGrafo.getComponenteConexaDeNodo(nodo));
		otraComponente.addAll(nodos);
		otraComponente.removeAll(componente);
		if(otraComponente.size()>0){
		
			CoTree componente1 = armarCoTree(g,componente,cantNodos);
			CoTree componente2 = armarCoTree(g,otraComponente,cantNodos);
			
			respuesta = new CoTree(componente1, componente2, CoTreeType.UNION);
		
		}else {
			subGrafo.complementar();
			componente.clear();
			componente.addAll(subGrafo.getComponenteConexaDeNodo(nodo)); 
			otraComponente.clear();
			otraComponente.addAll(nodos);
			otraComponente.removeAll(componente);
			CoTree componente1 = armarCoTree(g,componente,cantNodos);
			CoTree componente2 = armarCoTree(g,otraComponente,cantNodos);
			
			respuesta = new CoTree(componente1, componente2, CoTreeType.JOIN);
		}

		return respuesta;
	}
	
	private Integer getUnvisitedAdyNode(int node){
		for(Integer vecino: this.getListaAdyacencia().get(node)){
			if(!this.visitados[vecino]){
				return vecino;
			}
		}
		return null;
	}
	
	private void complementar(){
		for(int i =0; i<this.cantidadDeNodos;i++){
			for(int j=0; j<this.cantidadDeNodos;j++){
				if(this.matrizAdyacencia[i][j]==1){
					this.matrizAdyacencia[i][j]=0;
				}else{
					if(i!=j){
						this.matrizAdyacencia[i][j]=1;
					}
				}
			}
		}
		this.getAristas().clear();
		this.cantidadDeAristas=0;
		for(int i=0; i<this.cantidadDeNodos;i++){
			this.grados[i]=0;
			this.listaAdyacencia.get(i).clear();
		}
		
		
		for(int i=0; i<this.cantidadDeNodos; i++){
			
			for(int j=i+1; j<this.cantidadDeNodos;j++){
				if(this.matrizAdyacencia[i][j]==1){
					Arista aristaTemporal = new Arista();
					aristaTemporal.setNroDeArista(this.cantidadDeAristas++); 
					aristaTemporal.setNodoU(i);
					aristaTemporal.setNodoV(j);
		    	
					this.aristas.add(aristaTemporal);
			
					// aumenta el grado de los nodos de la arista
			    	this.grados[i]++;
			    	this.grados[j]++;
					
					this.listaAdyacencia.get(i).add(j);
					this.listaAdyacencia.get(j).add(i);
				}
			}
		}
	}
	
	public void mostrar(){
		System.out.println("Grafo");
	}

}
