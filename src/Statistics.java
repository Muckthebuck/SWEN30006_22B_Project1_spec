package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Statistics {

    private final ArrayList<Integer> roundScores = new ArrayList<>();
    private String difficulty;
    private int roundCount = 0;
    private int currScore = 0;
    private final int numOfPieces = Shape.ShapeIndex.values().length;
    private final int[] pieceCount = new int[numOfPieces];
    private final ArrayList<int[]> pieceCountByRound = new ArrayList<>();


    public Statistics() {}

    public void addRound() {
        updateStatCount();
        roundCount++;
        resetStatCount();
    }

    public void resetStatCount() {
        for (int i = 0; i < numOfPieces; i++) {
            pieceCount[i] = 0;
        }
        currScore = 0;

        roundScores.add(currScore);
        pieceCountByRound.add(Arrays.copyOf(pieceCount, numOfPieces));
        updateStatCount();
    }

    private void updateStatCount() {
        roundScores.set(roundCount, currScore);
        pieceCountByRound.set(roundCount, Arrays.copyOf(pieceCount, numOfPieces));
        writeStats();
    }

    public void updateRoundScore(int roundScore) {
        this.currScore = roundScore;
        updateStatCount();
    }

    public void updatePieceCount(int pieceIndex) {
        pieceCount[pieceIndex] = pieceCount[pieceIndex] + 1;
        updateStatCount();
//        System.out.println(Shape.ShapeIndex.values()[pieceIndex] + " count is " + (pieceCount[pieceIndex]));
    }

    private int calculateAverageScore() {
        int totalScore = 0;
        for (Integer roundScore : roundScores) {
            totalScore = totalScore + roundScore;
        }
        return totalScore / roundScores.size();
    }

    public void getDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    private void writeStats() {
        try(FileWriter fw = new FileWriter("Statistics.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
//            System.out.println("Game stats printed");
            out.println("Difficulty: " + this.difficulty);
            out.println("Average score per round: " + calculateAverageScore());
            for (int i = 0; i < roundScores.size(); i++) {
                out.println("-----------------------------");
                out.println("Round #" + (i + 1));
                out.println("Score: " +  roundScores.get(i));
                int[] pieceCountHelper = pieceCountByRound.get(i);
                for (int k = 0; k < numOfPieces; k++) {
                    out.println(Shape.ShapeIndex.values()[k] + ": " + pieceCountHelper[k]);
                }
            }
        } catch (IOException e) {
        }
    }
}
