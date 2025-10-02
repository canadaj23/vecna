package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;

import java.util.Collection;

/**
 * This abstract class serves as the backbone for a piece on the chess board.
 */
public abstract class Piece {
    protected final PieceType pieceType;
    protected final Alliance pieceAlliance;
    protected final int piecePosition;
    protected final boolean firstMove;
    private final int cachedHashCode;
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a piece.
     * @param pieceAlliance the piece's alliance
     * @param piecePosition the piece's position on the chess board
     */
    protected Piece(final PieceType pieceType, final Alliance pieceAlliance, final int piecePosition) {
        this.pieceType = pieceType;
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        // TODO: more work to do
        this.firstMove = false;
        this.cachedHashCode = computeHashCode();
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
     * @param move what the piece is trying to do
     * @return the same piece (but new object) but with an updated position
     */
    public abstract Piece movePiece(final Move move);

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
     * @return the abbreviation of the piece
     */
    public PieceType getPieceType() {
        return this.pieceType;
    }

    /**
     * @return whether it's the piece's first move
     */
    public boolean isFirstMove() {
        return this.firstMove;
    }

    /**
     * @return the newly created piece hashcode
     */
    private int computeHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove() ? 1 : 0);

        return result;
    }
    //********************************************************
    //******************Special Overrides*********************
    //********************************************************
    /**
     * Checks for more than just reference equality.
     * @param other the other object to compare
     * @return whether the object is equal to another
     */
    @Override
    public boolean equals(final Object other) {
        // Reference equality
        if (this == other) {
            return true;
        }
        // Check if other is a piece
        if (!(other instanceof Piece otherPiece)) {
            return false;
        }

        return this.piecePosition == otherPiece.getPiecePosition() &&
               this.pieceType == otherPiece.getPieceType() &&
               this.pieceAlliance == otherPiece.getPieceAlliance() &&
               this.firstMove == otherPiece.isFirstMove();
    }

    /**
     * @return the piece hashcode that was previously calculated
     */
    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    //********************************************************
    //***********************PieceType************************
    //********************************************************
    public enum PieceType {
        PAWN("P") {},
        ROOK("R") {
            /**
             * @return whether the piece is a Rook
             */
            @Override
            public boolean isRook() {
                return true;
            }
        },
        KNIGHT("N") {},
        BISHOP("B") {},
        QUEEN("Q") {},
        KING("K") {
            /**
             * @return whether the piece is a King
             */
            @Override
            public boolean isKing() {
                return true;
            }
        };

        private final String pieceName;
        //********************************************************
        //**********************Constructor***********************
        //********************************************************
        /**
         * Constructor for the piece type.
         * @param pieceName what the piece is designated as
         */
        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }
        //********************************************************
        //**********************Main Methods**********************
        //********************************************************
        /**
         * @return whether the piece is a King
         */
        public boolean isKing() {
            return false;
        }

        /**
         * @return whether the piece is a Rook
         */
        public boolean isRook() {
            return false;
        }

        /**
         * @return a String representation of a given piece
         */
        @Override
        public String toString() {
            return this.pieceName;
        }
    }
}
