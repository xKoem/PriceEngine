package pl.xkoem.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryTranslator {

    public static List<String> translateResultSetToListOfLinks(ResultSet resultSet) {
        List<String> listOfLinks = new ArrayList<>();
        try {
            while (resultSet.next()) {
                listOfLinks.add(resultSet.getString("link"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfLinks;
    }
}
