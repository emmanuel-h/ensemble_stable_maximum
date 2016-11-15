package graphe_package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EnsembleStableMaximum {

    /**
     * Affiche le résultat
     *
     * @param taille Le résultat de l'algorithme
     */
	private static void affichage(int taille) {
		System.out.println("La taille du plus grand ensemble stable est " + taille);
	}

    /**
     * L'algorithme de Fromin, Grandoni et Kratsch
     *
     * @param graphe le graphe en entrée
     * @return la taille du sous ensemble maximum
     */
	private static int algo(Graphe graphe) {
		if(graphe.getTaille()<=1){
			return graphe.getTaille();
		} else {
			Graphe[]connexe;
			if((connexe=Brique1.test(graphe))!= null){
				return algo(connexe[0]) + algo(connexe[1]);
			} else {
				int sommet_dominant;
				if((sommet_dominant=Brique2.test(graphe))!= -1){
					return algo(Brique2.run(graphe,sommet_dominant));
				} else {
					int sommet_pliable;
					if((sommet_pliable=Brique3.test(graphe))!= -1 ){
						return 1 + algo(Brique3.run(graphe,sommet_pliable));
					} else {
                        int v = Brique4.getV(graphe);
						return Math.max(algo(Brique4.miroir(v, graphe)),1+algo(Brique4.voisins(v, graphe)));
					}
				}
			}
		}
	}

    /**
     * Lit le fichier donné en entrée pour former le graphe
     *
     * @param file  le fichier à parser
     * @param g     le graphe vierge à remplir
     * @return      le graphe initialisé
     * @throws IOException
     */
	private static Graphe lireFichier(File file, Graphe g) throws IOException {
		FileInputStream ips = new FileInputStream(file);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String currentLine;
        // On commence par lire la taille
        g.setTailleInitiale(Integer.parseInt(br.readLine().toString()));
		String delim = " ";
        // On découpe la ligne suivant le délimiteur "espace"
		while ((currentLine = br.readLine()) != null) {
			StringTokenizer tok = new StringTokenizer(currentLine, delim);
			String token = tok.nextToken().toString();
            // Le premier token lu est le numero du sommet
			int sommet = Integer.parseInt(token.substring(0, token.length()-1));
			g.ajouterSommet(sommet);
            // Tous les autres tokens sont ses voisins
			for (; tok.hasMoreTokens();) {
                String s = tok.nextToken().toString();
                if(!s.equals("[]")) {
                    int a = Integer.parseInt(s.replaceAll(",", "").replaceAll("[\\[\\]]", ""));
                    g.ajouterVoisin(sommet, a);
                }
			}
		}
		br.close();
		return g;
	}

    /**
     * Main
     * @par les arguments
     */
	public static void main(String[] args) {
		Graphe graphe = new Graphe();
        if(args.length != 1){
            System.out.println("Argument 1 invalide, mettre le fichier .graphe");
        } else {
            try {
                graphe = lireFichier(new File(args[0]), graphe);
				affichage(algo(graphe));
            } catch (IOException e) {
				System.out.println("Nom de fichier non valide");
			}
        }
	}

}