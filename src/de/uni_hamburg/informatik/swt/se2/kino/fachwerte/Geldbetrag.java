package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Geldbetrag
{

    private final int _centanteil;
    private final int _euroanteil;

    public static Geldbetrag get(String geldbetragString)
    {
        assert stringKorrekt(
                geldbetragString) : "String nicht korrekt formatiert als 'EE,CC'";

        // Kommentar
        String euro = geldbetragString.substring(0,
                geldbetragString.length() - 3);
        String cent = geldbetragString.substring(geldbetragString.length() - 2,
                geldbetragString.length());
        int eurogeldbetrag = Integer.parseInt(euro);
        int centgeldbetrag = Integer.parseInt(cent);

        return new Geldbetrag(eurogeldbetrag, centgeldbetrag);
    }

    private Geldbetrag(int eurogeldbetrag, int centgeldbetrag)
    {
        _euroanteil = eurogeldbetrag;
        _centanteil = centgeldbetrag;
    }

    public int getEuroanteil()
    {
        // TODO Auto-generated method stub
        return _euroanteil;
    }

    public int getCentanteil()
    {
        // TODO Auto-generated method stub
        return _centanteil;
    }

    public String getStringRepraesentation()
    {     
        return String.format("%01d,%02d", _euroanteil, _centanteil);
    }

    public static boolean stringKorrekt(String geldbetragString)
    {
        String decimalRegex = "([-,+])?\\d+\\,\\d\\d";

        Pattern p = Pattern.compile(decimalRegex);
        Matcher m = p.matcher(geldbetragString);

        return m.matches();
    }

    public Geldbetrag addiere(Geldbetrag geldbetrag)
    {
        int gesamt1 = 0;
        int gesamt2 = 0;
        int gesamt3 = 0;

        if (_euroanteil < 0)
        {
            gesamt1 = _euroanteil * 100 - _centanteil;
        }
        else
        {
            gesamt1 = _euroanteil * 100 + _centanteil;
        }

        if (geldbetrag.getEuroanteil() < 0)
        {
            gesamt2 = geldbetrag.getEuroanteil() * 100
                    - geldbetrag.getCentanteil();
        }
        else
        {
            gesamt2 = geldbetrag.getEuroanteil() * 100
                    + geldbetrag.getCentanteil();
        }
        gesamt3 = gesamt1 + gesamt2;
        int euroneu = gesamt3 / 100;
        int centneu = Math.abs(gesamt3 % 100);

        return new Geldbetrag(euroneu, centneu);
    }

    public Geldbetrag subtrahiere(Geldbetrag geldbetrag)
    {

        return addiere(new Geldbetrag(-geldbetrag.getEuroanteil(),
                geldbetrag.getCentanteil()));
    }

    public Geldbetrag multipliziere(int faktor)
    {
        int gesamt = 0;
        if (_euroanteil < 0)
        {
            gesamt = _euroanteil * 100 - _centanteil;
        }
        else
        {
            gesamt = _euroanteil * 100 + _centanteil;
        }
        gesamt = gesamt * faktor;
        int euroneu = gesamt / 100;
        int centneu = Math.abs(gesamt % 100);

        return new Geldbetrag(euroneu, centneu);
    }

    @Override
    public boolean equals(Object o)
    {
        return (o instanceof Geldbetrag) && equals((Geldbetrag) o);
    }

    private boolean equals(Geldbetrag geldbetrag)
    {
        return (geldbetrag.getEuroanteil() == _euroanteil)
                && (geldbetrag.getCentanteil() == _centanteil);
    }

    @Override
    public int hashCode()
    {
        int hashcode = 0;
        if (getEuroanteil() < 0)
        {
            hashcode = _euroanteil - _centanteil;
        }
        else
        {
            hashcode = _euroanteil + _centanteil;

        }
        return hashcode;
    }

    public static Geldbetrag konvertiereString(String s1)
    {
        return get(s1);
    }

    public static Geldbetrag konvertiereInt(int i1)
    {

        return new Geldbetrag(i1 / 100, Math.abs(i1) % 100);
    }

}
