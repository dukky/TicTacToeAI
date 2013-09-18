package im.duk.tictactoe;

/**
 * @author Andreas Holley
 * Class to represent the game board. Uses a bitboard representation.
 *
 */
/**
 * @author Andreas
 * 
 */
public class Board {
	public static final int ROW_0 = 0b111000000;
	public static final int ROW_1 = 0b000111000;
	public static final int ROW_2 = 0b000000111;

	public static final int COL_0 = 0b100100100;
	public static final int COL_1 = 0b010010010;
	public static final int COL_2 = 0b001001001;
	
	public static final int DIAG_1 = 0b100010001;
	public static final int DIAG_2 = 0b001010100;

	public int noughts;
	public int crosses;

	/**
	 * Default constructor for Board class. Creates a new empty board.
	 */
	public Board() {
		this.noughts = 0b000000000;
		this.crosses = 0b000000000;
	}

	/**
	 * Constructor to create a board with a non empty state.
	 * 
	 * @param noughts
	 *            bitboard of noughts.
	 * @param crosses
	 *            bitboard of crosses.
	 */
	public Board(int noughts, int crosses) {
		this.noughts = noughts;
		this.crosses = crosses;
		// this.empty = ~(noughts | crosses);
	}

	/**
	 * Method to get the contents of a square on the board.
	 * 
	 * @param index
	 *            the index to check, 0-8.
	 * @return the contents of the square at the specified index.
	 */
	public Contents getAt(int index) {
		int indexBoard = bitIndex(index);

		if ((indexBoard & noughts) != 0) {
			return Contents.NOUGHT;
		} else if ((indexBoard & crosses) != 0) {
			return Contents.CROSS;
		} else {
			return Contents.EMPTY;
		}
	}

	/**
	 * Method to get the contents of a square on the board. This method should
	 * be slightly slower than the other one due to performing additional
	 * calculations.
	 * 
	 * @param x
	 *            x index to check, 0-2.
	 * @param y
	 *            y index to check, 0-2.
	 * @return the contents of the square at the specified index.
	 */
	public Contents getAt(int x, int y) {

		int pos = x + (3*y);

		if ((pos & noughts) != 0) {
			return Contents.NOUGHT;
		} else if ((pos & crosses) != 0) {
			return Contents.CROSS;
		} else {
			return Contents.EMPTY;
		}
	}

	/**
	 * Creates a bitboard to check a specific position on the board.
	 * @param index
	 * @return
	 */
	private static int bitIndex(int index) {
		int indexBoard = 0b100000000;
		if (index >= 0 && index <= 9) {
			indexBoard >>= index;
		}
		return indexBoard;
	}
	
	public void paint() {
		
	}

	public static void main(String[] args) {
		Board board = new Board(Board.DIAG_1, 0);
		System.out.println(board.getAt(7));
		System.out.println(board.getAt(0, 2));
	}
}
