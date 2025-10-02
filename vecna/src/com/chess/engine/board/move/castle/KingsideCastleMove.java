package com.chess.engine.board.move.castle;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

/**
 * This class represents castling on the kingside (two-tile gap).
 */
public class KingsideCastleMove extends CastleMove {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public KingsideCastleMove(final Board board, final Piece movedPiece, final int targetPosition) {
        super(board, movedPiece, targetPosition);
    }
}
