package ufabc.pi.projeto.filtros;

/*
 * UFABC – BC&T - BC0505
 * Algoritmo: Filtro simples para tons de cinza.
 *
 * Responsável: Leandro Mattos
 *
 * Data: 05/04/2015. (data da atualiação mais recente)
 */

import ufabc.pi.projeto.Filtro;

public class FiltroPretoEBranco implements Filtro {

    @Override
    public void aplicar(int[][][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int vermelho = pixels[i][j][0];
                int verde = pixels[i][j][1];
                int azul = pixels[i][j][2];

                int media = (vermelho + verde + azul) / 3;
                pixels[i][j][0] = media;
                pixels[i][j][1] = media;
                pixels[i][j][2] = media;
            }
        }
    }
}
