package java五子棋;
public interface Config {
	//定义棋盘起始点坐标（x,y）,以及棋盘规格，多少行（row）多少列（column）,以及格子大小（size）
		public static final int x=30,y=30,row=15,column=15,size=50;
	//定义存放棋子地址的数组
		public static final int[][]chessArray=new int [row][column];
	}
