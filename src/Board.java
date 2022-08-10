import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board extends JFrame {
    private List<Word> words;
    private Word pickedWord;
    private Word word1;
    private Word word2;
    String easy = "Easy";
    String hard = "Hard";
    int pairs;
    int rows;
    int columns;

    public Board() throws IOException {

        setTitle("Choose Difficulty: ");
        chooseDifficulty();

        List<Word> wordList = new ArrayList<>();
        List<String> wordValues = new ArrayList<>();

        for (int i = 0; i < pairs; i++) {
            File file = new File("src/resources/Words.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String word;
            while ((word = br.readLine()) != null) {
                wordValues.add(word);
            }
        }
        Collections.shuffle(wordValues);

    }

    public void chooseDifficulty() {
        JButton easy = new JButton("Easy");
        easy.setLocation(220, 250);
        easy.addActionListener(e -> {
            pairs = 4;
            rows = 2;
            columns = 4;
        });

        JButton hard = new JButton("Hard");
        hard.setLocation(440, 250);
        hard.addActionListener(e -> {
            pairs = 8;
            rows = 4;
            columns = 4;
        });
    }

    public void setBoard() {
        Container board = getContentPane();
        board.setLayout(new GridLayout(rows, columns));
        for (Word word : words) {
            board.add(word);
        }
    }
}