package com.chess.engine.board.move.misc;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.Piece;

/**
 * This class represents a move that should not be possible.
 */
public class NullMove extends Move {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public NullMove() {
        super(null, null, -1);
    }
    //********************************************************
    //**********************Main Methods***********************
    //********************************************************
    /**
     * @return a new board with the null move made
     */
    @Override
    public Board execute() {
        throw new RuntimeException("A null move cannot be executed!");
    }
}
