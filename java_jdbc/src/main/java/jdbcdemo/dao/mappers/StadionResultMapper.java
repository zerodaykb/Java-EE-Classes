package jdbcdemo.dao.mappers;

import jdbcdemo.domain.Stadion;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StadionResultMapper implements ResultSetMapper<Stadion> {

    public Stadion map(ResultSet rs) throws SQLException {
        Stadion p = new Stadion();
        p.setId(rs.getInt("id"));
        p.setMiasto(rs.getString("miasto"));
        p.setNazwa(rs.getString("nazwa"));
        return p;
    }
}
