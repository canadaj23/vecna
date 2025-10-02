import com.chess.engine.board.Board;
import com.chess.gui.GameWindow;

public class Vecna {
    public static void main(String[] args) {
        Board board = Board.CreateInitialBoard();

        System.out.println(board);

        new GameWindow();
    }
}
