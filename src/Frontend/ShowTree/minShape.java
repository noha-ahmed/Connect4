package Frontend.ShowTree;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class MinShape implements Shape {
    private final String shapeKind = "MinShape";
    private double x_axis;
    private double y_axis;
    private int id;
    private int parentId;
    private int value = 0;
    private int halfSideLength = 20;
  
    public MinShape(int parentId, int id, int value) {
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
        ctx.beginPath();
        ctx.moveTo(x_axis - halfSideLength, y_axis);
        ctx.lineTo(x_axis + halfSideLength, y_axis);
        ctx.lineTo(x_axis, y_axis + halfSideLength*2);
        ctx.lineTo(x_axis -halfSideLength, y_axis);
        ctx.closePath();
        ctx.setStroke(Color.RED.brighter().brighter().brighter());
        ctx.fill();
        ctx.stroke();
        ctx.setFill(Color.WHITE);
        ctx.fillText("" + this.value, this.x_axis - 2, this.y_axis + halfSideLength );

    }


}
