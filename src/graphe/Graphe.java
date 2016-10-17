package graphe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graphe {
	
	private Map<Integer,ArrayList<Integer>> graphe;
	private int taille;
	
	public Graphe (){
		this.graphe = new HashMap<>();
		this.taille = 0;
	}
	
	public Graphe(Map<Integer, ArrayList<Integer>> g){
		this.graphe = new HashMap<>(g);
		this.taille = g.size();
	}

	public Map<Integer, ArrayList<Integer>> getGraphe() {
		return this.graphe;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}
	
	public boolean supprimerSommet(int s){
		return true;
	}
	
	public ArrayList<Integer> voisinage(int s){
		return (this.graphe.get(s));
	}
	
	public boolean suppressionSommetsVoisins(int s){
		if(!this.graphe.containsKey(s)){
			return false;
		}
		ArrayList<Integer> aSupprimer = voisinage(s);
		aSupprimer.forEach((s2)->supprimerSommet(s2));
		supprimerSommet(s);
		return true;
	}
	
}
