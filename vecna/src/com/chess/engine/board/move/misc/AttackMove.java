package com.chess.engine.board.move.misc;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.Piece;

/**
 * This class represents all attacking moves.
 */
public class AttackMove extends Move {
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
    //********************************************************
    //**********************Main Methods**********************
    //********************************************************
    /**
     * @return a new board with the attack move made
     */
    @Override
    public Board execute() {
        return null;
    }

    /**
     * @return whether the move is an attack
     */
    @Override
    public boolean isAttackMove() {
        return true;
    }

    /**
     * @return the piece under attack
     */
    @Override
    public Piece getAttackedPiece() {
        return this.attackedPiece;
    }
    //********************************************************
    //******************Special Overrides*********************
    //********************************************************
    /**
     * Checks for more than just reference equality.
     * @param other the other object to compare
     * @return whether the object is equal to another
     */
    @Override
    public boolean equals(final Object other) {
        // Reference equality
        if (this == other) {
            return true;
        }
        // Check if other is a piece
        if (!(other instanceof AttackMove otherAttackMove)) {
            return false;
        }

        return super.equals(otherAttackMove) && this.attackedPiece.equals(otherAttackMove.getAttackedPiece());
    }

    /**
     * @return the piece hashcode that was previously calculated
     */
    @Override
    public int hashCode() {
        return this.attackedPiece.hashCode() + super.hashCode();
    }
}
