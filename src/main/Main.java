package main;

public class Main {
    public static void main(String[] args) {
        ProblemaSudoku problema = new ProblemaSudoku();
        Movimento resultado = null;

        long initTime = System.currentTimeMillis();
        resultado = IA.buscaEmLArgura(problema);


        while (resultado != null) {
            resultado.printMatriz();
            resultado = resultado.pai;
        }

        long endTime = System.currentTimeMillis();
        long totalMS = endTime - initTime;

        System.out.println("TEMPO DECORRIDO " + totalMS + "MS, E "+ IA.estads + " NÓS CRIADOS");


    }
}
