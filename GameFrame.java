import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class GameFrame extends JPanel{
    static final int MAX_WIDTH = 900;
    static final int  MAX_HEIGHT= 600;
    final int redBat_HEIGHT = 150;
    final int redBat_WIDTH = 50;
    final int blueBat_HEIGHT = 150;
    final int blueBat_WIDTH = 50;
    final int ball_HEIGHT = 15;
    final int ball_WIDTH = 15;
    int stepCount = 10;
    boolean run = true;
    int redY = 0;
    int blueY = 0;
    int ballX = (MAX_WIDTH - ball_WIDTH) / 2;
    int ballY = (MAX_HEIGHT -ball_HEIGHT) / 2;
    boolean moveUp, moveLeft;
    static int redScore = 0;
    static int blueScore = 0;
    int redXScore, redYScore, blueXScore, blueYScore;
    JFrame frame;
    Action redUp;
    Action redDown;
    Action blueUp;
    Action blueDown;
    Clip cClip;
    Clip goClip;

    GameFrame() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File gO = new File("go.wav");
        AudioInputStream goAudio = AudioSystem.getAudioInputStream(gO);
        goClip = AudioSystem.getClip();
        goClip.open(goAudio);

        File c = new File("c.wav");
        AudioInputStream cAudio = AudioSystem.getAudioInputStream(c);
        cClip = AudioSystem.getClip();
        cClip.open(cAudio);

        frame = new JFrame("Ping Pong");
        frame.setSize(MAX_WIDTH, MAX_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        redUp = new redUpAction();
        redDown = new redDownAction();
        blueUp = new blueUpAction();
        blueDown = new blueDownAction();

        this.getInputMap().put(KeyStroke.getKeyStroke('w'), "redUp");
        this.getActionMap().put("redUp", redUp);

        this.getInputMap().put(KeyStroke.getKeyStroke('a'), "redDown");
        this.getActionMap().put("redDown", redDown);

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "blueUp");
        this.getActionMap().put("blueUp", blueUp);

        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "blueDown");
        this.getActionMap().put("blueDown", blueDown);

        this.setBackground(Color.black);


        frame.setVisible(true);
        frame.add(this);
    }

    public class redUpAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(redY > 0)
            {
                redY -= stepCount;
                repaint();
            }
        }
    }

    public class redDownAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(redY+redBat_HEIGHT+30 != MAX_HEIGHT)
            {
                redY += stepCount;
                repaint();
            }
        }
    }

    public class blueUpAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(blueY > 0)
            {
                blueY -= stepCount;
                repaint();
            }
        }
    }

    public class blueDownAction extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(blueY+blueBat_HEIGHT+30 != MAX_HEIGHT)
            {
                blueY += stepCount;
                repaint();
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        blueXScore = MAX_WIDTH - blueBat_WIDTH;
        blueYScore = MAX_HEIGHT - 50;

        redXScore = (redBat_WIDTH/2) - 5;
        redYScore = MAX_HEIGHT - 50;

        g.setColor(Color.red);
        g.fillRect(5, redY, redBat_WIDTH, redBat_HEIGHT);

        g.setColor(Color.BLUE);
        g.fillRect(MAX_WIDTH - blueBat_WIDTH - 20, blueY, blueBat_WIDTH, blueBat_HEIGHT);

        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(blueScore), blueXScore, blueYScore);

        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(redScore), redXScore, redYScore);

        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, ball_WIDTH, ball_HEIGHT);

        if(ballX <= 0 || ballX >= MAX_WIDTH)
        {
            if(ballX <= 0)
            {
                endFrame.win = "BLUE";
                endFrame.score = String.valueOf(redScore);
            }
            else
            {
                endFrame.win = "RED";
                endFrame.score = String.valueOf(blueScore);
            }
            goClip.start();
            frame.dispose();
            new endFrame();
        }

        if ((ballX > MAX_WIDTH - ball_WIDTH - 30 - blueBat_WIDTH) && (ballY >= blueY  && ballY <= blueY + blueBat_HEIGHT))
        {
            cClip.setMicrosecondPosition(0);
            cClip.start();
            blueScore += stepCount;
            moveLeft = true;
        }
        else if ((ballX <  5 + blueBat_WIDTH) && (ballY >= redY + redBat_WIDTH && ballY <= redY + redBat_HEIGHT + redBat_WIDTH))
        {
            cClip.setMicrosecondPosition(0);
            cClip.start();
            redScore += stepCount;
            moveLeft = false;
        }


        if(ballY > MAX_HEIGHT - ball_HEIGHT - 40)
        {
            moveUp = true;
        }
        else if(ballY < 5)
        {
            moveUp = false;
        }


        if(moveLeft)
        {
            ballX -= stepCount;
        } else
        {
            ballX += stepCount;
        }

        if(moveUp)
        {
            ballY -= stepCount;
        }
        else
        {
            ballY += stepCount;
        }

        repaint();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
