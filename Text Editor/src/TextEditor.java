import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor extends JFrame {
    JTextArea textArea;
    JScrollPane scrollPane;
    JSpinner fontSizeSpinner;
    JLabel fontLabel;
    JButton fontColorButton;
    JComboBox fontBox;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    TextEditor(){
        //------------------ How the frame looks ------------------//
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Text Editor");
        this.setSize(500,500);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

        //------------------ Adjusting the textArea ------------------//
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN,25));

        //------------------ Adding the scrollbar ------------------//
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(450,350));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        //------------------ Adding the font label------------------//
        fontLabel = new JLabel("Font: ");

        //------------------ Adjusting the font size chooser ------------------//
        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50,25));
        fontSizeSpinner.setValue(25);
        fontSizeSpinner.addChangeListener(e ->
                        textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int)fontSizeSpinner.getValue()))
        );

        //------------------ Adjusting the font color chooser ------------------//
        fontColorButton = new JButton("Color");
        fontColorButton.addActionListener(e -> {
                JColorChooser colorChooser = new JColorChooser();
                Color color = colorChooser.showDialog(null, "Choose a color", Color.black);
                textArea.setForeground(color);}
        );

        //------------------ Adjusting the font family chooser ------------------//

        //Getting all font families in java.
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontBox = new JComboBox(fonts);
        fontBox.setSelectedItem("Arial");
        fontBox.addActionListener(e -> textArea.setFont(
                new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()))
        );

        //------------------ Adjusting the Menu Bar ------------------//
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            //Adding a filter, so it shows only files with ".txt".
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files","txt");
            fileChooser.setFileFilter(filter);

            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;

                try {
                    fileIn = new Scanner(file);
                    if (file.isFile()){
                        while (fileIn.hasNextLine()){
                            String line = fileIn.nextLine() + "\n";
                            textArea.append(line);
                        }
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } finally {
                    fileIn.close();
                }
            }
        });
        saveItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showSaveDialog(null);
            if (response == JFileChooser.APPROVE_OPTION){
                File file;
                PrintWriter fileOut = null;
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    fileOut = new PrintWriter(file);
                    fileOut.println(textArea.getText());
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } finally {
                    fileOut.close();
                }
            }
        });
        exitItem.addActionListener(e -> {
            System.exit(0);
        });

        menuBar.add(fileMenu);

        //------------------ Adding everything to the frame ------------------//
        this.setJMenuBar(menuBar);
        this.add(fontLabel);
        this.add(fontSizeSpinner);
        this.add(fontColorButton);
        this.add(fontBox);
        this.add(scrollPane);
        this.setVisible(true);
    }

}
