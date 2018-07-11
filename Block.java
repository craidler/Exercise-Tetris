package Tetris.Object;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

class Block extends ImageView {
    private static Image[] i = {
            new Image("Tetris/Image/Block-Transparent.png"),
            new Image("Tetris/Image/Block-Wall.png"),
            new Image("Tetris/Image/Block-Blue.png"),   // 2
            new Image("Tetris/Image/Block-Cyan.png"),   // 3
            new Image("Tetris/Image/Block-Green.png"),  // 4
            new Image("Tetris/Image/Block-Orange.png"), // 5
            new Image("Tetris/Image/Block-Purple.png"), // 6
            new Image("Tetris/Image/Block-Red.png"),    // 7
            new Image("Tetris/Image/Block-Yellow.png")  // 8
    };

    private int r;

    Block(AnchorPane pane, double x, double y, boolean wall) {
        pane.getChildren().add(this);
        setImage(i[wall ? 1 : 0]);
        relocate(x, y);
        r = wall ? 1 : 0;
    }

    void colorize(int color) {
        if (color > 1 && i[color] != getImage()) setImage(i[color]);
    }

    void reset() {
        if (i[r] != getImage()) setImage(i[r]);
    }
}
