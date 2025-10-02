package com.chess.engine.players;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.board.tile.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class performs various tasks for the Player (sub)classes.
 */
public class PlayerUtils {
    public static Collection<Move> CalculatePlayerCastles(final Player player,
                                                          final Collection<Move> opponentLegalMoves,
                                                          final int[] kingsideTiles,
                                                          final int[] queensideTiles,
                                                          final int kingsideRookPosition,
                                                          final int queensideRookPosition) {
        final List<Move> castles = new ArrayList<>();

        if (player.getPlayerKing().isFirstMove() && !player.isInCheck()) {
            final Board board = player.getBoard();
            // Kingside castle
            if (KingsidePossible(board, kingsideTiles[0], kingsideTiles[1])) {
                final Tile rookTile = board.getTile(kingsideRookPosition);
                if (IsRookFirstMove(rookTile)) {
                    if (CanOpponentAttackDuringKingsideCastle(kingsideTiles[0], kingsideTiles[1], opponentLegalMoves)) {
                        // TODO: add a castle move
                        castles.add(null);
                    }
                }
            }
            // Queenside castle
            if (QueensidePossible(board, queensideTiles[0], queensideTiles[1], queensideTiles[2])) {
                final Tile rookTile = player.getBoard().getTile(queensideRookPosition);
                if (IsRookFirstMove(rookTile)) {
                    if (CanOpponentAttackDuringQueensideCastle(queensideTiles[0], queensideTiles[1], queensideTiles[2], opponentLegalMoves)) {
                        // TODO: add a castle move
                        castles.add(null);
                    }
                }
            }
        }

        return ImmutableList.copyOf(castles);
    }

    /**
     * @param board          what the pieces are moved on
     * @param firstPosition  the first tile's position in between the King and Rook
     * @param secondPosition the second tile's position in between the King and Rook
     * @return whether there can be a kingside castle
     */
    private static boolean KingsidePossible(final Board board,
                                            final int firstPosition,
                                            final int secondPosition) {
        return !board.getTile(firstPosition).isTileOccupied() &&
               !board.getTile(secondPosition).isTileOccupied();
    }

    /**
     * @param board          what the pieces are moved on
     * @param firstPosition  the first tile's position in between the King and Rook
     * @param secondPosition the second tile's position in between the King and Rook
     * @param thirdPosition  the third tile's position in between the King and Rook
     * @return whether there can be a queenside castle
     */
    private static boolean QueensidePossible(final Board board,
                                             final int firstPosition,
                                             final int secondPosition,
                                             final int thirdPosition) {
        return !board.getTile(firstPosition).isTileOccupied() &&
               !board.getTile(secondPosition).isTileOccupied() &&
               !board.getTile(thirdPosition).isTileOccupied();
    }

    /**
     * @param rookTile where the Rook should be
     * @return whether the tile is occupied, the piece is a Rook, and it's the Rook's first move
     */
    private static boolean IsRookFirstMove(final Tile rookTile) {
        return rookTile.isTileOccupied() &&
               rookTile.getPiece().getPieceType().isRook() &&
               rookTile.getPiece().isFirstMove();
    }

    /**
     * @return whether the opponent can attack when the player wants to castle kingside
     */
    private static boolean CanOpponentAttackDuringKingsideCastle(final int firstPosition,
                                                                 final int secondPosition,
                                                                 final Collection<Move> opponentLegalMoves) {
        return Player.CalculateAttacksOnTile(firstPosition, opponentLegalMoves).isEmpty() &&
               Player.CalculateAttacksOnTile(secondPosition, opponentLegalMoves).isEmpty();
    }

    /**
     * @return whether the opponent can attack when the player wants to castle queenside
     */
    private static boolean CanOpponentAttackDuringQueensideCastle(final int firstPosition,
                                                                  final int secondPosition,
                                                                  final int thirdPosition,
                                                                  final Collection<Move> opponentLegalMoves) {
        return Player.CalculateAttacksOnTile(firstPosition, opponentLegalMoves).isEmpty() &&
               Player.CalculateAttacksOnTile(secondPosition, opponentLegalMoves).isEmpty() &&
               Player.CalculateAttacksOnTile(thirdPosition, opponentLegalMoves).isEmpty();
    }
}
