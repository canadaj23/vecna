package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

/**
 * This class represents a move done on a chess board with a chess piece.
 * E.g., a capture, en passant, or a tile jump
 */
public abstract class Move {
    final protected Board board;
    final protected Piece movedPiece;
    final protected int targetPosition;
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    protected Move(final Board board,
         final Piece movedPiece,
         final int targetPosition) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.targetPosition = targetPosition;
    }
}
