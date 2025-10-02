package com.chess.engine.players;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.Piece;

import java.util.Collection;

import static com.chess.engine.Alliance.*;
import static com.chess.engine.players.PlayerUtils.CalculatePlayerCastles;

public class BlackPlayer extends Player {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for Black.
     * @param board           where the game takes place
     * @param whiteLegalMoves White's legal moves
     * @param blackLegalMoves Black's legal moves
     */
    public BlackPlayer(final Board board,
                       final Collection<Move> whiteLegalMoves,
                       final Collection<Move> blackLegalMoves) {
        // Black's legal moves are the player's legal moves and White's legal moves are the opponent's legal moves
        super(board, blackLegalMoves, whiteLegalMoves);
    }
    //********************************************************
    //**********************Main Methods**********************
    //********************************************************
    /**
     * @return a list of Black's active pieces
     */
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getPieces(BLACK);
    }

    /**
     * @return White/Black
     */
    @Override
    public Alliance getPlayerAlliance() {
        return BLACK;
    }

    /**
     * @return the player's opponent
     */
    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }

    /**
     * @param opponentLegalMoves the opponent's legal moves
     * @return a list of legal castles
     */
    @Override
    protected Collection<Move> calculateCastles(final Collection<Move> opponentLegalMoves) {
        final int[] kingsideTiles = {5, 6}, queensideTiles = {1, 2, 3};
        final int kingsideRookPosition = 7, queensideRookPosition = 0;

        return CalculatePlayerCastles(this,
                                      opponentLegalMoves,
                                      kingsideTiles,
                                      queensideTiles,
                                      kingsideRookPosition,
                                      queensideRookPosition);
    }
}
