package com.chess.engine.pieces.utils;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.AttackMove;
import com.chess.engine.board.move.MajorMove;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.Piece;
import com.chess.engine.board.tile.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

import static com.chess.engine.board.BoardUtils.IsValidTilePosition;

public class PieceUtils {
    /**
     * Determines the sliding piece's legal moves.
     * The sliding pieces are the Rook, Bishop, and Queen.
     * In this sense, a sliding piece can move along a vector until it approaches an obstacle.
     * @param board         where the game takes place
     * @param movedPiece    the sliding piece
     * @param piecePosition where the piece is on the board
     * @param pieceOffsets  the sliding piece's move offsets
     * @param pieceAlliance White/Black
     * @return a list of the sliding piece's legal moves
     */
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
                    legalMoves.add(new MajorMove(board, movedPiece, possibleTargetPosition));
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

    /**
     * Decides whether the move with the occupied tile is an attack.
     * @param movedPiece             the piece that is trying to move
     * @param board                  where the game takes place
     * @param possibleTargetPosition where the piece wants to move to on the chess board
     * @param pieceAlliance          White/Black
     * @param possibleTargetTile     which tile the piece wants to move to on the chess board
     * @param legalMoves             the list of all the piece's legal moves
     */
    public static void MoveWithOccupiedTile(Piece movedPiece,
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
            legalMoves.add(new AttackMove(board, movedPiece, possibleTargetPosition, targetPiece));
        }
    }
}
