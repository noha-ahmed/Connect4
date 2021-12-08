package Frontend.ShowTree.Shapes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ArrowShape implements Shape {
    private double x1 = 0;
    private double y1 = 0;
    private double x2 = 0;
    private double y2 = 0;
    private int value;

    public ArrowShape(int value,MinMaxShape shape1, MinMaxShape shape2 ) {
        this.value = value+1;
            this.x1 = shape1.getX_axis();
            this.y1 = shape1.getY_axis()+40;
            this.x2 = shape2.getX_axis();
            this.y2 = shape2.getY_axis();
       
        
    } 
    
    @Override
    public void draw(GraphicsContext ctx) { 
        ctx.beginPath();
        ctx.moveTo(x1, y1);
        ctx.lineTo(x2, y2 - 5);
        double midX = (x1+x2)/2;
        double midY = (y1+y2)/2;
        ctx.setFill(Color.BLACK.darker().darker().darker());
        ctx.fillText("" + this.value , midX- 8, midY);
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

}
