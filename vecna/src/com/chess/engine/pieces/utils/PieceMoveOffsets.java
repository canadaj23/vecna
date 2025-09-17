package com.chess.engine.pieces.utils;

/**
 * This class holds every chess piece's move offsets.
 */
public class PieceMoveOffsets {
    public final static int[] KNIGHT_MOVE_OFFSETS = { -17, -15, -10, -6, 6, 10, 15, 17 };
    public final static int[] BISHOP_MOVE_OFFSETS = { -9, -7, 7, 9 };
    public final static int[] ROOK_MOVE_OFFSETS = { -8, -1, 1, 8 };
    public final static int[] QUEEN_KING_MOVE_OFFSETS = { -9, -8, -7, -1, 1, 7, 8, 9 };
}
