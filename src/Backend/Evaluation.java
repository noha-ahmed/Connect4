package Backend;

public class Evaluation {
    private static final int QUADRUPLE_SCORE = 100;
    private static final int TRIPLE_SCORE = 5;
    private static final int DOUBLE_SCORE = 2;
    private static final int WINDOW_SIZE = 4;
    public static int evaluateScore(int[][] board){
        int evalScore = 0;

        //Horizontal groups check 
        for( int i = 0 ; i < State.ROW_COUNT ; i++ )
            for( int j = 0 ; j < State.COLUMNS_COUNT -3 ; j++)
                evalScore += evalHorizontalWindow(board, i, j);
        
        //Vertical groups check
        for( int j = 0 ; j < State.COLUMNS_COUNT ; j++)
            for( int i = 0 ; i < State.ROW_COUNT -3 ; i++ )
                evalScore += evalVerticalWindow(board, i, j);
        
        for( int i = 0 ; i < State.ROW_COUNT - 3 ; i++ )
            for( int j = 0 ; j < State.COLUMNS_COUNT - 3 ; j++ ){
                //Positive diagonal groups check "/""
                evalScore += evalPDiagonalWindow(board, i, j);
                //Negative diagonal groups check "\"
                evalScore += evalNDiagonalWindow(board, i, State.COLUMNS_COUNT - j - 1);
            }
        
        return evalScore;
    }
    private static int evalHorizontalWindow(int[][] board, int startRow, int startColumn){
        int oppPiece = 0;
        int piece = 0;
        int empty = 0;
        for(int i=startColumn; i<startColumn+WINDOW_SIZE; i++){
            if(board[startRow][i]==State.PLAYER_TURN){
                oppPiece++;
            }else if(board[startRow][i]==State.COMPUTER_TURN){
                piece++;
            }else{
                empty++;
            }
        }
        return calculateWeights(piece,oppPiece,empty);
    }

    private static int evalVerticalWindow(int[][] board, int startRow, int startColumn){
        int compPieces = 0;
        int oppPieces = 0;
        int emptyPieces = 0;
        for(int i = startRow; i < startRow + Evaluation.WINDOW_SIZE; i++){
            if(board[i][startColumn] == State.COMPUTER_TURN)
                compPieces++;
            else if(board[i][startColumn] == State.PLAYER_TURN)
                oppPieces++;
            else
                emptyPieces++;
        }
        return calculateWeights(compPieces, oppPieces, emptyPieces);
    }

    private static int evalPDiagonalWindow(int[][] board, int startRow, int startColumn ){
        int compPieces = 0;
        int oppPieces = 0;
        int emptyPieces = 0;
        for(int i = startRow; i < startRow + Evaluation.WINDOW_SIZE; i++){
            if(board[i][startColumn] == State.COMPUTER_TURN)
                compPieces++;
            else if(board[i][startColumn] == State.PLAYER_TURN)
                oppPieces++;
            else
                emptyPieces++;
            startColumn++;
        }
        return calculateWeights(compPieces, oppPieces, emptyPieces);
    }

    private static int evalNDiagonalWindow(int[][] board, int startRow, int startColumn){
        int compPieces = 0;
        int oppPieces = 0;
        int emptyPieces = 0;
        for(int i = startRow; i < startRow + Evaluation.WINDOW_SIZE; i++){
            if(board[i][startColumn] == State.COMPUTER_TURN)
                compPieces++;
            else if(board[i][startColumn] == State.PLAYER_TURN)
                oppPieces++;
            else
                emptyPieces++;
            startColumn --;
        }
        return calculateWeights(compPieces, oppPieces, emptyPieces);
    }

