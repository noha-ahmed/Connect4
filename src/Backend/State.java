package Backend;

public class State implements Cloneable{
    public static final int EMPTY = 0;
    public static final int COMPUTER_TURN = 1;
    public static final int PLAYER_TURN = 2;
    public static final int ROW_COUNT = 6;
    public static final int COLUMNS_COUNT = 7;

    private int[][] board;
    private int[] freeCells;
    private EvaluationState evaluationState;

    public State(){
        this.board = new int[this.ROW_COUNT][this.COLUMNS_COUNT];
        this.freeCells = new int[7];
        this.evaluationState = new EvaluationState();
    }

    public int findColumn(State newState){
        int colNum = 0;
        int[] freeCellsTemp = newState.getFreeCells();
        for(int i = 0; i < 7; i++){
            if(freeCellsTemp[i] == this.freeCells[i] + 1){
                colNum = i;
                break;
            }
        }
        return colNum;
    }

    public State getChild(int columnNum , int player){
        State newState = null;
        if(this.freeCells[columnNum] == 6)
            return newState;
        try {
            newState = this.clone();
            newState.board[freeCells[columnNum]][columnNum] = player;
            newState.freeCells[columnNum] += 1;
            newState.evaluationState.setFromColumn(columnNum);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newState;
    }

    public void updateState(int columnNum, int player){
        this.board[freeCells[columnNum]][columnNum] = player;
        freeCells[columnNum] ++;
    }
    public State clone() throws CloneNotSupportedException{
        State newState = new State();
        newState.freeCells = this.freeCells.clone();
        newState.board = new int[this.ROW_COUNT][];
        for(int i = 0; i < this.ROW_COUNT; i++){
            newState.board[i] = this.board[i].clone();
        }
        newState.evaluationState = new EvaluationState();
        return newState;
    }
    public int evaluateState(){
        return Evaluation.evaluateScore(this.board);
    }

    public int[] getFreeCells() {
        return this.freeCells;
    }

    public void setFreeCells(int[] freeCells) {
        this.freeCells = freeCells;
    }

    public int[][] getBoard() {
        return this.board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public EvaluationState getEvaluationState() {
        return evaluationState;
    }

    public void setEvaluationState(EvaluationState evaluationState) {
        this.evaluationState = evaluationState;
    }
}
class test{
    public static void main(String[] args){
        State initial = new State();
     //   State child = initial.getChild(0);
        System.out.println("Done");
    }
}
