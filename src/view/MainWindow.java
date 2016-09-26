package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame
{
    private JButton startButton = new JButton("START");
    private JButton rulesButton = new JButton("RULES");
    private JButton exitButton = new JButton("EXIT");

    public MainWindow()
    {
        super("Escape the Bus");
        setMinimumSize(new Dimension(550, 600));
        setMaximumSize(new Dimension(550, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponents();
    }

    private void addComponents()
    {
        Box box = Box.createVerticalBox();
        JLabel gameNameLabel = new JLabel("Escape the Bus");
        gameNameLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        box.add(Box.createVerticalStrut(110));
        box.add(gameNameLabel);
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
        setContentPane(box);
    }

    private void addListeners()
    {
        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PlayWindow playWindow = new PlayWindow();
            }
        });

        rulesButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                RulesWindow rulesWindow = new RulesWindow();
            }
        });

        exitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args)
    {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setVisible(true);
    }
}
