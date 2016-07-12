import java.util.HashSet;
import java.util.Set;

public class CoTree {
	private CoTreeType tipo;
	
	private CoTree componente1;
	private CoTree componente2;
	
	private Integer nodo;
	private Set<Integer> nodos = new HashSet<>(); 
	
	public CoTree(Integer nodo){
		this.tipo = CoTreeType.HOJA;
		this.nodo = nodo;
		nodos.add(nodo);
	}
	
	public CoTree(CoTree componente1, CoTree componente2, CoTreeType tipo){
		this.tipo = tipo;
		this.componente1 = componente1;
		this.componente2 = componente2;
		nodos.addAll(componente1.nodos);
		nodos.addAll(componente2.nodos);
	}
	
	public CoTreeType getTipo(){
		return this.tipo;
	}

	public CoTree getComponente1() {
		return componente1;
	}

	public void setComponente1(CoTree componente1) {
		this.componente1 = componente1;
	}

	public CoTree getComponente2() {
		return componente2;
	}

	public void setComponente2(CoTree componente2) {
		this.componente2 = componente2;
	}

	public Integer getNodo() {
		return nodo;
	}

	public void setNodo(Integer nodo) {
		this.nodo = nodo;
	}

	public Set<Integer> getNodos() {
		return nodos;
	}

	public void setNodos(Set<Integer> nodos) {
		this.nodos = nodos;
	}
	
	
	
}
