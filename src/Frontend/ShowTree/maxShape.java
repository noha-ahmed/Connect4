package Frontend.ShowTree;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class MaxShape implements Shape {
    private final String shapeKind = "rectangle";
    private double x_axis;
    private double y_axis;
    private int id;
    private int parentId;
    private int value = 0;
    private int fromColumn;
  
    public MaxShape(int parentId, int id, int value) {
        this.parentId = parentId;
        this.id = id; 
        this.value = value;
    }

    public void setCoordinates( double x , double y){
        this.x_axis = x;
        this.y_axis = y;
    }

    public String getShapeKind() {
        return shapeKind;
    }

    public int getParentId() {
        return parentId;
    }

    public int getId() {
        return id;
    }

    public double getY_axis() {
        return y_axis;
    }

    public void setY_axis(double y_axis) {
        this.y_axis = y_axis;
    }

    public double getX_axis() {
        return x_axis;
    }

    public void setX_axis(double x_axis) {
        this.x_axis = x_axis;
    }


    @Override
    public void draw(GraphicsContext ctx) {
        ctx.setFill(Color.YELLOW.darker());
        ctx.setStroke(Color.YELLOW.darker().darker().darker());
        ctx.fillRect(this.x_axis - 40, this.y_axis + 20, 80, 40);
        ctx.stroke();
        ctx.setFill(Color.WHITE.brighter().brighter().brighter().brighter());
        ctx.fillText( ""+ this.value, this.x_axis - 2 , this.y_axis + 45);

    }





}
