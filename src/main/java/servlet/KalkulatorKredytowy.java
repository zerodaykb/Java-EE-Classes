package servlet;

import rodzaje_rat.Harmonogram;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/kalkulator")
public class KalkulatorKredytowy extends HttpServlet {
  private static final int MAKSYMALNA_ILOSC_RAT = 12 * 30;
  private static final long serialVersionUID = 1L;

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    ParametryKredytu parametryKredytu = new ParametryKredytu();

    if(!parametryKredytu.isValidFromRequest(request)) {
      response.sendRedirect("?error=nieprawidlowe_wartosci");
      return;
    }

    parametryKredytu.ustawParametry(request);
    Harmonogram harmonogram = Harmonogram.przygotujHarmonogram(parametryKredytu);

    String json = harmonogram.zrobJson();
    PrintWriter writer = response.getWriter();

    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Methods","GET, POST");
    response.addHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");

    response.setContentType("application/json");
    writer.print(json);
    writer.flush();


  }
}
