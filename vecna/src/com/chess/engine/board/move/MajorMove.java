package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

/**
 * This class represents all non-attacking moves.
 */
public final class MajorMove extends Move {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public MajorMove(final Board board,
                     final Piece movedPiece,
                     final int targetPosition) {
        super(board, movedPiece, targetPosition);
    }
}