    private static int calculateWeights(int pieces, int oppPiece, int empty){
        if(pieces==4){
            return QUADRUPLE_SCORE;
        }else if(pieces==3 && empty==1){
            return TRIPLE_SCORE;
        }else if(pieces==2 && empty==2){
            return DOUBLE_SCORE;
        }
        if(oppPiece==4){
            return -1*QUADRUPLE_SCORE;
        }else if(oppPiece==3 && empty==1){
            return -60;
       }else if(oppPiece==2 && empty==2){
            return -1*DOUBLE_SCORE;
        }
        return 0;
    }


    // calculating score of player
    public static int calculateMoveScore(int moveColumn, int player, int[][] board, int[] freeCells){
        int score = 0;
        int row = freeCells[moveColumn] - 1;
        int pieces = 0;
        // if the move made a vertical point
        if(row >= 3){
            pieces = countPlayerPieces(row, moveColumn, player, "vertical", board);
            if(pieces == 4)
                score ++;
        }
        pieces = 0;
        // if the move made a horizontal point
        //first piece in 4
        if(moveColumn <= 3){
            pieces = countPlayerPieces(row, moveColumn, player, "Horizontal", board);
            if(pieces == 4)
                score ++;
        }
        pieces = 0;
        //second piece in 4
        if(moveColumn >= 1 && moveColumn <= 4){
            pieces = countPlayerPieces(row, moveColumn - 1, player, "Horizontal", board);
            if(pieces == 4)
                score ++;
        }
        pieces = 0;
        //third piece in 4
        if(moveColumn >= 2 && moveColumn <= 5){
            pieces = countPlayerPieces(row, moveColumn - 2, player, "Horizontal", board);
            if(pieces == 4)
                score ++;
        }
        pieces = 0;
        //fourth piece in 4
        if(moveColumn >= 3){
            pieces = countPlayerPieces(row, moveColumn - 3, player, "Horizontal", board);
            if(pieces == 4)
                score ++;
        }
        // if the move made a positive diagonal point
        for(int i = 0; i < 4; i++){
            pieces = 0;
            if(row < 3 + i && row >= i && moveColumn < 4 + i && moveColumn >= i){
                pieces = countPlayerPieces(row - i, moveColumn - i, player, "positive diagonal", board);
            }
            if(pieces == 4)
                score++;
        }
        // if the move made a negative diagonal point
        for(int i = 0; i < 4; i++){
            pieces = 0;
            if(row > 2 - i && row < State.ROW_COUNT - i && moveColumn < 4 + i && moveColumn > i - 1){
                pieces = countPlayerPieces(row + i, moveColumn - i, player, "negative diagonal", board);
            }
            if(pieces == 4)
                score++;
        }
        return score;
    }
    private static int countPlayerPieces(int row, int column, int player, String type, int[][] board){
        int pieces = 0;
        if(type.equals("Horizontal")){
            for(int i = column; i < column + 4; i++){
                if(board[row][i] == player)
                    pieces++;
            }
        }else if(type.equals("vertical")){
            for(int i = row; i > row - 4; i--){
                if(board[i][column] == player)
                    pieces++;
            }
        }else if(type.equals("positive diagonal")){
            for(int i = row; i < row + 4; i++){
                if(board[i][column++] == player)
                    pieces++;
            }
        }else if(type.equals("negative diagonal")){
            for(int i = row; i > row - 4; i--){
                if(board[i][column++] == player)
                    pieces++;
            }
        }
        return pieces;
    }
    public static void printArr( int[][] arr ){
        for( int i = arr.length-1 ; i >= 0 ; i-- ){
            for( int j = 0 ; j < arr[0].length ; j++ ){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        int[][] arr = {{0,0,0,1,0,0,0} ,
                       {0,0,0,0,1,0,0},
                       {0,0,0,0,0,1,0},
                       {0,0,0,0,0,0,1},
                       {0,0,0,0,0,0,0},
                       {0,0,0,0,0,0,0}};
        int[] cells = {4,3,2,1,0,0,4};
        System.out.println(calculateMoveScore(6,1, arr, cells));
        printArr(arr);
    }

}

