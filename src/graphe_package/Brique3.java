package graphe_package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Brique3 {

	/**
	 * Pour tous les sommets, on vérifie s'il existe un sommet de degré 2
	 *
	 * @param graphe
	 * @return l'index du sommet de degré 2, -1 s'il n'y en a pas
	 */
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

	/**
	 * Retourne le graphe transformé par la brique 3
	 *
	 * @param graphe
	 * @param v le sommet de degré 2
	 * @return le graphe modifié
	 */
	public static Graphe run(Graphe graphe, int v) {
		int voisinSupp=graphe.getGraphe().get(v).get(1);
		int voisinAdd=graphe.getGraphe().get(v).get(0);
		ArrayList<Integer> voisinageSupp=graphe.voisinage(voisinSupp);

		for (int new_voisin:voisinageSupp){
			graphe.ajouterVoisin(voisinAdd, new_voisin);
			graphe.ajouterVoisin(new_voisin,voisinAdd);
		}
		graphe.supprimerSommet(v);
		graphe.supprimerSommet(voisinSupp);
		return graphe;
	}

}