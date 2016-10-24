package graphe_package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Graphe {

	private Map<Integer,ArrayList<Integer>> graphe;
	private int tailleInitiale;
	
	public Graphe (){
		this.graphe = new HashMap<>();
		tailleInitiale = this.graphe.size();
	}
	
	public Graphe(Map<Integer, ArrayList<Integer>> g, int tailleInitiale){
		this.graphe = new HashMap<>(g);
		this.tailleInitiale=tailleInitiale;
	}

	public Map<Integer, ArrayList<Integer>> getGraphe() {
		return this.graphe;
	}

	public int getTaille() {
		return this.graphe.size();
	}

	// Je supprime s ainsi que ses occurences chez ses voisins.
	public boolean supprimerSommet(int s){
        ArrayList<Integer> voisins = voisinage(s);
        Iterator iterVoisins = voisins.iterator();
        // Pour chaque sommet voisin à s
        while (iterVoisins.hasNext()){
            int v = (Integer) iterVoisins.next();
            // Je le retrouve dans la hashmap
            ArrayList voisins2 = this.graphe.get(v);
            voisins2.remove((Object)s);
        }
        this.graphe.remove(s);
        return true;
    }

    public int getFirst() {
        int i = 0;
        while (i < this.tailleInitiale){
            if (this.graphe.containsKey(i)) {
                return i;
            }
            i++;
        }
        return -1;
    }
	
	public ArrayList<Integer> voisinage(int s){
		return (this.graphe.get(s));
	}

	public boolean estVoisin(int s1, int s2){
		return this.graphe.get(s1).contains(s2);
	}
	
	
	//	Je supprime s ainsi que tous ses voisins.
	public boolean suppressionSommetsVoisins(int s){
		if(!this.graphe.containsKey(s)){
			return false;
		}
		ArrayList<Integer> aSupprimer = (ArrayList<Integer>) voisinage(s).clone();
        Iterator aSupprimerIt = aSupprimer.iterator();
        while (aSupprimerIt.hasNext()){
            int i = (Integer) aSupprimerIt.next();
            supprimerSommet(i);
        }
       // aSupprimer.forEach(this::supprimerSommet);
        //aSupprimer.forEach((s2)->supprimerSommet(s2));
		this.graphe.remove(s);
		return true;
	}
	
	public boolean ajouterSommet(int cle){
		if(this.graphe.containsKey(cle)){
			return false;
		} else {
			this.graphe.put(cle, new ArrayList<Integer>());
			return true;
		}
	}
	
	public boolean ajouterVoisin(int cle, int voisin){
		if(! this.graphe.containsKey(cle)){
			return false;
		} else {
			if(this.graphe.get(cle).contains(voisin)){
				return false;
			} else {
				this.graphe.get(cle).add(voisin);
				return true;
			}
		}
	}

	private String afficherListe(int cle, ArrayList<Integer> liste){
		return "";
	}
	
	@Override
	public String toString() {
		return "Taille : " + this.graphe.size()+ "\nListes d'adjacences :\n" + this.graphe.toString();
	}
	
	public Graphe clone(){
		Graphe clone = new Graphe(deepCopyMapIntList(this.graphe), this.tailleInitiale);
		return clone;
	}
	
	public static Map<Integer, ArrayList<Integer>> deepCopyMapIntList(Map<Integer, ArrayList<Integer>> original) {
		Map<Integer, ArrayList<Integer>> copy = new HashMap<>(original.size());
		for (int i : original.keySet()) {
			ArrayList<Integer> list = original.get(i);
			copy.put(i, (ArrayList<Integer>) list.clone());
		}
		return copy;
	}

	public int nombreVoisins (int s){
		return this.graphe.get(s).size();
	}

	public void setTailleInitiale(int tailleInitiale) {
		this.tailleInitiale = tailleInitiale;
	}

	public int getTailleInitiale() {
		return tailleInitiale;
	}
}
