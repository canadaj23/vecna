package com.chess.tests;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;
import com.chess.engine.players.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class TestBoard {

    @Test
    @DisplayName("Create Initial Chess Board")
    void createInitialBoard() {
        final Board board = Board.CreateInitialBoard();
        assertEquals(16, board.getCurrentPlayer().getActivePieces().size());
        assertEquals(16, board.getCurrentPlayer().getOpponent().getActivePieces().size());
        assertEquals(20, board.getCurrentPlayer().getLegalMoves().size());
        assertEquals(20, board.getCurrentPlayer().getOpponent().getLegalMoves().size());
        assertEquals("White", board.getCurrentPlayer().getPlayerAlliance().toString());
        assertEquals("Black", board.getCurrentPlayer().getOpponent().getPlayerAlliance().toString());
    }

    @Test
    @DisplayName("Retrieval of an Empty and Occupied Tile")
    void getTile() {
        final Board board = Board.CreateInitialBoard();
        assertEquals("-", board.getTile(16).toString());
        assertEquals("r", board.getTile(0).toString());
    }

    @Test
    @DisplayName("Retrieval of White's and Black's current pieces")
    void getPieces() {
        final Board board = Board.CreateInitialBoard();
        final Collection<Piece> whitePieces = board.getPieces(Alliance.WHITE);
        final Collection<Piece> blackPieces = board.getPieces(Alliance.BLACK);
        // Initial pieces
        assertEquals(16, whitePieces.size());
        assertEquals(16, blackPieces.size());
        // TODO: perform a move to change number of pieces
    }

    @Test
    @DisplayName("Retrieval of White")
    void getWhitePlayer() {
        final Board board = Board.CreateInitialBoard();
        final Player whitePlayer = board.getWhitePlayer();
        assertEquals("Black", whitePlayer.getOpponent().getPlayerAlliance().toString());
        // Initial
        assertEquals(board.getCurrentPlayer(), whitePlayer);
    }

    @Test
    @DisplayName("Retrieval of Black")
    void getBlackPlayer() {
        final Board board = Board.CreateInitialBoard();
        final Player blackPlayer = board.getBlackPlayer();
        assertEquals("White", blackPlayer.getOpponent().getPlayerAlliance().toString());
        // Initial
        assertNotEquals(board.getCurrentPlayer(), blackPlayer);
    }

    @Test
    void getCurrentPlayer() {
        final Board board = Board.CreateInitialBoard();
        // Initial current player
        assertEquals("White", board.getCurrentPlayer().getPlayerAlliance().toString());
        assertEquals("Black", board.getCurrentPlayer().getOpponent().getPlayerAlliance().toString());
    }

    @Test
    void getAllLegalMoves() {
        final Board board = Board.CreateInitialBoard();
        // Initial
        assertEquals(20, board.getCurrentPlayer().getLegalMoves().size());
        assertEquals(20, board.getCurrentPlayer().getOpponent().getLegalMoves().size());
    }
}