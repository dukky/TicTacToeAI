package im.duk.tictactoe;

import java.util.Scanner;

public class GameMain {

	private Board board;
	private GameState currentState;
	private Contents currentPlayer;
	
	private static Scanner in = new Scanner(System.in);
	
	public GameMain() {
		initGame();
	}
	
	private void initGame() {
		board = new Board();
		currentPlayer = Contents.CROSS;
		currentState = GameState.PLAYING;
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Test");
	}

}
