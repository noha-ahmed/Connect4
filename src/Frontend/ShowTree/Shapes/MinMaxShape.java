package Frontend.ShowTree.Shapes;

public abstract class MinMaxShape implements Shape{
    protected double x_axis;
    protected double y_axis;
    protected int id;
    protected int parentId;
    protected int value = 0;

    public void setCoordinates( double x , double y){
        this.x_axis = x;
        this.y_axis = y;
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


}