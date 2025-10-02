package com.chess.engine.board.move.castle;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

import static com.chess.engine.board.Board.*;

/**
 * This class serves as the backbone for the two castling moves.
 */
public abstract class CastleMove extends Move {
    protected final Rook castleRook;
    protected final int castleRookDestination;
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    protected CastleMove(final Board board,
                         final Piece movedPiece,
                         final int kingDestination,
                         final Rook castleRook,
                         final int castleRookDestination) {
        super(board, movedPiece, kingDestination);
        this.castleRook = castleRook;
        this.castleRookDestination = castleRookDestination;
    }

    /**
     * @return the rook involved with the castle
     */
    public Rook getCastleRook() {
        return this.castleRook;
    }

    /**
     * @return whether the move is a castle
     */
    @Override
    public boolean isCastleMove() {
        return true;
    }

    /**
     * @return a new board with the castle move made
     */
    @Override
    public Board execute() {
        final Builder builder = new Builder();
        // Place all the current player's non-moving pieces on a new board
        for (final Piece piece : board.getCurrentPlayer().getActivePieces()) {
            if (!movedPiece.equals(piece) && !this.castleRook.equals(piece)) {
                builder.setPiece(piece);
            }
        }
        // Place all the opponent's pieces on a new board
        for (final Piece piece : board.getCurrentPlayer().getOpponent().getActivePieces()) {
            builder.setPiece(piece);
        }
        builder.setPiece(this.movedPiece.movePiece(this));
        // TODO: look into individual first move booleans
        builder.setPiece(new Rook(this.castleRook.getPieceAlliance(), this.castleRookDestination));
        builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getPlayerAlliance());

        return builder.build();
    }
}
