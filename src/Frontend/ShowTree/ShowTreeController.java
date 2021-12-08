package Frontend.ShowTree;


import Backend.EvaluationState;
import Frontend.ShowTree.Shapes.ArrowShape;
import Frontend.ShowTree.Shapes.MaxShape;
import Frontend.ShowTree.Shapes.MinMaxShape;
import Frontend.ShowTree.Shapes.MinShape;
import Frontend.ShowTree.Shapes.Shape;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ShowTreeController implements Initializable{
    @FXML
    public Canvas canvas;

    private HashMap<Integer,Shape> shapesMap;
    private GraphicsContext ctx;
    private int shapeWidth = 40;
    private double spaceWidth = 100;
    private int levelDifference = 200;
    private double middleX;
    private int globalId = 0;


    public void setTreeRoot(EvaluationState rootState){
        System.out.println("set tree root");
        constructTree(rootState);
        drawTree();
    }

    public void constructTree( EvaluationState evalState ){
        LinkedList<EvaluationState> parentStates = new LinkedList<>();
        LinkedList<Integer> parentIds = new LinkedList<>();
        LinkedList<EvaluationState> childStates = new LinkedList<>();
        LinkedList<Integer> childIds = new LinkedList<>();
        shapesMap = new HashMap<>();
        MinMaxShape shape = new MaxShape(0, globalId,evalState.getEvaluationValue());
        shape.setCoordinates(middleX, 0);
        shapesMap.put(shape.getId(), shape);
        parentStates.add(evalState);
        parentIds.add(shape.getId());
        EvaluationState state;
        int parentId;
        boolean isEven = false;
        while( !parentStates.isEmpty() ){
            System.out.println("new level");
            isEven = !isEven;
            while( !parentStates.isEmpty() ){
                state = parentStates.pop();
                parentId = parentIds.pop();
                for( EvaluationState child : state.getChildren() ){
                    globalId++;
                    childStates.add(child);
                    if(isEven){
                        //to be min
                        shape = new MinShape(parentId, globalId, child.getEvaluationValue());
                    }
                    else{
                        shape = new MaxShape(parentId, globalId, child.getEvaluationValue());
                    } 
                    childIds.add(globalId);
                    shapesMap.put(globalId, shape );
                }
                 
            }
            constructShapes(childStates , childIds);
            parentStates = childStates;
            parentIds = childIds;
            childStates = new LinkedList<>();
            childIds = new LinkedList<>();
            System.out.println(parentIds.size());

        }
        
    }

    public void constructShapes( LinkedList<EvaluationState> states , LinkedList<Integer> ids){
        int nodesCount = ids.size();
        double levelWidth = nodesCount*this.shapeWidth + (nodesCount - 1)*this.spaceWidth; 
        double x = this.middleX - (levelWidth/2);
        
        double increment = this.shapeWidth + this.spaceWidth;
        this.spaceWidth = this.spaceWidth/5;
        Iterator<EvaluationState> statesIterator = states.iterator();
        Iterator<Integer> idsIterator = ids.iterator();
        while( statesIterator.hasNext() ){
            EvaluationState state = (EvaluationState)statesIterator.next();
            MinMaxShape shape = (MinMaxShape)shapesMap.get(idsIterator.next());
            MinMaxShape parentShape = (MinMaxShape)shapesMap.get(shape.getParentId());
            shape.setCoordinates(x, parentShape.getY_axis() + this.levelDifference);
            Shape arrowShape = new ArrowShape(state.getFromColumn(), parentShape, shape);
            globalId++;
            shapesMap.put(globalId, arrowShape);
            x += increment;
        }
    }

    

    public void drawTree() {
        ctx=canvas.getGraphicsContext2D();
        ctx.setFill(Color.TRANSPARENT);
        ctx.clearRect(0, 0, canvas.getWidth() - 1, canvas.getHeight() - 1);
        for( Shape shape : shapesMap.values() ){
            shape.draw(ctx);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       this.middleX = this.canvas.getWidth()/2;
    }
}
