package main;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class IA {
    static long estads = 0;

    static Movimento buscaEmLArgura(ProblemaSudoku problema) {

        PriorityQueue<Movimento> estadosFechados = new PriorityQueue<>(Comparator.comparingInt(Movimento::getPesoNo));

        Movimento movimentoInicial = new Movimento(null, problema.getIniEstado());
        movimentoInicial.geracao = 0;
        Movimento atual;
        List<Movimento> filhos;

        estadosFechados.add(movimentoInicial);

        while (!estadosFechados.isEmpty()) {
            atual = estadosFechados.poll();
            if (problema.estadoFinal(atual.getTabuleiroGerado())) {
                return atual;
            } else {
                filhos = problema.getFilhos(atual);
                estads += filhos.size();
                estadosFechados.addAll(filhos);
            }
        }

        return null;
    }

    private void pesaNos() {

    }

}
