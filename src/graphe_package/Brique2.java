package graphe_package;

import java.util.ArrayList;
import java.util.Map;


public class Brique2 {

	/**
	 * Teste si le graphe a un sommet dominant
	 *
	 * @param graphe
	 * @return le sommet dominant, -1 sinon
	 */
	public static int test(Graphe graphe) {
        boolean test = true;
		// Permet de récupérer la map du graphe
		Map<Integer,ArrayList<Integer>> map=graphe.getGraphe();

		// Pour tous les sommets du graphe
		for (Map.Entry<Integer, ArrayList<Integer>> first : map.entrySet()) {
			ArrayList<Integer> voisinsFirst = graphe.voisinage(first.getKey());

			// Pour tous les autres sommets du graphe
			for (Map.Entry<Integer, ArrayList<Integer>> second : map.entrySet()){

				// Si ces deux sommets sont voisins
				if(graphe.estVoisin(first.getKey(), second.getKey())){
					test = true;

					// Si les deux sommets sont différents, on vérifie que chaque voisin du premier est voisin du second
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

	/**
	 * Applique la brique 2 sur le graphe
	 *
	 * @param graphe
	 * @param v le sommet dominant
	 * @return le graphe modifié
	 */
	public static Graphe run(Graphe graphe, int v) {

		Graphe clone = graphe.clone();
        clone.supprimerSommet(v);
		return clone;
	}

}