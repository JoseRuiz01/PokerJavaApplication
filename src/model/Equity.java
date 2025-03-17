package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Equity {

    public static void getEquityAprox(Baraja pila, ArrayList<Jugador> lj, Carta[] mesa) {

        // List<Jugador> lj = Arrays.asList(players);
        int N = 2000000, len = mesa.length;
        Carta[] cartas = new Carta[7];

        for (int i = 0; i < len; i++) {
            cartas[i] = mesa[i];
        }

        for (Jugador j : lj) {
            j.resetCuenta();
        }

        for (int n = 0; n < N; n++) {

            for (int i = 0; i + len < 5; i++) {
                cartas[i + len] = pila.repartirCarta();
                pila.devolverCarta(cartas[i + len]);
            }

            for (int i = 0; i < lj.size(); i++) {
                cartas[5] = lj.get(i).getCarta1();
                cartas[6] = lj.get(i).getCarta2();
                lj.get(i).setMano(solveCase2(cartas));
            }

            Collections.sort(lj, Collections.reverseOrder());

            for (int a = 0; a < (lj.size() - 1); a++) {
                if (lj.get(a).compareTo(lj.get(a + 1)) != 0) {
                    for (int b = 0; b <= a; b++) {
                        lj.get(b).sumaCuenta(1.0 / (a + 1));
                    }
                    break;
                }
                if (a == 4 && lj.get(a).compareTo(lj.get(a + 1)) == 0) {
                    for (int b = 0; b < 6; b++) {
                        lj.get(b).sumaCuenta(1.0 / 6.0);
                    }
                }
            }

            if (n % 10000 == 0) {
                System.out.println(n);
            }

        }
        for (Jugador j : lj) {
            double tmp = j.cuenta() * 100/ N;
            j.setEquity(Math.round(tmp * 1000) / 1000.0);
        }
    }

    public static void getTotalEquity(Baraja pila, ArrayList<Jugador> lj) {
        long contador = 0;
        // List<Jugador> lj = Arrays.asList(players);

        for (int i = 0; i < lj.size(); i++) {
            pila.darCarta(lj.get(i).getCarta1().toString());
            pila.darCarta(lj.get(i).getCarta2().toString());
        }

        for (int i = 0; i < pila.porSalir.size(); i++) {
            for (int j = i + 1; j < pila.porSalir.size(); j++) {
                for (int k = j + 1; k < pila.porSalir.size(); k++) {
                    for (int l = k + 1; l < pila.porSalir.size(); l++) {
                        for (int m = l + 1; m < pila.porSalir.size(); m++) {
                            for (int a = 0; a < lj.size(); a++) {
                                Carta[] c = { pila.porSalir.get(i), pila.porSalir.get(j), pila.porSalir.get(k),
                                        pila.porSalir.get(l), pila.porSalir.get(m), lj.get(a).getCarta1(),
                                        lj.get(a).getCarta2() };
                                lj.get(a).setMano(solveCase2(c));

                            }

                            Collections.sort(lj, Collections.reverseOrder());
                            contador++;
                            for (int d = 0; d < lj.size() - 1; d++) {
                                if (lj.get(d).compareTo(lj.get(d + 1)) != 0) {
                                    for (int b = 0; b <= d; b++) {
                                        lj.get(b).sumaCuenta(1.0 / (d + 1));
                                    }
                                    break;
                                }
                                if (d == 4 && lj.get(d).compareTo(lj.get(d + 1)) == 0) {
                                    for (int b = 0; b < 6; b++) {
                                        lj.get(b).sumaCuenta(1.0 / 6.0);
                                    }
                                }
                            }
                        }
                        for (Jugador d : lj) {
                            double tmp = d.cuenta() * 100/ contador;
                            d.setEquity(Math.round(tmp * 1000) / 1000.0);
                        }
                    }
                }
            }
        }
    }

    public static Mano solveCase2(Carta[] cartas) {

        Mano mejor = null;

        for (int i = 0; i < cartas.length; i++) {
            for (int j = i + 1; j < cartas.length; j++) {
                for (int k = j + 1; k < cartas.length; k++) {
                    for (int l = k + 1; l < cartas.length; l++) {
                        for (int m = l + 1; m < cartas.length; m++) {
                            Carta[] c = { cartas[i], cartas[j], cartas[k], cartas[l], cartas[m] };
                            Mano tmp = new Mano(c);
                            if (mejor == null) {
                                mejor = tmp;
                            } else if (tmp.compareTo(mejor) == 1) {
                                mejor = tmp;
                            }
                        }
                    }
                }
            }
        }
        return mejor;
    }

}
