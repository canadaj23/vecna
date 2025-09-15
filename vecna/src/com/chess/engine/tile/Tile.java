package com.chess.engine.tile;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * This abstract class serves as the backbone for a tile on the chess board.
 */
public abstract class Tile {
    protected final int tilePosition;
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllEmptyTiles();

    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a Tile object.
     * @param tilePosition the tile's position on the board
     */
    private Tile (final int tilePosition) {
        this.tilePosition = tilePosition;
    }

    //********************************************************
    //*********************Main Methods***********************
    //********************************************************
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    /**
     * @param tilePosition where the tile is on the chess board
     * @param piece        the piece (not) on the tile
     * @return either an OccupiedTile or EmptyTile
     */
    public static Tile CreateTile(final int tilePosition, final Piece piece) {
        return piece != null ? new OccupiedTile(tilePosition, piece) : EMPTY_TILES_CACHE.get(tilePosition);
    }

    //********************************************************
    //********************Helper Methods**********************
    //********************************************************
    /**
     * @return an immutable Map cache of all 64 possible empty tiles
     */
    private static Map<Integer, EmptyTile> createAllEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < 64; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap);
    }

    //********************************************************
    //*******************EmptyTile Subclass*******************
    //********************************************************
    /**
     * This class represents an empty tile.
     */
    public static final class EmptyTile extends Tile {

        //********************************************************
        //**********************Constructor***********************
        //********************************************************
        /**
         * Constructor for an EmptyTile object.
         * @param tilePosition the tile's position on the board
         */
        private EmptyTile(final int tilePosition) {
            super(tilePosition);
        }

        //********************************************************
        //*********************Main Methods***********************
        //********************************************************
        /**
         * @return the tile is not occupied
         */
        @Override
        public boolean isTileOccupied() {
            return false;
        }

        /**
         * @return the piece on the tile
         */
        @Override
        public Piece getPiece() {
            return null;
        }
    }

    //********************************************************
    //******************OccupiedTile Subclass*****************
    //********************************************************
    /**
     * This class represents an occupied tile.
     */
    public static final class OccupiedTile extends Tile {
        private final Piece pieceOnTile;

        //********************************************************
        //**********************Constructor***********************
        //********************************************************
        /**
         * Constructor for an OccupiedTile object.
         *
         * @param tilePosition the tile's position on the board
         */
        private OccupiedTile(final int tilePosition, final Piece pieceOnTile) {
            super(tilePosition);
            this.pieceOnTile = pieceOnTile;
        }

        //********************************************************
        //*********************Main Methods***********************
        //********************************************************
        /**
         * @return the tile is occupied
         */
        @Override
        public boolean isTileOccupied() {
            return true;
        }

        /**
         * @return the piece on the tile
         */
        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
