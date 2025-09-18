package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.MajorMove;
import com.chess.engine.board.move.Move;
import com.chess.engine.board.tile.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.BoardUtils.IsValidTilePosition;
import static com.chess.engine.pieces.Piece.PieceType.KING;
import static com.chess.engine.pieces.utils.PieceMoveOffsets.QUEEN_KING_MOVE_OFFSETS;
import static com.chess.engine.pieces.utils.PieceUtils.MoveWithOccupiedTile;

public class King extends Piece {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a piece.
     *
     * @param pieceAlliance the piece's alliance
     * @param piecePosition the piece's position on the chess board
     */
    public King(Alliance pieceAlliance, int piecePosition) {
        super(KING, pieceAlliance, piecePosition);
    }
    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    /**
     * Determines all the piece's legal moves currently on the chess board.
     *
     * @param board what the piece will be moving on
     * @return a collection of all the piece's legal moves on the current chess board
     */
    @Override
    public Collection<Move> calculateLegalMoves(Board board) {
        List<Move> legalMoves = new ArrayList<>();
        // Iterate through all the piece's offsets to find valid moves
        for (final int currentOffset : QUEEN_KING_MOVE_OFFSETS) {
            // Find the next target's position
            final int possibleTargetPosition = this.piecePosition + currentOffset;
            // Determine if the tile is on the chess board
            if (IsValidTilePosition(possibleTargetPosition)) {
                // Determine if the piece is on an edge
                if (Math.abs(((this.piecePosition % 8)) - ((possibleTargetPosition % 8))) > 1) {
                    continue;
                }
                final Tile possibleTargetTile = board.getTile(possibleTargetPosition);
                // Determine if the tile is empty or occupied
                if (!possibleTargetTile.isTileOccupied()) {
                    // The piece can move to the empty tile.
                    legalMoves.add(new MajorMove(board, this, possibleTargetPosition));
                } else {
                    MoveWithOccupiedTile(this,
                                         board,
                                         possibleTargetPosition,
                                         this.getPieceAlliance(),
                                         possibleTargetTile,
                                         legalMoves);
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    /**
     * @return a String representation of a King
     */
    @Override
    public String toString() {
        return KING.toString();
    }
}
