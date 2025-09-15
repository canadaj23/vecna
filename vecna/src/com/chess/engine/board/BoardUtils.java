package com.chess.engine.board;

public class BoardUtils {
    // Tile-related
    public static final int START_TILE_INDEX = 0;
    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    /**
     * @param position where the tile might be on the chess board
     * @return whether the tile is on the chess board
     */
    public static boolean IsValidTilePosition(final int position) {
        return position >= START_TILE_INDEX && position < NUM_TILES;
    }
}
