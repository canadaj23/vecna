package com.chess.engine.board.move.castle;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.Piece;

/**
 * This class serves as the backbone for the two castling moves.
 */
public abstract class CastleMove extends Move {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    protected CastleMove(final Board board, final Piece movedPiece, final int targetPosition) {
        super(board, movedPiece, targetPosition);
    }
}
