import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        startGame();
//        Board board = new Board();
//        board.setPreferredSize(new Dimension(600,600));
//        board.setLocation(500,500);
//        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        board.pack();
//        board.setVisible(true);
    }

    static void startGame() throws IOException {
        Board board = new Board();
        board.setPreferredSize(new Dimension(600,600));
        board.setLocation(500,500);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.pack();
        board.setVisible(true);
    }
}