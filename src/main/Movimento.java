package main;

public class Movimento {
    public Movimento pai;
    final int n = 4;

    public int getPesoNo() {
        return pesoNo;
    }

    int pesoNo;
    int geracao;
    public int[][] tabuleiroGerado;

    public int[][] getTabuleiroGerado() {
        return tabuleiroGerado;
    }

    public Movimento(Movimento pai, int[][] tabuleiroGerado) {
        this.pai = pai;
        this.tabuleiroGerado = tabuleiroGerado;
    }

    public void printMatriz() {
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(tabuleiroGerado[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public int calculaPesoNo() {
        int total = 0;

        total += geracao;

        if (tabuleiroGerado[0][0] != 0) {
            if (tabuleiroGerado[0][1] != 0) {
                if (tabuleiroGerado[1][0] != 0) {
                    if (tabuleiroGerado[1][1] != 0) {
                        total--;
                    }
                }
            }
        }

        if (tabuleiroGerado[2][0] != 0) {
            if (tabuleiroGerado[2][1] != 0) {
                if (tabuleiroGerado[3][0] != 0) {
                    if (tabuleiroGerado[3][1] != 0) {
                        total--;
                    }
                }
            }
        }
        if (tabuleiroGerado[0][2] != 0) {
            if (tabuleiroGerado[1][2] != 0) {
                if (tabuleiroGerado[0][3] != 0) {
                    if (tabuleiroGerado[1][3] != 0) {
                        total--;
                    }
                }
            }
        }

        if (tabuleiroGerado[2][2] != 0) {
            if (tabuleiroGerado[2][3] != 0) {
                if (tabuleiroGerado[3][2] != 0) {
                    if (tabuleiroGerado[3][3] != 0) {
                        total--;
                    }
                }
            }
        }


        int contador = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tabuleiroGerado[i][j] != 0)
                    contador++;
                else
                    break;
            }
            if (contador == n) {
                total--;
                contador = 0;
            }
        }

        contador = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tabuleiroGerado[j][i] != 0)
                    contador++;
                else
                    break;
            }
            if (contador == n) {
                total--;
                contador = 0;
            }
        }

        return total;
    }
}
