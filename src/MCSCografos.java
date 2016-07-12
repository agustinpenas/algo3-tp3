import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MCSCografos {
	
	private  HashMap<Set<Integer>,HashMap<Integer,Integer>> map = new HashMap<>();

	public Solucion resolver(Grafo cografo, CoTree cotree, Grafo completo){
		
		int k= df(cografo,cotree,completo,completo.getCantidadDeNodos());
		
		Grafo g = new Grafo(cografo.getCantidadDeNodos());
		Iterator<Arista> it = cografo.getAristas().iterator();
		for(int i =0; i<k;i++){
			Arista a = it.next();
			g.agregarNodo(a.getNodoU());
			g.agregarNodo(a.getNodoV());
			g.agregarArista(a.getNodoU(), a.getNodoV());
		}
		Solucion fin = new Solucion();
		fin.setAsignacion(new ArrayList<>());
		for(Integer i: g.getNodos()){
			fin.getAsignacion().add(i);
		}
		fin.setCantidadAristas(g.getCantidadDeAristas());
		fin.setGrafoSol(g);
		return fin;
	}
	
	private Integer df(Grafo cografo, CoTree c, Grafo completo, Integer k){
		if(map.containsKey(c.getNodos())){
			if(map.get(c.getNodos()).containsKey(k)){
				return map.get(c.getNodos()).get(k);
			}
		}
		if(c.getTipo() == CoTreeType.HOJA){
			return 0;
		}
		if(c.getTipo() == CoTreeType.UNION){
			int max =0;
			
			for(int i=1;i<=k;i++){
				int a,b;
				a= df(cografo, c.getComponente1(), completo, i);
				b= df(cografo,c.getComponente2(),completo,k-i);
				if((a+b)>max){
					max=a+b;
				}
			}
			
			return max;
		}else{
			int max =0;

			
			for(int i=1;i<=k;i++){
				int a,b;
				int e,d;
				a= df(cografo, c.getComponente1(), completo, i);
				b= df(cografo,c.getComponente2(),completo,k-i);
				e= c.getComponente1().getNodos().size()<i ?  c.getComponente1().getNodos().size():i;
				d= c.getComponente2().getNodos().size()<(k-1) ?  c.getComponente2().getNodos().size():(k-i);
				if((a+b+(e*d))>max){
					max=(a+b+(e*d));
				}
			}
			
			if(map.containsKey(c.getNodos())){
				map.get(c.getNodos()).put(k, max);
			}else{
				map.put(c.getNodos(),new HashMap<>());
				map.get(c.getNodos()).put(k, max);
			}
			
			return max;
		}
		

	}
}
