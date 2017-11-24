
public class BoardCell {
	private boolean snakeHead;
	private boolean snakeTail;
	private boolean ladderTop;
	private boolean ladderBottom;
	private int[] destination = new int[2];
	
	public BoardCell(boolean snakeHead, boolean snakeTail, boolean ladderTop, boolean ladderBottom, int destinationX, int destinationY) {
		setSnakeHead(snakeHead);
		setSnakeTail(snakeTail);
		setLadderTop(ladderTop);
		setLadderBottom(ladderBottom);
		setDestination(destinationX, destinationY);
	}
	
	
	public void setSnakeHead(boolean snakeHead) {
		this.snakeHead = snakeHead;
	}
	
	public boolean isSnakeHead() {
		return snakeHead;
	}
	
	public void setSnakeTail(boolean snakeTail) {
		this.snakeTail = snakeTail;
	}
	
	public boolean isSnakeTail() {
		return snakeTail;
	}
	
	public void setLadderTop(boolean ladderTop) {
		this.ladderTop = ladderTop;
	}
	
	public boolean isLadderTop() {
		return ladderTop;
	}
	
	public void setLadderBottom(boolean ladderBottom) {
		this.ladderBottom = ladderBottom;
	}
	
	public boolean isLadderBottom() {
		return ladderBottom;
	}
	
	public void setDestination(int x, int y) {
		destination[0] = x;
		destination[1] = y;
	}
	
	public int[] getDestination() {
		return destination ;
	}
	
}
