package Frontend.ShowTree.Shapes;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MaxShape extends MinMaxShape {
    private int length = 40;
  
    public MaxShape(int parentId, int id, int value) {
        this.parentId = parentId;
        this.id = id; 
        this.value = value;
    }


    @Override
    public void draw(GraphicsContext ctx) {
        ctx.setFill(Color.YELLOW.darker());
        ctx.setStroke(Color.YELLOW.darker().darker());
        ctx.fillRect(this.x_axis - this.length/2, this.y_axis, this.length , this.length );
        ctx.stroke();
        ctx.setFill(Color.BLACK.darker().darker().darker());
        ctx.fillText( ""+ this.value, this.x_axis - 4 , this.y_axis + 25);
    }





}
