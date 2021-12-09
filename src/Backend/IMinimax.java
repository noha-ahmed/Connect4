package Backend;

public interface IMinimax {
    EvaluationState Decision(State initial, int level);
    int getNodesExpanded();
}
