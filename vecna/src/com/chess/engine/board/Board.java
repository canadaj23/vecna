package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.*;
import com.chess.engine.tile.Tile;
import com.google.common.collect.ImmutableList;

import java.util.*;

import static com.chess.engine.Alliance.*;
import static com.chess.engine.board.BoardUtils.*;
import static com.chess.engine.tile.Tile.*;

public class Board {
    private final List<Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    private Board(final Builder builder) {
        // Populate the chess board with all necessary occupied and empty tiles
        this.gameBoard = createGameBoard(builder);
        // Determine White and Black's active pieces
        whitePieces = CalculateActivePieces(this.gameBoard, WHITE);
        blackPieces = CalculateActivePieces(this.gameBoard, BLACK);
        // Calculate White and Black's current legal moves
        final Collection<Move> whiteStandardLegalMoves = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMoves = calculateLegalMoves(this.blackPieces);
    }
    //********************************************************
    //**********************Main Methods**********************
    //********************************************************
    /**
     * @param builder what will be referenced for details about the chess board
     * @return a list of all the tiles (both occupied and empty) on the chess board
     */
    private static List<Tile> createGameBoard(final Builder builder) {
        final Tile[] tiles = new Tile[NUM_TILES];
        for (int i = 0; i < NUM_TILES; i++) {
            tiles[i] = CreateTile(i, builder.boardConfig.get(i));
        }

        return ImmutableList.copyOf(tiles);
    }

    /**
     * @param gameBoard what the pieces are on
     * @param alliance  White/Black
     * @return a list of a player's currently active pieces
     */
    private static Collection<Piece> CalculateActivePieces(final List<Tile> gameBoard, final Alliance alliance) {
        final List<Piece> activePieces = new ArrayList<>();
        for (final Tile tile : gameBoard) {
            if (tile.isTileOccupied()) {
                final Piece piece = tile.getPiece();
                if (piece.getPieceAlliance() == alliance) {
                    activePieces.add(piece);
                }
            }
        }

        return ImmutableList.copyOf(activePieces);
    }

    /**
     * @param pieces the player's currently active pieces
     * @return a list of a player's legal moves
     */
    private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final Piece piece : pieces) {
            legalMoves.addAll(piece.calculateLegalMoves(this));
        }

        return ImmutableList.copyOf(legalMoves);
    }

    /**
     * @param tilePosition where the tile is on the chess board
     * @return the tile with a given position
     */
    public Tile getTile(final int tilePosition) {
        return gameBoard.get(tilePosition);
    }
    /**
     * @return the initial chess board
     */
    public static Board CreateInitialBoard() {
        final Builder builder = new Builder();
        SetInitialPieces(builder);

        // The initial move maker is White
        builder.setMoveMaker(WHITE);

        return builder.build();
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < NUM_TILES; i++) {
            final String tileText = this.gameBoard.get(i).toString();
            stringBuilder.append(String.format("%3s", tileText));
            if ((i + 1) % NUM_TILES_PER_ROW == 0) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    //********************************************************
    //********************Builder Pattern*********************
    //********************************************************
    /**
     * This inner class will help build an instance of a chess board.
     */
    public static class Builder {
        Map<Integer, Piece> boardConfig;
        Alliance nextMoveMaker;
        //********************************************************
        //**********************Constructor***********************
        //********************************************************
        public Builder() {
            this.boardConfig = new HashMap<>();
        }
        //********************************************************
        //**********************Main Methods**********************
        //********************************************************
        /**
         * @param piece the piece to be placed on the new board
         * @return an updated Builder
         */
        public Builder setPiece(final Piece piece) {
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        /**
         *
         * @param nextMoveMaker whose turn is next
         * @return an updated Builder
         */
        public Builder setMoveMaker(final Alliance nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        /**
         * @return a new instance of the chess board
         */
        public Board build() {
            return new Board(this);
        }
    }
}
