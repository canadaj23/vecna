package com.chess.engine.board.move.pawn;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.Piece;

/**
 * This class represents a one-tile Pawn advancement.
 */
public class PawnMove extends Move {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public PawnMove(final Board board, final Piece movedPiece, final int targetPosition) {
        super(board, movedPiece, targetPosition);
    }
}
