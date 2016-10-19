package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainWindow extends JFrame{
    public static final String START_IMG_PATH = "img\\start.jpg";
    public static final String RULES_IMG_PATH = "img\\rules.jpg";
    public static final String EXIT_IMG_PATH = "img\\exit.jpg";

    private JButton startButton = new JButton(new ImageIcon(START_IMG_PATH));
    private JButton rulesButton = new JButton(new ImageIcon(RULES_IMG_PATH));
    private JButton exitButton = new JButton(new ImageIcon(EXIT_IMG_PATH));

    public MainWindow(){
        super("Escape the Bus");
        setMinimumSize(new Dimension(550, 600));
        setMaximumSize(new Dimension(550, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponents();
    }

    private void addComponents(){
        ContentPanel mainPanel = new ContentPanel();
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(160));
        box.add(Box.createVerticalStrut(100));
        startButton.setMaximumSize(new Dimension(150, 50));
        rulesButton.setMaximumSize(new Dimension(150, 50));
        exitButton.setMaximumSize(new Dimension(150, 50));
        addListeners();
        box.add(startButton);
        box.add(Box.createVerticalStrut(10));
        box.add(rulesButton);
        box.add(Box.createVerticalStrut(10));
        box.add(exitButton);
        mainPanel.add(box);
        setContentPane(mainPanel);
    }

    private class ContentPanel extends JPanel{

        public static final String BACKGROUND_IMG_PATH = "img\\background.jpg";

        public void paintComponent(Graphics g){
            Image background = new ImageIcon(BACKGROUND_IMG_PATH).getImage();
            g.drawImage(background, 0, 0, this);
        }
    }

    private void addListeners(){
        startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                PlayWindow playWindow = new PlayWindow();
            }
        });

        rulesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                RulesWindow rulesWindow = new RulesWindow();
            }
        });

        exitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dispose();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args){
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}
