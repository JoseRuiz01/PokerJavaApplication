package view;

import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Baraja;
import model.Carta;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class CartaMesaPanel extends JPanel {
    private Carta[] cartas;
    private JLabel[] cartasLabels = new JLabel[5];

    public CartaMesaPanel(Carta[] cartas) {
        this.cartas = cartas;
        initGUI();
       
    }

    // initGUI will initialize the jlabels and add them to the panel
    public void initGUI() {
        int width = 75;
        int height =113;
        this.setLayout(null);
        setBounds(255, 250, 415, 115);
        if (cartas == null) {
            return;
        }
        this.setOpaque(false);
        for (int i = 0; i < cartas.length; i++) {
            try {
                BufferedImage img = ImageIO.read(new File("assets/cartas/" + cartas[i].fileString()));
                Image scaled_img = img.getScaledInstance(75, 113, Image.SCALE_SMOOTH);
                cartasLabels[i] = new JLabel(new ImageIcon(scaled_img));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //cartasLabels[i] = new JLabel();
            cartasLabels[i].setBounds((0 + 85 *i), 0, width, height);
            this.add(cartasLabels[i]); 
            
        }
        this.repaint();

    }

    public static Carta cartaRandom(Baraja pila) {
        return pila.repartirCarta();
    }

    Carta[] getCartas() {
        return cartas;
    }

    public void devolverABaraja(Baraja baraja) {
        if (cartas != null) {
            for (int i = 0; i < cartas.length; i++) {
                if (cartas[i] != null) {
                    baraja.devolverCarta(cartas[i]);
                }
            }
        }

    }

}
