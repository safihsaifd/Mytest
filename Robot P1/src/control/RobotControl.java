package control;

import robot.Robot;

public class RobotControl implements Control
{
	// ������Ҫ׷���ֱ۵�λ��
	private int height = Control.INITIAL_HEIGHT;//13
	private int width = Control.INITIAL_WIDTH;//1
	private int depth = Control.INITIAL_DEPTH;//0

	private int[] barHeights;
	private int[] blockHeights;
	
	private Robot robot;
	

	private boolean judge = true;//��ʼjudgeֵΪ��
	private int nowblockheight = 0;//ʰȡ�п��
	private int nowbarcolumn=Control.FIRST_BAR_COLUMN;//���ÿ�Ŀ����
	private static final int ARM2_HEIGHT = 1;
	private int destWidth/*Ŀ�ĵؿ��*/ = width;
	private static int LEFT = 1;
	private static int RIGHT = 0;
	private int number = 0;

	
	// ��RobotImpl����
	@Override
	public void control(Robot robot, int barHeightsDefault[], int blockHeightsDefault[])
	{
		this.robot = robot;

		this.barHeights = new int[] { 3, 4, 1, 5, 2, 3, 2, 6 };
		this.blockHeights = new int[] { 3, 2, 1, 2, 1, 1, 2, 2, 1, 1, 2, 1, 2, 3 };
        //����Դ�ĸ����Ϳ��
		// �����ύ�������к�ȡ��ע��
		//	this.barHeights = barHeightsDefault;
		//	this.blockHeights = blockHeightsDefault;

		// ��ʼ��robot
		robot.init(this.barHeights, this.blockHeights, height, width, depth);


		while(blocktotalheight(blockHeights) != 0)
		{
			processingLoop();
		}
	}
	private void processingLoop()
	{
		arm1height(MaxadjustHeight());//��е��1�����߶�
		arm2width1(mobilemethod1());//��е��2���Ƶ��׻������Ƶ���
		lowerArm3(getheight1());//������Դ���½���е��3
		pickBlock();//ʰȡ��
		raiseArm3(MaxadjustHeight());//������е��3
		arm2width1(destWidth);//������е��2
		lowerArm3(getheight2());//���м�BARԴ���½���е��3
		dropBlock();//���¿�
		raiseArm3(this.height - 1);//������е��3
	}
	//ѭ��������
	private void arm1height(int adjustheight) {
		while(this.height>adjustheight+ARM2_HEIGHT)
		{
			height--;
			robot.down();
		}
		while(this.height<adjustheight+ARM2_HEIGHT)
		{
			height++;
			robot.up();
		}
	}
	
	
    private void arm2width1(int destwidh) {//�ƶ���е��2
    	if (destwidh > this.width) {
    		extendarm2(destwidh);
		} else {
			contractarm2(destwidh);
		}
	}
    
    private void arm2width2() {
    	//��е��2����2����9ֱ�Ӱ�˳���ƶ�
    	//ֱ�����������һ��bar��
    	//Ȼ���л������ж�������Ϊfalse
    	if(judge/*ture*/)
    	{
    	if(this.width<this.nowbarcolumn)
    	{
    		robot.extend();
			this.width++;
			this.nowbarcolumn++;
    	}
    	else if(this.width== Control.LAST_BAR_COLUMN){
    		this.judge = false;
    	   }
    	}
    	else 
    	{
			if (this.width > this.nowbarcolumn) 
			{
				robot.contract();
				this.width--;
				this.nowbarcolumn--;
			} 
			else if (this.width == Control.LAST_BAR_COLUMN) {
				this.judge = true;
			}
    	}  	
    }
    
    private void lowerArm3(int blockmaxheight/*��ȡ��or��Դ�е��ܸ߶�*/) {
    	while ((this.height - ARM2_HEIGHT/*��е��2���*/ - this.nowblockheight/*ʰȡ�п��*/ - this.depth) > blockmaxheight) 
    	{
			robot.lower();
			this.depth++;
		} 	
    }
    
