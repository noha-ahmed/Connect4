package Backend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ComputerAgent implements IComputerAgent {
    IMinimax minimax;
    int k;
    int level;
    int turns = 0;
    int maxTurns = State.ROW_COUNT * State.COLUMNS_COUNT;
    State currentState;
    long maxRunningTime = Long.MIN_VALUE;
    long prevRunningTime = 0;
    long avRunningTime = 0;
    int avNodesExpanded = 0;
    int maxNodesExpanded = Integer.MIN_VALUE;
    private int compScore = 0;
    private int oppScore = 0;
    private boolean withPruning;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public ComputerAgent(boolean withPruning ,int k){
        this.currentState = new State();
        this.k = k;
        this.level = Math.min(k , maxTurns);
        this.withPruning = withPruning;
        if( withPruning )
            minimax = new MinimaxPruning();
        else
            minimax = new MinimaxWithoutPruning();
    }

    public int getNextMove(int playerMove){
        long start , end;
        currentState.updateState(playerMove, State.PLAYER_TURN);
        turns++;
        if( turns == maxTurns ){
            this.writeResultsToFile();
            return -1;
        }
        this.oppScore += currentState.getPlayerScore(playerMove, State.PLAYER_TURN);
        level = Math.min(level , maxTurns - turns);
        currentState.setEvaluationState(new EvaluationState());
        start = System.nanoTime()/1000;
        EvaluationState move = minimax.Decision(currentState, level);
        end = System.nanoTime()/1000;
        currentState.updateState(move.getFromColumn(), State.COMPUTER_TURN);
        this.compScore += currentState.getPlayerScore(move.getFromColumn(), State.COMPUTER_TURN);
        turns++;
        prevRunningTime = (end-start);
        avRunningTime+= prevRunningTime/21;
        avNodesExpanded+= minimax.getNodesExpanded()/21;
        if( prevRunningTime > maxRunningTime ){
            maxRunningTime = prevRunningTime;
        }
        if( minimax.getNodesExpanded() > maxNodesExpanded ){
            maxNodesExpanded = minimax.getNodesExpanded();
        }
        if( turns == maxTurns )
            this.writeResultsToFile();
        
        //currentState.getEvaluationState().printTree();
        return move.getFromColumn();
    }

    public int getFirstMove(){
        turns++;
        currentState.updateState(State.COLUMNS_COUNT / 2, State.COMPUTER_TURN);
        return State.COLUMNS_COUNT / 2;
    }

    public EvaluationState getEvaluationState(){
        return this.currentState.getEvaluationState();
    }
    @Override
    public void restart() {
        this.turns = 0;
        this.currentState = new State();
        this.compScore = 0;
        this.oppScore = 0;
        this.maxRunningTime = Long.MIN_VALUE;
        this.maxNodesExpanded = Integer.MIN_VALUE;
        this.level = this.k;
        this.avRunningTime = 0;
        this.avNodesExpanded = 0;
    }

    @Override
    public int getComputerScore() {
        return this.compScore;
    }

    @Override
    public int getPlayerScore() {
        return this.oppScore;
    }

    @Override
    public boolean isValidMove(int playerMove) {
        
        if(playerMove >= 0  && playerMove <= 6 && this.currentState.getFreeCells()[playerMove] < 6)
            return true;
        return false;
    }

    public long getPrevRunningTime(){
        return this.prevRunningTime;
    }

    public int getNodesExpanded(){
        return this.minimax.getNodesExpanded();
    }

    public void printBoard(){
        this.currentState.printBoard();
    }

    public void printTree(){
        currentState.getEvaluationState().printTree();
    }

    public void writeResultsToFile(){
        String sol = "";
        if( this.withPruning )
            sol += "<< Minimix with Pruning >>  ";
        else
            sol += "<< Minimix without Pruning >>  ";
        sol += "k: " + this.k + ",  Computer Score: " + this.compScore + ",  Player Score: " + this.oppScore + 
        ",  Max Running Time: " + df.format(this.maxRunningTime) + " microseconds, Max Nodes Expanded: " + this.maxNodesExpanded
        + ",  Average Running Time: " + df.format(this.avRunningTime) + " microseconds, Average Nodes Expanded: " + this.avNodesExpanded;
        try {
            File file = new File("results.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(),true));
            bw.write(sol+ "\n");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class Main2{
    public static void main(String[] args){
        ComputerAgent game = new ComputerAgent(true, 5);
        Scanner sc = new Scanner(System.in);
        while(game.turns < game.maxTurns){
            System.out.println("---------------------------------------------------------------");
            System.out.println("Comp : Player");
            System.out.println("  " + game.getComputerScore() + "  :  " + game.getPlayerScore());
            System.out.println("-------------");
            game.printBoard();
            System.out.println("-------------");
            System.out.println("enter player move: ");
            int oppMove = sc.nextInt();
            if( ! game.isValidMove(oppMove) ){
                continue;
            }
            int compMove = game.getNextMove(oppMove);
            System.out.println("computer move at: " + compMove);
            System.out.println("Thinking Time: " + game.getPrevRunningTime() + " microseconds");
            System.out.println("Nodes Expanded: " + game.getNodesExpanded());
           // game.printTree();
        }
        // int[][] board = new int[6][7];
        // ComputerAgent game = new ComputerAgent(false, 3);
        // board[0][0]=2;
        // board[0][1]=2;
        // board[1][0]=1;
        // board[1][1]=2;
        // board[2][0]=2;
        // board[3][0]=1;
        // board[4][0]=1;
        // board[5][0]=1;
        // game.currentState.setBoard(board);
        // game.getNextMove(1);
    }
}
