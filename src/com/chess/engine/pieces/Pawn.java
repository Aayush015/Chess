package com.chess.engine.pieces;

import com.chess.engine.Type;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{
    private final static int[] CANDIDATE_MOVE_COORDINATE = {9};
    Pawn(final int piecePosition,final Type pieceType) {
        super(piecePosition, pieceType);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currCandidateOffset: CANDIDATE_MOVE_COORDINATE){
            final int candidateDestinationCoordinate = this.piecePosition + (this.getPieceType().getDirection() * currCandidateOffset);
            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            if (currCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                legalMoves.add(new Move.majorMove(board, this, candidateDestinationCoordinate));
            } else if (currCandidateOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceType().isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceType().isWhite())) {
                final int candidateNextMove  = this.piecePosition + (this.getPieceType().getDirection() * 8);
                if (!board.getTile(candidateNextMove).isTileOccupied() &&
                    !board.getTile(candidateDestinationCoordinate).isTileOccupied()){
                    legalMoves.add(new Move.majorMove(board, this, candidateDestinationCoordinate));
                }
            }


        }
        return legalMoves;
    }
}
