package model;

import java.util.Comparator;

public class Jugador implements Comparator<Jugador>, Comparable<Jugador>{
    //The class Jugador will have 2 cards, an id and an equity
    //The class will have a method to set the equity of the hand

    private int id;
    private Carta carta1;
    private Carta carta2;
    private Carta carta3;
    private Carta carta4;
    private double equity = 0.0;
    private Mano mano;
    private double cuenta;
    private int manosGanadas;
    boolean folded = false;

    public Jugador(int id, Carta carta1, Carta carta2) {
        this.id = id;
        this.carta1 = carta1;
        this.carta2 = carta2;
        this.carta3 = null;
        this.carta4 = null;
    }

    public Jugador(int id, Carta carta1, Carta carta2, Carta carta3, Carta carta4) {
        this.id = id;
        this.carta1 = carta1;
        this.carta2 = carta2;
        this.carta3 = carta3;
        this.carta4 = carta4;
        manosGanadas=0;
    }

    public void resetCuenta() {
        cuenta = 0;
    }

    public void sumaCuenta(double d) {
        cuenta += d;
    }

    public double cuenta() {
        return cuenta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Carta getCarta1() {
        return carta1;
    }

    public void setCarta1(Carta carta1) {
        this.carta1 = carta1;
    }

    public Carta getCarta2() {
        return carta2;
    }

    public void Manoganada(){
        manosGanadas++;
    }
    public void setCarta2(Carta carta2) {
        this.carta2 = carta2;
    }

    public Carta getCarta3() {
        return carta3;
    }

    public void setCarta3(Carta carta3) {
        this.carta3 = carta3;
    }

    public Carta getCarta4() {
        return carta4;
    }

    public void setCarta4(Carta carta4) {
        this.carta4 = carta4;
    }

    public String getEquity() {
        return equity + "%";
    }

    public void setEquity(double equity) {
        this.equity = equity;
    }

    public void setMano(Mano m) {
        mano = m;
    }

    @Override
    public int compareTo(Jugador o) {
        return compare(this, o);
    }

    @Override
    public int compare(Jugador o1, Jugador o2) {
        return o1.mano.compareTo(o2.mano);
    }

    public boolean isFolded(){
        return folded;
    }

    public void setFolded(boolean b) {
        folded = b;
    }
}
