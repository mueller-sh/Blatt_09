package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeldbetragTest
{

    @Test
    public void erzeugeGeldbetrag()
    {
        Geldbetrag g = Geldbetrag.get("+20,00");
        assertEquals(20, g.getEuroanteil());
        assertEquals(0, g.getCentanteil());
        g = Geldbetrag.get("00,01");
        assertEquals(0, g.getEuroanteil());
        assertEquals(1, g.getCentanteil());
        g = Geldbetrag.get("-10,10");
        assertEquals(-10, g.getEuroanteil());
        assertEquals(10, g.getCentanteil());
        g = Geldbetrag.get("-100,10");
        assertEquals(-100, g.getEuroanteil());
        assertEquals(10, g.getCentanteil());
    }

    @Test
    public void stringKorrektTest()
    {
        assertTrue(Geldbetrag.stringKorrekt("-30,00"));
        assertTrue(Geldbetrag.stringKorrekt("+30,00"));
        assertFalse(Geldbetrag.stringKorrekt("d30,00"));
        assertFalse(Geldbetrag.stringKorrekt("3"));
        assertFalse(Geldbetrag.stringKorrekt("3,"));
        assertFalse(Geldbetrag.stringKorrekt("3,0"));
        assertTrue(Geldbetrag.stringKorrekt("3,00"));
        assertFalse(Geldbetrag.stringKorrekt("3,000"));
    }

    @Test
    public void stringRepraesentation()
    {
        Geldbetrag g1 = Geldbetrag.get("20,00");
        assertEquals("20,00", g1.getStringRepraesentation());
        g1 = Geldbetrag.get("30,17");
        assertEquals("30,17", g1.getStringRepraesentation());
        g1 = Geldbetrag.get("-30,10");
        assertEquals("-30,10", g1.getStringRepraesentation());
        g1 = Geldbetrag.get("000,99");
        assertEquals("0,99", g1.getStringRepraesentation());
        g1 = Geldbetrag.get("100,99");
        assertEquals("100,99", g1.getStringRepraesentation());
    }

    @Test
    public void addiere()
    {
        Geldbetrag g1 = Geldbetrag.get("20,00");
        Geldbetrag g2 = Geldbetrag.get("30,17");
        assertEquals(Geldbetrag.get("50,17"), g1.addiere(g2));
        g1 = Geldbetrag.get("20,25");
        g2 = Geldbetrag.get("30,76");
        assertEquals(Geldbetrag.get("51,01"), g1.addiere(g2));
        g1 = Geldbetrag.get("-20,00");
        g2 = Geldbetrag.get("30,00");
        assertEquals(Geldbetrag.get("10,00"), g1.addiere(g2));
        g1 = Geldbetrag.get("-20,17");
        g2 = Geldbetrag.get("30,18");
        assertEquals(Geldbetrag.get("10,01"), g1.addiere(g2));
        g1 = Geldbetrag.get("-20,17");
        g2 = Geldbetrag.get("-30,18");
        assertEquals(Geldbetrag.get("-50,35"), g1.addiere(g2));
        g1 = Geldbetrag.get("-20,17");
        g2 = Geldbetrag.get("-30,84");
        assertEquals(Geldbetrag.get("-51,01"), g1.addiere(g2));
    }

    @Test
    public void subtrahiere()
    {
        Geldbetrag g1 = Geldbetrag.get("20,00");
        Geldbetrag g2 = Geldbetrag.get("30,17");
        assertEquals(Geldbetrag.get("-10,17"), g1.subtrahiere(g2));
        g1 = Geldbetrag.get("20,00");
        g2 = Geldbetrag.get("10,17");
        assertEquals(Geldbetrag.get("9,83"), g1.subtrahiere(g2));
        g1 = Geldbetrag.get("-20,00");
        g2 = Geldbetrag.get("10,17");
        assertEquals(Geldbetrag.get("-30,17"), g1.subtrahiere(g2));
        g1 = Geldbetrag.get("-20,00");
        g2 = Geldbetrag.get("-10,17");
        assertEquals(Geldbetrag.get("-9,83"), g1.subtrahiere(g2));
        g1 = Geldbetrag.get("20,00");
        g2 = Geldbetrag.get("-10,17");
        assertEquals(Geldbetrag.get("30,17"), g1.subtrahiere(g2));

    }

    @Test
    public void multipliziere()
    {
        Geldbetrag g1 = Geldbetrag.get("20,00");
        assertEquals(Geldbetrag.get("40,00"), g1.multipliziere(2));
        assertEquals(Geldbetrag.get("60,00"), g1.multipliziere(3));

        g1 = Geldbetrag.get("17,50");
        assertEquals(Geldbetrag.get("35,00"), g1.multipliziere(2));

        g1 = Geldbetrag.get("-17,50");
        assertEquals(Geldbetrag.get("-35,00"), g1.multipliziere(2));

    }

    @Test
    public void konvertiereStringToGeldbetrag()
    {
        String s1 = "20,00";
        assertEquals(Geldbetrag.get(s1), Geldbetrag.konvertiereString(s1));

    }

    @Test
    public void konvertiereIntToGeldbetrag()
    {
        int i1 = 2000;
        String s1 = "20,00";
        assertEquals(Geldbetrag.get(s1), Geldbetrag.konvertiereInt(i1));
        i1 = 2017;
        s1 = "20,17";
        assertEquals(Geldbetrag.get(s1), Geldbetrag.konvertiereInt(i1));
        i1 = -2017;
        s1 = "-20,17";
        assertEquals(Geldbetrag.get(s1), Geldbetrag.konvertiereInt(i1));

    }

}
