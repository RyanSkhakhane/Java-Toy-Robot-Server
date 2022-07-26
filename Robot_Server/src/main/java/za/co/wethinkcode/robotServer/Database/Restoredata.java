package za.co.wethinkcode.robotServer.Database;

import java.sql.SQLException;
import java.util.List;

public class Restoredata {
    public static final String SEPARATOR = "\t";

//    public Restoredata(String[] args) {
//
//    }
//
//    public static void main( String[] args ) {
//        final Restoredata app = new Restoredata( args );}

    public void readData( final WorldDAI dai )
            throws SQLException
    {
       // final int worldCount = dai.getNumberOfWorlds();
        //display( Integer.toString( worldCount ) + " PRODUCTS." );

        final List<WorldDO> allWorlds = dai.getAllWorlds();

        display( "\nALL PRODUCTS:" );
        allWorlds.forEach( p -> displayWorld( p ) );




    }
    private void displayWorld( WorldDO p ){
        if( p == null ){
            display( SEPARATOR + "errrrr..." );
        }else{
            display( SEPARATOR + p.getName() + SEPARATOR + p.getId() + SEPARATOR + p.getSize() );
        }
    }

    private void display( String s ){
        System.out.println( s );
    }
}
