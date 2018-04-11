package rodzaje_rat;

public class Rata {
    int nr_raty;
    double kwota_kapitalu;
    double kwota_odsetek;
    double oplata_stala;
    double kwota_calkowita;

    public Rata(int nr_raty, double kwota_kapitalu, double kwota_odsetek, double oplata_stala, double kwota_calkowita) {
        this.nr_raty = nr_raty;
        this.kwota_kapitalu = kwota_kapitalu;
        this.kwota_odsetek = kwota_odsetek;
        this.oplata_stala = oplata_stala;
        this.kwota_calkowita = kwota_calkowita;
    }
}
