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
    private final JLabel scoreView;
    private final JButton pauseB;
    private final JPanel pauseP;
    private final JButton continueB;
    private final JButton quitB;
    private GridBagConstraints constraints = new GridBagConstraints();

    //TODO alle knöpfe und anzeigen links oder rechts

    public GamePanel(GameModel model, ViewListener viewListener) {//
        this.viewListener = viewListener;
        this.model = model;

        ////model.setGamePanel(this);
        model.setGameModelListener(this);

        setBackground(Color.PINK);

        pauseB = new JButton("I I");
        pauseB.setHorizontalAlignment(JLabel.RIGHT);
        add(pauseB, BorderLayout.NORTH);


        pauseP = new JPanel();
        pauseP.setBackground(new Color(50, 50, 50, 150));
        pauseP.setVisible(false);
        add(pauseP, BorderLayout.PAGE_START);

        pauseP.setLayout(new GridBagLayout());
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        continueB = new JButton("continue");
        continueB.setBackground(new Color(0, 50, 0));
        continueB.setBorderPainted(false);
        continueB.setVisible(false);
        continueB.setFont(new Font("sans", Font.PLAIN, 70));

        quitB = new JButton("quit");
        quitB.setBackground(new Color(50, 0, 0));
        quitB.setBorderPainted(false);
        quitB.setVisible(false);
        quitB.setFont(new Font("sans", Font.PLAIN, 70));

        addGB(continueB, 0, 0);
        addGB(quitB, 0, 1);


        pauseB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.pauseGame();
                pauseP.setVisible(true);
                continueB.setVisible(true);
                pauseB.setVisible(false);
            }
        });

        continueB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseP.setVisible(false);
                continueB.setVisible(false);
                pauseB.setVisible(true);
                viewListener.continueGame();
            }
        });

        JButton swb = new JButton("hi");
        this.add(swb);

        swb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.END_PANEL, new Bundle().put(KEY_SCORE, model.getScore()));
            }
        });

        fpsCounter = new JLabel();
        fpsCounter.setText(String.valueOf(model.getFps()));
        add(fpsCounter);

        scoreView = new JLabel();
        scoreView.setText(String.valueOf(model.getScore()));
        add(scoreView);
    }

    private static final int halfScreen = Dimensions.getScreenResolution().getKey() / 2;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(model.getMapImage(), halfScreen, 0, this);
        g2d.drawImage(model.getPlayer().getImg(), model.getPlayer().getX() + halfScreen, model.getPlayer().getY(), this);

        for (Corona c : model.getCovList()) {
            g2d.drawImage(c.getImage(model.getAnimationState()), c.getX() + halfScreen, c.getY(), c.getWidth(), c.getHeight(), this);
        }

        for (Map.Entry<Coord, MapChunkValues> entry : model.getGameMap().entrySet()) {
            if (entry.getValue().isHasDot()) {
                g2d.fillOval(((entry.getKey().getX() * Dimensions.ABSTAND_X) + halfScreen + Dimensions.MAP_OFFSET_X), (entry.getKey().getY() * Dimensions.ABSTAND_Y) + Dimensions.MAP_OFFSET_Y, 10, 10);
            }
            if (entry.getValue().isHasToiletPaper()) {
                TPaper tp = model.getTPaper();
                g2d.drawImage(tp.getImage(), tp.getX() + halfScreen, tp.getY(), this);
            }
            if (entry.getValue().isHasVac()) {
                for (Vac v : model.getVacs()) {
                    g2d.drawImage(v.getImage(), v.getX() + halfScreen, v.getY(), this);
                }
            }
        }
    }

    @Override
    public void onFpsChanged(int fps) {
        fpsCounter.setText(String.valueOf(model.getFps()));
    }

    @Override
    public void onScoreChanged(int score) {
        scoreView.setText(String.valueOf(score));
    }

    @Override
    public void finishGame(int score) {
        viewListener.onNavigate(NavigationPanels.END_PANEL, new Bundle().put(KEY_SCORE, score));
    }

    @Override
    public void update() {
        paintImmediately(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
    }
}