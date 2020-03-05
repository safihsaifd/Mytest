package java������;
import static java������.Config.x;
import static java������.Config.y;
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
import java������.Listener;
@SuppressWarnings("serial")
//�̳�Jpanel ʵ���Զ����Config�ӿ�
public class drawall extends JPanel implements Config{
	public static void main(String[] args) {
		drawall draw=new drawall();
		draw.showUI();
	}	
	public void showUI() {//��ƴ������
		String []itemArray= {"���Ҳ���"};
		JComboBox<String> item=new JComboBox<String>(itemArray);//ʵ������������󣬲�����ֵ
		Listener X=new Listener(this,item);//ʵ�����¼���������󣬲������������򴫵ݹ�ȥ
		
		JFrame frame=new JFrame();//�������嶥����������
		frame.setTitle("������");//����
		frame.setSize(new Dimension(900, 800));
		frame.setLocationRelativeTo(null);//���ô���λ��ʱ������ȷ�������С,˳���ܱ�
		
		this.setBackground(Color.YELLOW);//�������̱�����ɫ
		this.setPreferredSize(new Dimension(700, 800));//������������С
		this.addMouseListener(X);//������������������
		frame.add(this,BorderLayout.CENTER);//�߽粼�֣�����м����
		
		JPanel eastpanel=new JPanel();//ʵ�����������
		frame.add(eastpanel,BorderLayout.EAST);//��Ӷ������
		eastpanel.setBackground(Color.WHITE);//����������ð�ɫ
		eastpanel.setPreferredSize(new Dimension(100, 800));//���ö�������С
	
		
		JLabel label=new JLabel();//ʵ������ǩ�������
		label.setPreferredSize(new Dimension(100, 120));//���ñ�ǩ��ע���ǩ��͸���ģ����������ð�ť���Ƶ��м�
		eastpanel.add(label);//�ڶ���������label
		String [] typeArray= {"��ʼ����","����","����"};
		for(int i=0;i<typeArray.length;i++) {
			JButton button=new JButton(typeArray[i]);//������ť���
			button.setPreferredSize(new Dimension(100, 100));       
			button.setFont(new Font("����",1,15));//���ð�ť����
			button.addActionListener(X);//����ť���ϼ�������
			eastpanel.add(button);//���������Ӱ�ť
		}
		item.setPreferredSize(new Dimension(100, 100));//�����������С
		item.setFont(new Font("����",1,15));
		eastpanel.add(item);//���������
		frame.setDefaultCloseOperation(3);//���ô����˳�ģʽ
		frame.setResizable(false);//���ô����С���ɱ�
		frame.setVisible(true);//���ô���ɼ�
		
	}
	
	//�������̺�����
	public void paint(Graphics g) {
		super.paint(g);
		System.out.println("�����ػ�");             //�����ػ�ķ���,ע��˴�gΪ����Դ��ģ�����Ҫ�����ȡ                        
		this.drawChess(g);//���û����̵ķ���
	}
	
	public void drawChess(Graphics g) {
		for(int i=0;i<row;i++) {
			g.setColor(Color.BLACK); //��������������ɫ                   //�����ػ����̵ķ���        
			g.drawLine(x,y+i*size,x+size*(row-1),y+i*size);//������
			g.drawLine(x+i*size,y,x+i*size,y+size*(column-1));//������
		}
	}
	

			
}		
