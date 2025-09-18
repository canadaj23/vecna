package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.*;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.BoardUtils.IsValidTilePosition;
import static com.chess.engine.pieces.Piece.PieceType.PAWN;
import static com.chess.engine.pieces.utils.PieceMoveOffsets.PAWN_MOVE_OFFSETS;

public class Pawn extends Piece {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a Pawn.
     *
     * @param pieceAlliance the Pawn's alliance
     * @param piecePosition the Pawn's position on the chess board
     */
    public Pawn(final Alliance pieceAlliance, final int piecePosition) {
        super(PAWN, pieceAlliance, piecePosition);
    }
    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    /**
     * Determines all the Pawn's legal moves currently on the chess board.
     * @param board what the Pawn will be moving on
     * @return a collection of all the Pawn's legal moves on the current chess board
     */
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentOffset : PAWN_MOVE_OFFSETS) {
            // Position varies based on the piece's alliance
            final int pieceDirection = this.getPieceAlliance().getDirection();
            int possibleTargetPosition = piecePosition + (pieceDirection * currentOffset);
            if (!IsValidTilePosition(possibleTargetPosition)) {
                continue;
            }
            // A jumping-only Pawn must do a one-tile jump or two-tile jump to a legal empty tile.
            if (!board.getTile(possibleTargetPosition).isTileOccupied()) {
                if (currentOffset == 8 || currentOffset == 16) {
                    // Two-tile Pawn jump (pawn must be on its initial rank)
                    if (this.isFirstMove()) {
                        final int behindPossibleTargetPosition = this.piecePosition + (pieceDirection * 8);
                        if (!board.getTile(behindPossibleTargetPosition).isTileOccupied()) {
                            // TODO: make a two-tile Pawn jump
                            legalMoves.add(new MajorMove(board, this, possibleTargetPosition));
                        }
                    }
                    // One-tile Pawn jump (promotion must be on final rank in the Pawn's forward direction)
                    else {
                        // TODO: make a one-tile Pawn jump that may involve a promotion
                        legalMoves.add(new MajorMove(board, this, possibleTargetPosition));
                    }
                }
            }
            // Otherwise, a capture may occur via normal Pawn capture or En Passant.
            else {
                if (pawnDiagonalAttack(currentOffset)) {
                    final Piece targetPiece = board.getTile(possibleTargetPosition).getPiece();
                    if (this.pieceAlliance != targetPiece.getPieceAlliance()) {
                        // TODO: make a normal Pawn capture
                        legalMoves.add(new AttackMove(board, this, possibleTargetPosition, targetPiece));
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    /**
     * @param currentOffset the offset of the Pawn's forward attacking move
     * @return whether the attack is on a forward diagonal
     */
    private boolean pawnDiagonalAttack(final int currentOffset) {
        return (currentOffset == 7 && !(whitePawnOnLastFile() || blackPawnOnFirstFile())) ||
               (currentOffset == 9 && !(whitePawnOnFirstFile() || blackPawnOnLastFile()));
    }

    private boolean whitePawnOnFirstFile() {
        return this.getPieceAlliance().isWhite() && this.piecePosition % 8 == 0;
    }

    private boolean whitePawnOnLastFile() {
        return this.getPieceAlliance().isWhite() && this.piecePosition % 8 == 7;
    }

    private boolean blackPawnOnFirstFile() {
        return this.getPieceAlliance().isBlack() && this.piecePosition % 8 == 0;
    }

    private boolean blackPawnOnLastFile() {
        return this.getPieceAlliance().isBlack() && this.piecePosition % 8 == 7;
    }

    /**
     * @return a String representation of a Pawn
     */
    @Override
    public String toString() {
        return PAWN.toString();
    }
}
