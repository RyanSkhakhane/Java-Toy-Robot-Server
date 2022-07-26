package za.co.wethinkcode.robotServer.Database;

import net.lemnik.eodsql.BaseQuery;
import net.lemnik.eodsql.Select;
import net.lemnik.eodsql.Update;

import java.util.List;

public interface WorldDAI extends BaseQuery {

    @Select(
            "SELECT w.world_name, w.size FROM world_roboot w"
    )
    List<WorldDO> getAllWorlds();




}
