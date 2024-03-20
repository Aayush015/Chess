package com.chess.engine.pieces;
import com.chess.engine.Type;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends Piece {
    private final static int[] CANDIDATE_MOVE_COORDINATE = {-9, -8, -7, -1, 1, 7, 8, 9};
     King(int piecePosition, Type pieceType) {
        super(piecePosition, pieceType);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
         final List<Move> legalMoves = new ArrayList<>();
         for (final int currCandidateOffset: CANDIDATE_MOVE_COORDINATE){
             final int candidateDestinationCoordinate = this.piecePosition + currCandidateOffset;

             // If there is an opponent King nearby
             if (isFirstColumnExclusion(this.piecePosition, currCandidateOffset) ||
                isEightColumnExclusion(this.piecePosition, currCandidateOffset)) {
                 continue;
             }
             if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                 final Tile candidateDestinationOnTile = board.getTile(candidateDestinationCoordinate);
                 if (!candidateDestinationOnTile.isTileOccupied()) {
                     legalMoves.add(new Move.majorMove(board, this, candidateDestinationCoordinate));
                 } else {
                     final Piece pieceAtAnotherMove = candidateDestinationOnTile.getPiece();
                     final Type pieceType = pieceAtAnotherMove.getPieceType();

                     if (this.pieceType != pieceType) {
                         legalMoves.add(new Move.attackMove(board, this, candidateDestinationCoordinate,
                                 pieceAtAnotherMove));
                     }
                 }
             }
         }
        return null;
    }

    private static boolean isFirstColumnExclusion(final int currPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currPosition] && ((candidateOffset == -9) || (candidateOffset == -1) ||
                candidateOffset == 7);
    }

    private static boolean isEightColumnExclusion(final int currPosition, final int candidateOffset){
        return BoardUtils.EIGHTH_COLUMN[currPosition] && ((candidateOffset == -7) || (candidateOffset == 1) ||
                candidateOffset == 9);
    }
}