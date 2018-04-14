package servlet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import rodzaje_rat.Harmonogram;
import rodzaje_rat.Rata;

import java.io.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/pdf")
public class PdfGenerator extends HttpServlet {
  private static final long serialVersionUID = 1L;


  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    ParametryKredytu parametryKredytu = new ParametryKredytu();

    if(!parametryKredytu.isValidFromRequest(request)) {
      response.sendRedirect("?error=nieprawidlowe_wartosci");
      return;
    }

    parametryKredytu.ustawParametry(request);
    Harmonogram harmonogram = Harmonogram.przygotujHarmonogram(parametryKredytu);


    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods","GET, POST");
    response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");

    createPdf(harmonogram, response);
  }

  private void createPdf(Harmonogram harmonogram, HttpServletResponse response) throws IOException {
    try {
      Document document = new Document(PageSize.A4);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();

      PdfWriter.getInstance(document, baos);
      document.open();

      PdfPTable table = new PdfPTable(5);

      table.addCell("#");
      table.addCell("Kwota kapitalu");
      table.addCell("Kwota odsetek");
      table.addCell("Kwota laczna");
      table.addCell("Oplaty stale");

      for(Rata rata : harmonogram.getRaty()){
        table.addCell("" + rata.getNr_raty());
        table.addCell("" + rata.getKwota_kapitalu());
        table.addCell("" + rata.getKwota_odsetek());
        table.addCell("" + rata.getKwota_calkowita());
        table.addCell("" + rata.getOplata_stala());
      }

      document.add(table);
      document.close();

      response.setHeader("Expires", "0");
      response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
      response.setHeader("Pragma", "public");

      response.addHeader("Content-Disposition", "attachment; filename=test.pdf");
      response.setContentType("application/pdf");
      response.setContentLength(baos.size());

      ServletOutputStream os = response.getOutputStream();

      baos.writeTo(os);
      os.flush();
      os.close();

    } catch (DocumentException e) {
      e.printStackTrace();
    }

  }
}
