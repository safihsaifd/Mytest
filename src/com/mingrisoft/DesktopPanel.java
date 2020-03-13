package com.mingrisoft;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

public class DesktopPanel extends JDesktopPane {// ���������Ϣ

	private static final long serialVersionUID = 1L;
	//ʵ��java.io.Serializable����ӿ���Ϊ���л�,serialVersionUID ��������ʵ�����л���Ĳ�ͬ�汾��ļ����ԡ�
	//������޸��˴���, Ҫ�޸Ĵ�ֵ��������ǰ���ϰ汾�������л�����ָ�ʱ�����
	private final Image backImage;// ����ͼƬ

	public DesktopPanel() {// �������Ĺ��췽��
		super();// ���ø���JDesktopPane�Ĺ�����
		URL url = DesktopPanel.class.getResource("/res/back.jpg");// ��ñ���ͼƬ��·��
		backImage = new ImageIcon(url).getImage();// ��ñ���ͼƬ
	}

	@Override
	protected void paintComponent(Graphics g) {// ��д��������ķ���
		int width = getWidth();// �����������Ŀ��
		int height = this.getHeight();// �����������ĸ߶�
		g.drawImage(backImage, 0, 0, width, height, this);// ���Ʊ���ͼƬ
	}
}
