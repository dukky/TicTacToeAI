package im.duk.tictactoeai;

import im.duk.tictactoe.Board;
import im.duk.tictactoe.Contents;
import im.duk.tictactoe.Player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAiPlayer extends Player {

	public RandomAiPlayer(Contents side, String name) {
		super(side, name);
	}

	public Point makeMove(Board gameBoard) {
		int x = 0;
		int y = 0;
		List<Point> points = new ArrayList<Point>();
		
		for (int i = 0; i<3; ++i) {
			for (int j = 0; j < 3; ++j) {
				if(gameBoard.cells[i][j].getContent() == Contents.EMPTY) {
					x = j+1;
					y = i+1;
					points.add(new Point(x,y));
				}
			}
		}
		Random random = new Random();
		
		return points.get(random.nextInt(points.size()));
	}

}
