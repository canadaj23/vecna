package com.chess.engine.players;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.move.Move;
import com.chess.engine.pieces.Piece;

import java.util.Collection;

import static com.chess.engine.Alliance.*;
import static com.chess.engine.players.PlayerUtils.*;

public class WhitePlayer extends Player {
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for White.
     * @param board           where the game takes place
     * @param whiteLegalMoves White's legal moves
     * @param blackLegalMoves Black's legal moves
     */
    public WhitePlayer(final Board board,
                       final Collection<Move> whiteLegalMoves,
                       final Collection<Move> blackLegalMoves) {
        // White's legal moves are the player's legal moves and Black's legal moves are the opponent's legal moves
        super(board, whiteLegalMoves, blackLegalMoves);
    }
    //********************************************************
    //**********************Main Methods**********************
    //********************************************************
    /**
     * @return a list of White's active pieces
     */
    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getPieces(WHITE);
    }

    /**
     * @return White/Black
     */
    @Override
    public Alliance getPlayerAlliance() {
        return WHITE;
    }

    /**
     * @return the player's opponent
     */
    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }

    /**
     * @param opponentLegalMoves the opponent's legal moves
     * @return a list of legal castles
     */
    @Override
    protected Collection<Move> calculateCastles(final Collection<Move> playerLegalMoves,
                                                final Collection<Move> opponentLegalMoves) {
        return CalculatePlayerCastles(this,
                                      playerLegalMoves,
                                      opponentLegalMoves);
    }
}
