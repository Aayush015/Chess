package com.chess.engine.pieces;
import com.chess.engine.Type;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;

import java.util.List;


/**
 * Represent a particular Piece within a Tile.
 * Each piece type will extend this Piece class to have its abstract characteristics.
 */
public abstract class Piece {
    protected final int piecePosition;
    protected final Type pieceType;

    Piece(final int piecePosition, final Type pieceType){
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
    }

    public Type getPieceType() {
        return this.pieceType;
    }

    /*
        Calculate a list of legal moves for a particular piece
         */
    public abstract List<Move> calculateLegalMoves (final Board board);


}
