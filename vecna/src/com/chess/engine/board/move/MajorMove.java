package com.chess.engine.board.move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;

import static com.chess.engine.board.Board.*;

/**
 * This class represents all non-attacking moves.
 */
public final class MajorMove extends Move {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public MajorMove(final Board board,
                     final Piece movedPiece,
                     final int targetPosition) {
        super(board, movedPiece, targetPosition);
    }
    //********************************************************
    //**********************Main Methods**********************
    //********************************************************
    /**
     * @return a new board with the major move made
     */
    @Override
    public Board execute() {
        final Builder builder = new Builder();
        // Place all the current player's non-moving pieces on a new board
        for (final Piece piece : this.board.getCurrentPlayer().getActivePieces()) {
            // TODO: implement more sound equals and hashcode methods
            if (!this.movedPiece.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        // Place all the opponent's pieces on a new board
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }
        // Move the moving piece, then set the next move maker
        builder.setPiece(this.movedPiece.movePiece(this));
        builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getPlayerAlliance());

        return builder.build();
    }
}
