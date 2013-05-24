package im.duk.tictactoe;

import im.duk.tictactoeai.AiPlayer;

import java.awt.Point;
import java.util.Scanner;

public class GameMain {

	private Board board;
	private GameState currentState;
	private Contents currentPlayer;
	private AiPlayer bot;
	private static Scanner in = new Scanner(System.in);

	public GameMain() {
		initGame();
	}

	private void initGame() {
		board = new Board();
		currentPlayer = Contents.CROSS;
		System.out.println("Play with bot? y/n");
		boolean validInput = false;
		while (!validInput) {
			String res = in.nextLine();
			if (res.equals("y")) {
				validInput = true;
				bot = new AiPlayer(Contents.NOUGHT);
			} else if (res.equals("n")) {
				validInput = true;
				bot = new AiPlayer(Contents.EMPTY);
			} else {
				System.out.println("Invalid input, please enter only y or n.");
			}
		}
		currentState = GameState.PLAYING;
	}

	public void updateGame(Contents side) {
		if (board.hasWon(side)) {
			currentState = (side == Contents.CROSS) ? GameState.CROSS_WON
					: GameState.NOUGHT_WON;
		} else if (board.isDraw()) {
			currentState = GameState.DRAW;
		}
	}

	public void playerMove(Contents side) {
		boolean validInput = false;
		if (bot.getSide() != side) {
			while (!validInput) {
				if (side == Contents.CROSS) {
					System.out
							.println("Player X enter your move col(1-3),row(1-3):");
				} else {
					System.out
							.println("Player O enter your move col(1-3),row(1-3):");
				}
				String[] input = in.nextLine().split(",");
				int row = Integer.valueOf(input[1]) - 1;
				int col = Integer.valueOf(input[0]) - 1;
				if (row >= 0 && row < Board.ROWS && col >= 0
						&& col < Board.COLS
						&& board.cells[row][col].getContent() == Contents.EMPTY) {
					board.cells[row][col].setContent(side);
					board.row = row;
					board.col = col;
					validInput = true;
				} else {
					System.out.println("Invalid move at " + (col + 1) + ", "
							+ (row + 1) + ". Please try again.");
				}
			}
		} else {
			Point move = bot.makeMove(board);
			int row = move.y - 1;
			int col = move.x - 1;
			System.out.println("Bot playing at " + (col+1) + "," + (row +1) + ".");
			board.cells[row][col].setContent(Contents.NOUGHT);
			board.row = row;
			board.col = col;
			
		}
	}

	public void playGame() {
		while (currentState == GameState.PLAYING) {
			playerMove(currentPlayer);
			board.paint();
			updateGame(currentPlayer);
			switch (currentState) {
			case DRAW:
				System.out.println("It's a draw!");
				break;
			case CROSS_WON:
				System.out.println("X Won!");
				break;
			case NOUGHT_WON:
				System.out.println("O Won!");
				break;
			default:
				System.out.println("Still playing");
				break;
			}
			currentPlayer = (currentPlayer == Contents.CROSS) ? Contents.NOUGHT
					: Contents.CROSS;
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GameMain().playGame();
	}

}
