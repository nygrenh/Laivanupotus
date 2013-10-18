/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.tekoaly;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import objektit.Laiva;
import objektit.Pelilauta;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henrik
 */
public class TodennakoisyysKarttaTest {

    private TodennakoisyysKartta kartta;
    private Pelilauta pelilauta;

    @Before
    public void setUp() {
        ArrayList lista = new ArrayList<Laiva>();
        lista.add(new Laiva(2, "Kumivene"));
        pelilauta = new Pelilauta(10);
        kartta = new TodennakoisyysKartta(pelilauta, lista, Vaikeustaso.HELPPO);
    }

    @Test
    public void testAnnaiskukohdePalauttaaPisteenJotaVoiIskea() {
        Point p = kartta.annaiskukohde();
        assertTrue(pelilauta.koordinaatitOnPelilaudanRajojenSisalla(p.x, p.y));
    }
}