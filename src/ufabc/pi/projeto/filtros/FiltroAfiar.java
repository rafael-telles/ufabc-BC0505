package ufabc.pi.projeto.filtros;

import ufabc.pi.projeto.FiltroMatriz;

/*
 * UFABC – BC&T - BC0505
 * Algoritmo: Filtro para o efeito "Sharpen".
 *
 * Responsável: Leandro Mattos
 *
 * Data: 05/04/2015. (data da atualiação mais recente)
 */
public class FiltroAfiar extends FiltroMatriz {
    public FiltroAfiar() {
        super(new double[][]{
                {-1, -1, -1},
                {-1, 9, -1},
                {-1, -1, -1}
        }, 1, 0);
    }
}
