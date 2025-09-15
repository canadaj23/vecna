package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.tile.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.pieces.PieceMoveOffsets.BISHOP_MOVE_OFFSETS;
import static com.chess.engine.board.BoardUtils.IsValidTilePosition;

public class Knight extends Piece {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************

    /**
     * Constructor for a Knight.
     * @param pieceAlliance the Knight's alliance
     * @param piecePosition the Knight's position on the chess board
     */
    protected Knight(final Alliance pieceAlliance, final int piecePosition) {
        super(pieceAlliance, piecePosition);
    }

    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        List<Move> legalMoves = new ArrayList<>();

        // Iterate through all the piece's offsets to find valid moves
        for (final int currentOffset : BISHOP_MOVE_OFFSETS) {
            // Find the current target position
            final int possibleTargetPosition = this.piecePosition + currentOffset;
            // Determine if the tile is on the chess board
            if (IsValidTilePosition(possibleTargetPosition)) {
                // Determine if the piece is on an edge
                if (Math.abs (((this.piecePosition % 8)) - ((possibleTargetPosition % 8))) > 2){
                    continue;
                }

                final Tile possibleTargetTile = board.getTile(possibleTargetPosition);
                // Determine if the tile is empty or occupied
                if (!possibleTargetTile.isTileOccupied()) {
                    // TODO: implement normal move
                    // The piece can move to the empty tile.
                    legalMoves.add(new Move());
                } else {
                    final Piece targetPiece = possibleTargetTile.getPiece();
                    final Alliance targetPieceAlliance = targetPiece.getPieceAlliance();
                    // Determine if the target piece is the enemy's
                    if (this.pieceAlliance != targetPieceAlliance) {
                        // TODO: implement capture move
                        // The piece can capture the enemy's piece.
                        legalMoves.add(new Move());
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}
