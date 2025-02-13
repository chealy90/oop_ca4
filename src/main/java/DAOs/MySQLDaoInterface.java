package DAOs;
import java.util.List;

import java.util.List;

public interface MySQLDaoInterface {
    public List findAll();

    public int deleteByID(int id);

    public void createNew(Object newItem);

    public List findByMonth(int month);

}
