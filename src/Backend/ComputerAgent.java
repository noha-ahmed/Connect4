package Backend;

import java.util.Scanner;

public class ComputerAgent implements IComputerAgent {
    IMinimax minimax;
    int level;
    int turns = 0;
    int maxTurns = State.ROW_COUNT * State.COLUMNS_COUNT;
    State currentState;
    private int compScore = 0;
    private int oppScore = 0;

    public ComputerAgent(boolean withPruning ,int k){
        currentState = new State();
        level = Math.min(k , maxTurns);
        System.out.println(withPruning);
        if( withPruning )
            minimax = new MinimaxPruning();
        else
            minimax = new MinimaxWithoutPruning();
    }

    public int getNextMove(int playerMove){
        currentState.updateState(playerMove, State.PLAYER_TURN);
        turns++;
        level = Math.min(level , maxTurns - turns);
        currentState.setEvaluationState(new EvaluationState());
        EvaluationState move = minimax.Decision(currentState, level);
        currentState.updateState(move.getFromColumn(), State.COMPUTER_TURN);
        turns++;
        return move.getFromColumn();
    }

    public int getFirstMove(){
        turns++;
        currentState.updateState(State.COLUMNS_COUNT / 2, State.COMPUTER_TURN);
        return State.COLUMNS_COUNT / 2;
    }

    public int calculateMoveScore(int moveColumn, int player){
        int score = 0;
        int row = this.currentState.getFreeCells()[moveColumn] - 1;
        int tempColumn = moveColumn;
        int tempRow = row;
        int pieces = 0;
        // if the move made a vertical point
        if(row >= 3){
            for(int i = row; i > row - 4; i--){
                if(this.currentState.getBoard()[i][moveColumn] == player)
                    pieces++;
            }
            if(pieces == 4)
                score ++;
        }
        pieces = 0;
        // if the move made a horizontal point
        //need to check if it's first in score scond third and forth
        //check on the right of the move played
        if(moveColumn <= 3){
            for(int i = moveColumn; i < moveColumn + 4; i++){
                if(this.currentState.getBoard()[row][i] == player)
                    pieces++;
            }
            if(pieces == 4)
                score ++;
        }
        pieces = 0;
        //check on the left of the move played
        if(moveColumn >= 3){
            for(int i = moveColumn; i < moveColumn - 4; i--){
                if(this.currentState.getBoard()[row][i] == player)
                    pieces++;
            }
            if(pieces == 4)
                score ++;
        }
        // if the move made a positive diagonal point
        // if the move made a negative diagonal point
        return score;
    }
    public int countPlayerPieces(int player, String lineType){
        int piecesNum = 0;

        return piecesNum;
    }

    public EvaluationState getEvaluationState(){
        return this.currentState.getEvaluationState();
    }

    @Override
    public void restart() {
        this.turns = 0;
        this.currentState = new State();
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
