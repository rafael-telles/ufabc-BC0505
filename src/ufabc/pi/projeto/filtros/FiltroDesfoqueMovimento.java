package ufabc.pi.projeto.filtros;

import ufabc.pi.projeto.FiltroMatriz;

/*
 * UFABC – BC&T - BC0505
 * Algoritmo: Filtro para o efeito "Motion Blur"
 *
 * Responsáveis: Alexsandro Francisco
 *
 * Data: 09/04/2015. (data da atualiação mais recente)
 */
public class FiltroDesfoqueMovimento extends FiltroMatriz {
    public FiltroDesfoqueMovimento() {
        super(new double[][]{
                {1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 1}
        }, 1.0 / 7, 0);
    }
}
