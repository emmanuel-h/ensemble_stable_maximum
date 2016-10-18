package graphe_package;

import java.util.LinkedList;

public class Brique1 {

	public static boolean test(Graphe g){
        LinkedList<Integer> fifo = new LinkedList<>();
        fifo.add(g.getFirst());
		return true;
	}

	public static Graphe op1(Graphe graphe) {
		return graphe;
	}

	public static Graphe op2(Graphe graphe) {
		return graphe;
	}
}
