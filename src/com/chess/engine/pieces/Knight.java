package com.chess.engine.pieces;

import com.chess.engine.Type;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static com.chess.engine.board.BoardUtils.isValidTileCoordinate;
import static com.chess.engine.board.Move.*;

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
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currCandidate: CANDIDATE_MOVE_COORDINATES){
            int candidateDestinationCoordinate= this.piecePosition + currCandidate;
            if (isValidTileCoordinate(candidateDestinationCoordinate)){
                if (isFirstColumnExclusion(this.piecePosition, currCandidate) ||
                        isSecondColumnExclusion(this.piecePosition, currCandidate) ||
                        isSeventhColumnExclusion(this.piecePosition, currCandidate) ||
                        isEighthColumnExclusion(this.piecePosition, currCandidate)){
                    continue;
                }
                final Tile candidateDestinationOnTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationOnTile.isTileOccupied()) {
                    legalMoves.add(new majorMove(board, this, candidateDestinationCoordinate));
                } else {
                    final Piece pieceAtAnotherMove = candidateDestinationOnTile.getPiece();
                    final Type pieceType = pieceAtAnotherMove.getPieceType();

                    if (this.pieceType != pieceType) {
                        legalMoves.add(new attackMove(board, this, candidateDestinationCoordinate,
                                pieceAtAnotherMove));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currPosition] && ((candidateOffset == -17) || (candidateOffset == -10) ||
                candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean isSecondColumnExclusion(final int currPosition, final int candidateOffset){
        return BoardUtils.SECOND_COLUMN[currPosition] && ((candidateOffset == -10) || candidateOffset == 6);
    }

    private static boolean isSeventhColumnExclusion(final int currPosition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currPosition] && ((candidateOffset == -6) || candidateOffset == 10);
    }

    private static boolean isEighthColumnExclusion(final int currPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currPosition] && ((candidateOffset == -15) || (candidateOffset == -6) ||
                (candidateOffset == 10) || (candidateOffset == 17));
    }

}
