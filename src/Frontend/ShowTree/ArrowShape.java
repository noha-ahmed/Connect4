package Frontend.ShowTree;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ArrowShape implements Shape {
    private double x1 = 0;
    private double y1 = 0;
    private double x2 = 0;
    private double y2 = 0;
    private String kind = "arrow";
    private int id;
    private int value;

    public ArrowShape(int id ,int value,Shape shape1, Shape shape2 ) {
        this.value = value;
        this.id =id;
        if (shape1.getShapeKind().equals("circle")) {
            this.x1 = shape1.getX_axis();
            this.y1 = shape1.getY_axis()+60;
            this.x2 = shape2.getX_axis();
            this.y2 = shape2.getY_axis()+20;
        } else if (shape1.getShapeKind().equals("rectangle")) {
            this.x2 = shape2.getX_axis();
            this.y2 = shape2.getY_axis()+20;
            this.x1 = shape1.getX_axis();
            this.y1 = shape1.getY_axis()+60;
        }
        
    } 
    

    @Override
    public void draw(GraphicsContext ctx) { 
        ctx.setStroke(Color.BLACK);
        ctx.stroke();
        ctx.setFill(Color.BLACK);
        ctx.beginPath();
        ctx.moveTo(x1, y1);
        ctx.lineTo(x2, y2 - 5);
        ctx.setStroke(Color.BLACK);
        ctx.stroke();
        ctx.setFill(Color.BLACK);
        ctx.beginPath();
        ctx.moveTo(x2 -5, y2 - 5);
        ctx.lineTo(x2 + 5, y2 -5 );
        ctx.lineTo(x2, y2);
        ctx.lineTo(x2 -5, y2 - 5);
        ctx.closePath();
        ctx.setStroke(Color.BLACK);
        ctx.fill();
        ctx.stroke();
    }



    public String getShapeKind() {
        return "arrow";
    }



    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public void setCoordinates(double x, double y) {
        // TODO Auto-generated method stub
        
    }


    @Override
    public int getParentId() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public double getY_axis() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public double getX_axis() {
        // TODO Auto-generated method stub
        return 0;
    }
}
