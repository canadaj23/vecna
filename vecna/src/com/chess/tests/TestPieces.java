package com.chess.tests;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;
import com.chess.engine.players.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestPieces {

    @Test
    void calculateLegalMoves() {
        final Board board = Board.CreateInitialBoard();
        final List<Piece> whitePieces = (List<Piece>) board.getPieces(Alliance.WHITE);
        final List<Piece> blackPieces = (List<Piece>) board.getPieces(Alliance.BLACK);

        // White's initial pieces
        for (int i = 0; i < 8; i++) {
            assertEquals(2, whitePieces.get(i).calculateLegalMoves(board).size());
        }
        assertAll("Both Rooks' legal moves",
                () -> assertEquals(0, whitePieces.get(8).calculateLegalMoves(board).size()),
                () -> assertEquals(0, whitePieces.get(15).calculateLegalMoves(board).size()));
        assertAll("Both Knights' legal moves",
                  () -> assertEquals(2, whitePieces.get(9).calculateLegalMoves(board).size()),
                  () -> assertEquals(2, whitePieces.get(14).calculateLegalMoves(board).size()));
        for (int i = 10; i < 14; i++) {
            assertEquals(0, whitePieces.get(i).calculateLegalMoves(board).size());
        }

        // Black's initial pieces
        for (int i = 10; i < 14; i++) {
            assertEquals(2, blackPieces.get(i).calculateLegalMoves(board).size());
        }
        assertAll("Both Rooks' legal moves",
                () -> assertEquals(0, blackPieces.get(0).calculateLegalMoves(board).size()),
                () -> assertEquals(0, blackPieces.get(7).calculateLegalMoves(board).size()));
        assertAll("Both Knights' legal moves",
                () -> assertEquals(2, blackPieces.get(1).calculateLegalMoves(board).size()),
                () -> assertEquals(2, blackPieces.get(6).calculateLegalMoves(board).size()));
        for (int i = 0; i < 8; i++) {
            assertEquals(2, whitePieces.get(i).calculateLegalMoves(board).size());
        }
    }

    @Test
    void movePiece() {
    }

    @Test
    void getPiecePosition() {
    }

    @Test
    void getPieceAlliance() {
    }

    @Test
    void getPieceType() {
    }

    @Test
    void isFirstMove() {
    }
}