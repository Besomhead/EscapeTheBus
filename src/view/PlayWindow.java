package view;

import controller.InspectorMoving;
import controller.Performer;
import model.Inspector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayWindow extends JDialog
{
    private JPanel informationPanel = new JPanel(new FlowLayout());
    private JPanel gamePanel = new GamePanel();
    private Performer performer;

    public PlayWindow() throws IOException {
        super();
        setTitle("Escape the Bus");
        setMinimumSize(new Dimension(1000, 700));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createInformationPanel();
        add(informationPanel, BorderLayout.NORTH);
        gamePanel.setBorder(new LineBorder(new Color(15, 123, 90), 7));
        add(gamePanel, BorderLayout.CENTER);
        setModal(true);
        setVisible(true);
    }

    private void createInformationPanel()
    {
        informationPanel.setPreferredSize(new Dimension(1000, 35));
        informationPanel.setBorder(new LineBorder(Color.DARK_GRAY, 4));
        JLabel levelInfo = new JLabel("Level 1");
        informationPanel.add(levelInfo);
        JPanel spacePanel = new JPanel();
        spacePanel.setPreferredSize(new Dimension(880, 15));
        informationPanel.add(spacePanel);
        JLabel timeInfo = new JLabel("0:00");
        informationPanel.add(timeInfo);
    }

    private class GamePanel extends JPanel
    {
        private Timer gameTimer;
        private Thread inspectorMoving;

        {
            try {
                performer = new Performer();
            } catch (IOException e) {
                e.printStackTrace();
            }
            gameTimer = new Timer();
            inspectorMoving = new Thread(new InspectorMoving(performer));
        }

        GamePanel()
        {
            super();
            inspectorMoving.start();
            gameTimer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    revalidate();
                    repaint();
                    if(performer.ifCollision())
                    {
                        JOptionPane.showMessageDialog(null, "You lose!");
                        dispose();
                        gameTimer.cancel();
                    }
                }
            }, 0, 20);
            setPreferredSize(new Dimension(1000, 600));
            setBackground(new Color(55, 105, 15));
            setFocusable(true);
            addKeyListener(new GameKeyListener());
        }

        private class GameKeyListener extends KeyAdapter
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                super.keyPressed(e);
                performer.keyPressed(e);
            }
        }

        public void paint(Graphics g)
        {
            super.paint(g);
            g.drawImage(performer.getPlayerImg(), performer.getPlayerX(), performer.getPlayerY(), this);
            for(Inspector inspector : performer.getBus().getInspectors())
                g.drawImage(inspector.getImg(), inspector.getX(), inspector.getY(), this);
        }
    }
}
