package im.duk.tictactoeai;

import im.duk.tictactoe.Board;
import im.duk.tictactoe.Cell;
import im.duk.tictactoe.Contents;
import im.duk.tictactoe.Player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AiPlayer extends Player {

	public AiPlayer(Contents side, String name) {
		super(side, name);
	}

	@Override
	public Point makeMove(Board gameBoard) {
		List<Point> points = genMoves(gameBoard);
		for (Point point : points) {
			Board board = cloneBoard(gameBoard);
			int row = point.y - 1;
			int col = point.x - 1;
			System.out.println("Bot playing at " + (col + 1) + "," + (row + 1) + ".");
			board.cells[row][col].setContent(getSide());
			board.row = row;
			board.col = col;

			if (board.hasWon(getSide())) {
				return point;
			}
		}
		Random random = new Random();

		return points.get(random.nextInt(points.size()));

	}

	private static Board cloneBoard(Board gameBoard) {
		return new Board(gameBoard);
	}

	public static List<Point> genMoves(Board gameBoard) {
		int x = 0;
		int y = 0;
		List<Point> points = new ArrayList<Point>();

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if (gameBoard.cells[i][j].getContent() == Contents.EMPTY) {
					x = j + 1;
					y = i + 1;
					points.add(new Point(x, y));
				}
			}
		}
		return points;
	}

}
