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

public class MenuBar extends JMenuBar {// 菜单栏信息

	private JMenu jinhuo_Menu = null;//进货管理）菜单

	private JMenuItem jinhuoItem = null;//(进货单）菜单项，位于（进货管理）菜单内

	private JMenuItem jinhuo_tuihuoItem = null;//（进货退货）菜单项，位于（进货管理）菜单内

	private JMenu xiaoshou_Menu = null;//（销售管理）菜单

	private JMenu kucun_Menu = null;//（库存管理）菜单

	private JMenu xinxi_chaxunMenu = null;//（信息查询）菜单

	private JMenu jiben_ziliaoMenu = null;//（基本资料）菜单

	private JMenu xitong_weihuMenu = null;//（系统维护）菜单

	private JMenu chuang_kouMenu = null;//（窗口）菜单

	private JMenu bang_zhuMenu = null;//（帮助）菜单

	private JMenuItem guanyu_Item = null;//（关于）菜单项，位于（帮助）菜单内

	private JMenuItem bugItem = null;//（联系技术支持）菜单项，位于（帮助）菜单内

	private JMenuItem fangwen_wangzhanItem = null;//（访问技术网站）菜单项，位于（帮助）菜单内

	private JMenuItem xiaoshou_danItem = null;//（销售单）菜单项，位于（销售管理）菜单内

	private JMenuItem xiaoshou_tuihuoItem = null;//（销售退货）菜单项，位于（销售管理）菜单内

	private JMenuItem kucun_pandianItem = null;//（库存盘点）菜单项，位于（库存管理）菜单内

	private JMenuItem jiage_tiaozhengItem = null;//（价格调整）菜单项，位于（库存管理）菜单内

	private JMenuItem xiaoshou_chaxunItem = null;//（销售查询）菜单项，位于（信息查询）菜单内

	private JMenuItem shangpin_chaxunItem = null;//（商品查询）菜单项，位于（信息查询）菜单内

	private JMenuItem xiaoshou_paihangItem = null;//销售排行）菜单项，位于（信息查询）菜单内

	private JMenuItem shangpin_guanliItem = null;//（商品管理）菜单项，位于（基本资料）菜单内

	private JMenuItem kehu_guanliItem = null;//（客户管理）菜单项，位于（基本资料）菜单内

	private JMenuItem gys_guanliItem = null;//（供应商管理）菜单项，位于（基本资料）菜单内

	private JMenuItem jsr_guanliItem = null;//（经手人设置）菜单项，位于（基本资料）菜单内

	private JMenuItem mima_xiugaiItem = null;//（密码修改）菜单项，位于（系统维护）菜单内

	private JMenuItem shuju_beifenItem = null;//（数据库备份与恢复）菜单项，位于（系统维护）菜单内

	private JMenuItem exitItem = null;//（退出）菜单项，位于（系统维护）菜单内

	private JMenuItem pingpuItem = null;//（窗口平铺）菜单项，位于（窗口）菜单内

	private JMenuItem closeAllItem = null;//（全部关闭）菜单项，位于（窗口）菜单内

	private JMenuItem allIconItem = null;//（全部最小化）菜单项，位于（窗口）菜单内

	private JMenuItem allResumeItem = null;//（全部还原）菜单项，位于（窗口）菜单内
	
	private Map<JMenuItem, JInternalFrame> iFrames = null;//内部窗体的集合

	private int nextFrameX, nextFrameY;//内部窗体的位置坐标
	
	private JLabel stateLabel = null;// 状态栏的内部窗体提示标签

	private JDesktopPane desktopPanel = null;//容纳内部窗体的桌面面板

	private MenuBar() {//默认的构造方法
	}

	public MenuBar(JDesktopPane desktopPanel, JLabel label) {//初始化菜单栏界面（√）
		super();
		iFrames = new HashMap<JMenuItem, JInternalFrame>();
		this.desktopPanel = desktopPanel;
		this.stateLabel = label;
		initialize();
	}

	private void initialize() {//初始化菜单栏界面的方法
		this.setSize(new Dimension(600, 24));//设置组件尺寸
		add(getJinhuo_Menu());
		/*add(getXiaoshou_Menu());
		add(getKucun_Menu());
		add(getXinxi_chaxunMenu());
		add(getJiben_ziliaoMenu());
		add(getXitong_weihuMenu());
		add(getChuang_kouMenu());
		add(getBang_zhuMenu());*/
	}

