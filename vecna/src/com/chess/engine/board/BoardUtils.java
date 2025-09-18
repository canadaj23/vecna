package com.chess.engine.board;

import com.chess.engine.Alliance;
import com.chess.engine.pieces.*;

import static com.chess.engine.Alliance.BLACK;
import static com.chess.engine.Alliance.WHITE;
import static com.chess.engine.board.Board.*;

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
    //******************Initial Chess Board*******************
    //********************************************************
    /**
     * Sets the initial board with White and Black's starting pieces.
     * @param builder what contains the chess board's attributes
     */
    static void SetInitialPieces(final Builder builder) {
        // Populate the board with Black's initial pieces
        setBlackInitialPieces(builder);
        // Populate the board with White's initial pieces
        setWhiteInitialPieces(builder);
    }

    private static void setBlackInitialPieces(final Builder builder) {
        int[] mainBlackPieces = { 0, 1, 2, 3, 4, 5, 6, 7 };
        SetInitialMainPieces(builder, BLACK, mainBlackPieces);

        int[] blackPawns = { 8, 9, 10, 11, 12, 13, 14, 15 };
        SetInitialPawns(builder, BLACK, blackPawns);
    }

    private static void setWhiteInitialPieces(final Builder builder) {
        int[] whitePawns = { 48, 49, 50, 51, 52, 53, 54, 55 };
        SetInitialPawns(builder, WHITE, whitePawns);

        int[] mainWhitePieces = { 56, 57, 58, 59, 60, 61, 62, 63 };
        SetInitialMainPieces(builder, WHITE, mainWhitePieces);
    }

    private static void SetInitialMainPieces(final Builder builder, final Alliance alliance, final int[] mainPieces) {
        builder.setPiece(new Rook(alliance, mainPieces[0]));
        builder.setPiece(new Knight(alliance, mainPieces[1]));
        builder.setPiece(new Bishop(alliance, mainPieces[2]));
        builder.setPiece(new Queen(alliance, mainPieces[3]));
        builder.setPiece(new King(alliance, mainPieces[4]));
        builder.setPiece(new Bishop(alliance, mainPieces[5]));
        builder.setPiece(new Knight(alliance, mainPieces[6]));
        builder.setPiece(new Rook(alliance, mainPieces[7]));
    }

    private static void SetInitialPawns(final Builder builder, final Alliance alliance, final int[] pawns) {
        for (int i = pawns[0]; i < pawns[7] + 1; i++) {
            builder.setPiece(new Pawn(alliance, i));
        }
    }
}
