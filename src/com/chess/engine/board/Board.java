package com.chess.engine.board;

import com.chess.engine.Type;
import com.chess.engine.pieces.*;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Board {

    private final List<Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private Board(Builder builder) {
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Type.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Type.BLACK);
    }

    private Collection<Piece> calculateActivePieces(final List<Tile> gameBoard,
                                                    final Type type){
        final List<Piece> activePieces = new ArrayList<>();
        for (final Tile tile: gameBoard){
            if (tile.isTileOccupied()){
                final Piece piece = tile.getPiece();
                if(piece.getPieceType() == type) {
                    activePieces.add(piece);
                }
            }
        }
        return ImmutableList.copyOf(activePieces);
    }
    public Tile getTile(final int tileCoordinate){
        return gameBoard.get(tileCoordinate);
    }

    private static List<Tile> createGameBoard(final Builder builder){
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for (int i=0; i<BoardUtils.NUM_TILES; i++){
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return ImmutableList.copyOf(tiles);
    }

    public static Board createStandardBoard(){
        final Builder builder = new Builder();
        // Black layout
        builder.setPiece(new Rook(Type.BLACK, 0));
        builder.setPiece(new Knight(Type.BLACK, 1));
        builder.setPiece(new Bishop(Type.BLACK, 2));
        builder.setPiece(new Queen(Type.BLACK, 3));
        builder.setPiece(new King(Type.BLACK, 4));
        builder.setPiece(new Bishop(Type.BLACK, 5));
        builder.setPiece(new Knight(Type.BLACK, 6));
        builder.setPiece(new Rook(Type.BLACK, 7));
        builder.setPiece(new Pawn(Type.BLACK, 8));
        builder.setPiece(new Pawn(Type.BLACK, 9));
        builder.setPiece(new Pawn(Type.BLACK, 10));
        builder.setPiece(new Pawn(Type.BLACK, 11));
        builder.setPiece(new Pawn(Type.BLACK, 12));
        builder.setPiece(new Pawn(Type.BLACK, 13));
        builder.setPiece(new Pawn(Type.BLACK, 14));
        builder.setPiece(new Pawn(Type.BLACK, 15));

        // White layout
        builder.setPiece(new Rook(Type.WHITE, 48));
        builder.setPiece(new Knight(Type.WHITE, 49));
        builder.setPiece(new Bishop(Type.WHITE, 50));
        builder.setPiece(new Queen(Type.WHITE, 51));
        builder.setPiece(new King(Type.WHITE, 52));
        builder.setPiece(new Bishop(Type.WHITE, 53));
        builder.setPiece(new Knight(Type.WHITE, 54));
        builder.setPiece(new Rook(Type.WHITE, 55));
        builder.setPiece(new Pawn(Type.WHITE, 56));
        builder.setPiece(new Pawn(Type.WHITE, 57));
        builder.setPiece(new Pawn(Type.WHITE, 58));
        builder.setPiece(new Pawn(Type.WHITE, 59));
        builder.setPiece(new Pawn(Type.WHITE, 60));
        builder.setPiece(new Pawn(Type.WHITE, 61));
        builder.setPiece(new Pawn(Type.WHITE, 62));
        builder.setPiece(new Pawn(Type.WHITE, 63));

        return builder.build();
    }
    public static class Builder {
        Map<Integer, Piece> boardConfig;
        Type nextMove;
        public Builder(){

        }
        public Builder setPiece(final Piece piece){
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        public Builder setMoveMaker(final Type nextMove){
            this.nextMove = nextMove;
            return this;
        }

        public Board build() {
            return new Board(this);
        }
    }
}