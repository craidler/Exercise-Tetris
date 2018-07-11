package Tetris.Object;

import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Class Area is bound to an AnchorPane and consists of multiple Blocks.
 * It can render a Stack, a Piece or a LinkedList of Pieces.
 */
public class Area {
    private ArrayList<Block> blocks = new ArrayList<>();
    private int c;

    /**
     * Construct a new Area by given parameters
     *
     * @param pane AnchorPane
     * @param columns int
     * @param rows int
     * @param size int
     * @param wall boolean
     */
    public Area(AnchorPane pane, int columns, int rows, int size, boolean wall) {
        c = columns;

        for (int i = 0; i < c * rows; i++) blocks.add(new Block(pane,Math.floorMod(i, c) * size, Math.floorDiv(i, c) * size, wall));
        pane.resize(c * size, rows * size);
    }

    /**
     * Renders a Stack and a Piece
     *
     * @param stack Stack
     * @param piece Piece
     */
    public void render(Stack stack, Piece piece) {
        reset();
        render(stack);
        render(piece);
    }

    /**
     * Renders a LinkedList of Pieces
     *
     * @param queue LinkedList
     */
    public void render(LinkedList<Piece> queue) {
        reset();
    }

    /**
     * Renders a Stack
     *
     * @param stack Stack
     */
    private void render(Stack stack) {
        for (int i = 0; i < stack.length(); i++) blocks.get(i).colorize(stack.get(i));
    }

    /**
     * Retrieve a Block by coordinates
     *
     * @param x int
     * @param y int
     * @return Block
     */
    private Block get(int x, int y) {
        return blocks.get(y * c + x);
    }

    /**
     * Renders a Piece
     *
     * @param piece Piece
     */
    private void render(Piece piece) {
        for (int y = 0, c; y < piece.r; y++) {
            for (int x = 0; x < piece.c; x++) {
                c = piece.get(x, y);
                if (1 < c) get(piece.x + x, piece.y + y).colorize(c);
            }
        }
    }

    /**
     * Resets all Block
     */
    private void reset() {
        for (Block b : blocks) b.reset();
    }
}
