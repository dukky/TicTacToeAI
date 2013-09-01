package im.duk.tictactoe;

public class Cell {

	private Contents content;
	private int row;
	private int col;

	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		clear();
	}
	
	public Cell(Cell c) {
		this.content = c.content;
		this.row = c.row;
		this.col = c.col;
	}
	
	public void clear() {
		this.content = Contents.EMPTY;
	}

	public void paint() {
		switch (content) {
		case EMPTY:
			System.out.print("   ");
			break;
		case CROSS:
			System.out.print(" X ");
			break;
		case NOUGHT:
			System.out.print(" O ");
			break;
		}
	}

	public Contents getContent() {
		return content;
	}

	public void setContent(Contents content) {
		this.content = content;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}
