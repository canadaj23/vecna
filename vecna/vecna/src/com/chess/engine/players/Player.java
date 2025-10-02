package com.chess.engine.players;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.MoveStatus;
import com.chess.engine.board.move.MoveTransition;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    private final boolean inCheck;
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a player.
     * @param board             where the game takes place
     * @param legalMoves   the first collection of legal moves
     * @param opponentMoves  the second collection of legal moves
     */
    public Player(final Board board,
                  final Collection<Move> legalMoves,
                  final Collection<Move> opponentMoves) {
        this.board = board;
        this.playerKing = establishKing();
        this.legalMoves = ImmutableList.copyOf(Iterables.concat(legalMoves, calculateCastles(legalMoves,
                                                                                             opponentMoves)));
        this.inCheck = !CalculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
    }

    protected static Collection<Move> CalculateAttacksOnTile(final int piecePosition,
                                                             final Collection<Move> moves) {
        final List<Move> attackMoves = new ArrayList<>();
        for (final Move move : moves) {
            if (piecePosition == move.getTargetPosition()) {
                attackMoves.add(move);
            }
        }

        return ImmutableList.copyOf(attackMoves);
    }
    //********************************************************
    //**********************Main Methods**********************
    //********************************************************
    /**
     * @return a list of the player's active pieces
     */
    public abstract Collection<Piece> getActivePieces();

    /**
     * @return White/Black
     */
    public abstract Alliance getPlayerAlliance();

    /**
     * @return the player's opponent
     */
    public abstract Player getOpponent();

    /**
     * @param opponentLegalMoves the opponent's legal moves
     * @return a list of legal castles
     */
    protected abstract Collection<Move> calculateCastles(final Collection<Move> playerLegalMoves,
                                                         final Collection<Move> opponentLegalMoves);

    /**
     * Ensures that the player has one King for the game.
     * @return a King that the player should always have
     */
    private King establishKing() {
        for (final Piece piece : getActivePieces()) {
            if (piece.getPieceType().isKing()) {
                return (King) piece;
            }
        }

        throw new RuntimeException("The player does not have a King!!!");
    }

    /**
     * @param move what the piece is trying to do
     * @return whether the move is legal
     */
    public boolean isMoveLegal(final Move move) {
        return this.legalMoves.contains(move);
    }

    /**
     * @return whether the King is in check
     */
    public boolean isInCheck() {
        return this.inCheck;
    }

    /**
     * @return whether the King is in a checkmate
     */
    public boolean isInCheckmate() {
        return this.inCheck && !hasEscapeMoves();
    }

    /**
     * @return whether the King is in a stalemate
     */
    public boolean isInStalemate() {
        return !this.inCheck && !hasEscapeMoves();
    }

    /**
     * @return whether the King can escape from being in check
     */
    protected boolean hasEscapeMoves() {
        for (final Move move : this.legalMoves) {
            final MoveTransition moveTransition = makeMove(move);
            if (moveTransition.getMoveStatus().isDone()) {
                return true;
            }
        }

        return false;
    }

    // TODO: implement castle
    /**
     * @return whether a castle has been performed
     */
    public boolean isCastled() {
        return false;
    }

    /**
     * @param move what the piece is trying to do
     * @return a transition based on a move
     */
    public MoveTransition makeMove(final Move move) {
        // Illegal move
        if (!isMoveLegal(move)) {
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
        }
        // Legal move
        final Board transitionBoard = move.execute();
        final Player currentPlayer = transitionBoard.getCurrentPlayer();
        final King opponentKing = currentPlayer.getOpponent().getPlayerKing();
        final Collection<Move> attacksOnKing = CalculateAttacksOnTile(opponentKing.getPiecePosition(),
                                                                    currentPlayer.getLegalMoves());
        // In check
        if (!attacksOnKing.isEmpty()) {
            return new MoveTransition(this.board, move, MoveStatus.IN_CHECK);
        }

        return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
    }

    /**
     * @return the player's King
     */
    public King getPlayerKing() {
        return this.playerKing;
    }

    /**
     * @return the player's legal moves
     */
    public Collection<Move> getLegalMoves() {
        return this.legalMoves;
    }

    /**
     * @return the board the player is playing on
     */
    public Board getBoard() {
        return this.board;
    }
}
