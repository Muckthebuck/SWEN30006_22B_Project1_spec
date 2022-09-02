package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Statistics {

    private ArrayList<Integer> roundScores = new ArrayList<>();
    private String difficulty;
    private int roundCount = 0;
    // how do i do this better
    private int numOfPieces = Shape.ShapeIndex.values().length;
    private int[] pieceCount = new int[numOfPieces];
    private ArrayList<int[]> pieceCountByRound = new ArrayList<>();


    public Statistics() {}

    public void addRound(int roundScore) {
        roundScores.add(roundScore);
        pieceCountByRound.add(pieceCount);
        roundCount++;


    }

    public void resetPieceCount() {
        for (int i = 0; i < numOfPieces; i++) {
            pieceCount[i] = 0;
        }
    }

    public void updatePieceCount(int pieceIndex) {
        pieceCount[pieceIndex] = pieceCount[pieceIndex] + 1;
        System.out.println(Shape.ShapeIndex.values()[pieceIndex] + " count is " + (pieceCount[pieceIndex]));
    }

    private int calculateAverageScore() {
        int totalScore = 0;
        for (int i = 0; i < roundScores.size(); i++) {
            totalScore = totalScore + roundScores.get(i);
        }
        return totalScore / roundScores.size();
    }

    public void getDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void writeStats() {
        try(FileWriter fw = new FileWriter("src/Statistics.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            System.out.println("Game stats printed");
            out.println("Difficulty: " + this.difficulty);
            out.println("Average score per round: " + calculateAverageScore());
            for (int i = 0; i < roundScores.size(); i++) {
                out.println("-----------------------------");
                out.println("Round #" + (i + 1));
                out.println("Score: " +  roundScores.get(i));
//                for (int j = 0 ; j < numOfPieces; j++) {
//                    out.println(Shape.ShapeIndex.values()[j] + ": " + pieceCount[j]);
//                }
                int[] pieceCountHelper = pieceCountByRound.get(i);
                for (int k = 0; k < numOfPieces; k++) {
                    out.println(Shape.ShapeIndex.values()[k] + ": " + pieceCountHelper[k]);
                }
            }
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
}
