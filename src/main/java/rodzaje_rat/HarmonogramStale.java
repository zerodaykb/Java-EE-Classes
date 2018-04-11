package rodzaje_rat;

public class HarmonogramStale extends Harmonogram {

    @Override
    public void wyliczRaty(int ilosc_rat, double kwota_kredytu,  double oprocentowanie, double oplata_stala) {
        /*
               K*q^n(q-1)
          R = ----------
                q^n - 1
         */

        double q = 1 + 1/12.0 * oprocentowanie/100.0;

        double kwota_raty = (kwota_kredytu * (q - 1) * Math.pow(q, (double) ilosc_rat)) / (Math.pow(q, (double) ilosc_rat) - 1);
        kwota_raty = Math.round(kwota_raty * 100) / 100.0d;

        for (int i = 1; i<= ilosc_rat; i += 1) {
            double kwota_odsetek = kwota_kredytu * (q - 1);
            kwota_odsetek = Math.round(kwota_odsetek * 100) / 100.0d;

            double kwota_kapitalu = kwota_raty - kwota_odsetek;
            kwota_kapitalu = Math.round(kwota_kapitalu * 100) / 100.0d;

            kwota_kredytu -= kwota_kapitalu;

            Rata rata = new Rata(i, kwota_kapitalu, kwota_odsetek, oplata_stala, kwota_raty);
            raty.add(rata);
        }

    }
}
