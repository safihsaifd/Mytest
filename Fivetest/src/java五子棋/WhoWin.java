package java五子棋;
public class WhoWin implements Config{
	 //定义判断输赢的方法
	public boolean whowin(int r,int c) {
		if(check1(r,c)>=5||check2(r,c)>=5||check3(r,c)>=5||check4(r,c)>=5)
			return true;
		else 
			return false;
		
	}
	
	//判断上下棋子是否五子相连
	private int check1(int r,int c) {
		int num=1;//当前下的棋子默认已经被计算
		//向上判断，相当于列不变，行坐标递减
		for(int i=r-1;i>=0;i--)
			if(chessArray[r][c]==chessArray[i][c])
				num++;
			else break;
		//向下判断,相当于列不变，行坐标递增
		for(int i=r+1;i<chessArray[r].length;i++)
			if(chessArray[r][c]==chessArray[i][c])
				num++;
			else break;
		if(num<5) {
			num=1;
		}
		return num;
	}
	
	//判断左右棋子是否五子相连
	private int check2(int r,int c) {
		int num=1;
		//向做左判断，相当于行不变，列坐标递减
		for(int j=c-1;j>=0;j--)
			if(chessArray[r][c]==chessArray[r][j])
				num++;
			else break;
		//向右判断,相当于行不变，列坐标递增
		for(int j=c+1;j<chessArray.length;j++)
			if(chessArray[r][c]==chessArray[r][j])
				num++;
			else break;
		if(num<5) {
			num=1;
		}
		return num;
		
	}
	
	//判断右上和左下方向
	private int check3(int r,int c) {
		int num=1;
		//向右上判断
		for(int i=r-1,j=c+1;i>0&&j<chessArray.length;i--,j++)
		{
			if(chessArray[r][c]==chessArray[i][j])
				num++;
			else break;
		}
		//向左下判断
		for(int i=r+1,j=c-1;i<chessArray.length&&j>0;i++,j--)
		{
			if(chessArray[r][c]==chessArray[i][j])
				num++;
			else break;
		}
		if(num<5) {
			num=1;
		}
		return num;
		
	}
	
	//判断左上和右下方向是否五子相连
	private int check4(int r,int c) {
		int num=1;
		//向左上判断
		for(int i=r-1,j=c-1;i>0&&j>0;i--,j--)
		{
			if(chessArray[r][c]==chessArray[i][j])
				num++;
			else break;
		}
		//向右下判断
		for(int i=r+1,j=c+1;i<chessArray.length&&j<chessArray.length;i++,j++)
		{
			if(chessArray[r][c]==chessArray[i][j])
				num++;
			else break;
		}
		if(num<5) {
			num=1;
		}
		return num;	
	}
	
}