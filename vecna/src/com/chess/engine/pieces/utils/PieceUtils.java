package com.chess.engine.pieces.utils;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Piece;
import com.chess.engine.tile.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.BoardUtils.IsValidTilePosition;

public class PieceUtils {
    public static List<Move> CalculateSlidingLegalMoves(final Board board,
                                                       final Piece movedPiece,
                                                       final int piecePosition,
                                                       final int[] pieceOffsets,
                                                       final Alliance pieceAlliance) {
        List<Move> legalMoves = new ArrayList<>();
        // Iterate through all the piece's offsets to find valid moves
        for (final int currentOffset : pieceOffsets) {
            int possibleTargetPosition = piecePosition;
            // Determine if the tile is on the chess board
            while (IsValidTilePosition(possibleTargetPosition)) {
                // Determine if the piece is on an edge
                if (Math.abs(((piecePosition % 8)) - ((possibleTargetPosition % 8))) > 1) {
                    break;
                }
                final Tile possibleTargetTile = board.getTile(possibleTargetPosition);
                // Determine if the tile is empty or occupied.
                if (!possibleTargetTile.isTileOccupied()) {
                    // The piece can move to the empty tile.
                    legalMoves.add(new Move.MajorMove(board, movedPiece, possibleTargetPosition));
                } else {
                    // There is an enemy or friendly piece in the way.
                    MoveWithOccupiedTile(
                            movedPiece,
                            board,
                            possibleTargetPosition,
                            pieceAlliance,
                            possibleTargetTile,
                            legalMoves);
                    // Cannot move since the tile is occupied, regardless of the target's alliance.
                    break;
                }
                // Find the next target's position
                possibleTargetPosition += currentOffset;
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    private static void MoveWithOccupiedTile(Piece movedPiece,
                                      Board board,
                                      int possibleTargetPosition,
                                      Alliance pieceAlliance,
                                      Tile possibleTargetTile,
                                      List<Move> legalMoves) {
        final Piece targetPiece = possibleTargetTile.getPiece();
        final Alliance targetPieceAlliance = targetPiece.getPieceAlliance();
        // Determine if the target piece is the enemy's.
        if (pieceAlliance != targetPieceAlliance) {
            // The piece can capture the enemy's piece.
            legalMoves.add(new Move.AttackMove(board, movedPiece, possibleTargetPosition, targetPiece));
        }
    }
}
