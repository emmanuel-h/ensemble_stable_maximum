package graphe_package;

import java.util.ArrayList;
import java.util.Map;


public class Brique2 {

	public static int test(Graphe graphe) {
        boolean test = true;
		Map<Integer,ArrayList<Integer>> map=graphe.getGraphe();
		for (Map.Entry<Integer, ArrayList<Integer>> first : map.entrySet()) {
			ArrayList<Integer> voisinsFirst = graphe.voisinage(first.getKey());
			for (Map.Entry<Integer, ArrayList<Integer>> second : map.entrySet()){
				if(graphe.estVoisin(first.getKey(), second.getKey())){
					test =true;
					if(first.getKey() != second.getKey()) {
	                    for (int vf : voisinsFirst) {
	                        if (!graphe.estVoisin(vf, second.getKey())) {
	                            test = false;
	                        }
	                    }
	
						if (test) {
	                        return second.getKey();
	                    }
	                }
				}
			}
		}
		return -1;
	}

	public static Graphe op(Graphe graphe, int v) {

		Graphe clone = graphe.clone();
        clone.supprimerSommet(v);
		return clone;
	}

}
