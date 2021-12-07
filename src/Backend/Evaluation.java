package Backend;

public class Evaluation {

    public static int evaluateScore(int[][] board){
        int evalScore = 0;

        //Horizontal groups check 
        for( int i = 0 ; i < State.ROW_COUNT ; i++ )
            for( int j = 0 ; j < State.COLUMNS_COUNT -3 ; j++)
                evalScore += evalHorizantalWindow(board, i, j);
        
        //Vertical groups check
        for( int j = 0 ; j < State.COLUMNS_COUNT ; j++)
            for( int i = 0 ; i < State.ROW_COUNT -3 ; i++ )
                evalScore += evalVerticalWindow(board, i, j);
        
        for( int i = 0 ; i < State.ROW_COUNT - 3 ; i++ )
            for( int j = 0 ; j < State.COLUMNS_COUNT - 3 ; j++ ){
                //Positive diagonal groups check "/""
                evalScore += evalPDiagonalWindow(board, i, j);
                //Negative diagonal groups check "\"
                evalScore += evalNDiagonalWindow(board, i, State.COLUMNS_COUNT - j);
            }
        
        return evalScore;
    }

    private static int evalHorizantalWindow(int[][] board, int startRow, int startColumn ){
        return 0;
    }

    private static int evalVerticalWindow(int[][] board, int startRow, int startColumn ){
        return 0;
    }

    private static int evalPDiagonalWindow(int[][] board, int startRow, int startColumn ){
        return 0;
    }

    private static int evalNDiagonalWindow(int[][] board, int startRow, int startColumn){
        return 0;
    }


}
