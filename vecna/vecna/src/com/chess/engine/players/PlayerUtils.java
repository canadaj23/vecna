package com.chess.engine.players;

import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.board.move.castle.KingsideCastleMove;
import com.chess.engine.board.move.castle.QueensideCastleMove;
import com.chess.engine.board.tile.Tile;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Rook;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class performs various tasks for the Player (sub)classes.
 */
public class PlayerUtils {
    public static Collection<Move> CalculatePlayerCastles(final Player player,
                                                          final Collection<Move> playerLegalMoves,
                                                          final Collection<Move> opponentLegalMoves) {
        final List<Move> castles = new ArrayList<>();
        final Board board = player.getBoard();
        final King playerKing = player.getPlayerKing();
        final int playerKingPosition = playerKing.getPiecePosition();
        // Determine whether the King can legally move on its first turn
        if (playerKing.isFirstMove() && !player.isInCheck()) {
            // Kingside castle
            if (KingsidePossible(board, playerKingPosition + 1, playerKingPosition + 2)) {
                final Tile rookTile = board.getTile(playerKingPosition + 3);
                if (IsRookFirstMove(rookTile)) {
                    if (NoAttacksDuringKingsideCastle(playerKingPosition + 1,
                                                              playerKingPosition + 2,
                                                              opponentLegalMoves)) {
                        // Kingside castling is possible
                        castles.add(new KingsideCastleMove(board,
                                                           playerKing,
                                                           playerKingPosition + 2,
                                                           (Rook) rookTile.getPiece(),
                                                           playerKingPosition + 1));
                    }
                }
            }
            // Queenside castle
            if (QueensidePossible(board,
                                  playerKingPosition - 1,
                                  playerKingPosition - 2,
                                  playerKingPosition - 3)) {
                final Tile rookTile = board.getTile(player.getPlayerKing().getPiecePosition() - 4);
                if (IsRookFirstMove(rookTile)) {
                    if (NoAttacksDuringQueensideCastle(playerKingPosition - 1,
                                                               playerKingPosition - 2,
                                                               playerKingPosition - 3,
                                                               opponentLegalMoves)) {
                        // Queenside castling is possible
                        castles.add(new QueensideCastleMove(board,
                                                            playerKing,
                                                            playerKingPosition - 2,
                                                            (Rook) rookTile.getPiece(),
                                                            playerKingPosition - 1));
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
    private static boolean NoAttacksDuringKingsideCastle(final int firstPosition,
                                                         final int secondPosition,
                                                         final Collection<Move> opponentLegalMoves) {
        return Player.CalculateAttacksOnTile(firstPosition, opponentLegalMoves).isEmpty() &&
               Player.CalculateAttacksOnTile(secondPosition, opponentLegalMoves).isEmpty();
    }

    /**
     * @return whether the opponent can attack when the player wants to castle queenside
     */
    private static boolean NoAttacksDuringQueensideCastle(final int firstPosition,
                                                          final int secondPosition,
                                                          final int thirdPosition,
                                                          final Collection<Move> opponentLegalMoves) {
        return Player.CalculateAttacksOnTile(firstPosition, opponentLegalMoves).isEmpty() &&
               Player.CalculateAttacksOnTile(secondPosition, opponentLegalMoves).isEmpty() &&
               Player.CalculateAttacksOnTile(thirdPosition, opponentLegalMoves).isEmpty();
    }
}
