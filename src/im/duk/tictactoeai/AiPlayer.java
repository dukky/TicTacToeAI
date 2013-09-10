package im.duk.tictactoeai;

import im.duk.tictactoe.Board;
import im.duk.tictactoe.Cell;
import im.duk.tictactoe.Contents;
import im.duk.tictactoe.GameMain;
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
		MiniMaxReturn move = minimax(gameBoard, 9, getSide());
		System.out.println(move.pos);
		return move.pos;
	}
	
	public Point oldMakeMove(Board gameBoard) {
		List<Point> points = genMoves(gameBoard);
		for (Point point : points) {
			Board board = cloneBoard(gameBoard);
			int row = point.y - 1;
			int col = point.x - 1;
			System.out.println("Bot playing at " + (col + 1) + "," + (row + 1)
					+ ".");
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

	private static int eval(Board gameBoard, Contents side) {
		if (side.equals(Contents.CROSS)) {
			if (gameBoard.hasWon(side)) {
				return 1;
			} else if (gameBoard.hasWon(Contents.NOUGHT)) {
				return -1;
			} else {
				return 0;
			}
		} else if(side.equals(Contents.NOUGHT)) {
			if(gameBoard.hasWon(side)) {
				return 1;
			} else if(gameBoard.hasWon(Contents.CROSS)) {
				return -1;
			} else {
				return 0;
			}
		}
		return -100;
	}
	
	private static MiniMaxReturn minimax(Board gameBoard, int depth, Contents side) {
		Point bestMove = new Point(-1, -1);
		int bestScore = -2;
		//System.out.println(depth);
		List<Point> moves = genMoves(gameBoard);
		if(depth == 0 || moves.isEmpty()) {
			return new MiniMaxReturn(eval(gameBoard, side), bestMove);
		} else {
			int currentScore = 0;
			List<MiniMaxReturn> possibleMoves = new ArrayList<>();
			for (Point point : moves) {
				Board board = cloneBoard(gameBoard);
				int row = point.y - 1;
				int col = point.x - 1;
				board.cells[row][col].setContent(side);
				board.row = row;
				board.col = col;
				if(side == Contents.CROSS) {
					MiniMaxReturn ret  = minimax(gameBoard, depth - 1, Contents.NOUGHT);
					currentScore = ret.score;
					if(currentScore > bestScore) {
						bestScore = currentScore;
						bestMove = point; 
					}
				}
				else {
					MiniMaxReturn ret = minimax(gameBoard, depth -1, Contents.CROSS);
					currentScore =  ret.score;
					if(currentScore > bestScore) {
						bestScore = currentScore;
						bestMove = point;
					}
				}
			}
			if(bestScore != 0)
			System.out.println(bestScore);
		}
		return new MiniMaxReturn(bestScore, bestMove);
	}
	
	static class MiniMaxReturn {
		int score;
		Point pos;
		
		public MiniMaxReturn(int score, Point pos) {
			this.score = score;
			this.pos = pos;
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println(new AiPlayer(Contents.CROSS, "Test").minimax(new Board(), 9, Contents.CROSS).score);
	}

}
