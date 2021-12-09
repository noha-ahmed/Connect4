package Backend;

import java.util.Scanner;

public class ComputerAgent implements IComputerAgent {
    IMinimax minimax;
    int k;
    int level;
    int turns = 0;
    int maxTurns = State.ROW_COUNT * State.COLUMNS_COUNT;
    State currentState;
    private int compScore = 0;
    private int oppScore = 0;

    public ComputerAgent(boolean withPruning ,int k){
        this.currentState = new State();
        this.k = k;
        this.level = Math.min(k , maxTurns);
        if( withPruning )
            minimax = new MinimaxPruning();
        else
            minimax = new MinimaxWithoutPruning();
    }

    public int getNextMove(int playerMove){
        currentState.updateState(playerMove, State.PLAYER_TURN);
        turns++;
        this.oppScore += currentState.getPlayerScore(playerMove, State.PLAYER_TURN);
        level = Math.min(level , maxTurns - turns);
        currentState.setEvaluationState(new EvaluationState());
        EvaluationState move = minimax.Decision(currentState, level);
        currentState.updateState(move.getFromColumn(), State.COMPUTER_TURN);
        this.compScore += currentState.getPlayerScore(move.getFromColumn(), State.COMPUTER_TURN);
        turns++;
        currentState.getEvaluationState().printTree();
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
        this.level = this.k;
    }

    @Override
    public int getComputerScore() {
        return this.compScore;
    }

    @Override
    public int getPlayerScore() {
        return this.oppScore;
    }
}

class Main2{
    public static void main(String[] args){
       /* ComputerAgent game = new ComputerAgent(true, 10);
        Scanner sc = new Scanner(System.in);
        while(game.turns < game.maxTurns){
            System.out.println("enter player move: ");
            int oppMove = sc.nextInt();
            int compMove = game.getNextMove(oppMove);
            System.out.println("computer move at: " + compMove);
        }*/
        int[][] board = new int[6][7];
        ComputerAgent game = new ComputerAgent(false, 3);
        board[0][0]=2;
        board[0][1]=2;
        board[1][0]=1;
        board[1][1]=2;
        board[2][0]=2;
        board[3][0]=1;
        board[4][0]=1;
        board[5][0]=1;
        game.currentState.setBoard(board);
        game.getNextMove(1);
    }
}
