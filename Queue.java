package Tetris.Object;

import java.util.LinkedList;

/**
 * Class Queue maintains a LinkedList of Pieces.
 */
public class Queue extends LinkedList<Piece> {
    /**
     * Construct a Queue of a given number of Pieces
     *
     * @param length int
     */
    public Queue(int length) {
        for (int i = 0; i < length; i++) add(new Piece());
    }

    /**
     * Add a new piece, remove and return the first
     *
     * @return Piece
     */
    public Piece next() {
        add(new Piece());
        return pollFirst();
    }
}