    private void pickBlock() {//ʰȡ��
    	int dir = 0;//�ж�����Դ�л�����Դ��ʰȡ
		if (number % 2 == 0) {
			dir = LEFT;
		} else {
			dir = RIGHT;
		}
    	for(int i=dir;i<blockHeights.length;i+=2) 
    	{//+2��Ϊ����ͬһ����ʰȡ
    		if (blockHeights[i] != 0) 
    		{
				robot.pick();
				this.nowblockheight = blockHeights[i];//��ǰʰȡ�Ŀ�߶ȸ�nowblockheight
				this.destWidth = nowbarcolumn;//��ʼʱ����һ��bar�������Ŀ�Ŀ��
				//Ȼ�����nowbarcolumn���ϱ仯 ÿʰȡ�� �ٸ���Ŀ�Ŀ�� 
				blockHeights[i] = 0;//Դ������ʰȡ�Ŀ��Ϊ0
				break;
    		}
    	}
    }
      
    private void raiseArm3(int possiblemaxHeight ) 
    {
    	//���������ֱ�������Ŀ��߶�              
    	//Robot���ʱ��Ҫ�ҵ��ֱ۵���͵�
    	while ((this.height - ARM2_HEIGHT - this.nowblockheight - this.depth) < possiblemaxHeight) 
    	{
    		if (this.depth!= 0) 
    		{
    			robot.raise();
    			this.depth--;
    		}
    		else //�����е��3����0,������ͨ�����ܵ���͸߶�          
				//��������е��1�ĸ߶ȣ�ʹ��ͨ�����ܵ���͸߶�
    		{
    			arm1height(possiblemaxHeight + this.nowblockheight);
    			//����ǰ��߶Ⱥ�����и߶ȴ���arm1height����������е��1�߶ȣ�
    		}			
        }
    }
 
    private void dropBlock() {//���¿鲢������ӵ�����ͼ�߶�
    	robot.drop();
    	barHeights[nowbarcolumn - Control.FIRST_BAR_COLUMN] += this.nowblockheight;
    	//����·��ϵĿ� �����и߶�
    	this.nowblockheight = 0;//����ʰȡ�Ŀ�߶�����
    	NextBarColumn();// nowbarcolumn�ı�Ϊrobot��Ҫ����block����һ��bar��
    	number++;// ����һ��Ŀ��λ�ø��µ���һ��Դ��,�����ֻ�
    	this.destWidth = this.mobilemethod1();
    }   
    
    //�ƶ�����
    
    private void extendarm2(int width)//��е��2���Ʒ���
	{
		while (this.width < width)
		{
			robot.extend();
			this.width++;
		}
	}
	private void contractarm2(int width) //��е��2���Ʒ���
	{
		while (this.width > width) {
			robot.contract();
			this.width--;
		}
	}
	
	//���췽��
	
	private int blocktotalheight(int[] blockHeights)//��ȡ����Դ�е��ܸ߶����ڼ������Ƿ������ֹ          

