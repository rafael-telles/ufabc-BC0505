package ufabc.pi.projeto;

import ufabc.pi.projeto.filtros.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * UFABC – BC&T - BC0505
 * Algoritmo: Janela principal do programa
 *
 * Responsáveis: Rafael Telles, Leandro Mattos e Alexsandro Francisco
 *
 * Data: 10/04/2015. (data da atualiação mais recente)
 */
public class TelaPrincipal extends JFrame {
    private JPanel root;
    private JLabel lblImage;
    private List<Filtro> filtros;
    private BufferedImage imagemOriginal;
    private BufferedImage imagemModificada;

    public TelaPrincipal() {
        super("JGram");
        filtros = new ArrayList<Filtro>();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(root);

        pack();
        setSize(600, 400);

        JMenu menuArquivo = new JMenu("Arquivo");
        final JMenuItem abrir = new JMenuItem("Abrir");
        JMenuItem salvar = new JMenuItem("Salvar");
        JMenuItem sair = new JMenuItem("Sair");
        menuArquivo.add(abrir);
        menuArquivo.add(salvar);

        JMenu menuFiltros = new JMenu("Filtros");

        JMenuItem pretoBranco = new JMenuItem("Preto e Branco");
        pretoBranco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtros.add(new FiltroPretoEBranco());
                aplicar();
            }
        });
        menuFiltros.add(pretoBranco);

        JMenuItem sepia = new JMenuItem("Sépia");
        sepia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtros.add(new FiltroSepia());
                aplicar();
            }
        });
        menuFiltros.add(sepia);

        JMenuItem inverter = new JMenuItem("Inverter");
        inverter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtros.add(new FiltroInverter());
                aplicar();
            }
        });
        menuFiltros.add(inverter);

        menuFiltros.add(new JSeparator());

        JMenuItem afiar = new JMenuItem("Afiar");
        afiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtros.add(new FiltroAfiar());
                aplicar();
            }
        });
        menuFiltros.add(afiar);

        JMenuItem desfoque = new JMenuItem("Desfoque");
        desfoque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtros.add(new FiltroDesfoque());
                aplicar();
            }
        });
        menuFiltros.add(desfoque);

        JMenuItem desfoqueMovimento = new JMenuItem("Desfoque de movimento");
        desfoqueMovimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtros.add(new FiltroDesfoqueMovimento());
                aplicar();
            }
        });
        menuFiltros.add(desfoqueMovimento);

        menuFiltros.add(new JSeparator());

        JMenuItem resetar = new JMenuItem("Resetar");
        resetar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtros.clear();
                aplicar();
            }
        });
        menuFiltros.add(resetar);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuArquivo);
        menuBar.add(menuFiltros);
        setJMenuBar(menuBar);

        abrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Imagens (*.jpg, *.png, *.bmp)", "jpg", "png", "bmp"));

                int opcao = fileChooser.showOpenDialog(TelaPrincipal.this);
                if (opcao == JFileChooser.APPROVE_OPTION) {
                    File arquivo = fileChooser.getSelectedFile();
                    try {
                        carregarImagem(arquivo);
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Não foi possível abrir a imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                int userSelection = fileChooser.showSaveDialog(TelaPrincipal.this);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File arquivo = fileChooser.getSelectedFile();
                    arquivo = new File(arquivo.getAbsoluteFile() + ".png");
                    try {
                        salvarImage(arquivo);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Não foi possível salvar a imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuFiltros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicar();
            }
        });
    }

    public void aplicar() {
        int[][][] novosPixels;
        try {
            novosPixels = Utilidades.pegarCores(imagemOriginal);

            for (int i = 0; i < filtros.size(); i++) {
                Filtro filtro = filtros.get(i);
                filtro.aplicar(novosPixels);
            }

            for (int x = 0; x < novosPixels.length; x++) {
                for (int y = 0; y < novosPixels[0].length; y++) {
                    for (int i = 0; i < Utilidades.NUM_CANAIS; i++) {
                        novosPixels[x][y][i] = Math.min(Math.max(novosPixels[x][y][i], 0), 255);
                    }
                }
            }

            imagemModificada = Utilidades.pegarImagem(novosPixels);
            mudarImagem(imagemModificada);
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Não foi aplicar os filtros.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void carregarImagem(File file) throws Exception {
        BufferedImage img = ImageIO.read(file);
        imagemOriginal = new BufferedImage(img.getWidth(), img.getHeight(), Utilidades.TIPO_IMAGEM);
        Graphics2D g = imagemOriginal.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.finalize();

        mudarImagem(imagemOriginal);
        imagemModificada = imagemOriginal;

        pack();
    }

    public void salvarImage(File file) throws IOException {
        ImageIO.write(imagemModificada, "PNG", file);
    }

    private void mudarImagem(BufferedImage imagem) {
        lblImage.setIcon(new ImageIcon(imagem));
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        JFrame frame = new TelaPrincipal();

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        SwingUtilities.updateComponentTreeUI(frame);

        frame.setVisible(true);
    }

}
