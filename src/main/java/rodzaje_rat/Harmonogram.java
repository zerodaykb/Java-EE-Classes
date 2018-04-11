package rodzaje_rat;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public abstract class Harmonogram {
 List<Rata> raty = new ArrayList<>();


 public String przygotujJson() {
  return new Gson().toJson(raty);
 }

 public abstract void wyliczRaty(int ilosc_rat, double kwota_kredytu,  double oprocentowanie, double oplata_stala);

}
