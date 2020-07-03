package jdbcdemo.dao.repositories;

import jdbcdemo.domain.Klub;
import jdbcdemo.domain.Stadion;
import jdbcdemo.domain.Trener;

public interface RepositoryQuery {
    Repository<Trener> trenerzy();
    Repository<Stadion> stadiony();
    Repository<Klub> kluby();
    void saveChanges();
}