	public JMenu getJinhuo_Menu() {//初始化进货管理菜单的方法(√)
		if (jinhuo_Menu == null) {
			jinhuo_Menu = new JMenu();
			jinhuo_Menu.setText("进货管理(J)");
			jinhuo_Menu.setMnemonic(KeyEvent.VK_J);
			jinhuo_Menu.add(getJinhuoItem());
			jinhuo_Menu.add(getJinhuo_tuihuoItem());
		}
		return jinhuo_Menu;
	}

	public JMenuItem getJinhuoItem() {// 初始化（进货单）菜单项的方法 该方法定义菜单项打开进货单窗口,并使窗口处于被选择状态 (√)
		if (jinhuoItem == null) {
			jinhuoItem = new JMenuItem();
			jinhuoItem.setText("进货单");//设置标题
			jinhuoItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/jinhuodan.png")));//获取图片地址
			jinhuoItem.addActionListener(new ActionListener() {//鼠标监听
				public void actionPerformed(ActionEvent e) {
					OpenIFrame(jinhuoItem, JinHuoDan_IFrame.class);
				}
			});
		}
		return jinhuoItem;
	}

	public JMenuItem getJinhuo_tuihuoItem() {//初始化（进货退货）菜单项的方法，该方法定义菜单项打开进退货窗体，并使窗体处于已选择状态。 (√)
		if (jinhuo_tuihuoItem == null) {
			jinhuo_tuihuoItem = new JMenuItem();
			jinhuo_tuihuoItem.setText("进货退货");//设置标题
			jinhuo_tuihuoItem.setIcon(new ImageIcon(getClass().getResource(
					"/res/icon/jinhuo_tuihuo.png")));//获取图标地址
			jinhuo_tuihuoItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					OpenIFrame(jinhuo_tuihuoItem, JinHuoTuiHuo.class);
				}
			});
		}
		return jinhuo_tuihuoItem;
	}
	
	/**
	 * 创建内部窗体的方法，该方法使用发射技术获取内部窗体的构造方法，从而创建内部窗体。 
	 * @param item：激活该内部窗体的菜单项
	 * @param clazz：内部窗体的Class对象
	 */
	private JInternalFrame OpenIFrame(JMenuItem item, Class clazz) {
		Constructor constructor = clazz.getConstructors()[0];
		JInternalFrame iFrame = iFrames.get(item);
		try {
			if (iFrame == null || iFrame.isClosed()) {
				//Constructor.newInstance() 可以根据传入的参数，可以调用任意构造构造函数。 
				iFrame = (JInternalFrame) constructor.newInstance(new Object[] {});
				iFrames.put(item, iFrame);
				iFrame.setFrameIcon(item.getIcon());
				iFrame.setLocation(nextFrameX, nextFrameY);//将组件移到新位置。通过此组件父级坐标空间中的 x 和 y 参数来指定新位置的左上角。
				int frameH = iFrame.getPreferredSize().height;//窗体高
				int panelH = iFrame.getContentPane().getPreferredSize().height;//面板高
				int fSpacing = frameH - panelH;//空余高度
				nextFrameX += fSpacing;
				nextFrameY += fSpacing;
				if (nextFrameX + iFrame.getWidth() > desktopPanel.getWidth())
					nextFrameX = 0;
				if (nextFrameY + iFrame.getHeight() > desktopPanel.getHeight())
					nextFrameY = 0;
				desktopPanel.add(iFrame);
				iFrame.setResizable(false);//true 指示此窗体应该是可最小化的；false 指示其不应该是可最小化的
				iFrame.setMaximizable(false);// true 指示此窗体应该是可最大化的；false 指示其不应该是可最大化的
				iFrame.setVisible(true);//显示窗体
			}
			iFrame.setSelected(true);//设置选项可用
			stateLabel.setText(iFrame.getTitle());//设置标签
			iFrame.addInternalFrameListener(new InternalFrameAdapter() {
				public void internalFrameActivated(InternalFrameEvent e) {//当内部窗体被激活时调用。
					super.internalFrameActivated(e);
					JInternalFrame frame = e.getInternalFrame();
					stateLabel.setText(frame.getTitle());
				}

				public void internalFrameDeactivated(InternalFrameEvent e) {
					stateLabel.setText("没有选择窗体");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iFrame;
	}
	
}
	