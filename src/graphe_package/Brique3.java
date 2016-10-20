package graphe_package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Brique3 {

	public static int test(Graphe graphe) {
		Map<Integer,ArrayList<Integer>> map=graphe.getGraphe();
		for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()){
			if (entry.getValue().size() == 2){
				if(!graphe.estVoisin(entry.getValue().get(0),entry.getValue().get(1))){
					return entry.getKey();
				}
			}
		}
		return -1;
	}

	public static Graphe op(Graphe graphe, int v) {
		Graphe clone=graphe.clone();
		int voisinSupp=clone.getGraphe().get(v).get(1);
		int voisinAdd=clone.getGraphe().get(v).get(0);
		ArrayList<Integer> voisinageSupp=clone.voisinage(voisinSupp);
		for (int new_voisin:voisinageSupp){
			clone.ajouterVoisin(voisinAdd, new_voisin);
		}
		clone.supprimerSommet(v);
		clone.supprimerSommet(voisinSupp);
		System.out.println("Graphe"+graphe);
		System.out.println("Clone"+clone);
		return clone;
	}

}
