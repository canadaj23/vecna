package com.chess.engine.board.move.pawn;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;

import static com.chess.engine.board.Board.*;
import static com.chess.engine.board.move.utils.MoveUtils.PlaceNonMovingPieces;

/**
 * This class represents a two-tile Pawn advancement.
 */
public class PawnJump extends Move {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    public PawnJump(final Board board, final Piece movedPiece, final int targetPosition) {
        super(board, movedPiece, targetPosition);
    }
    //********************************************************
    //**********************Main Methods**********************
    //********************************************************
    /**
     * @return a new board with the move made
     */
    @Override
    public Board execute() {
        final Builder builder = new Builder();
        PlaceNonMovingPieces(builder, this.board, this.movedPiece);

        // Move the moving pawn, then set the next move maker
        final Pawn movedPawn = (Pawn) this.movedPiece.movePiece(this);
        builder.setPiece(movedPawn);
        builder.setEnPassantPawn(movedPawn);
        builder.setMoveMaker(this.board.getCurrentPlayer().getOpponent().getPlayerAlliance());

        return builder.build();
    }
}
