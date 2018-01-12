package jdbcdemo.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcdemo.domain.Trener;

public class TrenerResultMapper implements ResultSetMapper<Trener>{

	public Trener map(ResultSet rs) throws SQLException {
		Trener p = new Trener();
		p.setId(rs.getInt("id"));
		p.setImie(rs.getString("imie"));
		p.setNazwisko(rs.getString("nazwisko"));
		return p;
	}

}
