package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProblemaSudoku {
    final int n = 4;
    int pesoNo;
    int geracao;

    //retorna se G é 40
    public boolean estadoFinal(int[][] estado) {
        int g = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g += estado[i][j];
            }
        }
        return g == 40;
    }

    public int[][] getIniEstado() {
        geracao = 0;
        /*Random rand = new Random();

        int[][] estado = new int[n][n];
        int linha = rand.nextInt(4);
        int coluna = rand.nextInt(4);
        int valor = rand.nextInt(4);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                estado[i][j] = 0;
            }
        }

        estado[linha][coluna] = valor;*/
        int[][] estado = new int[n][n];
        estado[0][0] = 3;
        estado[0][1] = 4;
        estado[0][2] = 1;
        estado[1][1] = 2;
        estado[2][2] = 2;
        //estado[3][1] = 1;
        //estado[3][2] = 4;

        return estado;
    }

    public List<Movimento> getFilhos(Movimento pai) {
        List<Movimento> movimentos = new ArrayList<>();
        Movimento filho;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (pai.getTabuleiroGerado()[i][j] == 0) {

                    for (int k = 1; k <= n; k++) {
                        int[][] tabuleiroFilho = new int[n][n];

                        for (int x = 0; x < n; x++) {
                            for (int y = 0; y < n; y++) {
                                tabuleiroFilho[x][y] = pai.getTabuleiroGerado()[x][y];
                            }
                        }

                        tabuleiroFilho = adicionaElemento(tabuleiroFilho, testeQuadrante(tabuleiroFilho, k), k);
                        if (tabuleiroFilho != null) {
                            filho = new Movimento(pai, tabuleiroFilho);
                            filho.geracao = pai.geracao + 1;
                            filho.pesoNo = filho.calculaPesoNo();
                            movimentos.add(filho);
                        }
                    }
                }
            }
        }

        return movimentos;
    }

    private boolean valido(int[][] tabuleiro) {

        return false;
    }

    private int testeQuadrante(int[][] matriz, int elemento) {
        if (matriz == null) {
            System.out.println("matriz = null");
        }

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

    private int[][] adicionaElemento(int[][] matriz, int quadrante, int elemento) {
        int linha = -1, coluna = -1;
        int ini1 = 0, ini2 = 0, fim1 = 0, fim2 = 0;
        if (quadrante == 1) {
            ini1 = 0;
            fim1 = 2;
            ini2 = 0;
            fim2 = 2;
        } else if (quadrante == 2) {
            ini1 = 0;
            fim1 = 2;
            ini2 = 2;
            fim2 = 4;
        } else if (quadrante == 3) {
            ini1 = 2;
            fim1 = 4;
            ini2 = 0;
            fim2 = 2;
        } else if (quadrante == 4) {
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
            return null;
        }

        return matriz;
    }


}
