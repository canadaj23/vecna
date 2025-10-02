package com.chess.engine.board.move.castle;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

/**
 * This class represents castling on the queenside (three-tile gap).
 */
public class QueensideCastleMove extends CastleMove {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public QueensideCastleMove(final Board board,
                               final Piece movedPiece,
                               final int kingDestination,
                               final Rook castleRook,
                               final int castleRookDestination) {
        super(board, movedPiece, kingDestination, castleRook, castleRookDestination);
    }
    //********************************************************
    //*******************Special Overrides********************
    //********************************************************
    @Override
    public String toString() {
        return "O-O-O";
    }
}
