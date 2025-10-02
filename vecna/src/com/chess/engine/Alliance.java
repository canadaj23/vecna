package com.chess.engine;

import com.chess.engine.players.BlackPlayer;
import com.chess.engine.players.Player;
import com.chess.engine.players.WhitePlayer;

/**
 * This enum contains everything alliance-related.
 */
public enum Alliance {
    //********************************************************
    //********************White-attuned***********************
    //********************************************************
    WHITE {
        /**
         * @return the direction for White pieces
         */
        @Override
        public int getDirection() {
            return -1;
        }

        /**
         * @return whether the piece is White
         */
        @Override
        public boolean isWhite() {
            return true;
        }

        /**
         * @return whether the piece is Black
         */
        @Override
        public boolean isBlack() {
            return false;
        }

        /**
         * @param whitePlayer in contention for move maker
         * @param blackPlayer in contention for move maker
         * @return a chose player
         */
        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return whitePlayer;
        }

        /**
         * @return the String representation of the White alliance
         */
        @Override
        public String toString() {
            return "White";
        }
    },
    //********************************************************
    //********************Black-attuned***********************
    //********************************************************
    BLACK {
        /**
         * @return the direction for Black pieces
         */
        @Override
        public int getDirection() {
            return 1;
        }

        /**
         * @return whether the piece is White
         */
        @Override
        public boolean isWhite() {
            return false;
        }

        /**
         * @return whether the piece is Black
         */
        @Override
        public boolean isBlack() {
            return true;
        }

        /**
         * @param whitePlayer in contention for move maker
         * @param blackPlayer in contention for move maker
         * @return a chose player
         */
        @Override
        public Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer) {
            return blackPlayer;
        }

        /**
         * @return the String representation of the White alliance
         */
        @Override
        public String toString() {
            return "Black";
        }
    };
    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    /**
     * @return the direction the pieces go based on the alliance
     */
    public abstract int getDirection();

    /**
     * @return whether the piece is White
     */
    public abstract boolean isWhite();

    /**
     * @return whether the piece is Black
     */
    public abstract boolean isBlack();

    /**
     * @param whitePlayer in contention for move maker
     * @param blackPlayer in contention for move maker
     * @return a chose player
     */
    public abstract Player choosePlayer(final WhitePlayer whitePlayer, final BlackPlayer blackPlayer);
}
