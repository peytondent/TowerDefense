import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.JOptionPane;

/**
 * Operation class for Tower Defense game.
 *
 * @author Peyton Dent
 */
public class TowerDefenseGame {
    private static ControlPanel control;
    private static Display display;
    private static int money, grade, score;
    private static Random rand;
    private static Timer timer;
    private static ArrayList<Enemy> enemies;
    private static ArrayList<Tower> towers;
    private static int selection, wavenumber, yellowwaveremaining,
    orangewaveremaining, blackwaveremaining, toNextEnemy, cost,
    mousex, mousey;
    private static Mouse mouse;
    private static String upgradeText = "Upgrade Cost: ---";
    private static int ftowerx, ftowery, ftowerr;
    private static Color ftowerc;
    private static ArrayList<Explosion> explosions;
    /**
     * Main method for Tower Defense game
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        money = 120;
        score = 0;
        grade = 10;
        toNextEnemy = 0;
        cost = 30;
        rand = new Random();
        JFrame frame = new JFrame("Tower Defense!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        control = new ControlPanel();
        display = new Display(control);
        frame.add(control, BorderLayout.WEST);
        frame.add(display);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        enemies = new ArrayList<Enemy>(10);
        towers = new ArrayList<Tower>();
        explosions = new ArrayList<Explosion>();
        mouse = new Mouse();
        display.addMouseListener(mouse);
        display.addMouseMotionListener(mouse);
        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timerstep();
                checkRemoval();
                display.repaint();
            }
        });
        timer.start();
    }
    private static class ControlPanel extends JPanel {
        private JLabel title, selectionL, scoreL, moneyL, gradeL;
        private JButton sendwave, upgrade, placebasic, placeboomer,
            placemissile, placeminigun, pause;
        private BufferedImage logo;
        public ControlPanel() {
            setPreferredSize(new Dimension(155, 630));
            setLayout(new GridLayout(11, 1));
            try {
                logo = ImageIO.read(new File("Logo.png"));
            } catch (IOException io) {
                logo = null;
            }
            title = new JLabel(new ImageIcon(logo));
            add(title);
            placebasic = new JButton("Place Rifle Tower");
            placebasic.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selection = 0;
                }
            });
            add(placebasic);
            placeboomer = new JButton("Place Bomb Tower");
            placeboomer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selection = 2;
                }
            });
            add(placeboomer);
            placemissile = new JButton("Place Missile Tower");
            placemissile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selection = 3;
                }
            });
            add(placemissile);
            placeminigun = new JButton("Place Minigun Tower");
            placeminigun.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selection = 1;
                }
            });
            add(placeminigun);
            upgrade = new JButton("Upgrade a Tower");
            upgrade.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selection = 4;
                }
            });
            add(upgrade);
            pause = new JButton("Pause");
            gradeL = new JLabel(("Grade: " + grade));
            add(gradeL);
            moneyL = new JLabel(("Money: $" + money));
            add(moneyL);
            scoreL = new JLabel(("Score: " + score));
            add(scoreL);
            selectionL = new JLabel("Rifle Tower: $30");
            add(selectionL);
            sendwave = new JButton("Send Next Wave");
            sendwave.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (enemies.isEmpty()) {
                        sendNextWave();
                    }
                }
            });
            add(sendwave);
        }
        public void updateLabels() {
            gradeL.setText("Health: " + grade);
            moneyL.setText("Money: $" + money);
            scoreL.setText("Score: " + score);
            if (selection == 0) {
                selectionL.setText("Rifle Tower: $30");
            } else if (selection == 1) {
                selectionL.setText("Minigun Tower: $30");
            } else if (selection == 2) {
                selectionL.setText("Bomb Tower: $40");
            } else if (selection == 3) {
                selectionL.setText("Missile Tower: $40");
            } else {
                selectionL.setText(upgradeText);
            }
        }
    }
    private static class Display extends JPanel {
        public static final int WIDTH = 600, HEIGHT = 600;
        private ControlPanel cPanel;
        public Display(ControlPanel c) {
            cPanel = c;
            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(new Color(0, 127, 0));
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(255, 255, 255, 127));
            int x = 20;
            while (x < 620) {
                g.drawLine(x, 0, x, 640);
                g.drawLine(0, x, 640, x);
                x = x + 20;
            }
            g.setColor(new Color(127, 127, 127));
            g.fillRect(0, 100, 540, 20);
            g.fillRect(520, 120, 20, 200);
            g.fillRect(40, 300, 500, 20);
            g.fillRect(40, 300, 20, 200);
            g.fillRect(40, 500, 580, 20);
            drawAll(g);
        }
        public static void drawAll(Graphics g) {
            for (Enemy enemy : enemies) {
                enemy.draw(g);
            }
            for (Tower tower : towers) {
                tower.draw(g);
                if (tower.comparePoint(mousex, mousey)) {
                    tower.drawRange(g);
                }
            }
            g.setColor(ftowerc);
            g.drawOval(ftowerx, ftowery, ftowerr, ftowerr);
            for (Explosion explosion : explosions) {
                explosion.draw(g);
            }
        }
    }
    /**
     * Updates all labels, towers, and enemies each time the timer is run
     */
    public static void timerstep() {
        for (Enemy enemy : enemies) {
            enemy.step();
        }
        control.updateLabels();
        if ((blackwaveremaining > 0) && (toNextEnemy == 30)) {
            enemies.add(new BlackEnemy());
            blackwaveremaining--;
            toNextEnemy = 0;
        } else if ((orangewaveremaining > 0) && (toNextEnemy == 30)) {
            enemies.add(new OrangeEnemy());
            orangewaveremaining--;
            toNextEnemy = 0;
        } else if ((yellowwaveremaining > 0) && (toNextEnemy == 30)) {
            enemies.add(new YellowEnemy());
            yellowwaveremaining--;
            toNextEnemy = 0;
        } else if (toNextEnemy < 30) {
            toNextEnemy++;
        }
        for (Tower tower : towers) {
            tower.incrementTimer();
            for (Enemy enemy : enemies) {
                if (tower.canAttack(enemy)) {
                    if (!(tower instanceof BombTower
                    || tower instanceof MissileTower)) {
                        tower.attack(enemy);
                    } else if (tower instanceof BombTower) {
                        explosions.add(((BombTower) tower).explode(enemy));
                    } else if (tower instanceof MissileTower) {
                        explosions.add(((MissileTower) tower).explode(enemy));
                    }
                }
            }
        }
        if (!(explosions.isEmpty())) {
            Iterator<Explosion> i = explosions.iterator();
            while (i.hasNext()) {
                Explosion explosion = i.next();
                for (Enemy enemy : enemies) {
                    if (explosion.isInRange(enemy)) {
                        explosion.damageEnemy(enemy);
                    }
                }
                explosion.setDamage(0);
                if (!explosion.checkLinger()) {
                    i.remove();
                }
            }
        }
    }
    /**
     * Removes all enemies that have no health or are at the end of the path,
     * adding score and removing health as necessary.
     */
    public static void checkRemoval() {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy enemy = i.next();
            if (!enemy.checkHealth()) {
                money = money + enemy.getValue() * 2;
                score = score + enemy.getValue();
                i.remove();
            } else if (enemy.checkWin()) {
                grade--;
                i.remove();
                if (grade <= 0) {
                    loseGame();
                }
            }
        }
    }
    /**
     * Sends the next wave of enemies.  Will not send the next wave while
     * enemies are on the screen.
     */
    public static void sendNextWave() {
        wavenumber++;
        int waveCalc = wavenumber * 2;
        if (waveCalc <= 10) {
            yellowwaveremaining = 2 * wavenumber;
        } else if (waveCalc <= 20) {
            orangewaveremaining = (waveCalc - 10);
            yellowwaveremaining = 10 - orangewaveremaining;
        } else if (waveCalc <= 30) {
            blackwaveremaining = (waveCalc - 20);
            orangewaveremaining = 10 - blackwaveremaining;
        } else {
            blackwaveremaining = 10;
        }
    }
    /**
     * Stops the timer when the game has been lost
     */
    public static void loseGame() {
        timer.stop();
        money = 0;
        JOptionPane.showMessageDialog(display, "GAME OVER");
    }
    private static class Mouse extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            Tower upgrade = null;
            Point p = e.getPoint();
            int cx = p.x - p.x % 20;
            int cy = p.y - p.y % 20;
            boolean exists = false;
            for (Tower tower : towers) {
                if (!exists) {
                    exists = tower.comparePoint(cx, cy);
                    if (tower.comparePoint(cx, cy)) {
                        upgrade = tower;
                    }
                }
            }
            if (!exists) {
                if ((selection == 0) && (money >= 30)) {
                    towers.add(new RifleTower(cx, cy));
                    money = money - 30;
                } else if ((selection == 1) && (money >= 30)) {
                    towers.add(new MinigunTower(cx, cy));
                    money = money - 30;
                } else if ((selection == 2) && (money >= 40)) {
                    towers.add(new BombTower(cx, cy));
                    money = money - 40;
                } else if ((selection == 3) && (money >= 40)) {
                    towers.add(new MissileTower(cx, cy));
                    money = money - 40;
                }
            }
            if ((exists && (selection == 4))
            && ((money >= upgrade.getUpgradeCost()) && (upgrade != null))) {
                money = money - upgrade.getUpgradeCost();
                upgrade.upgrade();
            }
            control.updateLabels();
        }
        public void mouseMoved(MouseEvent e) {
            Point p = e.getPoint();
            mousex = p.x - p.x % 20;
            mousey = p.y - p.y % 20;
            Tower upgrade = null;
            boolean exists = false;
            for (Tower tower : towers) {
                if (!exists) {
                    exists = tower.comparePoint(mousex, mousey);
                    if (tower.comparePoint(mousex, mousey)) {
                        upgrade = tower;
                    }
                }
            }
            if (exists) {
                upgradeText = "Upgrade Cost: " + upgrade.getUpgradeCost();
            } else {
                upgradeText = "Upgrade Cost: ---";
            }
            control.updateLabels();
            if (selection == 0) {
                ftowerx = mousex - 190;
                ftowery = mousey - 190;
                ftowerc = new Color(255, 0, 0);
                ftowerr = 400;
            } else if (selection == 1) {
                ftowerx = mousex - 65;
                ftowery = mousey - 65;
                ftowerc = new Color(0, 0, 255);
                ftowerr = 150;
            } else if (selection == 2) {
                ftowerx = mousex - 40;
                ftowery = mousey - 40;
                ftowerc = new Color(255, 255, 255);
                ftowerr = 100;
            } else if (selection == 3) {
                ftowerx = mousex - 140;
                ftowery = mousey - 140;
                ftowerc = new Color(139, 0, 204);
                ftowerr = 300;
            } else {
                ftowerr = 0;
                ftowerc = new Color(0, 0, 0, 0);
            }
        }
    }
}
