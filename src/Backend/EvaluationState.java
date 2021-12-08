package Backend;

import java.util.ArrayList;
import java.util.Iterator;



public class EvaluationState {
    private int evaluationValue;
    private int fromColumn;
    private ArrayList<EvaluationState> children = new ArrayList<>();

    public EvaluationState(){
        this.children = new ArrayList<>();
    }

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

    public void addChild(EvaluationState child){this.children.add(child);}

    public String toString() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "" , 1);
        return buffer.toString();
    }
    public void printTree(){
        System.out.println(this.toString());
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix , int level) {
        buffer.append(prefix);
        buffer.append("(L" + level + ") Move : " + (this.fromColumn + 1) + ", Value : " + this.evaluationValue );
        buffer.append('\n');
        for (Iterator<EvaluationState> it = children.iterator(); it.hasNext();) {
            EvaluationState next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   " , level + 1);
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ",level + 1);
            }
        }
    }
}
