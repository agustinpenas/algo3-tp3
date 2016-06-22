import java.util.List;

public class Solucion{
		private List<Integer> asignacion;
		private Integer cantidadAristas;
		private Grafo grafoSol;
		
		public List<Integer> getAsignacion(){
			return this.asignacion;
		}
		public void setAsignacion(List<Integer> asignacion){
			this.asignacion = asignacion;
		}
		public Integer getCantidadAristas(){
			return this.cantidadAristas;
		}
		public void setCantidadAristas(Integer cantidadAristas){
			this.cantidadAristas=cantidadAristas;
		}
		public Grafo getGrafoSol() {
			return grafoSol;
		}
		public void setGrafoSol(Grafo grafoSol) {
			this.grafoSol = grafoSol;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Solucion sol = (Solucion) obj;
			if(this.getCantidadAristas()!=sol.getCantidadAristas())
				return false;
			if(!this.getAsignacion().equals(sol.getAsignacion()))
				return false;
			/*if(!this.getGrafoSol().equals(sol.getGrafoSol()))
				return false;*/
		return true;
		}
	}