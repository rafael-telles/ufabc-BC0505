package ufabc.pi.projeto.filtros;

import ufabc.pi.projeto.FiltroMatriz;

/*
 * UFABC – BC&T - BC0505
 * Algoritmo: Filtro para o efeito "Blur"
 *
 * Responsáveis: Alexsandro Francisco
 *
 * Data: 09/04/2015. (data da atualiação mais recente)
 */
public class FiltroDesfoque extends FiltroMatriz {
    public FiltroDesfoque() {
        super(new double[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0},
        }, 0.2, 0);
    }
}
