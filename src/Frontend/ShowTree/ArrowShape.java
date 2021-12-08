package Frontend.ShowTree;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ArrowShape implements Shape {
    private Shape one;
    private Shape two;
    private String kind = "arrow";
    private int value;
    public ArrowShape(int value,Shape one, Shape two) {
        this.one = one;
        this.two = two;
        this.value = value;
    } 

    @Override
    public void draw(GraphicsContext ctx) {
        double x1 = 0;
        double y1 = 0;
        double x2 = 0;
        double y2 = 0;

        // if (one.getShapeKind().equals("circle")) {
        //     x1 = ((MachineShape) one).getX_axis() + 50;
        //     y1 = ((MachineShape) one).getY_axis();
        //     x2 = ((QueueShape) two).getX_axis();
        //     y2 = ((QueueShape) two).getY_axis();
        // } else if (one.getShapeKind().equals("rectangle")) {
        //     x2 = ((MachineShape) two).getX_axis();
        //     y2 = ((MachineShape) two).getY_axis();
        //     x1 = ((QueueShape) one).getX_axis() + 80;
        //     y1 = ((QueueShape) one).getY_axis();
        // }
        ctx.beginPath();
        ctx.moveTo(x1, y1);
        ctx.lineTo(x2, y2);
        ctx.setStroke(Color.BLACK);
        ctx.stroke();
        ctx.setFill(Color.BLACK);
        ctx.beginPath();
        ctx.moveTo(x2, y2 - 10);
        ctx.lineTo(x2 + 10, y2);
        ctx.lineTo(x2, y2 + 10);
        ctx.lineTo(x2, y2 - 10);
        ctx.closePath();
        ctx.setStroke(Color.BLACK);
        ctx.fill();
        ctx.stroke();
    }



    public String getShapeKind() {
        return "arrow";
    }

    public Shape getOne() {
        return one;
    }

    public void setOne(Shape one) {
        this.one = one;
    }

    public Shape getTwo() {
        return two;
    }

    public void setTwo(Shape two) {
        this.two = two;
    }
}
