package im.duk.tictactoe;

public class Board {
	public static final int ROWS = 3;
	public static final int COLS = 3;

	public Cell[][] cells;

	public Board() {
		init();
	}

	private void init() {
		cells = new Cell[ROWS][COLS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				cells[i][j] = new Cell(i, j);
			}
		}
	}

	public boolean isDraw() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < ROWS; j++) {
				if (cells[i][j].getContent() == Contents.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean hasWon(Contents side, int row, int col) {
		
		// 3 in the row
		if (cells[row][0].getContent() == side
				&& cells[row][1].getContent() == side
				&& cells[row][2].getContent() == side) {
			return true;
		//3 in the column
		} else if (cells[0][col].getContent() == side
				&& cells[1][col].getContent() == side
				&& cells[2][col].getContent() == side) {
			return true;
		// 3 in the diagonal
		} else if (row == col) {
			if (cells[0][0].getContent() == side
					&& cells[1][1].getContent() == side
					&& cells[2][2].getContent() == side) {
				return true;
			}
		// 3 in the other diagonal
		} else if (row + col == 2) {
			if(cells[0][2].getContent() == side
					&& cells[1][1].getContent() == side
					&& cells[2][0].getContent() == side) {
				return true;
			}
		}

		return false;
	}
	
	public void paint() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				cells[i][j].paint();
				System.out.print((j < COLS - 1) ? "|" : "");
			}
			System.out.println();
			if(i < ROWS - 1) {
				System.out.println("-----------");
			}
		}
	}
	
	public static void main(String[] args) {
		Board board = new Board();
		board.cells[1][1].setContent(Contents.CROSS);
		board.cells[2][2].setContent(Contents.NOUGHT);
		board.cells[1][2].setContent(Contents.CROSS);
		board.paint();
	}

}
