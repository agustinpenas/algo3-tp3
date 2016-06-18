


public class Arista{
	private Integer nroDeArista;
	private Integer nodoU;
	private Integer nodoV;

	
	public Integer getNroDeArista() {
		return nroDeArista;
	}
	public Integer getNodoU() {
		return nodoU;
	}
	public Integer getNodoV() {
		return nodoV;
	}
	
	public void setNroDeArista(Integer nroDeArista) {
		this.nroDeArista = nroDeArista;
	}

	public void setNodoU(Integer nodoU) {
		this.nodoU = nodoU;
	}
	
	public void setNodoV(Integer nodoV) {
		this.nodoV = nodoV;
	}

	public void mostrar(){
		System.out.println("Arista Nro: " + getNroDeArista());
		System.out.println(" (" + getNodoU() + ", " + getNodoV() + ") ");
	}
	
}


