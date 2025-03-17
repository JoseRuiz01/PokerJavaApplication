package model;

import java.util.Comparator;

public class Carta implements Comparator<Carta>, Comparable<Carta> {
    int valor;
    char valor_C;
    char palo;

    Carta(char valor, char palo) {
        this.valor_C = valor;
        this.valor = valor_carta(valor);
        this.palo = palo;
    }

    public Carta(String carta) {
        this(carta.charAt(0), carta.charAt(1));
    }

    private int valor_carta(char n) {
        if (Character.getNumericValue(n) >= 2 && Character.getNumericValue(n) <= 9)
            return Character.getNumericValue(n); // Devuelve el valor númerico de la carta si es un número

        else {
            switch (n) { // Devuelve el valor númerico de la carta si es una letra
                case 'A':
                    return 14;
                case 'T':
                    return 10;
                case 'J':
                    return 11;
                case 'Q':
                    return 12;
                case 'K':
                    return 13;
            }
        }

        return -1;
    }

    public String toString() {
        return "" + valor_C + palo;
    }

    @Override
    public int compare(Carta o1, Carta o2) {
        if (o1.getValor() > o2.getValor()) {
            return 1;
        } else if (o1.getValor() > o2.getValor()) {
            return -1;
        } else {
            return 0;
        }
    }

    public boolean equals(Object o) {
 
        if (o == this) {
            return true;
        }
 
        if (!(o instanceof Carta)) {
            return false;
        }
         

        Carta c = (Carta) o;
         
        return c.valor == valor && c.palo == palo;
    }


    @Override
    public int compareTo(Carta o) {
        return compare(this, o);
    }

    public int getValor() {
        return this.valor;
    }

    public char getPalo() {
        return this.palo;
    }

    public String fileString() {
        String valorString = "";
        String paloString = "";
        String modifier = "";

        if (this.valor >= 2 && this.valor <= 9)
            valorString += this.valor_C; // Devuelve el valor númerico de la carta si es un número
        else {
            switch (this.valor_C) { // Devuelve el valor númerico de la carta si es una letra
                case 'A':
                    valorString += "ace";
                    break;
                case 'T':
                    valorString += "10";
                    break;
                case 'J':
                    valorString += "jack";
                    modifier = "2";
                    break;
                case 'Q':
                    valorString += "queen";
                    modifier = "2";
                    break;
                case 'K':
                    valorString += "king";
                    modifier = "2";
                    break;
            }
        }

        switch (this.palo) { // Devuelve el valor númerico de la carta si es una letra
            case 's':
                paloString += "spades";
                break;
            case 'h':
                paloString += "hearts";
                break;
            case 'd':
                paloString += "diamonds";
                break;
            case 'c':
                paloString += "clubs";
                break;

        }
        return "" + valorString + "_of_" + paloString + modifier + ".png";
    }
}