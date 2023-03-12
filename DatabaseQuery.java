import java.sql.*;

public class DatabaseQuery {
    public static ResultSet main( String queryType, String data, String table ) throws SQLException {
        String dbUrl = "databaseURL";
        String dbUser = "databaseUser";
        String dbPass = "databasePass";
        Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        String queryBuilt = queryType + " " + data + " from " + " " + table;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(queryBuilt);
        return rs;
    }
}
