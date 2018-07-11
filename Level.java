package Tetris.Controller;

import Tetris.Object.Area;
import Tetris.Object.Piece;
import Tetris.Object.Queue;
import Tetris.Object.Stack;
import javafx.animation.AnimationTimer;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Level implements Initializable {
    /**
     * Injected instances
     */
    public AnchorPane pane_play;
    public AnchorPane pane_queue;
    public Label label_point;
    public Label label_level;
    public Label label_lines;
    public Label label_speed;
    public Label label_clear;

    /**
     * Animation timer to compact the stack
     */
    private AnimationTimer timer_c = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (0 == tick_c++ % 5) {
                if (stack.compact()) {
                    lines_c++;
                    lines_t++;
                    return;
                }

                timer_c.stop();
                point += level * lines_c * 10;
                lines_c = 0;

                if (0 < lines_t && 0 == lines_t % 5) {
                    level();
                }
            }
        }

        public void start() {
            tick_c = 0;
            super.start();
        }
    };

    /**
     * Animation timer to drop the actual piece
     */
    private AnimationTimer timer_d = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (0 < tick_d++ && 0 == tick_d % speed && run && !move(0, 1)) {
                stack.add(piece_a);
                timer_c.start();
                spawn();
            }
        }

        public void start() {
            tick_d = 0;
            super.start();
        }
    };

    /**
     * Animation timer to render stack, piece, preview and labels
     */
    private AnimationTimer timer_r = new AnimationTimer() {
        @Override
        public void handle(long now) {
            render();
        }
    };


    /**
     * Generated instances
     */
    private Queue queue = new Queue(3);
    private Stack stack;
    private Piece piece_p;
    private Piece piece_a;
    private Area area_p;
    private Area area_q;

    /**
     * Primitives
     */
    private boolean run = false;
    private int tick_c = 0;
    private int tick_d = 0;
    private int lines_c = 0;
    private int lines_t = 0;
    private int level = 1;
    private int speed = 20;
    private int point = 0;

    public void initialize(URL location, ResourceBundle resources) {
        area_p = new Area(pane_play, 11, 20, 20, true);
        area_q = new Area(pane_queue, 4, 20, 20, true);
        stack = new Stack(11, 20);

        timer_r.start();
        timer_d.start();
    }

    /**
     * Attaches key bindings to the Scene
     *
     * @param scene Scene
     * @return Level fluent
     */
    public Level set(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    move(-1, 0);
                    break;

                case RIGHT:
                    move(1, 0);
                    break;

                case UP:
                    rotate(false);
                    break;

                case DOWN:
                    rotate(true);
                    break;

                case SPACE:
                    while (move(0, 1)) ;
                    break;

                case ESCAPE:
                    pause();
                    break;

                default:
            }
        });

        return this;
    }

    public void start() {
        spawn();
    }

    private void level() {
        if (2 < speed) speed--;
        level++;
    }

    private boolean move(int xo, int yo) {
        if (!run) return false;

        piece_p = new Piece(piece_a);
        piece_p.move(xo, yo);

        if (stack.collides(piece_p)) return false;

        piece_a.move(xo, yo);
        return true;
    }

    private void pause() {
        run = !run;
    }

    private void render() {
        area_q.render(queue);
        area_p.render(stack, piece_a);

        label_level.setText("Level: " + level);
        label_speed.setText("Speed: " + speed);
        label_point.setText("Point: " + point);
        label_lines.setText("Lines: " + lines_t);
        label_clear.setText("Lines: " + lines_c);
    }

    private void rotate(boolean cw) {
        if (!run) return;

        piece_p = new Piece(piece_a);
        piece_p.rotate(cw);

        if (stack.collides(piece_p)) return;
        piece_a.rotate(cw);
    }

    private void spawn() {
        piece_a = queue.next();
        piece_a.center(stack);

        if (stack.collides(piece_a)) {
            timer_d.stop();
            run = false;
            return;
        }

        run = true;
    }
}
