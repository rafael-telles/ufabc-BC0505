package ufabc.pi.projeto;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.awt.image.WritableRaster;

/**
 * Created by Rafael on 13/04/2015.
 */
public class Utilidades {

    /*
     * UFABC – BC&T - BC0505
     * Algoritmo: Classe para conversão de imagem em matriz e vice-versa.
     *
     * Responsáveis: Rafael Telles e Alexsandro Francisco
     *
     * Data: 03/04/2015. (data da atualização mais recente)
     */

    public static final int NUM_CANAIS = 3;
    public static final int TIPO_IMAGEM = BufferedImage.TYPE_3BYTE_BGR;

    public static BufferedImage pegarImagem(int[][][] cores) {
        BufferedImage img = new BufferedImage(cores.length, cores[0].length, TIPO_IMAGEM);
        WritableRaster raster = (WritableRaster) img.getData();

        for (int i = 0; i < cores.length; i++) {
            for (int j = 0; j < cores[0].length; j++) {
                for (int k = 0; k < NUM_CANAIS; k++) {
                    raster.setSample(i, j, k, cores[i][j][k]);
                }
            }
        }

        img.setData(raster);

        return img;
    }

    public static int[][][] pegarCores(BufferedImage img) throws InterruptedException {
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = new int[width * height];
        PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, pixels, 0, width);
        pg.grabPixels();
        int[][][] ret = new int[width][height][Utilidades.NUM_CANAIS];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = pixels[j * width + i];
                for (int k = 0; k < Utilidades.NUM_CANAIS; k++) {
                    ret[i][j][k] = pegarCanal(pixel, k);
                }
            }
        }

        return ret;
    }

    public static int pegarCanal(int rgb, int canal) {
        return (rgb >> (8 * (Utilidades.NUM_CANAIS - 1 - canal))) & 0x000000FF;
    }
}
