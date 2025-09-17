package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;

import java.util.Collection;

/**
 * This abstract class serves as the backbone for a piece on the chess board.
 */
public abstract class Piece {
    protected final Alliance pieceAlliance;
    protected final int piecePosition;
    protected final boolean firstMove;
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
        // TODO: more work to do
        this.firstMove = false;
    }
    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    /**
     * Determines all the piece's legal moves currently on the chess board.
     * @param board what the piece will be moving on
     * @return a collection of all the piece's legal moves on the current chess board
     */
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

    /**
     * @return whether it's the piece's first move
     */
    public boolean isFirstMove() {
        return this.firstMove;
    }
}
