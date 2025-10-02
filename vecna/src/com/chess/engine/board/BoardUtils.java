package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.board.move.Move;
import com.chess.engine.board.tile.Tile;
import com.chess.engine.pieces.*;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.Alliance.BLACK;
import static com.chess.engine.Alliance.WHITE;
import static com.chess.engine.board.Board.*;

/**
 * This class performs various tasks for the Board class.
 */
public class BoardUtils {
    // Tile-related
    public static final int START_TILE_INDEX = 0;
    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;
    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    /**
     * @param position where the tile might be on the chess board
     * @return whether the tile is on the chess board
     */
    public static boolean IsValidTilePosition(final int position) {
        return position >= START_TILE_INDEX && position < NUM_TILES;
    }
    //********************************************************
    //**********************Calculating***********************
    //********************************************************
    /**
     * @param gameBoard what the pieces are on
     * @param alliance  White/Black
     * @return a list of a player's currently active pieces
     */
    static Collection<Piece> CalculateActivePieces(final List<Tile> gameBoard, final Alliance alliance) {
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
    static Collection<Move> CalculateLegalMoves(final Collection<Piece> pieces, final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final Piece piece : pieces) {
            legalMoves.addAll(piece.calculateLegalMoves(board));
        }

        return ImmutableList.copyOf(legalMoves);
    }
    //********************************************************
    //******************Initial Chess Board*******************
    //********************************************************
    /**
     * Sets the initial board with White's and Black's starting pieces.
     * @param builder what will create the chess board with pieces on it
     */
    static void SetInitialPieces(final Builder builder) {
        // Populate the board with Black's initial pieces
        SetBlackInitialPieces(builder);
        // Populate the board with White's initial pieces
        setWhiteInitialPieces(builder);
    }

    /**
     * Sets all of Black's initial pieces on the board
     * @param builder what places the pieces on the board
     */
    private static void SetBlackInitialPieces(final Builder builder) {
        final int mainIndex = 0;
        SetInitialMainPieces(builder, BLACK, mainIndex);

        final int pawnIndex = 8;
        SetInitialPawns(builder, BLACK, pawnIndex);
    }

    /**
     * Sets all of White's initial pieces on the board
     * @param builder what places the pieces on the board
     */
    private static void setWhiteInitialPieces(final Builder builder) {
        final int mainIndex = 56;
        SetInitialMainPieces(builder, WHITE, mainIndex);

        final int pawnIndex = 48;
        SetInitialPawns(builder, WHITE, pawnIndex);
    }

    /**
     * Sets all the pieces to their initial positions
     * @param builder    what places the pieces on the board
     * @param alliance   White/Black
     * @param mainIndex  the starting index for all main pieces excluding pawns
     */
    private static void SetInitialMainPieces(final Builder builder, final Alliance alliance, final int mainIndex) {
        builder.setPiece(new Rook(alliance, mainIndex));
        builder.setPiece(new Knight(alliance, mainIndex + 1));
        builder.setPiece(new Bishop(alliance, mainIndex + 2));
        builder.setPiece(new Queen(alliance, mainIndex + 3));
        builder.setPiece(new King(alliance, mainIndex + 4));
        builder.setPiece(new Bishop(alliance, mainIndex + 5));
        builder.setPiece(new Knight(alliance, mainIndex + 6));
        builder.setPiece(new Rook(alliance, mainIndex + 7));
    }

    /**
     * Sets all the pawns to their initial positions
     * @param builder   what places the pieces on the board
     * @param alliance  White/Black
     * @param pawnIndex the starting index for all pawns
     */
    private static void SetInitialPawns(final Builder builder, final Alliance alliance, final int pawnIndex) {
        for (int i = pawnIndex; i < pawnIndex + 8; i++) {
            builder.setPiece(new Pawn(alliance, i));
        }
    }
}
