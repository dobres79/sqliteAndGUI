import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Main {
        public static void main(String[] args) throws SQLException {
                //sqlite
                DbHandler dbHandler = DbHandler.getInstance();
                List<Map<String, Object>> alldata = dbHandler.getAllData();
                System.out.println(alldata);

                //Dialog
                final JFileChooser fc = new JFileChooser();





                //TODO:
                JFrame myframe = new JFrame("Ебать-колотить");
                myframe.setSize(600,400);
                myframe.setResizable(false);

                ImageIcon img = new ImageIcon("c:\\123\\image.png");
                myframe.setIconImage(img.getImage());

                //меню
                Font font = new Font("Verdana", Font.PLAIN, 11);
                JMenuBar menuBar = new JMenuBar();

                JMenu fileMenu = new JMenu("File");
                fileMenu.setFont(font);

                JMenu newMenu = new JMenu("New");
                newMenu.setFont(font);
                fileMenu.add(newMenu);

                JMenuItem txtFileItem = new JMenuItem("Text file");
                txtFileItem.setFont(font);
                newMenu.add(txtFileItem);

                JMenuItem imgFileItem = new JMenuItem("Image file");
                imgFileItem.setFont(font);
                newMenu.add(imgFileItem);

                JMenuItem folderItem = new JMenuItem("Folder");
                folderItem.setFont(font);
                newMenu.add(folderItem);

                JMenuItem openItem = new JMenuItem("Open");
                openItem.setFont(font);
                fileMenu.add(openItem);

                JMenuItem closeItem = new JMenuItem("Close");
                closeItem.setFont(font);
                fileMenu.add(closeItem);

                JMenuItem closeAllItem = new JMenuItem("Close all");
                closeAllItem.setFont(font);
                fileMenu.add(closeAllItem);

                fileMenu.addSeparator();

                JMenuItem exitItem = new JMenuItem("Exit");
                exitItem.setFont(font);
                fileMenu.add(exitItem);

                exitItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                System.exit(0);
                        }
                });

                openItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                int returnVal = fc.showOpenDialog(null);
                                if (returnVal == JFileChooser.APPROVE_OPTION) {
                                        File file = fc.getSelectedFile();
                                        //This is where a real application would open the file.
                                        System.out.println(file.getName());
                                } else {
                                        System.out.println("Open command cancelled by user." );
                                }

                        }
                });

                menuBar.add(fileMenu);

                myframe.setJMenuBar(menuBar);

                myframe.add(new TestForm().getMyPanel());
                myframe.setVisible(true);
                myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
}
