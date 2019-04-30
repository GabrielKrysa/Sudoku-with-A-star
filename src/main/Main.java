package main;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final int n = 4;
        Node geracao;
        Node melhorNo = new Node(null);

        ArrayList<Node> nos = new ArrayList();
        ArrayList<int[][]> menorCaminho = new ArrayList<>();
        int[][] aux;

        int menor = Integer.MAX_VALUE;
        int menorID = -1;


        for (int l = 0; l < n; l++) {
            for (int i = 0; i < n * n; i++) {
                aux = new int[n][n];

                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        aux[j][k] = 0;
                    }
                }

                int coluna = i % n;
                int linha = i / n;

                aux[linha][coluna] = l;
                nos.add(new Node(aux));
            }
        }

        geracao = nos.get(5);
        geracao.geraFilhos();

        for (int i = 0; i < nos.size() - 1; i++) {
            geracao = nos.get(i);
            geracao.geraFilhos();
            if (geracao.isValida()) {
                if (geracao.getG() < menor) {
                    menor = geracao.getG() + 1;
                    menorID = i;
                    melhorNo = geracao;
                }
            }
        }

        System.out.println("MENOR CAMINHO COM " + menor + " NÓS");
        System.out.println("GERAÇÃO " + menorID);
        melhorNo.printCaminho();

    }
}
