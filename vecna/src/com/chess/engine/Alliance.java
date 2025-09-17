package com.chess.engine;

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
}
