
public class CriterioTerminacion {
	private int cantMaxIters ;
	
	public CriterioTerminacion(int cantMaxIters){
		this.cantMaxIters = cantMaxIters;
	}

	public boolean cumplido(ContextoEjecucion contextoEjecucion) {
		return contextoEjecucion.getLoopCount() > cantMaxIters;
	}
}
