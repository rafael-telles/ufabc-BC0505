package ufabc.pi.projeto.filtros;

/*
 * UFABC – BC&T - BC0505
 * Algoritmo: Filtro simples para sépia.
 *
 * Responsável: Leandro Mattos
 *
 * Data: 06/04/2015. (data da atualiação mais recente)
 */

import ufabc.pi.projeto.Filtro;

public class FiltroSepia implements Filtro {

    @Override
    public void aplicar(int[][][] pixels) {
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                int vermelho = pixels[i][j][0];
                int verde = pixels[i][j][1];
                int azul = pixels[i][j][2];

                int media = (vermelho + verde + azul) / 3;

                pixels[i][j][0] = media + 80; //vermelho
                pixels[i][j][1] = media + 40; //verde
                pixels[i][j][2] = media;      //azul
            }
        }
    }
}
