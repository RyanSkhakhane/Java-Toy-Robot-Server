package za.co.wethinkcode.robotServer.Database;

import net.lemnik.eodsql.QueryTool;

import java.sql.Connection;
import java.sql.SQLException;

import static za.co.wethinkcode.robotServer.ServerCommunication.ClientHandler.world;

public class Restore {

    Restoredata r=new Restoredata();

    public void useTheDb( final Connection db )
            throws SQLException
    {
        final WorldDAI worldQuery = QueryTool.getQuery( db, WorldDAI.class );
        r.readData(worldQuery);

//        createData( worldQuery );
//        updateData(worldQuery );
//        deleteData( worldQuery );
    }
}
