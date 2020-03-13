package com.mingrisoft;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.lang.reflect.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import com.mingrisoft.*;
import com.mingrisoft.iframe.*;

public class MenuBar extends JMenuBar {// �˵�����Ϣ

	private JMenu jinhuo_Menu = null;//���������˵�

	private JMenuItem jinhuoItem = null;//(���������˵��λ�ڣ����������˵���

	private JMenuItem jinhuo_tuihuoItem = null;//�������˻����˵��λ�ڣ����������˵���

	private JMenu xiaoshou_Menu = null;//�����۹����˵�

	private JMenu kucun_Menu = null;//���������˵�

	private JMenu xinxi_chaxunMenu = null;//����Ϣ��ѯ���˵�

	private JMenu jiben_ziliaoMenu = null;//���������ϣ��˵�

	private JMenu xitong_weihuMenu = null;//��ϵͳά�����˵�

	private JMenu chuang_kouMenu = null;//�����ڣ��˵�

	private JMenu bang_zhuMenu = null;//���������˵�

	private JMenuItem guanyu_Item = null;//�����ڣ��˵��λ�ڣ��������˵���

	private JMenuItem bugItem = null;//����ϵ����֧�֣��˵��λ�ڣ��������˵���

	private JMenuItem fangwen_wangzhanItem = null;//�����ʼ�����վ���˵��λ�ڣ��������˵���

	private JMenuItem xiaoshou_danItem = null;//�����۵����˵��λ�ڣ����۹����˵���

	private JMenuItem xiaoshou_tuihuoItem = null;//�������˻����˵��λ�ڣ����۹����˵���

	private JMenuItem kucun_pandianItem = null;//������̵㣩�˵��λ�ڣ��������˵���

	private JMenuItem jiage_tiaozhengItem = null;//���۸�������˵��λ�ڣ��������˵���

	private JMenuItem xiaoshou_chaxunItem = null;//�����۲�ѯ���˵��λ�ڣ���Ϣ��ѯ���˵���

	private JMenuItem shangpin_chaxunItem = null;//����Ʒ��ѯ���˵��λ�ڣ���Ϣ��ѯ���˵���

	private JMenuItem xiaoshou_paihangItem = null;//�������У��˵��λ�ڣ���Ϣ��ѯ���˵���

	private JMenuItem shangpin_guanliItem = null;//����Ʒ�����˵��λ�ڣ��������ϣ��˵���

	private JMenuItem kehu_guanliItem = null;//���ͻ������˵��λ�ڣ��������ϣ��˵���

	private JMenuItem gys_guanliItem = null;//����Ӧ�̹����˵��λ�ڣ��������ϣ��˵���

	private JMenuItem jsr_guanliItem = null;//�����������ã��˵��λ�ڣ��������ϣ��˵���

	private JMenuItem mima_xiugaiItem = null;//�������޸ģ��˵��λ�ڣ�ϵͳά�����˵���

	private JMenuItem shuju_beifenItem = null;//�����ݿⱸ����ָ����˵��λ�ڣ�ϵͳά�����˵���

	private JMenuItem exitItem = null;//���˳����˵��λ�ڣ�ϵͳά�����˵���

	private JMenuItem pingpuItem = null;//������ƽ�̣��˵��λ�ڣ����ڣ��˵���

	private JMenuItem closeAllItem = null;//��ȫ���رգ��˵��λ�ڣ����ڣ��˵���

	private JMenuItem allIconItem = null;//��ȫ����С�����˵��λ�ڣ����ڣ��˵���

	private JMenuItem allResumeItem = null;//��ȫ����ԭ���˵��λ�ڣ����ڣ��˵���
	
	private Map<JMenuItem, JInternalFrame> iFrames = null;//�ڲ�����ļ���

	private int nextFrameX, nextFrameY;//�ڲ������λ������
	
	private JLabel stateLabel = null;// ״̬�����ڲ�������ʾ��ǩ

	private JDesktopPane desktopPanel = null;//�����ڲ�������������

	private MenuBar() {//Ĭ�ϵĹ��췽��
	}

	public MenuBar(JDesktopPane desktopPanel, JLabel label) {//��ʼ���˵������棨�̣�
		super();
		iFrames = new HashMap<JMenuItem, JInternalFrame>();
		this.desktopPanel = desktopPanel;
		this.stateLabel = label;
		initialize();
	}

	private void initialize() {//��ʼ���˵�������ķ���
		this.setSize(new Dimension(600, 24));//��������ߴ�
		add(getJinhuo_Menu());
		/*add(getXiaoshou_Menu());
		add(getKucun_Menu());
		add(getXinxi_chaxunMenu());
		add(getJiben_ziliaoMenu());
		add(getXitong_weihuMenu());
		add(getChuang_kouMenu());
		add(getBang_zhuMenu());*/
	}

