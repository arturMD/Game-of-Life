package artur.md.game.of.life;

import java.awt.Container;
import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;




public class MainWindow {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


class MainFrame extends JFrame {
    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private JTextArea board;
    private JButton nextButton;

    private CellGrid cg;
    private JPanel boardPanel;
    private JPanel controlPanel;


    MainFrame() {
        setSize(WIDTH, HEIGHT);
        Container container = getContentPane();

        cg = new CellGrid(10,10);
        Pattern.setSize(10,10);
        Pattern.get(PatternName.GLIDER, cg, 7,3);

        boardPanel = new JPanel();
        controlPanel = new JPanel();

        board = new JTextArea(15, 15);

        boardPanel.add(board);
        board.setFont(new Font("monospaced", Font.PLAIN, 24));
        board.setText(cg.toString());

        nextButton = new JButton("Next");
        controlPanel.add(nextButton);
        nextButton.addActionListener(e -> {
            board.setText(cg.toString());
            cg.next();
        });

        container.add(boardPanel, BorderLayout.WEST);
        container.add(controlPanel, BorderLayout.EAST);
    }
}


