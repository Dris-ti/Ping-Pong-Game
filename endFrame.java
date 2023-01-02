import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class endFrame {
    static String win;
    static String score;
    endFrame()
    {
        JLabel playerLabel = new JLabel();
        JLabel scoreLabel = new JLabel();
        if(Objects.equals(win, "RED"))
        {
            win += " wins!";
            playerLabel.setForeground(Color.RED);
            scoreLabel.setForeground(Color.RED);
        }
        else if(Objects.equals(win, "BLUE"))
        {
            win += " wins!";
            playerLabel.setForeground(Color.BLUE);
            scoreLabel.setForeground(Color.BLUE);
        }
        else
        {
            win = "Draw";
        }
        JFrame frame = new JFrame("Ping Pong");
        frame.setSize(GameFrame.MAX_WIDTH, GameFrame.MAX_HEIGHT);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        playerLabel.setBounds(320, 60, 300, 70);
        playerLabel.setFont(new Font("Cheri Liney",Font.BOLD, 50));
        playerLabel.setBackground(Color.WHITE);
        playerLabel.setText(win);
        playerLabel.setOpaque(true);

        ImageIcon gameOver = new ImageIcon("gameOverR.jpg");
        JLabel endLabel = new JLabel();
        endLabel.setLayout(null);
        endLabel.setSize(GameFrame.MAX_WIDTH, GameFrame.MAX_HEIGHT);
        endLabel.setIcon(gameOver);
        endLabel.setHorizontalAlignment(JLabel.CENTER);
        endLabel.setOpaque(true);




        scoreLabel.setLayout(null);
        scoreLabel.setBounds(400, 500, 200, 70);
        scoreLabel.setFont(new Font("Cheri Liney",Font.BOLD, 30));
        scoreLabel.setBackground(Color.decode("#ffffff"));
        scoreLabel.setText(score);
        scoreLabel.setOpaque(true);

        frame.add(playerLabel);
        frame.add(scoreLabel);
        frame.add(endLabel);
        frame.setVisible(true);
    }
}
