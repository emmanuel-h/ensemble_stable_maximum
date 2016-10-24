package graphe_package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EnsembleStableMaximum {

	private static void affichage(int taille) {
		System.out.println("La taille de la bite de ta m�re est " + taille);
	}

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
					return algo(Brique2.op(graphe,sommet_dominant));
				} else {
					int sommet_pliable;
					if((sommet_pliable=Brique3.test(graphe))!= -1 ){
						return 1 + algo(Brique3.op(graphe,sommet_pliable));
					} else {
						//Brique 4
					}
				}
			}
		}
		return 0;
	}

	private static Graphe lireFichier(File file, Graphe g) throws IOException {
		FileInputStream ips = new FileInputStream(file);
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String currentLine;
		g.setTaille(Integer.parseInt(br.readLine().toString()));
		String delim = " ";
		while ((currentLine = br.readLine()) != null) {
			StringTokenizer tok = new StringTokenizer(currentLine, delim);
			String token = tok.nextToken().toString();
			int sommet = Integer.parseInt(token.substring(0, token.length()-1));
			g.ajouterSommet(sommet);
			for (; tok.hasMoreTokens();) {
				int a = Integer.parseInt(tok.nextToken().toString().replaceAll(",", "").replaceAll("[\\[\\]]" , ""));
				g.ajouterVoisin(sommet,a);
			}
		}
		br.close();
		return g;
	}

	public static void main(String[] args) {
		Graphe graphe = new Graphe();
		try {
			graphe = lireFichier(new File("test.graphe"), graphe);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Graphe[] connexe;
		connexe=Brique1.test(graphe);

		Graphe g=new Graphe();
		int sommet_pliable=0;
		if((sommet_pliable=Brique3.test(graphe))!= -1 ){
			g=Brique3.op(graphe, sommet_pliable);
		}

		//int taille = algo(graphe);
		//affichage(taille);
	}

}
