package Frontend.ShowTree;

import javafx.scene.canvas.GraphicsContext;

public interface Shape {
    public void draw(GraphicsContext ctx);
    public String getShapeKind();
    public int getId();
    public void setCoordinates(double x , double y);
    public int getParentId();
    public double getY_axis();
    public double getX_axis();
}
