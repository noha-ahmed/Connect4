package Frontend;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GameHelper {

   public Controller controller;
   public  void setController(Controller c){
       controller=c;
   }

    public   boolean gameEnded(int column, int row, boolean redMove) {
        List<Point2D> vertical = IntStream.rangeClosed(row - 3, row + 3)
                .mapToObj(r -> new Point2D(column, r))
                .collect(Collectors.toList());

        List<Point2D> horizontal = IntStream.rangeClosed(column - 3, column + 3)
                .mapToObj(c -> new Point2D(c, row))
                .collect(Collectors.toList());

        Point2D topLeft = new Point2D(column - 3, row - 3);
        List<Point2D> diagonal1 = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> topLeft.add(i, i))
                .collect(Collectors.toList());

        Point2D botLeft = new Point2D(column - 3, row + 3);
        List<Point2D> diagonal2 = IntStream.rangeClosed(0, 6)
                .mapToObj(i -> botLeft.add(i, -i))
                .collect(Collectors.toList());

        return checkRange(vertical, redMove) || checkRange(horizontal, redMove)
                || checkRange(diagonal1, redMove) || checkRange(diagonal2, redMove);
    }

    private boolean checkRange(List<Point2D> points, boolean redMove) {
        int chain = 0;

        for (Point2D p : points) {
            int column = (int) p.getX();
            int row = (int) p.getY();

            Controller.Disc disc = controller.getDisc(column, row).orElse(new Controller.Disc(!redMove));
            if (disc.getColor() == redMove) {
                chain++;
                if (chain == 4) {
                    return true;
                }
            } else {
                chain = 0;
            }
        }

        return false;
    }

    public void gameOver(boolean redMove) {
        String winningMessage = "The Winner is the " + (redMove ? "Red" : "Yellow") + " player";
        //System.out.println(winningMessage);
        controller.textWinnerMessage.setText(winningMessage);
        controller.textWinnerMessage.setVisible(true);
        if (redMove)
            controller.textWinnerMessage.setFill(Color.RED);
        else
            controller.textWinnerMessage.setFill(Color.valueOf("#ccdd16"));
        controller.disablePane();
    }
}
