package Backend;

public class MinimaxWithoutPruning implements IMinimax{

    public static EvaluationState Maximize(State state, int k){
        if(k==0){
            state.getEvaluationState().setEvaluationValue(0);
            return state.getEvaluationState();
        }
        for(int i=0; i<7; i++){
            State child = state.getChild(i);
            child.setEvaluationState(Minimize(child,k-1));
            state.getEvaluationState().addChild(child.getEvaluationState());
            if(child.getEvaluationState().getEvaluationValue()>state.getEvaluationState().getEvaluationValue()){
                state.getEvaluationState().setEvaluationValue(child.getEvaluationState().getEvaluationValue());
            }
        }
        return state.getEvaluationState();
    }

    public static EvaluationState Minimize(State state, int k){
        if(k==0){
            state.getEvaluationState().setEvaluationValue(0);
            return state.getEvaluationState();
        }
        for(int i=0; i<7; i++){
            State child = state.getChild(i);
            child.setEvaluationState(Maximize(child,k-1));
            state.getEvaluationState().addChild(child.getEvaluationState());
            if(child.getEvaluationState().getEvaluationValue()<state.getEvaluationState().getEvaluationValue()){
                state.getEvaluationState().setEvaluationValue(child.getEvaluationState().getEvaluationValue());
            }
        }
        return state.getEvaluationState();
    }

    @Override
    public EvaluationState Decision(State initial, int k) {
        return Maximize(initial,k);
    }
}
