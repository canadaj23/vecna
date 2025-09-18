package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.*;
import com.chess.engine.board.tile.Tile;
import com.chess.engine.players.BlackPlayer;
import com.chess.engine.players.Player;
import com.chess.engine.players.WhitePlayer;
import com.google.common.collect.ImmutableList;

import java.util.*;

import static com.chess.engine.Alliance.*;
import static com.chess.engine.board.BoardUtils.*;
import static com.chess.engine.board.tile.Tile.*;

/**
 * This class represents the chess board in its entirety.
 * The board is made up of tiles, both occupied and empty.
 * The board will be "updated" by creating another board based on a move performed by a player.
 */
public class Board {
    private final List<Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a chess board.
     * @param builder what will build each subsequent board
     */
    private Board(final Builder builder) {
        // Populate the chess board with all necessary occupied and empty tiles
        this.gameBoard = createGameBoard(builder);
        // Determine White and Black's active pieces
        this.whitePieces = CalculateActivePieces(this.gameBoard, WHITE);
        this.blackPieces = CalculateActivePieces(this.gameBoard, BLACK);
        // Calculate White and Black's current legal moves
        final Collection<Move> whiteStandardLegalMoves = CalculateLegalMoves(this.whitePieces, this);
        final Collection<Move> blackStandardLegalMoves = CalculateLegalMoves(this.blackPieces, this);
        // Set up the players
        this.whitePlayer = new WhitePlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
        this.blackPlayer = new BlackPlayer(this, whiteStandardLegalMoves, blackStandardLegalMoves);
        // Designate the current player
        this.currentPlayer = null;
    }
    //********************************************************
    //*********************Board Creation*********************
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
     * @return the initial chess board
     */
    public static Board CreateInitialBoard() {
        final Builder builder = new Builder();
        SetInitialPieces(builder);

        // The initial move maker is White
        builder.setMoveMaker(WHITE);

        return builder.build();
    }
    //********************************************************
    //************************Getters*************************
    //********************************************************
    /**
     * @param tilePosition where the tile is on the chess board
     * @return the tile with a given position
     */
    public Tile getTile(final int tilePosition) {
        return this.gameBoard.get(tilePosition);
    }

    /**
     * @param alliance White/Black
     * @return an alliance's pieces
     */
    public Collection<Piece> getPieces(final Alliance alliance) {
        return alliance.isWhite() ? this.whitePieces : this.blackPieces;
    }

    /**
     * @return the white player
     */
    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    /**
     * @return the black player
     */
    public Player getBlackPlayer() {
        return this.blackPlayer;
    }

    /**
     * @return the current move maker
     */
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }
    //********************************************************
    //********************Special Overrides*******************
    //********************************************************
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
