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
    //********************************************************
    //***********************PieceType************************
    //********************************************************
    public enum PieceType {
        PAWN("P") {
            /**
             * @return whether the piece is a King
             */
            @Override
            public boolean isKing() {
                return false;
            }
        },
        ROOK("R") {
            /**
             * @return whether the piece is a King
             */
            @Override
            public boolean isKing() {
                return false;
            }
        },
        KNIGHT("N") {
            /**
             * @return whether the piece is a King
             */
            @Override
            public boolean isKing() {
                return false;
            }
        },
        BISHOP("B") {
            /**
             * @return whether the piece is a King
             */
            @Override
            public boolean isKing() {
                return false;
            }
        },
        QUEEN("Q") {
            /**
             * @return whether the piece is a King
             */
            @Override
            public boolean isKing() {
                return false;
            }
        },
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
        public abstract boolean isKing();

        /**
         * @return a String representation of a given piece
         */
        @Override
        public String toString() {
            return this.pieceName;
        }
    }
}
