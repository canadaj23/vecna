package com.chess.engine.board.move.pawn;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

/**
 * This class represents the En Passant Pawn attack.
 */
public class PawnEnPassantAttackMove extends PawnAttackMove {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public PawnEnPassantAttackMove(final Board board,
                                   final Piece movedPiece,
                                   final int targetPosition,
                                   final Piece attackedPiece) {
        super(board, movedPiece, targetPosition, attackedPiece);
    }
}
