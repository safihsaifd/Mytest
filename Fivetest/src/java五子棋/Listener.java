package java五子棋;
import static java五子棋.Config.x;
import static java五子棋.Config.y;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Listener extends MouseAdapter implements ActionListener,Config{
	private int chess=0,m,n;
	private int start=0;//控制下棋标志
	private int flag;//奇数下黑棋，偶数下白棋
	private Graphics2D g;
	private JPanel panel;
	private JComboBox<String> item;
	WhoWin pk = new WhoWin();//实例化判断输赢类的对象
	int setX[] = new int[row * column];//用来存放每个棋子的X的顺序
	int setY[] = new int[row * column];//用来存放每个棋子的Y的顺序
	//重载构造方法，将面板和下拉框对象传递过来
	public Listener(JPanel a,JComboBox<String> item) {
		this.panel=a;
		this.item=item;
	}
	 
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("开始下棋")){
			System.out.println("开始下棋");
			g=(Graphics2D)panel.getGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
RenderingHints.VALUE_ANTIALIAS_ON);//设置抗锯齿
			start=1;//start=1控制可以下棋
			//实现开始下棋功能
			for(int r=0;r<row;r++)                                                                                                                                             
				for(int c=0;c<column;c++) {
					chessArray[r][c]=0;//清空棋子
					flag=1;//flag=1为奇数，下黑棋
				}
			panel.repaint();//重新画出面板，使面板清空
			}
	
		else if(e.getActionCommand().equals("认输")) { //实现认输功能
			start=0;//start=0则不能下棋                   
			System.out.println("你已经认输了");
		}
		
		else if(e.getActionCommand().equals("悔棋")) {
			System.out.println("正在悔棋");
			if(chess>=1) {
				int c = setX[chess];//获取当前棋子的列坐标
				int r = setY[chess];//获取当前棋子的行坐标
				chessArray[r][c] = 0;//清空当前棋子
				flag++;
                chess--;//让当前棋子退后一个棋子
				panel.repaint();//重绘界面
			}
		}
			
	}
	 public void mousePressed(MouseEvent e) {
		 if(start==1) {
			 chess++;
			 m=e.getX();
			 n=e.getY();//获取按下时的坐标
			 int c= (m - x + size / 2) / size;//计算该点对应的是第几列
			 int r= (n - y + size / 2) / size;//计算该点对应的是第几行
			//计算该列对应的x1,y1坐标,由于计算行和列时会有误差，所以减去size/2来消除影响
			 int x1 = c* size + x-size/2;//计算该列对应的x1坐标
			 int y1 = r * size + y-size/2;//计算该行对应的y1坐标
			 System.out.println("r="+r+" c="+c);
			 if(item.getSelectedItem().equals("自我博弈")) {
					System.out.println("自我博弈");     //选择下拉框的对战模式
					this.pvp(x1,y1,r,c);
			 }
	 }
 }
	 	//定义自我博弈的方法
	 public void pvp(int x1,int y1,int r,int c) {	
	 		if(m>=x&&m<=size*(row-1)+size&&n>y&&n<=size*(column-1)+size) {
				 if(flag%2!=0) {
					 //判断按下处是否有棋子
					 if(chessArray[r][c]==0) {
					 System.out.println("下黑棋");
					 this.drawqizhi(x1,y1,size,size);
					 /* 记录棋子落子的次序和位置*/
					chessArray[r][c]=1;//存储黑棋
					setX[chess] = c;
					setY[chess] = r;
					 flag++;
					 
					 if(pk.whowin(r, c)==true) {
					JOptionPane.showMessageDialog(panel, "黑棋赢了", "游戏结果", 1);//弹出提示框
					 }
					}
					 
				 	}else {
					 //判断按下处是否有棋子
					 if(chessArray[r][c]==0) {
					 System.out.println("下白棋");
					 this.drawqizhi(x1,y1,size,size);
					 /* 记录棋子落子的次序和位置*/
					 chessArray[r][c]=-1;//存储白棋
					setX[chess] = c;
					setY[chess] = r;
					if(pk.whowin(r, c)==true) {
						JOptionPane.showMessageDialog(panel, "白棋赢了", "游戏结果", 1);//弹出提示框
						 }
					 flag++;
					}
				 } 
			}		
	 	}
	 
	 	public void drawqizhi(int x,int y,int height,int lenght) {
			Graphics2D pen=(Graphics2D)g;//强制转型，将画笔变为Graphic2D 类型
			pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿设置
			
			//定义重绘棋子的方法
			for(int r=0;r<chessArray.length;r++)
				for(int c=0;c<chessArray.length;c++)                                                    
				{
						if(flag%2!=0) //判断是否为黑棋
						{
							pen.setColor(Color.BLACK);
							pen.fillOval(x,y,size, size);//实心椭圆
						}else if(flag%2==0) //判断是否为白棋
						{
							pen.setColor(Color.WHITE);
							pen.fillOval(x,y,size, size);
						}
				}
		}

}
