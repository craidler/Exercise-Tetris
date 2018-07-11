package Tetris.Object;

import java.util.Arrays;

/**
 * Matrix class maintaining an int array
 */
class Matrix {
    int c, r;
    int[] d;

    /**
     * Constructor for a Matrix with given dimensions
     *
     * @param columns int
     * @param rows    int
     */
    Matrix(int columns, int rows) {
        c = columns;
        r = rows;

        Arrays.fill(d = new int[c * r], 0);
    }

    /**
     * Set a given coordinate of the Matrix to a given value
     *
     * @param x int
     * @param y int
     * @param v int
     */
    void set(int x, int y, int v) {
        d[y * c + x] = v;
    }

    /**
     * Set a given index of the Matrix to a given value
     *
     * @param i int
     * @param v int
     */
    void set(int i, int v) {
        d[i] = v;
    }

    /**
     * Retrieves the value of a given coordinate in the Matrix
     *
     * @param x int
     * @param y int
     * @return int
     */
    int get(int x, int y) {
        return d[y * c + x];
    }

    /**
     * Retrieves the value of a given index in the Matrix
     *
     * @param i int
     * @return int
     */
    int get(int i) {
        return d[i];
    }

    /**
     * Returns the overall length of the matrix
     *
     * @return int
     */
    int length() {
        return d.length;
    }

    /**
     * Fills the Matrix by a given String
     * @param s String
     */
    void parseString(String s) {
        for (int i = 0; i < s.length(); i++) set(i, s.charAt(i) - '0');
    }
}
