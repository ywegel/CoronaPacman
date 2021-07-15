package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.GameModelListener;
import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.entities.Corona;
import de.dickeLunten.coronaPacman.models.entities.TPaper;
import de.dickeLunten.coronaPacman.models.entities.Vac;
import de.dickeLunten.coronaPacman.models.panel.GameModel;
import util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class GamePanel extends PanelView implements GameModelListener {
    public static final String KEY_SCORE = "game_panel";

    private final GameModel model;
    private final ViewListener viewListener;

    private final JLabel fpsCounter;
    private final JLabel scoreLivesL;
    private final JButton pauseB;
    private final JPanel pauseP;
    private final JLabel pauseL;
    private final JButton continueB;
    private final JButton quitB;
    private final JButton restartB;
    private GridBagConstraints constraints = new GridBagConstraints();

    public GamePanel(GameModel model, ViewListener viewListener) {//
        this.viewListener = viewListener;
        this.model = model;
        model.setGameModelListener(this);
        setLayout(new BorderLayout());

        fpsCounter = new JLabel();
        fpsCounter.setText(String.valueOf(model.getFps()));
        fpsCounter.setBackground(Color.LIGHT_GRAY);
        add(fpsCounter, BorderLayout.NORTH);

        scoreLivesL = new JLabel();
        scoreLivesL.setText("<html>Score: " + String.valueOf(model.getScore()) + "<br />Lives: " + model.getPlayer().getLives());
        scoreLivesL.setFont(new Font("sans", Font.PLAIN, 40));
        add(scoreLivesL);

        model.setGameModelListener(this);

        setBackground(Color.LIGHT_GRAY);

        pauseB = new JButton(" I I ");
        pauseB.setFont(new Font("sans", Font.PLAIN, 70));
        pauseB.setBackground(new Color(50, 50, 50));
        pauseB.setForeground(Color.WHITE);
        pauseB.setBorderPainted(false);
        pauseB.setVisible(true);
        add(pauseB, BorderLayout.EAST);

        pauseP = new JPanel();
        pauseP.setPreferredSize(new Dimension(1, Dimensions.getScreenResolution().getKey()));
        pauseP.setBackground(new Color(50, 50, 50, 220));
        pauseP.setVisible(false);
        add(pauseP, BorderLayout.NORTH);

        pauseP.setLayout(new GridBagLayout());
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        continueB = new JButton("continue");
        continueB.setBackground(new Color(0, 50, 0));
        continueB.setForeground(Color.WHITE);
        continueB.setBorderPainted(false);
        continueB.setVisible(true);
        continueB.setFont(Data.setPacFont());

        restartB = new JButton("  restart  ");
        restartB.setBackground(new Color(0, 0, 50));
        restartB.setForeground(Color.WHITE);
        restartB.setBorderPainted(false);
        restartB.setVisible(true);
        restartB.setFont(Data.setPacFont());

        pauseL = new JLabel("game paused", SwingConstants.CENTER);
        pauseL.setBackground(new Color(0, 0, 0));
        pauseL.setForeground(Color.WHITE);
        pauseL.setFont(Data.setPacFont());

        quitB = new JButton("      quit      ");
        quitB.setBackground(new Color(50, 0, 0));
        quitB.setForeground(Color.WHITE);
        quitB.setBorderPainted(false);
        quitB.setFont(Data.setPacFont());

        constraints.gridwidth = 3;
        addGB(pauseL, 0, 0);
        constraints.gridwidth = 1;
        addGB(continueB, 0, 1);
        addGB(restartB, 1, 1);
        addGB(quitB, 2, 1);


        pauseB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseB.setVisible(false);
                viewListener.notifyPauseGame();
                pauseP.setVisible(true);
            }
        });

        continueB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseP.setVisible(false);
                viewListener.notifyContinueGame();
                pauseB.setVisible(true);
            }
        });

        restartB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.GAME_PANEL, Bundle.emptyBundle());
                viewListener.notifyGameStarted();
            }
        });

        quitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseP.setVisible(false);
                viewListener.onNavigate(NavigationPanels.START_PANEL, Bundle.emptyBundle());
                viewListener.notifyExitGame();
            }
        });
    }

    void addGB(Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        pauseP.add(component, constraints);
    }


    private static final int halfScreen = Dimensions.getScreenResolution().getKey() / 2;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Hintergrund
        g2d.drawImage(model.getMapImage(), halfScreen, 0, this);

        //Dots, Impfung, Toilettenpapier
        for (Map.Entry<Coord, MapChunkValues> entry : model.getGameMap().entrySet()) {
            if (entry.getValue().isHasDot()) {
                g2d.fillOval(((entry.getKey().getX() * Dimensions.PIXEL_PER_CHUNK_X) + halfScreen + Dimensions.MAP_OFFSET_X), (entry.getKey().getY() * Dimensions.PIXEL_PER_CHUNK_Y) + Dimensions.MAP_OFFSET_Y, 10, 10);
            }
            if (entry.getValue().isHasToiletPaper()) {
                TPaper tp = model.getTPaper();
                g2d.drawImage(tp.getImage(), tp.getX() + halfScreen + Dimensions.MAP_OFFSET_X, tp.getY() + Dimensions.MAP_OFFSET_Y, this);
            }
            if (entry.getValue().isHasVac()) {
                for (Vac v : model.getVacs()) {
                    g2d.drawImage(v.getImage(), v.getX() + halfScreen + Dimensions.MAP_OFFSET_X, v.getY() + Dimensions.MAP_OFFSET_Y, this);
                }
            }
        }

        //Spieler
        g2d.drawImage(model.getPlayer().getImg(model.getPlayerAnimationState()), model.getPlayer().getX() + halfScreen, model.getPlayer().getY(), this);

        //Corona
        for (Corona c : model.getCovList()) {
            g2d.drawImage(c.getImage(model.getCoronaAnimationState(), model.isCoronaEdible()), c.getX() + halfScreen, c.getY(), c.getWidth(), c.getHeight(), this);
        }
    }

    @Override
    public void onFpsChanged(int fps) {
        fpsCounter.setText(String.valueOf(model.getFps()));
    }

    @Override
    public void onScoreLivesChanged(int score, int lives) {
        scoreLivesL.setText("<html>Score: " + score + "<br /><br />Lives: " + lives + "</html>");
    }

    @Override
    public void finishGame(int score) {
        viewListener.notifyExitGame();
        viewListener.onNavigate(NavigationPanels.END_PANEL, new Bundle().put(KEY_SCORE, score));
    }

    @Override
    public void update() {
        paintImmediately(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    }
}