package com.chess.engine.board;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to represent a particular tile in the chess board
 * This might be empty tile or occupied tile
 * We cannot instantiate this class as it is abstract.
 * Making this class immutable to save need for thread synchronization and efficiency of code.
 */
public abstract class Tile {
    protected final int tileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    /**
     * Map to create all possible tiles. This method is made immutable using Google's Guava dependency.
     * @return
     */
    private static Map<Integer,EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i=0; i<BoardUtils.NUM_TILES; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }
    private Tile (final int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    /**
     * Method to represent boolean which says whether a tile is occupied or not.
     */
    public abstract boolean isTileOccupied();

    /**
     * To represent what the piece is in that tile.
     * @return
     */
    public abstract Piece getPiece();

    /**
     * Represent an empty tile
     * Static because this class is on their own and not under Tile
     */
    public static final class EmptyTile extends Tile{
        private EmptyTile (final int coordinate){
            super(coordinate);
        }
        @Override
        public boolean isTileOccupied() {
            return false;
        }
        @Override
        public  Piece getPiece(){
            return null;
        }
    }

    /**
     * Represent the occupied tiles
     */
    public static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;
        private OccupiedTile(int coordinate, final Piece piece){
            super(coordinate);
            this.pieceOnTile = piece;
        }

        @Override
        public boolean isTileOccupied(){
            return true;
        }

        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }
    }
}
