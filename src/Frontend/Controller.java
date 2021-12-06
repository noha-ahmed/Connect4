package Frontend;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //constants
    private static final int TILE_SIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;
    private static final int COMPUTER = 1;
    public static final int PLAYER = 2;
    public GameHelper gameHelper;
    //used components
    private Disc[][] grid = new Disc[COLUMNS][ROWS];
    @FXML
    public Button buttonPlayerStart ;
    @FXML
    public Button buttonComputerStart ;
    @FXML
    public Pane discRoot = new Pane();
    @FXML
    public Pane connect4Pane = new Pane();
    public static Text textWinnerMessage = new Text();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gameHelper=new GameHelper();
        gameHelper.setController(this);
        connect4Pane.getChildren().add(discRoot);
        Shape gridShape = makeGrid();
        connect4Pane.getChildren().add(gridShape);
        connect4Pane.getChildren().addAll(makeColumns());
    }
    public void handle(ActionEvent event) {
        initializeGame();
        if(event.getSource()==buttonComputerStart){
        placeDisc(new Disc(false), AIConnect4.getAIMove(),false);
        }
    }
    public static class Disc extends Circle {
        private final boolean color;

        public Disc(boolean color) {
            super(TILE_SIZE / 2, color ? Color.RED : Color.YELLOW);
            this.color = color;

            setCenterX(TILE_SIZE / 2);
            setCenterY(TILE_SIZE / 2);
        }
        public boolean getColor(){
            return color;
        }

    }


    public  void disablePane() {
        connect4Pane.setDisable(true);
    }

    public  void enablePane() {
        connect4Pane.setDisable(false);
    }

    private void initializeGame() {
        buttonComputerStart.setVisible(false);
        buttonPlayerStart.setVisible(false);
        textWinnerMessage.setVisible(false);
        grid = new Disc[COLUMNS][ROWS];
//        discRoot.getChildren().clear();
        AIConnect4.initialize_board();
        enablePane();
    }

    private Shape makeGrid() {
        Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);

        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLUMNS; x++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setCenterX(TILE_SIZE / 2);
                circle.setCenterY(TILE_SIZE / 2);
                circle.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
                circle.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 4);
                shape = Shape.subtract(shape, circle);
            }
        }

        Light.Distant light = new Light.Distant();
        light.setAzimuth(30.0);
        light.setElevation(30.0);
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);
        shape.setFill(Color.BLUE);
        shape.setEffect(lighting);

        return shape;
    }

    private List<Rectangle> makeColumns() {
        List<Rectangle> list = new ArrayList<>();

        for (int x = 0; x < COLUMNS; x++) {
            Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
            rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 4);
            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int column = x;
            rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    placeDisc(new Disc(true), column,true);
                    //update the board at the backend
                   AIConnect4.update_board(column, PLAYER);
                }
            });

            list.add(rect);
        }

        return list;
    }

    private void placeDisc(Disc disc, int column,boolean playerTurn) {
        disablePane();
        boolean redMove = disc.getColor();
        int row = ROWS - 1;
        do {
            if (!getDisc(column, row).isPresent())
                break;

            row--;
        } while (row >= 0);

        if (row < 0)
            return;

        grid[column][row] = disc;
        discRoot.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);

        final int currentRow = row;

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(row * (TILE_SIZE + 5) + TILE_SIZE / 4);
        animation.setOnFinished(e -> {
            if (gameHelper.gameEnded(column, currentRow, redMove)) {
                gameHelper.gameOver(redMove);
                return;
            } else if (playerTurn) {

                //if the player played then this is the computer move
                placeDisc(new Disc(false), AIConnect4.getAIMove(),false);
                //next turn is player

            }
            enablePane();
        });
        animation.play();


    }


    public Optional<Disc> getDisc(int column, int row) {
        if (column < 0 || column >= COLUMNS
                || row < 0 || row >= ROWS)
            return Optional.empty();

        return Optional.ofNullable(grid[column][row]);
    }



}
