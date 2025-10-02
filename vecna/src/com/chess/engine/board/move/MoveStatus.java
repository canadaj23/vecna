package com.chess.engine.board.move;

public enum MoveStatus {
    DONE {
        /**
         * @return whether the move is done
         */
        @Override
        public boolean isDone() {
            return true;
        }
    },
    ILLEGAL_MOVE {},
    IN_CHECK {};

    /**
     * @return whether the move is done
     */
    public boolean isDone() {
        return false;
    }
}
