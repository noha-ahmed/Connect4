package Frontend.ShowTree;


import com.sun.javafx.UnmodifiableArrayList;

import Backend.EvaluationState;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ShowTreeController {
    public String shapeKind;
    public Color color;

    public int globalId = 0;


    public double prevLocationX;
    public double prevLocationY;


    //flags
    public boolean firstUsed = false;
    public boolean select = false;

    @FXML
    private Button edit;

    @FXML
    private Canvas canvas;
    private GraphicsContext ctx;
    @FXML
    private ListView<String>initialProducts;
    @FXML
    private TextField number;
    public  ArrayList<Shape> shapesList=new ArrayList<>();
    private static final int Limit = 7;
    private EvaluationState rootState;


    public void setTreeRoot(EvaluationState rootState){
        this.rootState = rootState;
    }
    public void makeShapeList(){
        ArrayList list=new ArrayList();
        //   list=load from back end
        for(int i=0;i<list.size();i++){

        }
    }

    public void reDraw() {
        ctx.setFill(Color.TRANSPARENT);
        ctx.clearRect(0, 0, canvas.getWidth() - 1, canvas.getHeight() - 1);
        //ctx.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
       ;
        for (int i = 0; i < shapesList.size(); i++) {
            Shape shape = shapesList.get(i);
            shape.draw(ctx);
        }
    }








    /////////////////////////////////////////////////////////////////////////////////////////////


}
