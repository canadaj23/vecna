package com.chess.engine.board.move.utils;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

import static com.chess.engine.board.Board.*;

/**
 * This class performs various tasks for moves.
 */
public class MoveUtils {
    public static void PlaceNonMovingPieces(final Builder builder, final Board board, final Piece movedPiece) {
        // Place all the current player's non-moving pieces on a new board
        for (final Piece piece : board.getCurrentPlayer().getActivePieces()) {
            if (!movedPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        // Place all the opponent's pieces on a new board
        for (final Piece piece : board.getCurrentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }
    }
}
