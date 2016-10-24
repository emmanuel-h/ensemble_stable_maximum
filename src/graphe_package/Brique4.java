package graphe_package;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Bibi on 24/10/2016.
 */
public class Brique4 {

    public static int getV(Graphe g){
        int v = g.getFirst();
        for (Map.Entry<Integer, ArrayList<Integer>> entry : g.getGraphe().entrySet()){
            if(g.nombreVoisins(v) < g.nombreVoisins(entry.getKey())){
                v=entry.getKey();
            }
        }
        return v;
    }

    public static boolean clique(List<Integer> voisins, Graphe g){
        for(int v:voisins){
            for(int u:voisins){
                if(v != u){
                    if(! g.estVoisin(v,u)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static Graphe miroir(int v, Graphe graphe){
        Graphe g = graphe.clone();
        List<Integer> sommetsASupprimer = new ArrayList<>();
        sommetsASupprimer.add(v);
        // Pour tous les voisins u à distance 2 de v
        List<Integer> voisins2 = new ArrayList<>();
        for(int voisinDeV:g.voisinage(v)){
            for(int voisin2DeV:g.voisinage(voisinDeV)){
                if(v != voisin2DeV && !g.estVoisin(v,voisin2DeV) && !voisins2.contains(voisin2DeV)){
                    voisins2.add(voisin2DeV);
                }
            }
        }
        // Pour tous les voisins w de v non voisins de u => clique
        List<Integer> cliqueATester = new ArrayList<>();
        for(int u:voisins2){
            for(int w:g.voisinage(v)) {
                if (! g.estVoisin(w, u)){
                    cliqueATester.add(w);
                }
            }
            if(clique(cliqueATester,g)){
                // rajouter u  la liste des sommets à supprimer
                if(! sommetsASupprimer.contains(u)) {
                    sommetsASupprimer.add(u);
                }
            }
            cliqueATester.clear();
        }
        for(int aSupp:sommetsASupprimer) {
            g.supprimerSommet(aSupp);
        }
        return g;
    }

    public static Graphe voisins(int v, Graphe g){
        Graphe g_voisins = g.clone();
        g_voisins.suppressionSommetsVoisins(v);
        return g_voisins;
    }
}
