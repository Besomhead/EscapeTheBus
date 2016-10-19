package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RulesWindow extends JFrame{

    private JTextArea rulesTextArea;
    private static final String FILE_PATH = "rules.txt";

    public RulesWindow(){
        super("Rules");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(550, 150));
        rulesTextArea = new JTextArea();
        readRules();
        add(rulesTextArea);
        setVisible(true);
    }

    private void readRules(){
        char[] rules = new char[1];
        int readStat = 0;
        File rulesFile = new File(FILE_PATH);
        try (FileReader rulesReader = new FileReader(rulesFile)) {
            rules = new char[(int)rulesFile.length()];
            readStat = rulesReader.read(rules);
            rulesReader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        rulesTextArea.append(String.valueOf(rules, 0, readStat));
    }
}
