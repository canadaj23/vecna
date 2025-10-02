package com.chess.gui;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.move.Move;
import com.chess.engine.board.move.MoveTransition;
import com.chess.engine.board.tile.Tile;
import com.chess.engine.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.SwingUtilities.isLeftMouseButton;
import static javax.swing.SwingUtilities.isRightMouseButton;

public class GameWindow {
    private final JFrame gameFrame;
    private final BoardPanel boardPanel;
    private final Board chessboard;

    private Tile sourceTile;
    private Tile destinationTile;
    private Piece humanMovedPiece;

    protected final static float SCALE = 1.15f;
    private final static Dimension OUTER_FRAME_DIMENSION = new Dimension((int) (900 * SCALE), (int) (900 * SCALE));
    protected final static Dimension BOARD_PANEL_DIMENSION = new Dimension((int) (400 * SCALE), (int) (350 * SCALE));
    protected final static Dimension TILE_PANEL_DIMENSION = new Dimension((int) (10 * SCALE), (int) (10 * SCALE));
    //********************************************************
    //**********************Constructor***********************
    //********************************************************
    /**
     * Constructor for a GameWindow object.
     */
    public GameWindow() {
        this.gameFrame = new JFrame("Vecna");
        this.chessboard = Board.CreateInitialBoard();
        this.gameFrame.setLayout(new BorderLayout());
        final JMenuBar windowMenuBar = createWindowMenuBar();
        this.gameFrame.setJMenuBar(windowMenuBar);

        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);

        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.gameFrame.add(gamePanel);
        this.gameFrame.setResizable(false);
        this.gameFrame.pack();
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setVisible(true);
    }
    //********************************************************
    //**********************Main Methods**********************
    //********************************************************
    /**
     * @return a menu bar for the game window
     */
    private JMenuBar createWindowMenuBar() {
        final JMenuBar windowMenuBar = new JMenuBar();

        windowMenuBar.add(createFileOption());

        return  windowMenuBar;
    }

    /**
     * @return the file option with its choices
     */
    private JMenu createFileOption() {
        final JMenu fileOption = new JMenu("File");

        final JMenuItem openPGN = new JMenuItem("Load PGN File");
        openPGN.addActionListener(actionEvent -> System.out.println("Open that PGN file!"));
        fileOption.add(openPGN);

        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(actionEvent -> System.exit(0));
        fileOption.add(exitMenuItem);

        return fileOption;
    }
    //********************************************************
    //**********************BoardPanel************************
    //********************************************************
    /**
     * Visual component for the chess board.
     * This will have tiles and pieces drawn on the board.
     */
    private class BoardPanel extends JPanel {
        private final java.util.List<TilePanel> boardTiles;
        //********************************************************
        //**********************Constructor***********************
        //********************************************************
        /**
         * Constructor for a BoardPanel object.
         */
        public BoardPanel() {
            super(new GridLayout(8, 8));
            boardTiles = new ArrayList<>();
            addTilesToBoard(this.boardTiles);
            setPreferredSize(BOARD_PANEL_DIMENSION);
            validate();
        }
        //********************************************************
        //**********************Main Methods**********************
        //********************************************************
        /**
         * Adds all tiles (occupied and empty) to the board to be displayed.
         *
         * @param boardTiles      the list of tiles on the board
         */
        private void addTilesToBoard(final List<TilePanel> boardTiles) {
            for (int i = 0; i < BoardUtils.NUM_TILES; i++) {
                final TilePanel tilePanel = new TilePanel(this, i);
                boardTiles.add(tilePanel);
                add(tilePanel);
            }
        }
    }
    //********************************************************
    //***********************TilePanel************************
    //********************************************************
    /**
     * Visual component for all tiles on the chess board.
     */
    private class TilePanel extends JPanel {
        private final int tileID;
        private final Color lightTileColor = Color.decode("#eeeed2");
        private final Color darkTileColor = Color.decode("#769656");
        private final String defaultPieceImagePath = "res/pieces/";
        //********************************************************
        //**********************Constructor***********************
        //********************************************************
        /**
         * Constructor for a TilePanel object.
         */
        TilePanel(final BoardPanel boardPanel,
                  final int tileID) {
            super(new GridBagLayout());
            this.tileID = tileID;
            setPreferredSize(TILE_PANEL_DIMENSION);
            assignTileColor();
            assignTilePieceIcon(chessboard);
            addMouseListeners(chessboard);
            validate();
        }
        //********************************************************
        //**********************Main Methods**********************
        //********************************************************
        /**
         * Assigns an occupied tile a piece icon.
         * @param board what the tiles are on
         */
        private void assignTilePieceIcon(final Board board) {
            this.removeAll();
            if (board.getTile(this.tileID).isTileOccupied()) {
                final char allianceChar = board.getTile(this.tileID).getPiece().getPieceAlliance().toString().charAt(0);
                final String pieceName = board.getTile(this.tileID).getPiece().toString();
                final String fileName = allianceChar + pieceName;
                try {
                    BufferedImage image = ImageIO.read(new File(defaultPieceImagePath + fileName + ".png"));
                    final int scaledImageWidth = (int) (image.getWidth() * SCALE);
                    final int scaledImageHeight = (int) (image.getHeight() * SCALE);
                    add(new JLabel(new ImageIcon(
                                   new ImageIcon(image).getImage().getScaledInstance(scaledImageWidth,
                                                                                     scaledImageHeight,
                                                                                     Image.SCALE_AREA_AVERAGING))));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        /**
         * Assigns a tile color based on an even or odd tileID
         */
        private void assignTileColor() {
            setBackground((tileID + tileID / 8) % 2 == 0 ? lightTileColor : darkTileColor);
        }

        /**
         * Appropriately implements mouse listeners for the chess game.
         */
        private void addMouseListeners(final Board chessboard) {
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(final MouseEvent e) {
                    if (isRightMouseButton(e)) {
                        // Right click cancels all selections
                        sourceTile = null;
                        destinationTile = null;
                        humanMovedPiece = null;
                    } else if (isLeftMouseButton(e)) {
                        // User hasn't clicked a source tile
                        if (sourceTile == null) {
                            sourceTile = chessboard.getTile(tileID);
                            humanMovedPiece = sourceTile.getPiece();
                            if (humanMovedPiece == null) {
                                sourceTile = null;
                            }
                        } else {
                            // User has clicked a source tile
                            destinationTile = chessboard.getTile(tileID);
                            // TODO: assign an actual move
                            final Move move = null;
                            final MoveTransition transition = chessboard.getCurrentPlayer().makeMove(move);
                        }
                    }
                }

                @Override
                public void mousePressed(final MouseEvent e) {}

                @Override
                public void mouseReleased(final MouseEvent e) {}

                @Override
                public void mouseEntered(final MouseEvent e) {}

                @Override
                public void mouseExited(final MouseEvent e) {}
            });
        }
    }
}
