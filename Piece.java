package Tetris.Object;

import java.util.concurrent.ThreadLocalRandom;

/**
 *  Class Piece is a Matrix of 4 by 4.
 */
public class Piece extends Matrix {
    int x = 0, y = 0;

    private static String[] p = {
            "0060006006600000", // J
            "0200020002200000", // L
            "0700070007000700", // I
            "0880088000000000", // O
            "3000330003000000", // S
            "0400440040000000", // Z,
            "5550050000000000", // T
    };

    /**
     * Constructor to generate a random piece
     */
    Piece() {
        super(4, 4);
        String s = p[ThreadLocalRandom.current().nextInt(0, p.length)];
        for (int i = 0; i < s.length(); i++) set(Math.floorMod(i, c), Math.floorDiv(i, c), s.charAt(i) - '0');
    }

    /**
     * Constructor to clone a piece
     *
     * @param piece Piece
     */
    public Piece(Piece piece) {
        super(piece.c, piece.r);
        for (int i = 0; i < piece.length(); i++) this.set(i, piece.get(i));
        x = piece.x;
        y = piece.y;
    }

    /**
     * Center the Piece on a Stack
     *
     * @param stack Stack
     */
    public void center(Stack stack) {
        x = Math.floorDiv(stack.c, 2) - 1;
    }

    /**
     * Move the piece by a given offset
     *
     * @param xo int
     * @param yo int
     */
    public void move(int xo, int yo) {
        x += xo;
        y += yo;
    }

    /**
     * Rotate the piece in a direction
     *
     * @param cw boolean
     */
    public void rotate(boolean cw) {
        int n = c;

        for (int i = 0; i < (cw ? 1 : 3); i++) {
            for (int x = 0; x < n / 2; x++) {
                for (int y = x; y < n - x - 1; y++) {
                    int t = get(x, y);
                    set(x, y, get(y, n - 1 - x));
                    set(y, n - 1 - x, get(n - 1 - x, n - 1 - y));
                    set(n - 1 - x, n - 1 - y, get(n - 1 - y, x));
                    set(n - 1 - y, x, t);
                }
            }
        }
    }
}
