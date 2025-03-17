package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Baraja {

    List<Carta> porSalir = new ArrayList<>();
    List<Carta> salidas = new ArrayList<>();
    Random rand = new Random();

    char[] palos = {'c', 'h', 's', 'd'};
    char[] valores = {'A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'};

    public Baraja() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                porSalir.add(new Carta(valores[j], palos[i]));
            }
        }
        Collections.shuffle(porSalir);
    }

    public boolean darCarta(String carta) {
        boolean b = porSalir.remove(new Carta(carta));
        if (!b) {
            return false;
        }
        salidas.add(new Carta(carta));
        return true;
    }

    public Carta repartirCarta() {
        int pos = rand.nextInt(porSalir.size());
        Carta c = porSalir.get(pos);
        darCarta(c.toString());
        return c;
    }

    public boolean devolverCarta(Carta c) {
        boolean b = salidas.remove(c);
        if (!b) {
            return false;
        }
        porSalir.add(c);
        return true;
    }

    public void reset() {
        for (Carta c: salidas) {
            porSalir.add(c);
        }
        Collections.shuffle(porSalir);
        salidas.clear();
    }
}