	{
		int totalheight= 0;/*��ʼ�ܸ߶�Ϊ0*/ 
		for (int height : blockHeights/*��߶�,foreach���*/) {
			totalheight=totalheight + height;
		}
		return totalheight;
	}
	private int getheight2()//��õ�ǰ�еĸ߶�
	    {
	    	int columnheight;
	    	columnheight=barHeights[this.nowbarcolumn- Control.FIRST_BAR_COLUMN];
	    	return columnheight;
	    }
	private int getheight1()//��ȡ��or��Դ�е��ܸ߶�
	{
		if (number % 2 == 0) {
			return columnHeight(LEFT);//������Դ�и߶�;
		} else {
			return columnHeight(RIGHT);//������Դ�и߶�
		}
	}
	private int MaxadjustHeight()//��ȡ���height
	{

		
		int maxHeight = 0;
		
		if (this.destWidth == Control.SRC1_COLUMN) {//������ʱ
			if (maxHeight < columnHeight(LEFT))
			{
				maxHeight = columnHeight(LEFT);
			}//maxHeight��ʱΪ��Դ�и߶�
			for (int i = 0; i <= this.width - Control.FIRST_BAR_COLUMN; i++)
			{
				if (barHeights[i] > maxHeight) 
				{
					maxHeight = barHeights[i];
				}
			}//�������bar�бȽϣ���bar�б���Դ�и�ʱ��maxHeightΪ���bar�и߶�
		} 
		else if (this.destWidth == Control.SRC2_COLUMN) {//������ʱ
			if (maxHeight < columnHeight(RIGHT))
			{
				maxHeight = columnHeight(RIGHT);
			}//maxHeight��ʱΪ��Դ�и߶�
			for (int i = width - Control.FIRST_BAR_COLUMN; i < barHeights.length; i++)
			{
				if (barHeights[i] > maxHeight) 
				{
					maxHeight = barHeights[i];
				}
			}//�����ұ�bar�бȽϣ���bar�б���Դ�и�ʱ��maxHeightΪ���bar�и߶�
		} 
		else if (this.width == Control.SRC1_COLUMN) {//������Դ��ʱ
			if (maxHeight < columnHeight(LEFT))
			{
				maxHeight = columnHeight(LEFT);
			}//maxHeight��ʱΪ��Դ�и߶�
			for (int i = 0; i <= this.destWidth - Control.FIRST_BAR_COLUMN; i++) 
			{
				if (barHeights[i] > maxHeight) 
				{
					maxHeight = barHeights[i];
				}
			}//���뵽Ŀ����ֱ�ӵ�bar�бȽϣ���bar�б���Դ�и�ʱ��maxHeightΪ���bar�и߶�
		} 
		else {//������Դ��ʱ
			if (maxHeight < columnHeight(RIGHT))
			{
				maxHeight = columnHeight(RIGHT);
			}
			for (int i = this.destWidth - Control.FIRST_BAR_COLUMN; i < barHeights.length; i++) 
			{
				if (barHeights[i] > maxHeight)
				{
					maxHeight = barHeights[i];
				}
			}//���뵽Ŀ����ֱ�ӵ�bar�бȽϣ���bar�б���Դ�и�ʱ��maxHeightΪ���bar�и߶�
		}
		return maxHeight;
	}
	private int columnHeight(int dir) {// ������or��Դ�е��ܸ߶�

		int totalHeight = 0;
		for (int i = dir;i< blockHeights.length; i+= 2) 
		{
			totalHeight += blockHeights[i];
		}
		return totalHeight;
	}
	private int mobilemethod1() {
		//����number����������Ŀ��Դ��,������robot��Ҫ�ƶ�����Ŀ����
		//��number��������ʹĿ����Ϊ��Դ�л�����Դ��
		if (number % 2 == 0) {
			destWidth = Control.SRC1_COLUMN;//1
			return Control.SRC1_COLUMN;//1
		} else {
			destWidth = Control.SRC2_COLUMN;//10
			return Control.SRC2_COLUMN;//10
		}
	}
	private void NextBarColumn() {
		//�ҵ�robot��Ҫ���ÿ��bar��              
		//��������ƶ�����ÿ�������н�+1               
		//ֱ�����������һ��bar��               
		//Ȼ���л����򣬽�judge�жϸ���Ϊfalse              
		if (judge) 
		{
			if (this.nowbarcolumn < Control.LAST_BAR_COLUMN/*9*/) 
			{
				this.nowbarcolumn++;
			} 
			else if (this.nowbarcolumn == Control.LAST_BAR_COLUMN) 
			{
				this.judge = false;
			}
		} 
		else 
		{
			if (this.nowbarcolumn > Control.FIRST_BAR_COLUMN/*2*/) 
			{
				this.nowbarcolumn--;
			} 
			else if (this.nowbarcolumn == Control.LAST_BAR_COLUMN/*2*/) 
			{
				this.judge = true;
			}
		}
	}
}	
