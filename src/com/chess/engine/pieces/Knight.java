package com.chess.engine.pieces;

import com.chess.engine.Type;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Knight Piece, and how its legal moves work
 */
public class Knight extends Piece{
    /*
    List of int co-ordinates that a Knight may have legal moves. All possible moves
    including the ones that may be occupied or out of bounds.
     */
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
    Knight(int piecePosition, Type pieceType) {
        super(piecePosition, pieceType);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        int candidateDestinationCoordinate;
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currCandidate: CANDIDATE_MOVE_COORDINATES){
            candidateDestinationCoordinate = this.piecePosition + currCandidate;
            if (true){
                final Tile candidateDestinationOnTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationOnTile.isTileOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtAnotherMove = candidateDestinationOnTile.getPiece();
                    final Type pieceType = pieceAtAnotherMove.getPieceType();

                    if (this.pieceType != pieceType) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
}
