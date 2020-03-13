package com.mingrisoft;

import static java.awt.BorderLayout.*;
import static javax.swing.border.BevelBorder.*;
import java.awt.*;
import java.util.Date;
import javax.swing.*;

import com.mingrisoft.login.LoginDialog;

public class MainFrame extends JFrame {// ������
	private static final long serialVersionUID = 1L;
	//ʵ��java.io.Serializable����ӿ���Ϊ���л�,serialVersionUID ��������ʵ�����л���Ĳ�ͬ�汾��ļ����ԡ�
	//������޸��˴���, Ҫ�޸Ĵ�ֵ��������ǰ���ϰ汾�������л�����ָ�ʱ�����
	
	private JPanel frameContentPane = null;// �������
	private MenuBar frameMenuBar = null;// �˵���
	private ToolBar toolBar = null;// ������
	private DesktopPanel desktopPane = null;// �������
	
	private JPanel statePanel = null;// ״̬��壨�ں�ѡ������״̬������Ա����ǰ���ڡ���˾���Ƶ���Ϣ��
	private JLabel stateLabel = null;// ѡ������״̬��ǩ(��ʾ��ǰ������Ϣ
	private JLabel nameLabel = null;// ����˾���ơ���ǩ(��ʾ��Ȩ��˾��Ϣ
	private JLabel nowDateLabel = null;// ����ǰ���ڡ���ǩ(��ʾ��ǰ������Ϣ
	private static JLabel czyStateLabel = null;// ������Ա����ǩ(��ʾ��¼�û�����Ϣ
	
	private JSeparator jSeparator1 = null;// �ָ���
	private JSeparator jSeparator2 = null;// �ָ���

	/**
	 * ���������������г�������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SplashScreen splashScreen = SplashScreen.getSplashScreen();// ����������Ļ����
		JFrame login = new LoginDialog();// ʵ�л���¼����
		if (splashScreen != null) {// ������Ļ����Ϊ��
			try {
				login.setDefaultCloseOperation(EXIT_ON_CLOSE);// ���õ�¼����Ĺرշ�ʽ
				Thread.sleep(3000);// �߳�����3��
			} catch (InterruptedException e) {
				
			}
		}
		login.setVisible(true);// ʹ��¼����ɼ�
	}

	/**
	 * ��ʼ��������
	 * 
	 * @return JToolBar
	 */
	private ToolBar getJJToolBar() {//��ʼ�����������̣�
		if (toolBar == null) {// ����������Ϊ��
			toolBar = new ToolBar(getFrameMenuBar());// ��������������
			toolBar.setCursor(new Cursor(Cursor.HAND_CURSOR));// ���ù��ͼ��
		}
		return toolBar;
	}

	/**
	 * ��ʼ������˵����ķ���
	 * 
	 * @return JMenuBar
	 */
	protected MenuBar getFrameMenuBar() {//��ʼ������˵����ķ������̣�
		if (frameMenuBar == null) {// �˵�������Ϊ��
			frameMenuBar = new MenuBar(getDesktopPane(), getStateLabel());// �����˵�������
		}
		return frameMenuBar;
	}

	/**
	 * ��ʼ���������ķ���
	 * 
	 * @return JDesktopPane
	 */
	private DesktopPanel getDesktopPane() {//��ʼ���������ķ������̣�
		if (desktopPane == null) {// ����������Ϊ��
			desktopPane = new DesktopPanel();// ��������������
		}
		return desktopPane;
	}

