package view;

import controller.*;
import model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class PlayWindow extends JDialog{
    private JPanel informationPanel = new JPanel(new FlowLayout());
    private JPanel gamePanel = new GamePanel();
    private Performer performer;
    private static int level;
    private JLabel pushedButtons;
    private JPanel spacePanel;

    public PlayWindow(){
        super();
        setTitle("Escape the Bus");
        setMinimumSize(new Dimension(1000, 700));
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createInformationPanel();
        add(informationPanel, BorderLayout.NORTH);
        gamePanel.setBorder(new LineBorder(new Color(102, 102, 102), 7));
        add(gamePanel, BorderLayout.CENTER);
        setModal(true);
        setVisible(true);
    }

    private void createInformationPanel(){
        informationPanel.setPreferredSize(new Dimension(1000, 45));
        informationPanel.setBorder(new LineBorder(Color.DARK_GRAY, 4));
        JLabel levelInfo = new JLabel("Level 1");
        informationPanel.add(levelInfo);
        spacePanel = new JPanel();
        spacePanel.setPreferredSize(new Dimension(880, 25));
        informationPanel.add(spacePanel);
        pushedButtons = new JLabel("0/" + performer.getButtonsAmount());
        informationPanel.add(pushedButtons);
    }

    private class GamePanel extends JPanel{
        private Timer gameTimer;
        private Thread inspectorMoving;
        private Thread luckyTicketAppearance;
        private Thread impregnablePlayerFalse;
        private ImpregnablePlayerFalse impregnablePlayer;
        private Thread buttonsCreator;
        private int influenceTime = 0;

        {
            level = 1;
            performer = new Performer(level);
            gameTimer = new Timer();
            inspectorMoving = new Thread(new InspectorMoving(performer));
            luckyTicketAppearance = new Thread(new LuckyTicketAppearance(performer));
            impregnablePlayer = new ImpregnablePlayerFalse(performer);
            impregnablePlayerFalse = new Thread(impregnablePlayer);
            buttonsCreator = new Thread(new ButtonsCreator(performer));
        }

        GamePanel(){
            super();
            inspectorMoving.start();
            luckyTicketAppearance.start();
            buttonsCreator.start();
            gameTimer.schedule(new TimerTask(){
                @Override
                public void run(){
                    revalidate();
                    repaint();

                    if(performer.isPlayerImpregnable()) {
                        impregnablePlayerFalse.interrupt();
                        performer.getBus().getPlayer().equipShield(true);
                    }

                    if(performer.ifLuckyTicketPicked()){
                        performer.setImpregnable(true);
                        influenceTime = performer.getBus().getLuckyTicket().getInfluenceTime();
                        performer.getBus().removeLuckyTicket();
                        impregnablePlayer.setInfluenceTime(influenceTime);
                        if (impregnablePlayerFalse.isInterrupted()) impregnablePlayerFalse.start();
                    }

                    if(performer.ifButtonPushed()){
                        performer.incPushedButtons();
                        performer.getBus().getButton().pushButton();
                        pushedButtons.setText(String.valueOf(performer.getPushedButtons()) + "/" + performer.getButtonsAmount());
                        performer.getBus().removeButton();
                    }

                    if(!performer.isPlayerImpregnable()){
                        performer.getBus().getPlayer().equipShield(false);
                        if(performer.ifCollision()) {
                            JOptionPane.showMessageDialog(null, "You lose!");
                            dispose();
                            gameTimer.cancel();
                        }
                    }

                    if(performer.ifEnoughButtons()){
                        spacePanel.removeAll();
                        spacePanel.add(new JLabel("Escape!!!"));
                    }

                    if(performer.ifExit()){
                        if(performer.ifEnoughButtons()) {
                            JOptionPane.showMessageDialog(null, "You win!");
                            dispose();
                            gameTimer.cancel();
                        }
                        else{
                            if (spacePanel.getUI() != null) spacePanel.removeAll();
                            spacePanel.add(new JLabel("Push more buttons to escape!"));
                        }
                    }
                }
            }, 0, 20);
            setPreferredSize(new Dimension(1000, 600));
            setBackground(Color.GRAY);
            setFocusable(true);
            addKeyListener(new GameKeyListener());
        }

        private class GameKeyListener extends KeyAdapter{
            @Override
            public void keyPressed(KeyEvent e){
                super.keyPressed(e);
                performer.keyPressed(e);
            }
        }

        public void paint(Graphics g){
            super.paint(g);
            g.drawImage(performer.getPlayerImg(), performer.getPlayerX(), performer.getPlayerY(), this);
            for(Inspector inspector : performer.getBus().getInspectors())
                g.drawImage(inspector.getImg(), inspector.getX(), inspector.getY(), this);
            for(Seat seat : performer.getBus().getSeats())
                g.drawImage(seat.getImg(), seat.getX(), seat.getY(), this);
            for(Door door : performer.getBus().getDoors())
                g.drawImage(door.getImg(), door.getX(), door.getY(), this);
            if(performer.getBus().getLuckyTicket() != null)
                g.drawImage(performer.getBus().getLuckyTicket().getImg(),
                            performer.getBus().getLuckyTicket().getX(),
                            performer.getBus().getLuckyTicket().getY(), this);
            if(performer.getBus().getButton() != null)
                g.drawImage(performer.getBus().getButton().getImg(),
                        performer.getBus().getButton().getX(),
                        performer.getBus().getButton().getY(), this);
        }
    }
}
