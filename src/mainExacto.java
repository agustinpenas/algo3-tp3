import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class mainExacto {
	public static void main(String[] args) {
		try {

			BufferedReader br = null;

			br = new BufferedReader(new InputStreamReader(System.in));

			// Se parsea linea por linea el archivo.
			String linea;
			linea = br.readLine();
			String[] datos = linea.split(" ");
			int cantidadDeNodos = (new Integer(datos[0]));
			int cantidadDeAristas = (new Integer(datos[1]));
			int cantidadDeNodos2 = (new Integer(datos[2]));
			int cantidadDeAristas2 = (new Integer(datos[3]));

			Grafo grafo = new Grafo(cantidadDeNodos);

			Grafo grafo2 = new Grafo(cantidadDeNodos2);

			for (int i = 0; i < cantidadDeAristas; i++) {
				linea = br.readLine();
				datos = linea.split(" ");

				int nodoU = (new Integer(datos[0]));
				int nodoV = (new Integer(datos[1]));
				nodoU--;
				nodoV--;
				grafo.agregarNodo(nodoU);
				grafo.agregarNodo(nodoV);

				grafo.agregarArista(nodoU, nodoV);

			}

			for (int i = 0; i < cantidadDeAristas2; i++) {
				linea = br.readLine();
				datos = linea.split(" ");

				int nodoU = (new Integer(datos[0]));
				int nodoV = (new Integer(datos[1]));
				nodoU--;
				nodoV--;
				grafo2.agregarNodo(nodoU);
				grafo2.agregarNodo(nodoV);

				grafo2.agregarArista(nodoU, nodoV);

			}
			Grafo grande;
			Grafo chico;
			if(cantidadDeNodos>cantidadDeNodos2){
				grande = grafo;
				chico = grafo2;
			}else{
				grande = grafo2;
				chico = grafo;
			}
			MCSExacto exacto = new MCSExacto();
			Solucion fin = exacto.resolver(chico, grande);
			List<Integer> lista1;
			List<Integer> lista2;
			
			if(cantidadDeNodos>cantidadDeNodos2){
				lista1 = fin.getAsignacion();
				lista2 = new ArrayList<>();
				for(int i=0; i<cantidadDeNodos2;i++){
					lista2.add(i);
				}
			}else{
				lista2 = fin.getAsignacion();
				lista1 = new ArrayList<>();
				for(int i=0; i<cantidadDeNodos;i++){
					lista1.add(i);
				}
			}
			System.out.println(fin.getGrafoSol().getCantidadDeNodos() + " "+ fin.getCantidadAristas());
			
			for(int i=0; i<fin.getGrafoSol().getCantidadDeNodos();i++){
				System.out.print(lista1.get(i) + " ");
			}
			System.out.println();
			
			for(int i=0; i<fin.getGrafoSol().getCantidadDeNodos();i++){
				System.out.print(lista2.get(i) + " ");
			}
			
			for(Arista a: fin.getGrafoSol().getAristas()){
				System.out.println(a.getNodoU() + " " + a.getNodoV());
			}
			

		} catch (Exception e) {
			System.out.println("FATAL ERROR (" + e.getMessage() + ")");
			e.printStackTrace();
		}
	}
}
