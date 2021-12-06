package Backend;

public class State implements Cloneable{
    private static final int EMPTY = 0;
    private static final int COMPUTER_TURN = 1;
    public static final int PLAYER_TURN = 2;
    private static final int ROW_COUNT = 6;
    private static final int COLUMNS_COUNT = 7;

    private static int[][] board;
    private int[] freeCells;

    public void getInitialState(){
        this.board = new int[this.ROW_COUNT][this.COLUMNS_COUNT];
        this.freeCells = new int[7];
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

    public State getChild(int columnNum){
        State newState = null;
        if(this.freeCells[columnNum] == 6)
            return newState;
        try {
            newState = (State) this.clone();
            newState.board[freeCells[columnNum]][columnNum] = this.COMPUTER_TURN;
            newState.freeCells[columnNum] += 1;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newState;
    }

    /*
    value evaluatedValue
    function getInitialState
    function getChildrenStates
    int[] firstFreeCell
    function evaluateState
    function findSpecifiedColumn



    heuristic should :
    2 priorities:
    check if player will win and stop this move
    check if there is w inning move for computer






     */
    public State clone() throws CloneNotSupportedException{
        State newState = new State();
        newState.freeCells = this.freeCells.clone();
        newState.board = this.board.clone();
        return newState;
    }
    public int[] getFreeCells() {
        return this.freeCells;
    }

    public void setFreeCells(int[] freeCells) {
        this.freeCells = freeCells;
    }

    public static int[][] getBoard() {
        return board;
    }

    public static void setBoard(int[][] board) {
        State.board = board;
    }
}
class test{
    public static void main(String[] args){
        State initial = new State();
        initial.getInitialState();
        State child = initial.getChild(0);
        System.out.println("Done");
    }
}