	/**
	 * ��ʼ��״̬���ķ���
	 * 
	 * @return JPanel
	 */
	private JPanel getStatePanel() {
		if (statePanel == null) {// ״̬������Ϊ��
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints6.gridx = 2;// ���λ������ĺ�������Ϊ2
			gridBagConstraints6.fill = GridBagConstraints.VERTICAL;// �����ֱ������ռ�ݿհ�����
			gridBagConstraints6.insets = new Insets(0, 5, 0, 5);// ����˴˵ļ��
			gridBagConstraints6.gridy = 0;// ���λ���������������Ϊ0
			
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints4.gridx = 3;// ���λ������ĺ�������Ϊ3
			gridBagConstraints4.gridy = 0;// ���λ���������������Ϊ0
			
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints3.gridx = 6;// ���λ������ĺ�������Ϊ6
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;// �����ֱ������ռ�ݿհ�����
			gridBagConstraints3.insets = new Insets(0, 5, 0, 5);// ����˴˵ļ��
			gridBagConstraints3.gridy = 0;// ���λ���������������Ϊ0
			
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints11.gridx = 5;// ���λ������ĺ�������Ϊ5
			gridBagConstraints11.insets = new Insets(0, 5, 0, 5);// ����˴˵ļ��
			gridBagConstraints11.gridy = 0;// ���λ���������������Ϊ0
			
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints2.gridx = 7;// ���λ������ĺ�������Ϊ7
			gridBagConstraints2.weightx = 0.0;// ��������ϲ�����
			gridBagConstraints2.fill = GridBagConstraints.NONE;// ���������
			gridBagConstraints2.gridy = 0;// ���λ���������������Ϊ0
			
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints1.gridx = 4;// ���λ������ĺ�������Ϊ4
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;// �����ֱ������ռ�ݿհ�����
			gridBagConstraints1.weighty = 1.0;// ������������Ȩ����1.0
			gridBagConstraints1.insets = new Insets(0, 5, 0, 5);// ����˴˵ļ��
			gridBagConstraints1.gridy = 0;// ���λ���������������Ϊ0
			
			GridBagConstraints gridBagConstraints = new GridBagConstraints();// �����������ƶ���
			gridBagConstraints.gridx = 0;// ���λ������ĺ�������Ϊ0
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;// ���ˮƽ������ռ�ݿհ�����
			gridBagConstraints.weightx = 1.0;// ������������Ȩ����1.0
			gridBagConstraints.gridy = 0;// ���λ���������������Ϊ0
			
			nowDateLabel = new JLabel();// ����ǰ���ڡ���ǩ
			Date now = new Date();// ����Date����
			nowDateLabel.setText(String.format("%tF", now));// ���á���ǰ���ڡ���ǩ���ı�����
			nameLabel = new JLabel("����ʡ��̩�������޹�˾   ");// ����˾���ơ���ǩ
			
			statePanel = new JPanel();// ״̬���
			statePanel.setLayout(new GridBagLayout());// ����״̬���Ĳ���
			statePanel.setBorder(BorderFactory.createBevelBorder(RAISED));// ����״̬���ı߿�
			statePanel.add(getStateLabel(), gridBagConstraints);// ��״̬��������ѡ������״̬��ǩ
			statePanel.add(getJSeparator(), gridBagConstraints1);// ��״̬�������ӷָ���
			statePanel.add(getJSeparator1(), gridBagConstraints3);// ��״̬�������ӷָ���
			statePanel.add(getCzyStateLabel(), gridBagConstraints4);// ��״̬�������ӡ�����Ա����ǩ
			statePanel.add(getJSeparator2(), gridBagConstraints6);// ��״̬�������ӷָ���
			statePanel.add(nameLabel, gridBagConstraints2);// ��״̬�������ӡ���˾���ơ���ǩ
			statePanel.add(nowDateLabel, gridBagConstraints11);// ��״̬�������ӡ���ǰ���ڡ���ǩ
		}
		return statePanel;
	}
	
	public MainFrame() {// ȱʡ���캯�����̣�
		super();// ���ø���JFrame�Ĺ�����
		initialize();// ��ʼ��������ķ���
	}

	private void initialize() {// ��ʼ��������ķ������̣�
		this.setSize(800, 600);// ����������Ŀ��
		this.setJMenuBar(getFrameMenuBar());// ���ò˵���
		this.setContentPane(getFrameContentPane());// �����������
		this.setTitle("��̩��ҵ���������ϵͳ");// ���ô������Ŀ
	}
	
	public static JLabel getCzyStateLabel() {// ��á�����Ա����ǩ�ķ������̣�
		if (czyStateLabel == null) {// ������Ա����ǩ����Ϊ��
			czyStateLabel = new JLabel("����Ա��");// ����������Ա����ǩ
		}
		return czyStateLabel;
	}

	public JLabel getStateLabel() {// ���ѡ������״̬��ǩ�ķ������̣�
		if (stateLabel == null) {// ѡ������״̬��ǩ����Ϊ��
			stateLabel = new JLabel();// ����ѡ������״̬��ǩ
			stateLabel.setText("��ǰû��ѡ������");// ����ѡ������״̬��ǩ���ı�����
		}
		return stateLabel;
	}

	private JSeparator getJSeparator() {// ��÷ָ���
		JSeparator jSeparator = new JSeparator();// �����ָ�������
		jSeparator.setOrientation(JSeparator.VERTICAL);// ��ֱ�ָ���
		return jSeparator;
	}

	private JSeparator getJSeparator1() {// ��÷ָ���1���̣�
		if (jSeparator1 == null) {// �ָ�������Ϊ��
			jSeparator1 = new JSeparator();// �����ָ�������
			jSeparator1.setOrientation(SwingConstants.VERTICAL);// ��ֱ�ָ���
		}
		return jSeparator1;
	}

	private JSeparator getJSeparator2() {// ��÷ָ���2���̣�
		if (jSeparator2 == null) {// �ָ�������Ϊ��
			jSeparator2 = new JSeparator();// �����ָ�������
			jSeparator2.setOrientation(SwingConstants.VERTICAL);// ��ֱ�ָ���
		}
		return jSeparator2;
	}

	private JPanel getFrameContentPane() {// ����������ķ������̣�
		if (frameContentPane == null) {// ����������Ϊ��
			frameContentPane = new JPanel();// �����������
			frameContentPane.setLayout(new BorderLayout());// �����������Ĳ���
			frameContentPane.add(getStatePanel(), SOUTH);// ״̬����������������ϲ�
			frameContentPane.add(getJJToolBar(), NORTH);// �������������������ϲ�
			frameContentPane.add(getDesktopPane(), CENTER);// ��������������������ϲ�
		}
		return frameContentPane;
	}
}