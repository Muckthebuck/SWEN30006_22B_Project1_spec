package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Statistics {

    private ArrayList<Integer> roundScores = new ArrayList<>();


    public Statistics() {}

    public void addRound(int roundScore) {
        roundScores.add(roundScore);

    }

    private int calculateAverageScore() {
        int totalScore = 0;
        for (int i = 0; i < roundScores.size(); i++) {
            totalScore = totalScore + roundScores.get(i);
        }
        return totalScore / roundScores.size();
    }

    public void writeStats() {
        try(FileWriter fw = new FileWriter("src/Statistics.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            System.out.println("Game stats printed");
            out.println("Difficulty: Easy");
            out.println("Average score per round: " + calculateAverageScore());
            for (int i = 0; i < roundScores.size(); i++) {
                out.println("-----------------------------");
                out.println("Round #" + (i + 1));
                out.println("Score: " +  roundScores.get(i));
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
