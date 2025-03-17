package view;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Jugador;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JugadorOmahaCartasPanel extends JPanel {
    private Jugador jugador;
    private JLabel carta1;
    private JLabel carta2;
    private JLabel carta3;
    private JLabel carta4;
    private JLabel equity;
    private JButton fold;


    public JugadorOmahaCartasPanel(Jugador jugador) {
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

        // create carta2 label
        try {
            BufferedImage img = ImageIO.read(new File("assets/cartas/" + jugador.getCarta2().fileString()));
            Image scaled_img = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            carta2 = new JLabel(new ImageIcon(scaled_img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedImage img = ImageIO.read(new File("assets/cartas/" + jugador.getCarta3().fileString()));
            Image scaled_img = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            carta1 = new JLabel(new ImageIcon(scaled_img));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create carta2 label
        try {
            BufferedImage img = ImageIO.read(new File("assets/cartas/" + jugador.getCarta4().fileString()));
            Image scaled_img = img.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            carta2 = new JLabel(new ImageIcon(scaled_img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        equity = new JLabel();
        equity.setText(jugador.getEquity());
        //create fold button
        fold = new JButton("Fold");

        carta1.setBounds(0, 0, 100, 150);
        carta2.setBounds(33, 0, 100, 150);
        carta3.setBounds(66, 0, 100, 150);
        carta4.setBounds(100, 0, 100, 150);
        equity.setBounds(0, 150, 100, 50);
        fold.setBounds(150, 150, 50, 50);

        this.add(carta4);
        this.add(carta3);
        this.add(carta2);
        this.add(carta1);
        this.add(equity);
        this.add(fold);
    }

    //method to set the position of the panel




}
