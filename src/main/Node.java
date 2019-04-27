package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Node {

    private int[][] tabuleiro;
    private ArrayList<int[][]> filhos = new ArrayList<>();

    private int g = 0;
    private int h = 0;
    private final int n = 4;

    private boolean valida = true;

    public Node(int[][] tab) {
        this.tabuleiro = tab;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public boolean isValida() {
        return valida;
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public ArrayList getFilhos() {
        return filhos;
    }

    private int testeQuadrante(int[][] matriz, int elemento) {

        if (matriz[0][0] != elemento) {
            if (matriz[0][1] != elemento) {
                if (matriz[1][0] != elemento) {
                    if (matriz[1][1] != elemento) {
                        return 1;
                    }
                }
            }
        }

        if (matriz[2][0] != elemento) {
            if (matriz[2][1] != elemento) {
                if (matriz[3][0] != elemento) {
                    if (matriz[3][1] != elemento) {
                        return 3;
                    }
                }
            }
        }
        if (matriz[0][2] != elemento) {
            if (matriz[1][2] != elemento) {
                if (matriz[0][3] != elemento) {
                    if (matriz[1][3] != elemento) {
                        return 2;
                    }
                }
            }
        }

        if (matriz[2][2] != elemento) {
            if (matriz[2][3] != elemento) {
                if (matriz[3][2] != elemento) {
                    if (matriz[3][3] != elemento) {
                        return 4;
                    }
                }
            }
        }

        return -1;
    }

    private boolean testeColuna(int[][] matriz, int coluna, int elemento) {
        for (int k = 0; k < n; k++) {
            if (matriz[k][coluna] == elemento) {
                return false;
            }
        }
        return true;
    }

    private boolean testeLinha(int[][] matriz, int linha, int elemento) {
        for (int k = 0; k < n; k++) {
            if (matriz[linha][k] == elemento) {
                return false;
            }
        }
        return true;
    }

    private int[][] adicionaElemento(int[][] matriz, int elemento) {

        int linha = -1, coluna = -1;
        int ini1 = 0, ini2 = 0, fim1 = 0, fim2 = 0;
        int quadrante = testeQuadrante(matriz, elemento);

        if (testeQuadrante(matriz, elemento) == 1) {
            ini1 = 0;
            fim1 = 2;
            ini2 = 0;
            fim2 = 2;
        } else if (testeQuadrante(matriz, elemento) == 2) {
            ini1 = 0;
            fim1 = 2;
            ini2 = 2;
            fim2 = 4;
        } else if (testeQuadrante(matriz, elemento) == 3) {
            ini1 = 2;
            fim1 = 4;
            ini2 = 0;
            fim2 = 2;
        } else if (testeQuadrante(matriz, elemento) == 4) {
            ini1 = 2;
            fim1 = 4;
            ini2 = 2;
            fim2 = 4;
        }

        for (int i = ini1; i < fim1; i++) {
            for (int j = ini2; j < fim2; j++) {
                if (matriz[i][j] != elemento && matriz[i][j] == 0) {
                    if (testeColuna(matriz, j, elemento)) {
                        if (testeLinha(matriz, i, elemento)) {
                            linha = i;
                            coluna = j;
                            break;
                        }
                    }
                }
                if (linha != -1 && coluna != -1) {
                    break;
                }
            }
        }
        try {
            matriz[linha][coluna] = elemento;
        } catch (Exception e) {
            this.h = -1;
            this.valida = false;
            return null;
        }

        return matriz;

    }

    public void geraFilhos() {

        int um, dois, tres, quatro = 0;
        um = dois = tres = quatro;

        int[][] pai = getTabuleiro();
        filhos.add(pai);
        // se H que na nossa heuristica é a somatória da matriz for igual a 40, quer dizer que a matriz
        //está completa
        while (h < 40 && valida) {


            h = 0;
            int[][] filho = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    filho[i][j] = pai[i][j];
                }
            }


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (filho[i][j] == 1) {
                        um++;
                    }
                    if (filho[i][j] == 2) {
                        dois++;
                    }
                    if (filho[i][j] == 3) {
                        tres++;
                    }
                    if (filho[i][j] == 4) {
                        quatro++;
                    }

                    h += filho[i][j];
                }
            }


            if (um <= 4) {
                filho = adicionaElemento(filho, 1);
                um--;
                filhos.add(filho);
                g++;
            }
            if (dois <= 4 && filho != null) {
                filho = adicionaElemento(filho, 2);
                dois--;
                filhos.add(filho);
                g++;
            }
            if (tres <= 4 && filho != null) {
                filho = adicionaElemento(filho, 3);
                filhos.add(filho);
                g++;
                tres--;
            }
            if (quatro <= 4 && filho != null) {
                filho = adicionaElemento(filho, 4);
                filhos.add(filho);
                g++;
                quatro--;
            }


            if (filho != null) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        pai[i][j] = filho[i][j];
                    }
                }

                System.out.println();
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        System.out.print(filho[i][j] + " ");
                    }
                    System.out.println();
                }
            }

        }
    }

}
