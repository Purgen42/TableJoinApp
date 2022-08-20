import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    private Connection conn;
    private Statement st;

    public Dao() throws SQLException, ClassNotFoundException {
        init();
    }

    public String[][] getData() throws SQLException {
        ResultSet rs = st.executeQuery("SELECT FT.NAME, S.NAME FROM STICKS S INNER JOIN FIR_TREES FT ON S.FIR_TREE_ID = FT.ID;");
        List<String[]> resultList = new ArrayList<>();
        while (rs.next()) {
            String []row = new String[2];
            row[0] = rs.getString(1);
            row[1] = rs.getString(2);
            resultList.add(row);
        }
        return resultList.toArray(new String[1][1]);
    }

    public void exit() throws SQLException {
        conn.close();
    }

    private void init() throws ClassNotFoundException, SQLException {
        Class.forName ("org.h2.Driver");
        conn = DriverManager.getConnection ("jdbc:h2:mem:default", "sa","");
        st = conn.createStatement();
        ScriptRunner sr = new ScriptRunner(conn);
        InputStream initStream = Dao.class.getResourceAsStream("sql/init.sql");
        sr.runScript(new InputStreamReader(initStream));
    }
}
