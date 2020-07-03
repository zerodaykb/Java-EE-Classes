package jdbcdemo.dao.mappers;

import jdbcdemo.domain.Klub;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KlubResultMapper implements ResultSetMapper<Klub> {

    public Klub map(ResultSet rs) throws SQLException {
        Klub p = new Klub();
        p.setId(rs.getInt("id"));
        p.setNazwa(rs.getString("nazwa"));
        p.setTrener(rs.getInt("trener"));
        p.setStadion(rs.getInt("stadion"));
        return p;
    }
}
