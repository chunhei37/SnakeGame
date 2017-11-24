
public class Board {
	private BoardCell[][] bc = new BoardCell[10][10];
	
	public Board() {
		for(int i = 0; i < bc.length; i++) {
			for(int p = 0; p < bc[i].length; p++) {
				bc[i][p] = new BoardCell(false, false, false, false, i, p);
			}
		}
		readObject();
	}
	
	private void readObject() {
	 	//  					   SH   ST      LT    LB    Dest
		bc[0][0] = new BoardCell(false, false, false, true, 3, 2);
		bc[0][3] = new BoardCell(false, false, false, true, 1, 6);
		bc[0][6] = new BoardCell(false, true, false, false, 0, 6);
		bc[0][8] = new BoardCell(false, false, false, true, 3, 9);
		bc[1][1] = new BoardCell(false, true, false, false, 1, 1);
		bc[1][3] = new BoardCell(true, false, false, false, 0, 6);
		bc[1][6] = new BoardCell(false, false, true, false, 1, 6);
		bc[2][0] = new BoardCell(false, false, false, true, 4, 1);
		bc[2][3] = new BoardCell(false, true, false, false, 2, 3);
		bc[2][7] = new BoardCell(false, false, false, true, 8, 3);
		bc[3][6] = new BoardCell(false, true, false, false, 3, 6);
		bc[3][9] = new BoardCell(false, false, true, false, 3, 9);
		bc[4][1] = new BoardCell(false, false, true, false, 4, 1);
		bc[5][0] = new BoardCell(false, true, false, false, 5, 0);
		bc[5][6] = new BoardCell(true, false, false, false, 3, 6);
		bc[5][9] = new BoardCell(false, false, false, true, 6, 6);
		bc[6][1] = new BoardCell(true, false, false, false, 1, 1);
		bc[6][3] = new BoardCell(true, false, false, false, 5, 0);
		bc[6][6] = new BoardCell(false, false, true, false, 6, 6);
		bc[7][0] = new BoardCell(false, false, false, true, 9, 0);
		bc[7][1] = new BoardCell(false, true, false, false, 7, 1);
		bc[7][5] = new BoardCell(false, true, false, false, 7, 5);
		bc[7][7] = new BoardCell(false, true, false, false, 7, 7);
		bc[7][9] = new BoardCell(false, false, false, true, 9, 9);
		bc[8][6] = new BoardCell(true, false, false, false, 2, 3);
		bc[9][2] = new BoardCell(true, false, false, false, 7, 1);
		bc[9][5] = new BoardCell(true, false, false, false, 7, 5);
		bc[9][7] = new BoardCell(true, false, false, false, 7, 7);
	}
	
	public BoardCell checkEvent(int[] cell) {
		int y = cell[0];
		int x = cell[1];
		
		return bc[y][x];
	}

}
