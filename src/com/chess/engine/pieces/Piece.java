package com.chess.engine.pieces;
import com.chess.engine.Type;
import com.chess.engine.board.Move;
import com.chess.engine.board.Board;

import java.util.Collection;


/**
 * Represent a particular Piece within a Tile.
 * Each piece type will extend this Piece class to have its abstract characteristics.
 */
public abstract class Piece {
    protected final int piecePosition;
    protected final Type pieceType;
    protected final boolean isFirstMove;

    Piece(final int piecePosition, final Type pieceType){
        this.pieceType = pieceType;
        this.piecePosition = piecePosition;
        this.isFirstMove = false;
    }

    public Type getPieceType() {
        return this.pieceType;
    }
    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    /*
        Calculate a list of legal moves for a particular piece
         */
    public abstract Collection<Move> calculateLegalMoves (final Board board);


}
