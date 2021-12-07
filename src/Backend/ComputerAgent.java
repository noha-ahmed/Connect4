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
    int level;
    int turns = 0;
    int maxTurns = State.ROW_COUNT * State.COLUMNS_COUNT;
    State currentState;

    public ComputerAgent(boolean withPruning ,int k){
        currentState = new State();
        level = Math.min(k , maxTurns);
        if( withPruning )
            minimax = new MinimaxPruning();
        else
            minimax = new MinimaxWithoutPruning();
    }

    public int getNextMove(int playerMove){
        currentState.updateState(playerMove, State.PLAYER_TURN);
        turns+=2;
        level = Math.min(level , maxTurns - turns);
        currentState.setEvaluationState(new EvaluationState());
        EvaluationState move = minimax.Decision(currentState, level);
        currentState.updateState(move.getFromColumn(), State.COMPUTER_TURN);
        return move.getFromColumn();
    }

    public int getFirstMove(){
        turns++;
        currentState.updateState(State.COLUMNS_COUNT / 2, State.COMPUTER_TURN);
        return State.COLUMNS_COUNT / 2;
    }
    


}
