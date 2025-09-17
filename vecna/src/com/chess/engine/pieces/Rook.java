package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.tile.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.BoardUtils.IsValidTilePosition;
import static com.chess.engine.pieces.utils.PieceMoveOffsets.ROOK_MOVE_OFFSETS;
import static com.chess.engine.pieces.utils.PieceUtils.CalculateSlidingLegalMoves;

public class Rook extends Piece {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a Rook.
     * @param pieceAlliance the Rook's alliance
     * @param piecePosition the Rook's position on the chess board
     */
    protected Rook(Alliance pieceAlliance, int piecePosition) {
        super(pieceAlliance, piecePosition);
    }
    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    /**
     * Determines all the Rook's legal moves currently on the chess board.
     * @param board what the Rook will be moving on
     * @return a collection of all the Rook's legal moves on the current chess board
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        // In class version
//        calculateRookLegalMoves(board);
        // Sliding piece version
        List<Move> legalMoves = CalculateSlidingLegalMoves(
                board,
                this,
                this.piecePosition,
                ROOK_MOVE_OFFSETS,
                this.pieceAlliance);
        return ImmutableList.copyOf(legalMoves);
    }

    /**
     * Determines all the Rook's legal moves currently on the chess board.
     * @param board what the Rook will be moving on
     * @return a collection of all the Rook's legal moves on the current chess board
     */
    private Collection<Move> calculateRookLegalMoves(final Board board) {
        List<Move> legalMoves = new ArrayList<>();
        // Iterate through all the piece's offsets to find valid moves
        for (final int currentOffset : ROOK_MOVE_OFFSETS) {
            int possibleTargetPosition = this.piecePosition;
            // Determine if the tile is on the chess board
            while (IsValidTilePosition(possibleTargetPosition)) {
                // Determine if the piece is on an edge
                if (Math.abs(((this.piecePosition % 8)) - ((possibleTargetPosition % 8))) > 1) {
                    break;
                }
                final Tile possibleTargetTile = board.getTile(possibleTargetPosition);
                // Determine if the tile is empty or occupied
                if (!possibleTargetTile.isTileOccupied()) {
                    // The piece can move to the empty tile.
                    legalMoves.add(new Move.MajorMove(board, this, possibleTargetPosition));
                } else {
                    final Piece targetPiece = possibleTargetTile.getPiece();
                    final Alliance targetPieceAlliance = targetPiece.getPieceAlliance();
                    // Determine if the target piece is the enemy's
                    if (this.pieceAlliance != targetPieceAlliance) {
                        // The piece can capture the enemy's piece.
                        legalMoves.add(new Move.AttackMove(board, this, possibleTargetPosition, targetPiece));
                    }
                    // Cannot move since the tile is occupied, regardless of the target's alliance
                    break;
                }
                // Find the next target's position
                possibleTargetPosition += currentOffset;
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}
