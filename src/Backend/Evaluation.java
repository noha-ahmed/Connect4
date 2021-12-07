package Backend;

public class Evaluation {
    private static int quadrupleScore = 100;
    private static int tripleScore = 50;
    private static int doubleScore = 25;
    public static int evaluateScore(int[][] board){
        return 0;
    }
    private static int evalHorizontalWindow(int[][] board, int startRow, int startColumn){
        int oppPiece = 0;
        int piece = 0;
        int empty = 0;
        for(int i=startColumn; i<4+startColumn; i++){
            if(board[startRow][i]==State.PLAYER_TURN){
                oppPiece--;
            }else if(board[startRow][i]==State.COMPUTER_TURN){
                piece++;
            }else{
                empty++;
            }
        }
        return calculateWeights(piece,oppPiece,empty);
    }

    private static int evalVerticalWindow(int[][] board, int startRow, int startColumn){
        return 0;
    }

    private static int evalPDiagonalWindow(int[][] board, int startRow, int startColumn ){
        return 0;
    }

    private static int evalNDiagonalWindow(int[][] board, int startRow, int startColumn){
        return 0;
    }

    private static int calculateWeights(int pieces, int oppPiece, int empty){


        return 0;
    }

}
