package jdbcdemo.jdbcdemo;

import jdbcdemo.dao.JdbcRepositoriesFactory;
import jdbcdemo.dao.repositories.RepositoryQuery;
import jdbcdemo.domain.Klub;
import jdbcdemo.domain.Stadion;
import jdbcdemo.domain.Trener;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Trener trener = new Trener();
        trener.setImie("aa trener imie");
        trener.setNazwisko("a trener nazwisko");

        Stadion stadion = new Stadion();
        stadion.setNazwa("aa stadion");
        stadion.setMiasto("aa miasto");

        Klub klub = new Klub();
        klub.setNazwa("inny klub");
        klub.setTrener(27);
        klub.setStadion(27);

        RepositoryQuery workdb = new JdbcRepositoriesFactory().HsqlDbWorkdb();

        workdb.trenerzy().createTable();
        workdb.stadiony().createTable();
        workdb.kluby().createTable();

        workdb.trenerzy().add(trener);
        workdb.stadiony().add(stadion);
        workdb.kluby().add(klub);

        workdb.saveChanges();

        for(Klub k : workdb.kluby().getAll()) {
            System.out.println(
                k.getId()+ "\t" +
                k.getNazwa()+ "\t" +
                workdb.stadiony().getOne(k.getStadion()).getMiasto()+ "\t" +
                workdb.trenerzy().getOne(k.getTrener()).getImie() + "\t" +
                workdb.trenerzy().getOne(k.getTrener()).getNazwisko()
            );
        }


    }
}
