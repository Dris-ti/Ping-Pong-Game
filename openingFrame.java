import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class openingFrame  implements MouseListener {
    JFrame frame;
    JLabel playLabel;
    JLabel textLabel;

    openingFrame()
    {
        frame = new JFrame("Ping Pong");
        frame.setSize(GameFrame.MAX_WIDTH, GameFrame.MAX_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon image = new ImageIcon("art.jpg");
        ImageIcon playImage = new ImageIcon("rP.png");


        textLabel = new JLabel();
        textLabel.setBounds(40, 40, 360, 100);
        textLabel.setFont(new Font("Cheri Liney", Font.BOLD, 70));
        textLabel.setText("Ping Pong");
        textLabel.setBackground(Color.decode("#ffffff"));
        textLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                textLabel.setFont(new Font("Cheri", Font.BOLD, 70));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                textLabel.setFont(new Font("Cheri Liney", Font.BOLD, 70));
            }
        });
        textLabel.setOpaque(true);

        playLabel = new JLabel();
        playLabel.setBounds(400, 400, 100, 100);
        playLabel.setIcon(playImage);
        playLabel.setBackground(Color.decode("#ffffff"));
        playLabel.addMouseListener(this);
        playLabel.setOpaque(true);

        JLabel label = new JLabel();
        label.setSize(GameFrame.MAX_WIDTH, GameFrame.MAX_HEIGHT);
        label.setIcon(image);
        label.setOpaque(true);

        frame.add(textLabel);
        frame.add(playLabel);
        frame.add(label);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new openingFrame();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        frame.dispose();
        try {
            new GameFrame();
        } catch (UnsupportedAudioFileException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        playLabel.setBounds(398, 398, 102, 102);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        playLabel.setBounds(400, 400, 100, 100);
    }
}
