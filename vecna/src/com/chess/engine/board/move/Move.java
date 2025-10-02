package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.misc.NullMove;
import com.chess.engine.pieces.Piece;

import static com.chess.engine.board.Board.*;
import static com.chess.engine.board.move.utils.MoveUtils.PlaceNonMovingPieces;

/**
 * This class represents a move done on a chess board with a chess piece.
 * E.g., a capture, en passant, or a tile jump
 */
public abstract class Move {
    final protected Board board;
    final protected Piece movedPiece;
    final protected int targetPosition;
    // NullMove singleton
    public static final Move NULL_MOVE = new NullMove();
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    protected Move(final Board board,
         final Piece movedPiece,
         final int targetPosition) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.targetPosition = targetPosition;
    }
    //********************************************************
    //**********************Main Methods**********************
    //********************************************************
    /**
     * @return a new board with the move made
     */
    public Board execute() {
        final Builder builder = new Builder();
        PlaceNonMovingPieces(builder, this.board, this.movedPiece);
        // Move the moving piece, then set the next move maker
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getPlayerAlliance());

        return builder.build();
    }

    /**
     * @return where the piece wants to move to
     */
    public int getTargetPosition() {
        return this.targetPosition;
    }

    /**
     * @return the piece to be moved
     */
    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    /**
     * @return whether the move is an attack
     */
    public boolean isAttackMove() {
        return false;
    }

    /**
     * @return whether the move is a castle
     */
    public boolean isCastleMove() {
        return false;
    }

    /**
     * @return the piece under attack
     */
    public Piece getAttackedPiece() {
        return null;
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
        // Check if other is a move
        if (!(other instanceof Move otherMove)) {
            return false;
        }

        return this.targetPosition == otherMove.getTargetPosition() &&
               this.movedPiece.equals(otherMove.getMovedPiece());
    }

    /**
     * @return the newly calculated piece hashcode
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + this.targetPosition;
        result = 31 * result + this.movedPiece.hashCode();

        return result;
    }
    //********************************************************
    //**********************Move Factory**********************
    //********************************************************
    /**
     * This class is responsible for creating a specific move object by using other move-related classes' attributes.
     */
    public static class MoveFactory {
        //********************************************************
        //**********************Constructor***********************
        //********************************************************
        private MoveFactory() {}
        //********************************************************
        //**********************Main Methods**********************
        //********************************************************
        /**
         * @param board           where the chess game takes place on
         * @param currentPosition where the piece currently is
         * @param targetPosition  where the piece wants to move to
         * @return a move on the board with the given positions
         */
        public static Move createMove(final Board board, final int currentPosition, final int targetPosition) {
            for (final Move move : board.getAllLegalMoves()) {
                if (move.getMovedPiece().getPiecePosition() == currentPosition &&
                move.getTargetPosition() == targetPosition) {
                    return move;
                }
            }

            return NULL_MOVE;
        }
    }
}
