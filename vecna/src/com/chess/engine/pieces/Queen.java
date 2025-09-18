package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.*;
import com.chess.engine.board.tile.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.BoardUtils.IsValidTilePosition;
import static com.chess.engine.pieces.Piece.PieceType.QUEEN;
import static com.chess.engine.pieces.utils.PieceMoveOffsets.QUEEN_KING_MOVE_OFFSETS;
import static com.chess.engine.pieces.utils.PieceUtils.CalculateSlidingLegalMoves;
import static com.chess.engine.pieces.utils.PieceUtils.MoveWithOccupiedTile;

public class Queen extends Piece {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a Queen.
     *
     * @param pieceAlliance the Queen's alliance
     * @param piecePosition the Queen's position on the chess board
     */
    public Queen(final Alliance pieceAlliance, final int piecePosition) {
        super(QUEEN, pieceAlliance, piecePosition);
    }
    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    /**
     * Determines all the Queen's legal moves currently on the chess board.
     * @param board what the Queen will be moving on
     * @return a collection of all the Queen's legal moves on the current chess board
     */
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        // In class version
//        calculateQueenLegalMoves(board);
        // Sliding piece version
        List<Move> legalMoves = CalculateSlidingLegalMoves(
                board,
                this,
                this.piecePosition,
                QUEEN_KING_MOVE_OFFSETS,
                this.pieceAlliance);
        return ImmutableList.copyOf(legalMoves);
    }

    /**
     * Determines all the Queen's legal moves currently on the chess board.
     * @param board what the Queen will be moving on
     * @return a collection of all the Queen's legal moves on the current chess board
     */
    private Collection<Move> calculateQueenLegalMoves(final Board board) {
        List<Move> legalMoves = new ArrayList<>();
        // Iterate through all the piece's offsets to find valid moves
        for (final int currentOffset : QUEEN_KING_MOVE_OFFSETS) {
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
                    MoveWithOccupiedTile(this,
                            board,
                            possibleTargetPosition,
                            this.getPieceAlliance(),
                            possibleTargetTile,
                            legalMoves);
                    // Cannot move since the tile is occupied, regardless of the target's alliance
                    break;
                }
                // Find the next target's position
                possibleTargetPosition += currentOffset;
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    /**
     * @return a String representation of a Queen
     */
    @Override
    public String toString() {
        return QUEEN.toString();
    }
}
