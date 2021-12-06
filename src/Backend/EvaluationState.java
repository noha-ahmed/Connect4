package Backend;

import java.util.ArrayList;

public class EvaluationState {
    private int evaluationValue;
    private int fromColumn;
    private ArrayList<EvaluationState> children;

    public int getEvaluationValue() {
        return evaluationValue;
    }

    public void setEvaluationValue(int evaluationValue) {
        this.evaluationValue = evaluationValue;
    }

    public int getFromColumn() {
        return fromColumn;
    }

    public void setFromColumn(int fromColumn) {
        this.fromColumn = fromColumn;
    }

    public ArrayList<EvaluationState> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<EvaluationState> children) {
        this.children = children;
    }
}
