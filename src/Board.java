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
    int pairs = 4;
    int rows = 2;
    int columns = 4;
    int tries = 10;
    int counter;
    boolean isChosen = false;
    List<String> allWords;
    Container frame = getContentPane();
    Timer timer;

    public Board() throws IOException {

        allWords = new ArrayList<>();

        File file = new File("src/resources/Words.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String word;
        while ((word = br.readLine()) != null) {
            allWords.add(word);
        }
        Collections.shuffle(allWords);

        setTitle("Choose Difficulty: ");
        chooseDifficulty();

    }


    public void doTurn(Word pickedWord) {
        if (word1 == null && word2 == null) {
            word1 = pickedWord;
            word1.showText();
        } else if (word1 != null && word1 != pickedWord && word2 == null) {
            word2 = pickedWord;
            word2.showText();
            timer.start();
        }
    }


    private void checkWords() {
        if (word1.getWord().equals(word2.getWord())) {
            word1.setEnabled(false);
            word2.setEnabled(false);
            word1.setMatch(true);
            word2.setMatch(true);
            if (this.isWon()) {
                JOptionPane.showMessageDialog(this, "You have won!");
                //TODO: Restarting game
            }
        } else {
            word1.setText("");
            word2.setText("");
        }
        word1 = null;
        word2 = null;
    }


    private boolean isWon() {
        for (Word word : this.words) {
            if (!word.isMatch()) {
                return false;
            }
        }
        return true;
    }


    public void chooseDifficulty() {

        JButton easy = new JButton("Easy");
        easy.setPreferredSize(new Dimension());
        easy.addActionListener(e -> {
            pairs = 4;
            rows = 2;
            columns = 4;
            tries = 10;
            isChosen = true;
            try {
                setBoard();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        JButton hard = new JButton("Hard");
        hard.addActionListener(e -> {
            pairs = 8;
            rows = 4;
            columns = 4;
            tries = 15;
            isChosen = true;
            try {
                setBoard();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        frame.setLayout(new GridLayout());
        frame.add(easy);
        frame.add(hard);
        frame.setVisible(true);
    }


    public void setBoard() throws IOException {
        frame.removeAll();
        frame.revalidate();
        frame.repaint();

        List<Word> wordList = new ArrayList<>();
        List<String> wordValues = new ArrayList<>();

        for (int i = 0; i < pairs; i++) {
            wordValues.add(allWords.get(i));
            wordValues.add(allWords.get(i));
        }
        Collections.shuffle(wordValues);

        for (String a : wordValues) {
            Word word = new Word();
            word.setWord(a);
            word.addActionListener(e -> doTurn(word));
            wordList.add(word);
        }
        this.words = wordList;

        timer = new Timer(780, e -> checkWords());

        timer.setRepeats(false);

        this.frame.setLayout(new GridLayout(rows, columns));
        for (Word word : words) {
            frame.add(word);
        }
        setTitle("Memory");
    }
}