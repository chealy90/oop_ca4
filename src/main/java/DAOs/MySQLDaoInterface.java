package DAOs;
import java.util.List;

import java.util.List;

public interface MySQLDaoInterface {
    public List findAll();

    public Object findByID(int id);

    public void createNew(Object newItem);

}
