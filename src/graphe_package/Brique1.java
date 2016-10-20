package graphe_package;

import java.util.ArrayList;
import java.util.LinkedList;

public class Brique1 {

	public static Graphe[] test(Graphe g){
        LinkedList<Integer> fifo = new LinkedList<>();          // La file du parcours en largeur
        ArrayList<Integer> sommetsMarques = new ArrayList<>();  // La liste des sommets déjà rencontrés
        //Initialisation
        int sommetCourant = g.getFirst();
        fifo.add(sommetCourant);
        sommetsMarques.add(sommetCourant);
        Graphe c = new Graphe();        // La composante connexe
        c.getGraphe().put(sommetCourant,(ArrayList<Integer>)g.voisinage(sommetCourant).clone());
        Graphe gSansC = g.clone();   // Le graphe sans la composante connexe
        gSansC.supprimerSommet(sommetCourant);

        //Tant que la file n'est pas vide
        while (! fifo.isEmpty()){
            //je défile le premier sommet.
            sommetCourant = fifo.getFirst();
            fifo.removeFirst();

            // Pour tous les voisins du sommet courant
            for(int voisin : g.getGraphe().get(sommetCourant)){

                    // Si voisin n'a pas encoré été rencontré
                    if(! sommetsMarques.contains(voisin)){

                        fifo.add(voisin);
                        sommetsMarques.add(voisin);
                        c.getGraphe().put(voisin,(ArrayList<Integer>)g.voisinage(voisin).clone());
                        gSansC.supprimerSommet(voisin);
                    }
            }
        }
        if(c.getTaille() != g.getTaille()) {
            Graphe tabG[] = new Graphe[2];
            tabG[0] = c;
            tabG[1] = gSansC;
            System.out.println("c:"+c);
            System.out.println("gSansC:"+gSansC);
            return tabG;
        } else {
            return null;
        }
	}

	public static Graphe op1(Graphe graphe) {
		return graphe;
	}

	public static Graphe op2(Graphe graphe) {
		return graphe;
	}
}