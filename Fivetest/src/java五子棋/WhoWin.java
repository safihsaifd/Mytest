package java������;
public class WhoWin implements Config{
	 //�����ж���Ӯ�ķ���
	public boolean whowin(int r,int c) {
		if(check1(r,c)>=5||check2(r,c)>=5||check3(r,c)>=5||check4(r,c)>=5)
			return true;
		else 
			return false;
		
	}
	
	//�ж����������Ƿ���������
	private int check1(int r,int c) {
		int num=1;//��ǰ�µ�����Ĭ���Ѿ�������
		//�����жϣ��൱���в��䣬������ݼ�
		for(int i=r-1;i>=0;i--)
			if(chessArray[r][c]==chessArray[i][c])
				num++;
			else break;
		//�����ж�,�൱���в��䣬���������
		for(int i=r+1;i<chessArray[r].length;i++)
			if(chessArray[r][c]==chessArray[i][c])
				num++;
			else break;
		if(num<5) {
			num=1;
		}
		return num;
	}
	
	//�ж����������Ƿ���������
	private int check2(int r,int c) {
		int num=1;
		//�������жϣ��൱���в��䣬������ݼ�
		for(int j=c-1;j>=0;j--)
			if(chessArray[r][c]==chessArray[r][j])
				num++;
			else break;
		//�����ж�,�൱���в��䣬���������
		for(int j=c+1;j<chessArray.length;j++)
			if(chessArray[r][c]==chessArray[r][j])
				num++;
			else break;
		if(num<5) {
			num=1;
		}
		return num;
		
	}
	
	//�ж����Ϻ����·���
	private int check3(int r,int c) {
		int num=1;
		//�������ж�
		for(int i=r-1,j=c+1;i>0&&j<chessArray.length;i--,j++)
		{
			if(chessArray[r][c]==chessArray[i][j])
				num++;
			else break;
		}
		//�������ж�
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
	
	//�ж����Ϻ����·����Ƿ���������
	private int check4(int r,int c) {
		int num=1;
		//�������ж�
		for(int i=r-1,j=c-1;i>0&&j>0;i--,j--)
		{
			if(chessArray[r][c]==chessArray[i][j])
				num++;
			else break;
		}
		//�������ж�
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