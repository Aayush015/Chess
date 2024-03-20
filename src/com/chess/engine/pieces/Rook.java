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

public class Rook extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-8, -1, 1, 8};
    Rook (int piecePosition, Type pieceType){
        super(piecePosition, pieceType);
    }
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinateOffset: CANDIDATE_MOVE_COORDINATES){
            int candidateDestinationCoordinate = this.piecePosition;
            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
                        isEightColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)){
                    break;
                }
                candidateDestinationCoordinate +=candidateCoordinateOffset;
                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if (!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new Move.majorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Type pieceType = pieceAtDestination.getPieceType();
                        if (this.pieceType != pieceType){
                            legalMoves.add(new Move.attackMove(board, this, candidateDestinationCoordinate,
                                    pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currPosition, final int candidateOffset){
        return BoardUtils.FIRST_COLUMN[currPosition] && ((candidateOffset == -1));
    }

    private static boolean isEightColumnExclusion(final int currPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currPosition] && ((candidateOffset == 1));
    }
}
