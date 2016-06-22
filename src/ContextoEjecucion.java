
public class ContextoEjecucion {
	
	private long startTime;
	private long loopCount;
	
	public ContextoEjecucion(){
		this.startTime = System.currentTimeMillis();
		loopCount = 0;
	}

	public long timeElapsed() {
		return System.currentTimeMillis() - startTime;
	}


	public void update() {
		loopCount++;		
	}

	public long getLoopCount() {
		return loopCount;
	}
}
