import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class TableScreen {
    private static Dao dao;
    private JTable dbTable;
    private JPanel mainPanel;
    private static TableScreen screen;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        JFrame frame = new JFrame("Таблица");
        screen = new TableScreen();
        frame.setContentPane(screen.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        dao = new Dao();

        screen.updateTable();
    }

    private void updateTable() {
        String []columnsHeader = new String[] {"Ёлки", "Палки"};
        String [][]content;
        try {
            content = dao.getData();
            DefaultTableModel tableModel = new DefaultTableModel(content, columnsHeader);
            screen.dbTable.setModel(tableModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
