package com.chess.engine.board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final boolean[] SECOND_ROW = null;
    public static final boolean[] SEVENTH_ROW = null;
    public static final int NUM_TILES = 64;
    private static final int NUM_TILES_ROW = 8;
    private static boolean[] initColumn(int colNumber) {
        final boolean[] column = new boolean[NUM_TILES];
        do {
            column[colNumber] = true;
            colNumber+= NUM_TILES_ROW;
        } while (colNumber < NUM_TILES);
        return column;
    }
    private BoardUtils(){
        throw new RuntimeException("Cannot Instantiate this BoardUtils class");
    }
    public static boolean isValidTileCoordinate(final int coordinate){
        return coordinate >= 0 && coordinate < 64;
    }
}
