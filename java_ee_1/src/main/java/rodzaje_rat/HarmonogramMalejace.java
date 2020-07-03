package rodzaje_rat;


import servlet.ParametryKredytu;

public class HarmonogramMalejace extends Harmonogram {

    HarmonogramMalejace (ParametryKredytu parametryKredytu) {
        super(parametryKredytu);
    }

    @Override
    public void wyliczRaty() {

        double q = 1/12.0 * oprocentowanie/100;
        double kwota_kapitalu = Math.round(kwota_kredytu / ilosc_rat * 100) / 100.0d;

        for (int i = 1; i<= ilosc_rat; i += 1) {
            double kwota_odsetek = Math.round(q * kwota_kredytu * 100) / 100.0d;
            double kwota_raty = Math.round((kwota_kapitalu + kwota_odsetek) * 100) / 100.0d;

            kwota_kredytu -= kwota_kapitalu;

            Rata rata = new Rata(i, kwota_kapitalu, kwota_odsetek, oplata_stala, kwota_raty);
            raty.add(rata);
        }
    }
}
