package Frontend.ShowTree.Shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MinShape extends MinMaxShape {
    private int radius = 20;
  
    public MinShape(int parentId, int id, int value) {
        this.parentId = parentId;
        this.id = id; 
        this.value = value;
    }

    
    @Override
    public void draw(GraphicsContext ctx) {
        ctx.setStroke(Color.RED.brighter().brighter().brighter().brighter().brighter().brighter());
        ctx.setFill(Color.RED.brighter().brighter().brighter().brighter().brighter());
        ctx.strokeOval(this.x_axis - this.radius , this.y_axis , this.radius*2 , this.radius*2);
        ctx.fillOval(this.x_axis - this.radius , this.y_axis, this.radius*2 , this.radius*2 );
        ctx.setFill(Color.BLACK.darker().darker().darker());
        ctx.fillText("" + this.value , this.x_axis - 4, this.y_axis + 25);
    }


}
