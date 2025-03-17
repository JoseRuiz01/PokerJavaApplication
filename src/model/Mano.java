package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Mano implements Comparator<Mano>, Comparable<Mano> {

    String jugada;
    ArrayList<String> draws = new ArrayList<String>();
    Carta[] mano = new Carta[5];
    int njugada, val_jugada;
    private int diamantes;
    private int corazones;
    private int treboles;
    private int picas;
    private int[] valores = new int[13];

    Mano(Carta[] cartas) {
        mano = cartas;
        cargarMano();
        resuelveMano();
    }

    public String toString() {
        return jugada;
    }

    private void resuelveMano() {
        Arrays.sort(mano);
        setJugada();
        setDraws();
    }

    private void setJugada() {
        if (hayEscaleraColor()) {
            return;
        }
        if (hayPoker()) {
            return;
        }
        if (hayFull()) {
            return;
        }
        if (hayColor()) {
            return;
        }
        if (hayEscalera()) {
            return;
        }
        if (hayTrio()) {
            return;
        }
        if (hayDoblePareja()) {
            return;
        }
        if (hayPareja()) {
            return;
        }
        njugada = mano[4].getValor();
        jugada = "Carta alta";
        return;
    }

    private void setDraws() {
        int sum = 0;
        if (valores[12] == 1)
            sum++;
        if (valores[11] == 1)
            sum++;
        if (valores[10] == 1)
            sum++;
        if (valores[9] == 1)
            sum++;
        if (valores[8] == 1)
            sum++;
        int idx = 8;
        while (sum < 4 && idx > 0) {
            idx--;
            if (valores[idx] == 1) {
                sum++;
            }
            if (valores[idx + 5] == 1) {
                sum--;
            }
        }
        if (sum == 4 && idx > 0 && valores[idx - 1] != 1) { // idx > 0 && valores[idx-1] != 1 esta ocndicion se usa para
                                                            // que no de draw en caso de que haya ya una escalera
            if (valores[idx + 4] == 0 || valores[idx] == 0) {
                draws.add("Straight open ended");
            } else {
                draws.add("Straight gutshot");
            }
        }
        if (diamantes == 4 || corazones == 4 || treboles == 4 || picas == 4) {
            draws.add("Color");
        }

    }

    private String parse(int i) {
        String str = "";
        switch (i) {
            case 2:
                str = "doses";
                break;
            case 3:
                str = "treses";
                break;
            case 4:
                str = "cuatros";
                break;
            case 5:
                str = "cincos";
                break;
            case 6:
                str = "seises";
                break;
            case 7:
                str = "sietes";
                break;
            case 8:
                str = "ochos";
                break;
            case 9:
                str = "nueves";
                break;
            case 10:
                str = "dieces";
                break;
            case 11:
                str = "jotas";
                break;
            case 12:
                str = "reinas";
                break;
            case 13:
                str = "reyes";
                break;
            case 14:
                str = "ases";
                break;
            default:
                break;
        }

        return str;
    }

    private boolean hayPareja() {
        for (int i = 13; i > 0; i--) {
            if (valores[i % 13] == 2) {
                val_jugada = i;
                njugada = 15 + i;
                jugada = "Pareja de " + parse(i + 1);
                return true;
            }
        }
        return false;
    }

    private boolean hayDoblePareja() {
        boolean aux = false;
        for (int i = 13; i > 0; i--) {
            if (valores[i % 13] == 2) {

                if (!aux) {
                    val_jugada = i + 1;
                    njugada = 15 * 2 + i;
                    aux = true;
                } else {
                    jugada = "Dobles parejas " + parse(i + 1) + " " + parse(val_jugada);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hayTrio() {
        for (int i = 13; i > 0; i--) {
            if (valores[i % 13] == 3) {
                val_jugada = i + 1;
                njugada = 15 * 3 + i;
                jugada = "Trio de " + parse(i + 1);
                return true;
            }
        }
        return false;
    }

    private boolean hayEscalera() {
        for (int i = 13; i > 3; i--) {
            if (valores[i % 13] > 0 && valores[i - 1 % 13] > 0 && valores[i - 2 % 13] > 0 && valores[i - 3 % 13] > 0
                    && valores[i - 4 % 13] > 0) {
                val_jugada = i + 1;
                njugada = 15 * 4 + i;
                jugada = "Escalera";
                return true;
            }
        }
        return false;
    }

    private boolean hayColor() {
        if (diamantes > 4) {
            // val_jugada='d';
            njugada = 15 * 5 + mano[4].getPalo();
            jugada = "Color (diamantes)";
            return true;
        }
        if (corazones > 4) {
            // val_jugada='h';
            njugada = 15 * 5 + mano[4].getPalo();
            jugada = "Color (corazones)";
            return true;
        }
        if (treboles > 4) {
            // val_jugada='c';
            njugada = 15 * 5 + mano[4].getPalo();
            jugada = "Color (tréboles)";
            return true;
        }
        if (picas > 4) {
            // val_jugada='s';
            njugada = 15 * 5 + mano[4].getPalo();
            jugada = "Color (picas)";
            return true;
        }
        return false;
    }

    private boolean hayFull() {
        if (!hayPareja()) {
            return false;
        }
        String[] par = jugada.split(" ");
        if (hayTrio()) {
            String[] trio = jugada.split(" ");
            njugada = 15 * 6 + val_jugada;
            jugada = "Full de " + trio[2] + " y " + par[2];
            return true;
        }
        return false;
    }

    private boolean hayPoker() {
        for (int i = 13; i > 0; i--) {
            if (valores[i % 13] == 4) {
                val_jugada = i + 1;
                njugada = 15 * 7 + i;
                jugada = "Poker de " + parse(i + 1);
                return true;
            }
        }
        return false;
    }

    private boolean hayEscaleraColor() {
        if (hayColor() && hayEscalera()) {
            njugada = 15 * 8 + val_jugada;
            jugada = "Escalera de color";
            return true;
        }
        return false;
    }

    private int getValor() {
        return njugada;
    }

    private Carta[] getMano() {
        return mano;
    }

    @Override
    public int compareTo(Mano o) {
        return compare(this, o);
    }

    @Override
    public int compare(Mano o1, Mano o2) {
        if (o1.getValor() > o2.getValor()) {
            return 1;
        }
        if (o1.getValor() < o2.getValor()) {
            return -1;
        }
        for (int i = 0; i < 5; i++) {
            int com = o1.getMano()[i].compareTo(o2.getMano()[i]);
            if (com != 0) {
                return com;
            }
        }

        return 0;
    }

    private void cargarMano() {

        char c;

        for (int i = 0; i < mano.length; i++) {

            c = mano[i].getPalo();
            if (c == 'd') {
                diamantes++;
            } else if (c == 'h') {
                corazones++;
            } else if (c == 'c') {
                treboles++;
            } else if (c == 's') {
                picas++;
            }

            valores[(mano[i].getValor() - 1) % 13]++;
        }
    }

    public String stringCartas() {
        String str = "";
        for (int i = 0; i < mano.length; i++) {
            str += mano[i];
        }
        return str;
    }

    public String draws() {
        String str = "";
        for (int i = 0; i < draws.size(); i++) {
            str += " - Draw: " + draws.get(i) + "\n";
        }
        return str;
    }
}
