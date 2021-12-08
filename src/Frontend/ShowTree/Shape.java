package Frontend.ShowTree;

import javafx.scene.canvas.GraphicsContext;

public interface Shape {
    public void draw(GraphicsContext ctx);

    public String getShapeKind();

}
