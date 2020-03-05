package java������;
import static java������.Config.x;
import static java������.Config.y;

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
	private int start=0;//���������־
	private int flag;//�����º��壬ż���°���
	private Graphics2D g;
	private JPanel panel;
	private JComboBox<String> item;
	WhoWin pk = new WhoWin();//ʵ�����ж���Ӯ��Ķ���
	int setX[] = new int[row * column];//�������ÿ�����ӵ�X��˳��
	int setY[] = new int[row * column];//�������ÿ�����ӵ�Y��˳��
	//���ع��췽������������������󴫵ݹ���
	public Listener(JPanel a,JComboBox<String> item) {
		this.panel=a;
		this.item=item;
	}
	 
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("��ʼ����")){
			System.out.println("��ʼ����");
			g=(Graphics2D)panel.getGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
RenderingHints.VALUE_ANTIALIAS_ON);//���ÿ����
			start=1;//start=1���ƿ�������
			//ʵ�ֿ�ʼ���幦��
			for(int r=0;r<row;r++)                                                                                                                                             
				for(int c=0;c<column;c++) {
					chessArray[r][c]=0;//�������
					flag=1;//flag=1Ϊ�������º���
				}
			panel.repaint();//���»�����壬ʹ������
			}
	
		else if(e.getActionCommand().equals("����")) { //ʵ�����书��
			start=0;//start=0��������                   
			System.out.println("���Ѿ�������");
		}
		
		else if(e.getActionCommand().equals("����")) {
			System.out.println("���ڻ���");
			if(chess>=1) {
				int c = setX[chess];//��ȡ��ǰ���ӵ�������
				int r = setY[chess];//��ȡ��ǰ���ӵ�������
				chessArray[r][c] = 0;//��յ�ǰ����
				flag++;
                chess--;//�õ�ǰ�����˺�һ������
				panel.repaint();//�ػ����
			}
		}
			
	}
	 public void mousePressed(MouseEvent e) {
		 if(start==1) {
			 chess++;
			 m=e.getX();
			 n=e.getY();//��ȡ����ʱ������
			 int c= (m - x + size / 2) / size;//����õ��Ӧ���ǵڼ���
			 int r= (n - y + size / 2) / size;//����õ��Ӧ���ǵڼ���
			//������ж�Ӧ��x1,y1����,���ڼ����к���ʱ���������Լ�ȥsize/2������Ӱ��
			 int x1 = c* size + x-size/2;//������ж�Ӧ��x1����
			 int y1 = r * size + y-size/2;//������ж�Ӧ��y1����
			 System.out.println("r="+r+" c="+c);
			 if(item.getSelectedItem().equals("���Ҳ���")) {
					System.out.println("���Ҳ���");     //ѡ��������Ķ�սģʽ
					this.pvp(x1,y1,r,c);
			 }
	 }
 }
	 	//�������Ҳ��ĵķ���
	 public void pvp(int x1,int y1,int r,int c) {	
	 		if(m>=x&&m<=size*(row-1)+size&&n>y&&n<=size*(column-1)+size) {
				 if(flag%2!=0) {
					 //�жϰ��´��Ƿ�������
					 if(chessArray[r][c]==0) {
					 System.out.println("�º���");
					 this.drawqizhi(x1,y1,size,size);
					 /* ��¼�������ӵĴ����λ��*/
					chessArray[r][c]=1;//�洢����
					setX[chess] = c;
					setY[chess] = r;
					 flag++;
					 
					 if(pk.whowin(r, c)==true) {
					JOptionPane.showMessageDialog(panel, "����Ӯ��", "��Ϸ���", 1);//������ʾ��
					 }
					}
					 
				 	}else {
					 //�жϰ��´��Ƿ�������
					 if(chessArray[r][c]==0) {
					 System.out.println("�°���");
					 this.drawqizhi(x1,y1,size,size);
					 /* ��¼�������ӵĴ����λ��*/
					 chessArray[r][c]=-1;//�洢����
					setX[chess] = c;
					setY[chess] = r;
					if(pk.whowin(r, c)==true) {
						JOptionPane.showMessageDialog(panel, "����Ӯ��", "��Ϸ���", 1);//������ʾ��
						 }
					 flag++;
					}
				 } 
			}		
	 	}
	 
	 	public void drawqizhi(int x,int y,int height,int lenght) {
			Graphics2D pen=(Graphics2D)g;//ǿ��ת�ͣ������ʱ�ΪGraphic2D ����
			pen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
					RenderingHints.VALUE_ANTIALIAS_ON);//���������
			
			//�����ػ����ӵķ���
			for(int r=0;r<chessArray.length;r++)
				for(int c=0;c<chessArray.length;c++)                                                    
				{
						if(flag%2!=0) //�ж��Ƿ�Ϊ����
						{
							pen.setColor(Color.BLACK);
							pen.fillOval(x,y,size, size);//ʵ����Բ
						}else if(flag%2==0) //�ж��Ƿ�Ϊ����
						{
							pen.setColor(Color.WHITE);
							pen.fillOval(x,y,size, size);
						}
				}
		}

}
