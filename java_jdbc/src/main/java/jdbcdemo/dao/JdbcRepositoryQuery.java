package jdbcdemo.dao;

import java.sql.Connection;
import java.sql.SQLException;

import jdbcdemo.dao.mappers.KlubResultMapper;
import jdbcdemo.dao.mappers.StadionResultMapper;
import jdbcdemo.dao.mappers.TrenerResultMapper;
import jdbcdemo.dao.repositories.*;
import jdbcdemo.dao.uow.UnitOfWork;
import jdbcdemo.domain.Klub;
import jdbcdemo.domain.Stadion;
import jdbcdemo.domain.Trener;

public class JdbcRepositoryQuery implements RepositoryQuery {
    
	Connection connection;
	UnitOfWork uow;
	
	public JdbcRepositoryQuery(Connection connection, UnitOfWork uow) {
		this.connection = connection;
		this.uow = uow;
	}

	public Repository<Trener> trenerzy() {
		try {
			return new RepositoryTrener(connection, new TrenerResultMapper(), uow);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Repository<Stadion> stadiony() {
		try {
			return new RepositoryStadion(connection, new StadionResultMapper(), uow);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Repository<Klub> kluby() {
		try {
			return new RepositoryKlub(connection, new KlubResultMapper(), uow);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}


	public void saveChanges() {
		uow.saveChanges();
	}
}
