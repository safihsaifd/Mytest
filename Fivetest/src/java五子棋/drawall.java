package java五子棋;
import static java五子棋.Config.x;
import static java五子棋.Config.y;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java五子棋.Listener;
@SuppressWarnings("serial")
//继承Jpanel 实现自定义的Config接口
public class drawall extends JPanel implements Config{
	public static void main(String[] args) {
		drawall draw=new drawall();
		draw.showUI();
	}	
	public void showUI() {//设计大体界面
		String []itemArray= {"自我博弈"};
		JComboBox<String> item=new JComboBox<String>(itemArray);//实例化下拉框对象，并赋初值
		Listener X=new Listener(this,item);//实例化事件处理类对象，并将面板和下拉框传递过去
		
		JFrame frame=new JFrame();//创建窗体顶级容器对象
		frame.setTitle("五子棋");//标题
		frame.setSize(new Dimension(900, 800));
		frame.setLocationRelativeTo(null);//设置窗体位置时必须先确定窗体大小,顺序不能变
		
		this.setBackground(Color.YELLOW);//设置棋盘背景颜色
		this.setPreferredSize(new Dimension(700, 800));//设置中央面板大小
		this.addMouseListener(X);//给面板加上鼠标监听方法
		frame.add(this,BorderLayout.CENTER);//边界布局，添加中间面板
		
		JPanel eastpanel=new JPanel();//实例化东边面板
		frame.add(eastpanel,BorderLayout.EAST);//添加东边面板
		eastpanel.setBackground(Color.WHITE);//东边面板设置白色
		eastpanel.setPreferredSize(new Dimension(100, 800));//设置东边面板大小
	
		
		JLabel label=new JLabel();//实例化标签组件对象
		label.setPreferredSize(new Dimension(100, 120));//设置标签，注意标签是透明的，这样可以让按钮都移到中间
		eastpanel.add(label);//在东边面板加上label
		String [] typeArray= {"开始下棋","认输","悔棋"};
		for(int i=0;i<typeArray.length;i++) {
			JButton button=new JButton(typeArray[i]);//创建按钮组件
			button.setPreferredSize(new Dimension(100, 100));       
			button.setFont(new Font("黑体",1,15));//设置按钮字体
			button.addActionListener(X);//给按钮加上监听方法
			eastpanel.add(button);//东北面板添加按钮
		}
		item.setPreferredSize(new Dimension(100, 100));//设置下拉框大小
		item.setFont(new Font("黑体",1,15));
		eastpanel.add(item);//添加下拉框
		frame.setDefaultCloseOperation(3);//设置窗体退出模式
		frame.setResizable(false);//设置窗体大小不可变
		frame.setVisible(true);//设置窗体可见
		
	}
	
	//绘制棋盘和棋子
	public void paint(Graphics g) {
		super.paint(g);
		System.out.println("正在重绘");             //定义重绘的方法,注意此处g为面板自带的，不需要额外获取                        
		this.drawChess(g);//调用画棋盘的方法
	}
	
	public void drawChess(Graphics g) {
		for(int i=0;i<row;i++) {
			g.setColor(Color.BLACK); //设置棋盘线条颜色                   //定义重绘棋盘的方法        
			g.drawLine(x,y+i*size,x+size*(row-1),y+i*size);//画横线
			g.drawLine(x+i*size,y,x+i*size,y+size*(column-1));//画竖线
		}
	}
	

			
}		
