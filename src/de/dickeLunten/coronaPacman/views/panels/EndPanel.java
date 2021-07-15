package de.dickeLunten.coronaPacman.views.panels;

import de.dickeLunten.coronaPacman.ViewListener;
import de.dickeLunten.coronaPacman.controller.NavigationPanels;
import de.dickeLunten.coronaPacman.models.panel.EndModel;
import util.Bundle;
import util.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndPanel extends PanelView {

    /**
     *
     */

    private final EndModel model;
    private final JLabel scoreL;
    private final JButton replayB;
    private final JButton quitB;
    private final JButton exitB;
    private final GridBagConstraints constraints = new GridBagConstraints();


    ViewListener viewListener;

    /**
     * Erzeugt das EndPanel, in dem der Endscore angegeben wird
     *
     * @param model Das Model des EndPanels
     * @param viewListener Der Beobachter des EndPanels
     * @param bundle Objekt der Util-Klasse bundle, in der hier der score transportiert wird
     */

    public EndPanel(EndModel model, ViewListener viewListener, Bundle bundle) {
        this.model = model;
        this.model.setScore(bundle.get(GamePanel.KEY_SCORE));

        //Auslesen des HighScore Files
/*        File highScoreFile = Data.loadFileFromRes("HighScore.txt");
        System.out.println(highScoreFile.getAbsolutePath());
        highScoreFile.setWritable(true);
        try{
            BufferedReader in = new BufferedReader( new FileReader(highScoreFile));
            highscore = Integer.parseInt(in.readLine());
            System.out.println(highscore + "");
            if(score < highscore || highscore == 0){
                highscore = score;
                System.out.println("Neuer HighScore!!!");
                FileWriter fw = new FileWriter(Data.loadFileFromRes("HighScore.txt"));
                fw.write(String.valueOf(highscore));
                fw.close();
            }
            in.close();
        }
        catch (Exception e){
            System.out.println("Highscore konnte nicht ausgelesen werden!");
            e.printStackTrace();
        }
        highScoreFile.setReadOnly();*/


        this.viewListener = viewListener;

        System.out.println("THREAD COUNT 3: " + Thread.activeCount());

        this.setLayout(new GridBagLayout());
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        setBackground(Color.GRAY);

        scoreL = new JLabel("ScoreLabel", SwingConstants.CENTER);
        scoreL.setOpaque(true);
        scoreL.setBackground(Color.DARK_GRAY);
        if (model.getScore() == 0) {
            scoreL.setText("Du bist infiziert und hast verloren  >_<");
        } else {

            scoreL.setText("<html> Du hast gewonnen und " + model.getScore() + " Punkte erreicht </html>");
        }
        scoreL.setFont(new Font("sans", Font.PLAIN, 60));
        scoreL.setForeground(Color.WHITE);

        replayB = new JButton("replay");
        replayB.setFont(Data.setPacFont());
        replayB.setBackground(new Color(50, 80, 0));
        //replayb.setFont(new Font("sans", Font.PLAIN, 70));
        replayB.setForeground(Color.WHITE);
        replayB.setBorderPainted(false);


        quitB = new JButton("  quit  ");
        quitB.setFont(Data.setPacFont());
        quitB.setBackground(new Color(50, 0, 0));
        //quitb.setFont(new Font("sans", Font.PLAIN, 70));
        quitB.setForeground(Color.WHITE);
        quitB.setBorderPainted(false);


        exitB = new JButton("  exit  ");
        exitB.setFont(Data.setPacFont());
        exitB.setBackground(new Color(10, 10, 10));
        //exitb.setFont(new Font("sans", Font.PLAIN, 70));
        exitB.setForeground(Color.WHITE);
        exitB.setBorderPainted(false);


        constraints.gridwidth = 3; // span three columns
        this.addGB(scoreL, 0, 0);
        constraints.gridwidth = 1; // set it back
        this.addGB(replayB, 0, 1);
        this.addGB(quitB, 1, 1);
        this.addGB(exitB, 2, 1);


        replayB.addActionListener(new ActionListener() {
            /**
             * Wechselt zum GamePanel
             *
             * @param e  neues ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.GAME_PANEL, Bundle.emptyBundle());
                viewListener.notifyGameStarted();
            }
        });


        quitB.addActionListener(new ActionListener() {
            /**
             * Wechselt zum StartPanel
             *
             * @param e neues ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                viewListener.onNavigate(NavigationPanels.START_PANEL, Bundle.emptyBundle());
            }
        });

        exitB.addActionListener(new ActionListener() {
            /**
             * Beendet das Programm
             *
             * @param e neues ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    /**
     * Fügt
     *
     * @param component Button/Label, das zum GridBagLayout hinzugefügt werden soll
     * @param x x-Wert im GridBagLayout
     * @param y y-Wert im GridBagLayout
     */
    void addGB(Component component, int x, int y) {
        constraints.gridx = x;
        constraints.gridy = y;
        add(component, constraints);
    }
}