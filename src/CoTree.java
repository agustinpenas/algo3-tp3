
public class CoTree {
	private CoTreeType tipo;
	
	private CoTree componente1;
	private CoTree componente2;
	
	private Integer nodo;
	
	public CoTree(Integer nodo){
		this.tipo = CoTreeType.HOJA;
		this.nodo = nodo;
	}
	
	public CoTree(CoTree componente1, CoTree componente2, CoTreeType tipo){
		this.tipo = tipo;
		this.componente1 = componente1;
		this.componente2 = componente2;
	}
	
	public CoTreeType getTipo(){
		return this.tipo;
	}
	
}
