package Frontend.ShowTree;


import com.sun.javafx.UnmodifiableArrayList;

import Backend.EvaluationState;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class ShowTreeController implements Initializable{
    public String shapeKind;
    public Color color;
    public double prevLocationX;
    public double prevLocationY;
    private HashMap<Integer,Shape> shapesMap;


    //flags
    public boolean firstUsed = false;
    public boolean select = false;

    @FXML
    private Button edit;

    @FXML
    public Canvas canvas;
    private GraphicsContext ctx;
    @FXML
    private ListView<String>initialProducts;
    @FXML
    private TextField number;
    public  ArrayList<Shape> shapesList=new ArrayList<>();
    private static final int Limit = 7;
    private EvaluationState rootState;
    private int shapeWidth = 80;
    private int spaceWidth = 30;
    private int levelDifference = 100;
    private double middleX;
    private int globalId = 0;


    public void setTreeRoot(EvaluationState rootState){
        this.rootState = rootState;
        System.out.println("set tree root");
        constructTree(rootState);
        drawTree();
    }

    public void drawTreeRoot( EvaluationState evalState ){
        ctx.setFill(Color.TRANSPARENT);
        ctx.clearRect(0, 0, canvas.getWidth() - 1, canvas.getHeight() - 1);
        Shape shape = new MaxShape(0, 1, 6);
        shape.setCoordinates(canvas.getWidth()/2, 0 );
        shape.draw(ctx);
    }

    public void constructTree( EvaluationState evalState ){
        LinkedList<EvaluationState> parentStates = new LinkedList<>();
        LinkedList<Integer> parentIds = new LinkedList<>();
        LinkedList<EvaluationState> childStates = new LinkedList<>();
        LinkedList<Integer> childIds = new LinkedList<>();
        shapesMap = new HashMap<>();
        Shape shape = new MaxShape(0, globalId,evalState.getEvaluationValue());
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
        int levelWidth = nodesCount*this.shapeWidth + (nodesCount - 1)*this.spaceWidth; 
        double x = this.middleX - (levelWidth/2);
        int increment = this.shapeWidth + this.spaceWidth;
        Iterator statesIterator = states.iterator();
        Iterator idsIterator = ids.iterator();
        while( statesIterator.hasNext() ){
            EvaluationState state = (EvaluationState)statesIterator.next();
            Shape shape = shapesMap.get(idsIterator.next());
            Shape parentShape = shapesMap.get(shape.getParentId());
            shape.setCoordinates(x, parentShape.getY_axis() + this.levelDifference);
            globalId++;
            Shape arrowShape = new ArrowShape(globalId , state.getFromColumn(), parentShape, shape);
            shapesMap.put(globalId, arrowShape);
            x += increment;
        }
    }

    

    public void drawTree() {
        ctx=canvas.getGraphicsContext2D();
        ctx.setFill(Color.TRANSPARENT);
        ctx.clearRect(0, 0, canvas.getWidth() - 1, canvas.getHeight() - 1);
        //ctx.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

        for( Shape shape : shapesMap.values() ){
            shape.draw(ctx);
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        System.out.println("initialize");
       this.middleX = this.canvas.getWidth()/2;
        
    }








    /////////////////////////////////////////////////////////////////////////////////////////////


}
