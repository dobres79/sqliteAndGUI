
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main {
        public static void main(String[] args) throws SQLException {
                //sqlite
                DbHandler dbHandler = DbHandler.getInstance();
                List<Map<String, Object>> alldata = dbHandler.getAllData();
                System.out.println(alldata);
                GuiHandler myGui = new GuiHandler();
                myGui.init();
        }
}
