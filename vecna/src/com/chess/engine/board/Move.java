package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

/**
 * This class represents a move done on a chess board with a chess piece.
 * E.g., a capture, en passant, or a tile jump
 */
public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int targetPosition;

    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    private Move(final Board board,
                final Piece movedPiece,
                final int targetPosition) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.targetPosition = targetPosition;
    }

    // TODO: place each subclass in their own class file for better readability

    //********************************************************
    //******************MajorMove subclass********************
    //********************************************************
    public static final class MajorMove extends Move {
        //********************************************************
        //**********************Constructor***********************
        //********************************************************
        public MajorMove(final Board board,
                         final Piece movedPiece,
                         final int targetPosition) {
            super(board, movedPiece, targetPosition);
        }
    }

    //********************************************************
    //*****************AttackMove subclass********************
    //********************************************************
    public static final class AttackMove extends Move {
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
}
