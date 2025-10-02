package com.chess.engine.board.move.pawn;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.misc.AttackMove;
import com.chess.engine.pieces.Piece;

/**
 * This class represents a normal Pawn attack.
 */
public class PawnAttackMove extends AttackMove {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public PawnAttackMove(final Board board,
                          final Piece movedPiece,
                          final int targetPosition,
                          final Piece attackedPiece) {
        super(board, movedPiece, targetPosition, attackedPiece);
    }
}
