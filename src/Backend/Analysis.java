package Backend;

import java.util.Random;

public class Analysis {
    private static int testCases = 10;
    private static int kLimit = 15;
    private static Random rand = new Random();

    public static void analyze(boolean isPruning){
        IComputerAgent computerAgent;
        double start , end;
        int m;
        int playerMove;
        double meanTime;
        double meanScoreDifference;
        double[] time = new double[kLimit];
        int[] scoreDifference = new int[kLimit];
        for( int k = 0 ; k < kLimit ; k++ ){
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
                meanScoreDifference+= (computerAgent.getComputerScore() - computerAgent.getPlayerScore());
                meanTime+= (meanTime/testCases);
            }
            meanScoreDifference = meanScoreDifference/testCases;
            scoreDifference[k] = (int) meanScoreDifference;
            time[k] = meanTime;
            
        }
    }
    public static void main(String[] args){

    }
    
}
