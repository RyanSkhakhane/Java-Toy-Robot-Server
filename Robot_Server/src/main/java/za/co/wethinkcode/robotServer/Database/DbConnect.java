package za.co.wethinkcode.robotServer.Database;

import java.sql.*;

/**
 * DbTest is a small command-line tool used to check that we can connect to a SQLite database.
 *
 * By default (without any command-line arguments) it attempts to create a SQLite table in an in-memory database.
 * If it succeeds, we assume that all the working parts we need to use SQLite databases are in place and working.
 *
 * The only command-line argument this app understands is
 *  `-f <filename>`
 *  which tells that application to create the test table in a real (disk-resident) database named by the given
 *  filename. Note that the application _does not delete_ the named file, but leaves it in the filesystem
 *  for later examination if desired.
 */
public class DbConnect
{
    public static final String IN_MEMORY_DB_URL = "jdbc:sqlite:world.db";

    public static final String DISK_DB_URL = "jdbc:sqlite:";



    private String dbUrl = null;
    public Connection dbConnection=null;
    public DbConnect( String[] args ) {
        processCmdLineArgs( args );
       try {
            dbConnection = DriverManager.getConnection( dbUrl );
            System.out.println( "Connected to database " );
            runTest( dbConnection );
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

    private void runTest( Connection connection ) {
        try( final Statement stmt = connection.createStatement() ){
            stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS world_roboot(world_name TEXT UNIQUE, size INTEGER,obstacles_x INTEGER,obstacles_y INTEGER)" );
            //PreparedStatement ps=connection.prepareStatement("CREATE TABLE world_roboot( size,positions,obstacles,bottomless pits,mines)");
            System.out.println( "Success creating test table!" );
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

    private void processCmdLineArgs( String[] args ){
        if( args.length == 2 && args[ 0 ].equals( "-f" )){
            dbUrl = DISK_DB_URL + args[ 1 ];
        }else if( args.length == 0 ){
            dbUrl = IN_MEMORY_DB_URL;
        }else{
            throw new RuntimeException( "Illegal command-line arguments." );
        }
    }
}