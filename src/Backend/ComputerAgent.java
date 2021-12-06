package Backend;

public class ComputerAgent {
    /*

    thinker
    turnsCount

    getNextMove
    function minimize
    function maximize
     */
    IMinimax minimax;
    int depth;
    int turns;
    int maxTurns = 6*7;

    public ComputerAgent(boolean withPruning ,int k){
        depth = Math.min(k , maxTurns);
        if( withPruning )
            minimax = null;
        else
            minimax = new MinimaxWithoutPruning();
    }

    public int getNextMove(int playerMove){
        turns+=2;
        depth = Math.min(depth , maxTurns - turns);
        return 0;
    }
    


}
