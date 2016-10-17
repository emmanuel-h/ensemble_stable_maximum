package graphe;

public class EnsembleStableMaximum {

	private static void affichage(int taille) {
		System.out.println("La taille de la bite de ta mère est" + taille);
	}

	private static int algo(Graphe graphe) {
		if(graphe.getTaille()<=1){
			return graphe.getTaille();
		} else {
			if(Brique1.test(graphe)){
				return algo(Brique1.op1(graphe)) + algo(Brique1.op2(graphe));
			} else {
				if(Brique2.test(graphe)){
					return algo(Brique2.op(graphe));
				} else {
					if(Brique3.test(graphe)){
						return 1 + algo(Brique3.op(graphe));
					} else {
						//Brique 4
					}
				}
			}
		}
		return 0;
	}

	private static Graphe lireFichier(String string) {
		Graphe g = new Graphe();
		
		return g;
	}

	public static void main(String[] args) {
		Graphe graphe = lireFichier(args[1]);
		int taille = algo(graphe);
		affichage(taille);
	}

}
