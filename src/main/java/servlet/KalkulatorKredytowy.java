package servlet;

import rodzaje_rat.Harmonogram;
import rodzaje_rat.HarmonogramMalejace;
import rodzaje_rat.HarmonogramStale;
import sun.security.validator.ValidatorException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/kalkulator")
public class KalkulatorKredytowy extends HttpServlet implements ParametryKredytu {
  private static final int MAKSYMALNA_ILOSC_RAT = 12 * 30;

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String kwota_kredytu = getField(request, response, KWOTA_KREDYTU);
      String ilosc_rat = getField(request, response, ILOSC_RAT);
      String oprocentowanie = getField(request, response, OPROCENTOWANIE);
      String oplata_stala = getField(request, response, OPLATA_STALA);
      String rodzaj_rat = getField(request, response, RODZAJ_RAT);


    Harmonogram harmonogram = ustawRaty(
      Integer.parseInt(ilosc_rat),
      Double.parseDouble(kwota_kredytu),
      Double.parseDouble(oprocentowanie),
      Double.parseDouble(oplata_stala),
      rodzaj_rat
    );

    String json = harmonogram.przygotujJson();
    PrintWriter writer = response.getWriter();


    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Methods","GET, POST");
    response.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");

    response.setContentType("application/json");
    writer.print(json);
    writer.flush();


  }

  private String getField(HttpServletRequest request, HttpServletResponse response, String fieldName) throws IOException {
    String fieldValue = request.getParameter(fieldName);

    if (fieldValue == null || fieldValue.trim().isEmpty() || !poprawnyParametr(fieldName, fieldValue)) {
      response.sendRedirect("/?error=test");
    }
    return fieldValue;
  }


  private boolean poprawnyParametr(String parametr, String wartosc) {
    switch (parametr) {
      case KWOTA_KREDYTU:
        return isDouble(wartosc) &&
          Double.parseDouble(wartosc) > 0;
      case ILOSC_RAT:
        return isInteger(wartosc) &&
          Integer.parseInt(wartosc) > 0 &&
          Integer.parseInt(wartosc) <= MAKSYMALNA_ILOSC_RAT;
      case OPROCENTOWANIE:
        return isDouble(wartosc) &&
          Double.parseDouble(wartosc) >= 0;
      case OPLATA_STALA:
        return isDouble(wartosc) &&
          Double.parseDouble(wartosc) >= 0;
      case RODZAJ_RAT:
        return wartosc.equals("malejace") || wartosc.equals("stale");
      default:
        return true;
    }
  }

  private boolean isDouble(String str) {
    try {
      Double.parseDouble(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private Harmonogram ustawRaty(int ilosc_rat, double kwota_kredytu, double oprocentowanie, double oplata_stala, String rodzaj_rat) {
    Harmonogram harmonogram = null;

    switch(rodzaj_rat) {
      case "malejace":
        harmonogram = new HarmonogramMalejace();
        break;
      case "stale":
        harmonogram = new HarmonogramStale();
        break;
      default:
        break;
    }

    harmonogram.wyliczRaty(ilosc_rat, kwota_kredytu, oprocentowanie, oplata_stala);

    return harmonogram;
  }

}
