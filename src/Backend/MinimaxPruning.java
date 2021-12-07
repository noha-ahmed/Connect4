package Backend;

public class MinimaxPruning implements IMinimax{
    @Override
    public EvaluationState Decision(State initial, int level) {
        EvaluationState child = maximize(initial, level, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return child;
    }

    private EvaluationState maximize(State state, int level, int alpha, int beta){
        if(level == 0){
            // get evaluation value of leaf
            int evaluation = state.evaluateState();
            state.getEvaluationState().setEvaluationValue(evaluation);
            return state.getEvaluationState();
        }
        EvaluationState maxChild = new EvaluationState();
        maxChild.setEvaluationValue(Integer.MIN_VALUE);
        for(int i = 0; i < 7; i++){
            State child = state.getChild(i , State.COMPUTER_TURN);
            if(child != null){
                state.getEvaluationState().addChild(child.getEvaluationState());
                EvaluationState temp = minimize(child, level - 1, alpha, beta);
                child.getEvaluationState().setEvaluationValue(temp.getEvaluationValue());
                if(temp.getEvaluationValue() > maxChild.getEvaluationValue())
                    maxChild = temp;
                if(maxChild.getEvaluationValue() >= beta)
                    break;
                if(maxChild.getEvaluationValue() > alpha)
                    alpha = maxChild.getEvaluationValue();
            }
        }
        return maxChild;
    }

    private EvaluationState minimize(State state, int level, int alpha, int beta){
        if( level == 0 ){
            int evaluation = state.evaluateState();
            state.getEvaluationState().setEvaluationValue(evaluation);
            return state.getEvaluationState();
        }
        EvaluationState minChild = new EvaluationState();
        minChild.setEvaluationValue(Integer.MAX_VALUE);
        for( int i = 0 ; i < 7 ; i++ ){
            State child = state.getChild(i , State.PLAYER_TURN);
            if( child != null ){
                int evalValue = maximize(child, level -1 , alpha, beta).getEvaluationValue();
                child.getEvaluationState().setEvaluationValue(evalValue);
                if( evalValue < minChild.getEvaluationValue() ){
                    minChild = child.getEvaluationState();
                }
                if(minChild.getEvaluationValue() <= alpha)
                    break;
                if(minChild.getEvaluationValue() < beta)
                    alpha = minChild.getEvaluationValue();
            }
        }
        return minChild;
    }
}
