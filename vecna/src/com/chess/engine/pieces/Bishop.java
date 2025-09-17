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
import static com.chess.engine.board.Move.*;
import static com.chess.engine.pieces.PieceMoveOffsets.BISHOP_MOVE_OFFSETS;

public class Bishop extends Piece {
    /**
     * Constructor for a Bishop.
     *
     * @param pieceAlliance the Bishop's alliance
     * @param piecePosition the Bishop's position on the chess board
     */
    protected Bishop(Alliance pieceAlliance, int piecePosition) {
        super(pieceAlliance, piecePosition);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        List<Move> legalMoves = new ArrayList<>();
        // Iterate through all the piece's offsets to find valid moves
        for (final int currentOffset : BISHOP_MOVE_OFFSETS) {
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
                    legalMoves.add(new MajorMove(board, this, possibleTargetPosition));
                } else {
                    final Piece targetPiece = possibleTargetTile.getPiece();
                    final Alliance targetPieceAlliance = targetPiece.getPieceAlliance();
                    // Determine if the target piece is the enemy's
                    if (this.pieceAlliance != targetPieceAlliance) {
                        // The piece can capture the enemy's piece.
                        legalMoves.add(new AttackMove(board, this, possibleTargetPosition, targetPiece));
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
