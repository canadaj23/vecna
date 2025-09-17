package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

/**
 * This abstract class serves as the backbone for a piece on the chess board.
 */
public abstract class Piece {
    protected final Alliance pieceAlliance;
    protected final int piecePosition;

    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a piece.
     * @param pieceAlliance the piece's alliance
     * @param piecePosition the piece's position on the chess board
     */
    protected Piece(final Alliance pieceAlliance, final int piecePosition) {
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
    }

    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    public abstract Collection<Move> calculateLegalMoves(final Board board);

    /**
     * @return the piece's position
     */
    public int getPiecePosition() {
        return this.piecePosition;
    }

    /**
     * @return the piece's alliance (White or Black)
     */
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }
}
