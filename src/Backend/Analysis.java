package Backend;

import java.util.Arrays;
import java.util.Random;

public class Analysis {
    private static int testCases = 3;
    private static int kLimit = 15;
    private static Random rand = new Random();

    public static void analyze(boolean isPruning){
        IComputerAgent computerAgent;
        double start , end;
        int playerMove;
        double meanTime;
        double meanScoreDifference;
        double[] time = new double[kLimit];
        int[] scoreDifference = new int[kLimit];
        int[] kValues = new int[kLimit];
        for( int k = 0 ; k < kLimit ; k++ ){
            kValues[k] = k+1;
            computerAgent = new ComputerAgent(isPruning, k + 1);
            meanTime = 0;
            meanScoreDifference = 0;
            // test cases for the same k loop
            for( int i = 0 ; i < testCases ; i++ ){
                //game loop
                for( int j = 0 ; j < 21 ; j++ ){
                    do{
                        playerMove = rand.nextInt(7);
                    }while( !computerAgent.isValidMove(playerMove) );
                    start = System.nanoTime();
                    computerAgent.getNextMove(playerMove);
                    end = System.nanoTime();
                    meanTime += (end-start)/21;
                }
                System.out.println(k+1 + " - " + meanScoreDifference + " - "  + meanTime);
                meanScoreDifference+= (computerAgent.getComputerScore() - computerAgent.getPlayerScore());
                meanTime+= (meanTime/testCases);
                computerAgent.restart();
            } 
            meanScoreDifference = meanScoreDifference/testCases;
            scoreDifference[k] = (int) meanScoreDifference;
            time[k] = meanTime;   
            System.out.println(k+1 + " -> s:" + meanScoreDifference + " - c: "  + meanTime);
        }
        System.out.println("Minimax with Pruning Analysis:/n");
        System.out.println("K Values : " + Arrays.toString(kValues));
        System.out.println("Running Time : " + Arrays.toString(time));
        System.out.println("Score Difference : " + Arrays.toString(scoreDifference));
    }
    public static void main(String[] args){
        analyze(true);
    }
    
}