	public JMenu getJinhuo_Menu() {//��ʼ����������˵��ķ���(��)
		if (jinhuo_Menu == null) {
			jinhuo_Menu = new JMenu();
			jinhuo_Menu.setText("��������(J)");
			jinhuo_Menu.setMnemonic(KeyEvent.VK_J);
			jinhuo_Menu.add(getJinhuoItem());
			jinhuo_Menu.add(getJinhuo_tuihuoItem());
		}
		return jinhuo_Menu;
	}

	public JMenuItem getJinhuoItem() {// ��ʼ�������������˵���ķ��� �÷�������˵���򿪽���������,��ʹ���ڴ��ڱ�ѡ��״̬ (��)
		if (jinhuoItem == null) {
			jinhuoItem = new JMenuItem();
			jinhuoItem.setText("������");//���ñ���
			jinhuoItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/jinhuodan.png")));//��ȡͼƬ��ַ
			jinhuoItem.addActionListener(new ActionListener() {//������
				public void actionPerformed(ActionEvent e) {
					OpenIFrame(jinhuoItem, JinHuoDan_IFrame.class);
				}
			});
		}
		return jinhuoItem;
	}

	public JMenuItem getJinhuo_tuihuoItem() {//��ʼ���������˻����˵���ķ������÷�������˵���򿪽��˻����壬��ʹ���崦����ѡ��״̬�� (��)
		if (jinhuo_tuihuoItem == null) {
			jinhuo_tuihuoItem = new JMenuItem();
			jinhuo_tuihuoItem.setText("�����˻�");//���ñ���
			jinhuo_tuihuoItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/jinhuo_tuihuo.png")));//��ȡͼ���ַ
			jinhuo_tuihuoItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					OpenIFrame(jinhuo_tuihuoItem, JinHuoTuiHuo.class);
				}
			});
		}
		return jinhuo_tuihuoItem;
	}
	
	/**
	 * �����ڲ�����ķ������÷���ʹ�÷��似����ȡ�ڲ�����Ĺ��췽�����Ӷ������ڲ����塣 
	 * @param item��������ڲ�����Ĳ˵���
	 * @param clazz���ڲ������Class����
	 */
	private JInternalFrame OpenIFrame(JMenuItem item, Class clazz) {
		Constructor constructor = clazz.getConstructors()[0];
		JInternalFrame iFrame = iFrames.get(item);
		try {
			if (iFrame == null || iFrame.isClosed()) {
				//Constructor.newInstance() ���Ը��ݴ���Ĳ��������Ե������⹹�칹�캯���� 
				iFrame = (JInternalFrame) constructor.newInstance(new Object[] {});
				iFrames.put(item, iFrame);
				iFrame.setFrameIcon(item.getIcon());
				iFrame.setLocation(nextFrameX, nextFrameY);//������Ƶ���λ�á�ͨ���������������ռ��е� x �� y ������ָ����λ�õ����Ͻǡ�
				int frameH = iFrame.getPreferredSize().height;//�����
				int panelH = iFrame.getContentPane().getPreferredSize().height;//����
				int fSpacing = frameH - panelH;//����߶�
				nextFrameX += fSpacing;
				nextFrameY += fSpacing;
				if (nextFrameX + iFrame.getWidth() > desktopPanel.getWidth())
					nextFrameX = 0;
				if (nextFrameY + iFrame.getHeight() > desktopPanel.getHeight())
					nextFrameY = 0;
				desktopPanel.add(iFrame);
				iFrame.setResizable(false);//true ָʾ�˴���Ӧ���ǿ���С���ģ�false ָʾ�䲻Ӧ���ǿ���С����
				iFrame.setMaximizable(false);// true ָʾ�˴���Ӧ���ǿ���󻯵ģ�false ָʾ�䲻Ӧ���ǿ���󻯵�
				iFrame.setVisible(true);//��ʾ����
			}
			iFrame.setSelected(true);//����ѡ�����
			stateLabel.setText(iFrame.getTitle());//���ñ�ǩ
			iFrame.addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameActivated(InternalFrameEvent e) {//���ڲ����屻����ʱ���á�
					super.internalFrameActivated(e);
					JInternalFrame frame = e.getInternalFrame();
					stateLabel.setText(frame.getTitle());
				}

				public void internalFrameDeactivated(InternalFrameEvent e) {
					stateLabel.setText("û��ѡ����");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iFrame;
	}
	
}
	