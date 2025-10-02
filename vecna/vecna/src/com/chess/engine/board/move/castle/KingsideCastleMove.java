package com.chess.engine.board.move.castle;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

/**
 * This class represents castling on the kingside (two-tile gap).
 */
public class KingsideCastleMove extends CastleMove {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public KingsideCastleMove(final Board board,
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
        return "O-O";
    }
}
