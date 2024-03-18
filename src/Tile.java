/**
 * Class to represent a particular tile in the chess board
 * This might be empty tile or occupied tile
 */
public abstract class Tile {
    int tileCoordinate;

    Tile (int tileCoordinate){
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
     */
    public static final class EmptyTile extends Tile{
        EmptyTile (int coordinate){
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
        Piece pieceOnTile;
        OccupiedTile(int coordinate, Piece piece){
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
