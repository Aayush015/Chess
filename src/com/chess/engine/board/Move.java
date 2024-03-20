package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.sun.jdi.connect.AttachingConnector;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    private Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public static final class majorMove extends Move {
        public majorMove(Board board, Piece movedPiece, int destinationCoordinate){
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class attackMove extends Move {
        final Piece attackedPiece;
        public attackMove(final Board board, final Piece movedPiece, final int destinationCoordination,
                    final Piece attackedPiece){
            super(board, movedPiece, destinationCoordination);
            this.attackedPiece = attackedPiece;
        }
    }
}
