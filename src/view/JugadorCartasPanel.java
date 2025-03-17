package view;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Baraja;
import model.Carta;
import model.Jugador;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

public class JugadorCartasPanel extends JPanel {
    private Jugador jugador;
    private JLabel carta1;
    private JLabel carta2;
    private JLabel equity;
    private JButton fold;
    private boolean folded = false;


    public JugadorCartasPanel(Jugador jugador) {
        this.jugador = jugador;
        initGUI();
    }

    //initGUI will initialize the jlabels and add them to the panel
    public void initGUI() {
        this.setLayout(null);
        int width = 200;
        int height = 200;
        switch (jugador.getId()) {
            case 1:
                setBounds(40, 70, width, height);
                break;
            case 2:
                setBounds(365, 25, width, height);
                break;
            case 3:
                setBounds(690, 70, width, height);
                break;
            case 4:
                setBounds(690, 345, width, height);
                break;
            case 5:
                setBounds(365, 420, width, height);
                break;
            case 6:
                setBounds(40, 345, width, height);
                break;
        }
        
        try {
            BufferedImage img = ImageIO.read(new File("assets/cartas/" + jugador.getCarta1().fileString()));
            Image scaled_img = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            carta1 = new JLabel(new ImageIcon(scaled_img));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create carta2 label
        try {
            BufferedImage img = ImageIO.read(new File("assets/cartas/" + jugador.getCarta2().fileString()));
            Image scaled_img = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            carta2 = new JLabel(new ImageIcon(scaled_img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        equity = new JLabel();
        equity.setText("P" + jugador.getId() + " "+ jugador.getEquity());
        //create fold button
        fold = new JButton("Fold");
        fold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foldButtonActionPerformed(evt);
            }
        });

        

        carta1.setBounds(0, 0, 100, 150);
        carta2.setBounds(100, 0, 100, 150);
        equity.setBounds(0, 150, 100, 50);
        fold.setBounds(100, 150, 100, 50);

        this.add(carta1);
        this.add(carta2);
        this.add(equity);
        this.add(fold);
        this.repaint();
    }

    public boolean cartasRandom(Baraja pila, Carta c1, Carta c2) {
        pila.devolverCarta(jugador.getCarta1());
        pila.devolverCarta(jugador.getCarta2());

        if (!pila.darCarta(c1.toString())) {
            return false;
        }
        jugador.setCarta1(c1);
        if (!pila.darCarta(c2.toString())) {
            return false;
        }
        jugador.setCarta2(c2);
        updateCardIm();

        return true;
    }

    public void cartasRandom(Baraja pila) {
        pila.devolverCarta(jugador.getCarta1());
        pila.devolverCarta(jugador.getCarta2());
        jugador.setCarta1(pila.repartirCarta());
        jugador.setCarta2(pila.repartirCarta());
        updateCardIm();
    }

    private void updateCardIm() {
        try {
            BufferedImage img;
            if (folded) {
                img = ImageIO.read(new File("assets/cartas/red_joker.png"));
            } else {
                img = ImageIO.read(new File("assets/cartas/" + jugador.getCarta1().fileString()));
            }
            Image scaled_img = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            carta1 = new JLabel(new ImageIcon(scaled_img));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //create carta2 label
        try {
            BufferedImage img;
            if (folded) {
                img = ImageIO.read(new File("assets/cartas/red_joker.png"));
            } else {
                img = ImageIO.read(new File("assets/cartas/" + jugador.getCarta2().fileString()));
            }
            Image scaled_img = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            carta2 = new JLabel(new ImageIcon(scaled_img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        carta1.setBounds(0, 0, 100, 150);
        carta2.setBounds(100, 0, 100, 150);

        this.removeAll();
        this.add(carta1);
        this.add(carta2);
        this.add(equity);
        this.add(fold);
        this.repaint();
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void reset() {
        jugador.setEquity(0.0);
        equity.setText("P" + jugador.getId() + " " + jugador.getEquity());
        folded = false;
        jugador.setFolded(false);
    }

    public void updateEquity() {
        equity.setText("P" + jugador.getId() + " "+ jugador.getEquity());
        System.out.println(jugador.getEquity());
    }

    private void foldButtonActionPerformed(java.awt.event.ActionEvent evt) {
        folded = true;
        jugador.setFolded(true);
        updateCardIm();
    }

}
