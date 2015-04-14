package ufabc.pi.projeto;

/*
 * UFABC – BC&T - BC0505
 * Algoritmo: Base para filtros com matrizes
 *
 * Responsáveis: Rafael Telles e Leandro Mattos
 *
 * Data: 0/04/2015. (data da atualiação mais recente)
 */
public class FiltroMatriz implements Filtro {
    double[][] matriz;
    double fator;
    double deslocamento;

    public FiltroMatriz(double[][] matriz, double fator, double deslocamento) {
        this.matriz = matriz;
        this.fator = fator;
        this.deslocamento = deslocamento;
    }

    @Override
    public void aplicar(int[][][] pixels) {
        int larguraImagem = pixels.length;
        int alturaImagem = pixels[0].length;
        int larguraFiltro = matriz.length;
        int alturaFiltro = matriz[0].length;

        int[][][] pixelsNovos = new int[larguraImagem][alturaImagem][Utilidades.NUM_CANAIS];

        for (int x = 0; x < larguraImagem; x++) {
            for (int y = 0; y < alturaImagem; y++) {
                double[] cor = new double[Utilidades.NUM_CANAIS];

                for (int xf = 0; xf < larguraFiltro; xf++) {
                    for (int yf = 0; yf < alturaFiltro; yf++) {
                        int ix = (int) (x - larguraFiltro / 2.0 + xf) + 1;
                        int iy = (int) (y - alturaFiltro / 2.0 + yf) + 1;

                        if (ix < 0 || iy < 0 || ix >= larguraImagem || iy >= alturaImagem) {
                            ix = Math.min(Math.max(ix, 0), larguraImagem - 1);
                            iy = Math.min(Math.max(iy, 0), alturaImagem - 1);
                        }
                        for (int i = 0; i < Utilidades.NUM_CANAIS; i++) {
                            cor[i] += pixels[ix][iy][i] * matriz[xf][yf];
                        }
                    }
                }

                for (int i = 0; i < Utilidades.NUM_CANAIS; i++) {
                    pixelsNovos[x][y][i] = (int) (fator * cor[i] + deslocamento);
                }
            }
        }

        for (
                int x = 0;
                x < larguraImagem; x++)

        {
            for (int y = 0; y < alturaImagem; y++) {
                for (int c = 0; c < Utilidades.NUM_CANAIS; c++) {
                    pixels[x][y][c] = pixelsNovos[x][y][c];
                }
            }
        }
    }
}
