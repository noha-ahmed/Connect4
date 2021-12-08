package Backend;

public interface IComputerAgent {
    public int getNextMove(int playerMove);
    public int getFirstMove();
    public EvaluationState getEvaluationState();
    public void restart();
}
