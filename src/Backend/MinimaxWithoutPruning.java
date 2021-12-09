package Backend;

public class MinimaxWithoutPruning implements IMinimax{
    private int nodesExpanded = 0;
    public EvaluationState maximize(State state, int level){
        nodesExpanded++;
        if(level==0){
            int eval = state.evaluateState();
            state.getEvaluationState().setEvaluationValue(eval);
            return state.getEvaluationState();
        }
        state.getEvaluationState().setEvaluationValue(Integer.MIN_VALUE);
        EvaluationState maxChild = new EvaluationState();
        for(int i=0; i<7; i++){
            State child = state.getChild(i , State.COMPUTER_TURN);
            if(child!=null){
                child.getEvaluationState().setEvaluationValue(minimize(child,level-1).getEvaluationValue());
               // state.getEvaluationState().addChild(child.getEvaluationState());
                if(child.getEvaluationState().getEvaluationValue()>state.getEvaluationState().getEvaluationValue()){
                    state.getEvaluationState().setEvaluationValue(child.getEvaluationState().getEvaluationValue());
                    maxChild=child.getEvaluationState();
                }
            }
        }
        return maxChild;
    }

    public EvaluationState minimize(State state, int level){
        nodesExpanded++;
        if(level==0){
            int eval = state.evaluateState();
            state.getEvaluationState().setEvaluationValue(eval);
            return state.getEvaluationState();
        }
        state.getEvaluationState().setEvaluationValue(Integer.MAX_VALUE);
        EvaluationState minChild = new EvaluationState();
        for(int i=0; i<7; i++){
            State child = state.getChild(i , State.PLAYER_TURN);
            if(child!=null){
                child.getEvaluationState().setEvaluationValue(maximize(child,level-1).getEvaluationValue());
               // state.getEvaluationState().addChild(child.getEvaluationState());
                if(child.getEvaluationState().getEvaluationValue()<state.getEvaluationState().getEvaluationValue()){
                    state.getEvaluationState().setEvaluationValue(child.getEvaluationState().getEvaluationValue());
                    minChild = child.getEvaluationState();
                }
            }
        }
        return minChild;
    }

    public int getNodesExpanded(){
        return this.nodesExpanded;
    }

    @Override
    public EvaluationState Decision(State initial, int level) {
        EvaluationState child = maximize(initial, level);
        initial.getEvaluationState().setEvaluationValue(child.getEvaluationValue());
        return child;
    }
}
