package im.duk.tictactoe;

import java.awt.Point;

public abstract class Player {
	
	private Contents side;
	private String name;

	public Contents getSide() {
		return side;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Player(Contents side, String name) {
		this.side = side;
		this.name = name;
	}
	
	public abstract Point makeMove(Board gameBoard);
	

}
