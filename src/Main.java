import java.util.Scanner;

public class Main {

	static Status[][] board = new Status[8][8];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				board[i][j] = Status.Empty;
			}
		}
		Scanner sc = new Scanner(System.in);
		char init = sc.next().charAt(0);
		switch(init) {
		case 'a':
			initializeA();
			break;
		case 'b':
			initializeB();
			break;
		case 'c':
			initializeC();
			break;
		default:
			return;
		}
		
		int n = sc.nextInt();
		Status turn=Status.Black;
		for (int i=0;i<n;i++) {
			int row=sc.nextInt();
			int col=sc.nextInt();
			put(row,col,turn);
			if (turn==Status.Black)
				turn = Status.White;
			else 
				turn = Status.Black;
		}
		
		int blacks=0;
		int whites=0;
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				switch(board[i][j]) {
				case Black:
					blacks++;
					break;
				case White:
					whites++;
					break;
				default:
					break;
				}
			}
		}
		System.out.println(blacks+" "+whites);
		
	}
	
	private static void initializeC() {
		// TODO Auto-generated method stub
		flip(-1,2,8,2,Status.Black,1,0);
		flip(-1,3,8,3,Status.Black,1,0);
		flip(-1,4,8,4,Status.White,1,0);
		flip(-1,5,8,5,Status.White,1,0);
	}

	private static void initializeB() {
		// TODO Auto-generated method stub
		flip(-1,-1,8,8,Status.Black,1,1);
		flip(-1,8,8,-1,Status.White,1,-1);
	}

	private static void initializeA() {
		// TODO Auto-generated method stub
		flip(3,3,6,6,Status.White,1,1);
		flip(6,3,3,6, Status.Black,-1,1);
		
	}

	private static void put(int row, int col, Status turn) {
		// TODO Auto-generated method stub
		check(row,col,turn,-1,0); // up, decrease row by 1 each step
		check(row,col,turn, 1, 0); // down
		check(row,col,turn,0,-1); // left
		check(row,col,turn,0, 1); // right;
		check(row,col,turn,-1,-1); // left up
		check(row,col, turn, 1,1); // right down
		check(row,col,turn,-1,1); // right top;
		check(row,col,turn, 1,-1); // left down;
	}

	private static void check(int row, int col, Status turn, int deltaR, int deltaC) {
		// TODO Auto-generated method stub
		Status opStatus = (turn==Status.Black)?Status.White:Status.Black;
		
		int newRow = row+deltaR;
		int newCol = col+deltaC;

		while (inBoudary(newRow,newCol) && board[newRow][newCol] == Status.White) {
			newRow+=deltaR;
			newCol+=deltaC;
		}
		if (inBoudary(newRow,newCol) && board[newRow][newCol] == turn)
			flip(row,col, newRow,newCol, opStatus, deltaR,deltaC);
		
	}

	private static void flip(int row, int col, int newRow, int newCol, Status opStatus, int deltaR, int deltaC) {
		// TODO Auto-generated method stub
		int r=row+deltaR;
		int c=col+deltaC;
		
		while (r!=newRow && c!=newCol) {
			board[r][c] = opStatus;
			r+=deltaR;
			c+=deltaC;
		}
	}

	private static boolean inBoudary(int r, int c) {
		// TODO Auto-generated method stub
		return r>=0 && r<8 && c>=0 && col<8;
	}

}

enum Status {
	Empty,
	Black,
	White
}
