package com.chess.engine.board.move;

import com.chess.engine.board.Board;

import java.util.concurrent.Future;

/**
 * This class represents the transition from one board to another based on a move.
 */
public class MoveTransition {
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a move transition.
     * @param transitionBoard then board after a move is done
     * @param move            what the piece is trying to do
     * @param moveStatus      the status of the move
     */
    public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus) {
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }
    //********************************************************
    //**********************Main Methods**********************
    //********************************************************
    public MoveStatus getMoveStatus() {
        return this.moveStatus;
    }
}
