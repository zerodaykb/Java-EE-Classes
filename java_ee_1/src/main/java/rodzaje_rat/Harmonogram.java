package rodzaje_rat;

import com.google.gson.Gson;
import servlet.ParametryKredytu;

import java.util.ArrayList;
import java.util.List;

public abstract class Harmonogram {
 List<Rata> raty = new ArrayList<>();

 int ilosc_rat;
 double oprocentowanie;
 double kwota_kredytu;
 double oplata_stala;

 Harmonogram(ParametryKredytu parametryKredytu) {
  this.ilosc_rat = parametryKredytu.getIlosc_rat();
  this.oprocentowanie = parametryKredytu.getOprocentowanie();
  this.kwota_kredytu = parametryKredytu.getKwota_kredytu();
  this.oplata_stala = parametryKredytu.getOplata_stala();
 }

 public List<Rata> getRaty() {
  return raty;
 }


 public String zrobJson() {
  return new Gson().toJson(raty);
 }

 public String przygotujPdf() {
  return new Gson().toJson(raty);
 }


 public abstract void wyliczRaty();


 public static Harmonogram przygotujHarmonogram(ParametryKredytu parametryKredytu) {
  Harmonogram harmonogram = null;

  switch(parametryKredytu.getRodzaj_rat()) {
   case "malejace":
    harmonogram = new HarmonogramMalejace(parametryKredytu);
    break;
   case "stale":
    harmonogram = new HarmonogramStale(parametryKredytu);
    break;
   default:
    break;
  }

  harmonogram.wyliczRaty();

  return harmonogram;
 }
}
