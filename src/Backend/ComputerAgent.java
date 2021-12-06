package Backend;

public class ComputerAgent {
    /*

    thinker
    turnsCount

    getNextMove
    function minimize
    function maximize
     */

    int depth;
    int turns;
    int maxTurns = 6*7;

    public ComputerAgent(boolean withPruning ,int k){
        depth = Math.min(k , maxTurns);
        
    }

    public int getNextMove(int playerMove){
        turns++;
        depth = Math.min(depth , maxTurns - turns);
        return 0;
    }
    


}
