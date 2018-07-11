package Tetris.Object;

/**
 * Class Stack maintains the landed blocks of the game.
 * It also can determine if a Piece is colliding with occupied coordinates.
 */
public class Stack extends Matrix {
    /**
     * Constructor to build a stack of given dimension
     *
     * @param columns int
     * @param rows int
     */
    public Stack(int columns, int rows) {
        super(columns, rows);
    }

    /**
     * Add a Piece to the Stack
     *
     * @param piece Piece
     */
    public void add(Piece piece) {
        for (int y = 0; y < piece.r; y++) {
            for (int x = 0; x < piece.c; x++) {
                if (0 != piece.get(x, y)) set(x + piece.x, y + piece.y, piece.get(x, y));
            }
        }
    }

    /**
     * Collision detection for a whole Piece
     *
     * @param piece Piece
     * @return boolean
     */
    public boolean collides(Piece piece) {
        for (int y = 0; y < piece.r; y++) {
            for (int x = 0; x < piece.c; x++) {
                if (1 < piece.get(x, y) && collides(x + piece.x, y + piece.y)) return true;
            }
        }

        return false;
    }

    /**
     * Removes a complete line from the Stack
     *
     * @return boolean
     */
    public boolean compact() {
        for (int y = r - 1; y >= 0; y--) {
            if (!column(y).contains("0")) {
                StringBuilder s = new StringBuilder(column());
                for (int i = 0; i < y; i++) s.append(column(i));
                for (int i = y + 1; i < r; i++) s.append(column(i));
                parseString(s.toString());
                return true;
            }
        }

        return false;
    }

    /**
     * Collision detection for a single coordinate
     *
     * @param x int
     * @param y int
     * @return boolean
     */
    private boolean collides(int x, int y) {
        // Collision on x axis
        if (0 > x || c <= x) return true;

        // Collision on y axis
        if (0 > y || r <= y) return true;

        // Collision with block in the matrix
        return 0 != get(x, y);
    }

    /**
     * Generates an empty column for being injected into the Matrix
     *
     * @return String
     */
    private String column() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < c; i++) s.append("0");
        return s.toString();
    }

    /**
     * Returns a certain column of the Matrix
     *
     * @param y int
     * @return String
     */
    private String column(int y) {
        StringBuilder s = new StringBuilder();
        for (int i = c * y; i < c * y + c; i++) s.append(d[i]);
        return s.toString();
    }
}
