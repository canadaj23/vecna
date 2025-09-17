package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.*;
import com.chess.engine.tile.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.pieces.utils.PieceMoveOffsets.KNIGHT_MOVE_OFFSETS;
import static com.chess.engine.board.BoardUtils.IsValidTilePosition;

/**
 * This class represents the Knight chess piece.
 */
public class Knight extends Piece {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a Knight.
     * @param pieceAlliance the Knight's alliance
     * @param piecePosition the Knight's position on the chess board
     */
    public Knight(final Alliance pieceAlliance, final int piecePosition) {
        super(pieceAlliance, piecePosition);
    }
    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    /**
     * Determines all the Knight's legal moves currently on the chess board.
     * @param board what the Knight will be moving on
     * @return a collection of all the Knight's legal moves on the current chess board
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        List<Move> legalMoves = new ArrayList<>();
        // Iterate through all the piece's offsets to find valid moves
        for (final int currentOffset : KNIGHT_MOVE_OFFSETS) {
            // Find the current target position
            final int possibleTargetPosition = this.piecePosition + currentOffset;
            // Determine if the tile is on the chess board
            if (IsValidTilePosition(possibleTargetPosition)) {
                // Determine if the piece is on an edge
                if (Math.abs(((this.piecePosition % 8)) - ((possibleTargetPosition % 8))) > 2){
                    continue;
                }
                final Tile possibleTargetTile = board.getTile(possibleTargetPosition);
                // Determine if the tile is empty or occupied
                if (!possibleTargetTile.isTileOccupied()) {
                    // The piece can move to the empty tile.
                    legalMoves.add(new MajorMove(board, this, possibleTargetPosition));
                } else {
                    final Piece targetPiece = possibleTargetTile.getPiece();
                    final Alliance targetPieceAlliance = targetPiece.getPieceAlliance();
                    // Determine if the target piece is the enemy's
                    if (this.pieceAlliance != targetPieceAlliance) {
                        // The piece can capture the enemy's piece.
                        legalMoves.add(new AttackMove(board, this, possibleTargetPosition, targetPiece));
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}
