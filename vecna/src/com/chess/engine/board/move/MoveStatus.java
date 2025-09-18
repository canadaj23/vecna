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
    ILLEGAL_MOVE {
        /**
         * @return whether the move is done
         */
        @Override
        public boolean isDone() {
            return false;
        }
    },
    IN_CHECK {
        /**
         * @return whether the move is done
         */
        @Override
        public boolean isDone() {
            return false;
        }
    };

    /**
     * @return whether the move is done
     */
    public abstract boolean isDone();
}
