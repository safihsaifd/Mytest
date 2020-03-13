package com.mingrisoft;

import java.awt.*;
import java.net.URL;

import javax.swing.*;

public class DesktopPanel extends JDesktopPane {// 桌面面板信息

	private static final long serialVersionUID = 1L;
	//实现java.io.Serializable这个接口是为序列化,serialVersionUID 用来表明实现序列化类的不同版本间的兼容性。
	//如果你修改了此类, 要修改此值。否则以前用老版本的类序列化的类恢复时会出错。
	private final Image backImage;// 背景图片

	public DesktopPanel() {// 桌面面板的构造方法
		super();// 调用父类JDesktopPane的构造器
		URL url = DesktopPanel.class.getResource("/res/back.jpg");// 获得背景图片的路径
		backImage = new ImageIcon(url).getImage();// 获得背景图片
	}

	@Override
	protected void paintComponent(Graphics g) {// 重写绘制组件的方法
		int width = getWidth();// 定义桌面面板的宽度
		int height = this.getHeight();// 定义桌面面板的高度
		g.drawImage(backImage, 0, 0, width, height, this);// 绘制背景图片
	}
}
