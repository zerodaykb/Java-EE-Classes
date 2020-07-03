package servlet;

import javax.servlet.http.HttpServletRequest;

public class ParametryKredytu {
    private int ilosc_rat;
    private double kwota_kredytu;
    private double oprocentowanie;
    private double oplata_stala;
    private String rodzaj_rat;


    public double getKwota_kredytu () {
        return kwota_kredytu;
    }

    public void setKwota_kredytu (double kwota_kredytu) {
        this.kwota_kredytu = kwota_kredytu;
    }

    public int getIlosc_rat () {
        return ilosc_rat;
    }

    public void setIlosc_rat (int ilosc_rat) {
        this.ilosc_rat = ilosc_rat;
    }

    public double getOprocentowanie () {
        return oprocentowanie;
    }

    public void setOprocentowanie (double oprocentowanie) {
        this.oprocentowanie = oprocentowanie;
    }

    public double getOplata_stala () {
        return oplata_stala;
    }

    public void setOplata_stala (double oplata_stala) {
        this.oplata_stala = oplata_stala;
    }

    public String getRodzaj_rat () {
        return rodzaj_rat;
    }

    public void setRodzaj_rat (String rodzaj_rat) {
        this.rodzaj_rat = rodzaj_rat;
    }


    public void ustawParametry (HttpServletRequest request) {
        ilosc_rat = toInt(request.getParameter("ilosc_rat"));
        kwota_kredytu = toDouble(request.getParameter("kwota_kredytu"));
        oprocentowanie = toDouble(request.getParameter("oprocentowanie"));
        oplata_stala = toDouble(request.getParameter("oplata_stala"));
        rodzaj_rat = request.getParameter("rodzaj_rat");
    }

    public boolean isValidFromRequest (HttpServletRequest request) {
        return isFieldValid(request, "ilosc_rat") &&
               isFieldValid(request, "kwota_kredytu") &&
               isFieldValid(request, "oplata_stala") &&
               isFieldValid(request, "oprocentowanie") &&
               isFieldValid(request, "rodzaj_rat");
    }

    private boolean isFieldValid(HttpServletRequest request, String fieldName) {
        String fieldValue = request.getParameter(fieldName);

        return fieldValue != null &&
          !fieldValue.trim().isEmpty() &&
          checkParamType(fieldName, fieldValue);
    }


    private boolean isDouble (String str) {
        try {
            Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private boolean isInteger (String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private double toDouble(String str) {
        return Double.parseDouble(str);
    }

    private int toInt(String str) {
        return Integer.parseInt(str);
    }


    private boolean checkParamType (String parametr, String wartosc) {
        switch (parametr) {
            case "kwota_kredytu":
                return isDouble(wartosc);
            case "ilosc_rat":
                return isInteger(wartosc);
            case "oprocentowanie":
                return isDouble(wartosc);
            case "oplata_stala":
                return isDouble(wartosc);
            case "rodzaj_rat":
                return wartosc.equals("malejace") || wartosc.equals("stale");
            default:
                return true;
        }
    }
}