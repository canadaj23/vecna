package com.chess.engine.board.move.castle;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

/**
 * This class represents castling on the queenside (three-tile gap).
 */
public class QueensideCastleMove extends CastleMove {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public QueensideCastleMove(final Board board, final Piece movedPiece, final int targetPosition) {
        super(board, movedPiece, targetPosition);
    }
}
