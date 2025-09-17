package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

/**
 * This class represents all attacking moves.
 */
public final class AttackMove extends Move {
    final Piece attackedPiece;

    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public AttackMove(final Board board,
                      final Piece movedPiece,
                      final int targetPosition,
                      final Piece attackedPiece) {
        super(board, movedPiece, targetPosition);
        this.attackedPiece = attackedPiece;
    }
}
