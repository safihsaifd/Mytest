package control;

import robot.Robot;

public class RobotControl implements Control
{
	// 我们需要追踪手臂的位置
	private int height = Control.INITIAL_HEIGHT;//13
	private int width = Control.INITIAL_WIDTH;//1
	private int depth = Control.INITIAL_DEPTH;//0

	private int[] barHeights;
	private int[] blockHeights;
	
	private Robot robot;
	

	private boolean judge = true;//初始judge值为真
	private int nowblockheight = 0;//拾取中块高
	private int nowbarcolumn=Control.FIRST_BAR_COLUMN;//放置块目标列
	private static final int ARM2_HEIGHT = 1;
	private int destWidth/*目的地宽度*/ = width;
	private static int LEFT = 1;
	private static int RIGHT = 0;
	private int number = 0;

	
	// 由RobotImpl调用
	@Override
	public void control(Robot robot, int barHeightsDefault[], int blockHeightsDefault[])
	{
		this.robot = robot;

		this.barHeights = new int[] { 3, 4, 1, 5, 2, 3, 2, 6 };
		this.blockHeights = new int[] { 3, 2, 1, 2, 1, 1, 2, 2, 1, 1, 2, 1, 2, 3 };
        //两列源的个数和块高
		// 对于提交：在两行后取消注释
		//	this.barHeights = barHeightsDefault;
		//	this.blockHeights = blockHeightsDefault;

		// 初始化robot
		robot.init(this.barHeights, this.blockHeights, height, width, depth);


		while(blocktotalheight(blockHeights) != 0)
		{
			processingLoop();
		}
	}
	private void processingLoop()
	{
		arm1height(MaxadjustHeight());//机械臂1调整高度
		arm2width1(mobilemethod1());//机械臂2左移到底或者右移到底
		lowerArm3(getheight1());//在左右源列下降机械臂3
		pickBlock();//拾取块
		raiseArm3(MaxadjustHeight());//上升机械臂3
		arm2width1(destWidth);//调正机械臂2
		lowerArm3(getheight2());//在中间BAR源列下降机械臂3
		dropBlock();//放下块
		raiseArm3(this.height - 1);//上升机械臂3
	}
	//循环主方法
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
	
	
    private void arm2width1(int destwidh) {//移动机械臂2
    	if (destwidh > this.width) {
    		extendarm2(destwidh);
		} else {
			contractarm2(destwidh);
		}
	}
    
    private void arm2width2() {
    	//机械臂2在柱2与柱9直接按顺序移动
    	//直到它到达最后一个bar列
    	//然后切换方向，判断条件变为false
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
    
    private void lowerArm3(int blockmaxheight/*获取左or右源列的总高度*/) {
    	while ((this.height - ARM2_HEIGHT/*机械臂2宽度*/ - this.nowblockheight/*拾取中块高*/ - this.depth) > blockmaxheight) 
    	{
			robot.lower();
			this.depth++;
		} 	
    }
    
    private void pickBlock() {//拾取块
    	int dir = 0;//判断在左源列还是右源列拾取
		if (number % 2 == 0) {
			dir = LEFT;
		} else {
			dir = RIGHT;
		}
    	for(int i=dir;i<blockHeights.length;i+=2) 
    	{//+2是为了在同一列上拾取
    		if (blockHeights[i] != 0) 
    		{
				robot.pick();
				this.nowblockheight = blockHeights[i];//当前拾取的块高度给nowblockheight
				this.destWidth = nowbarcolumn;//初始时将第一个bar列坐标给目的宽度
				//然后后面nowbarcolumn不断变化 每拾取后 再给予目的宽度 
				blockHeights[i] = 0;//源列中已拾取的块标为0
				break;
    		}
    	}
    }
      
    private void raiseArm3(int possiblemaxHeight ) 
    {
    	//将机器人手臂提升到目标高度              
    	//Robot提块时需要找到手臂的最低点
    	while ((this.height - ARM2_HEIGHT - this.nowblockheight - this.depth) < possiblemaxHeight) 
    	{
    		if (this.depth!= 0) 
    		{
    			robot.raise();
    			this.depth--;
    		}
    		else //如果机械臂3缩到0,还不能通过可能的最低高度          
				//将调整机械臂1的高度，使其通过可能的最低高度
    		{
    			arm1height(possiblemaxHeight + this.nowblockheight);
    			//将当前块高度和最高列高度带入arm1height（）调整机械臂1高度；
    		}			
        }
    }
 
    private void dropBlock() {//放下块并将其添加到条形图高度
    	robot.drop();
    	barHeights[nowbarcolumn - Control.FIRST_BAR_COLUMN] += this.nowblockheight;
    	//添加新放上的块 更新列高度
    	this.nowblockheight = 0;//正在拾取的块高度清零
    	NextBarColumn();// nowbarcolumn改变为robot需要放置block的下一个bar列
    	number++;// 将下一个目标位置更新到下一个源列,左右轮换
    	this.destWidth = this.mobilemethod1();
    }   
    
    //移动方法
    
    private void extendarm2(int width)//机械臂2右移方法
	{
		while (this.width < width)
		{
			robot.extend();
			this.width++;
		}
	}
	private void contractarm2(int width) //机械臂2左移方法
	{
		while (this.width > width) {
			robot.contract();
			this.width--;
		}
	}
	
	//构造方法
	
	private int blocktotalheight(int[] blockHeights)//获取两个源列的总高度用于检测程序是否可以终止          

	{
		int totalheight= 0;/*初始总高度为0*/ 
		for (int height : blockHeights/*块高度,foreach语句*/) {
			totalheight=totalheight + height;
		}
		return totalheight;
	}
	private int getheight2()//获得当前列的高度
	    {
	    	int columnheight;
	    	columnheight=barHeights[this.nowbarcolumn- Control.FIRST_BAR_COLUMN];
	    	return columnheight;
	    }
	private int getheight1()//获取左or右源列的总高度
	{
		if (number % 2 == 0) {
			return columnHeight(LEFT);//计算左源列高度;
		} else {
			return columnHeight(RIGHT);//计算右源列高度
		}
	}
	private int MaxadjustHeight()//获取最高height
	{

		
		int maxHeight = 0;
		
		if (this.destWidth == Control.SRC1_COLUMN) {//向左移时
			if (maxHeight < columnHeight(LEFT))
			{
				maxHeight = columnHeight(LEFT);
			}//maxHeight暂时为左源列高度
			for (int i = 0; i <= this.width - Control.FIRST_BAR_COLUMN; i++)
			{
				if (barHeights[i] > maxHeight) 
				{
					maxHeight = barHeights[i];
				}
			}//再与左边bar列比较，当bar列比左源列高时，maxHeight为最高bar列高度
		} 
		else if (this.destWidth == Control.SRC2_COLUMN) {//向右移时
			if (maxHeight < columnHeight(RIGHT))
			{
				maxHeight = columnHeight(RIGHT);
			}//maxHeight暂时为右源列高度
			for (int i = width - Control.FIRST_BAR_COLUMN; i < barHeights.length; i++)
			{
				if (barHeights[i] > maxHeight) 
				{
					maxHeight = barHeights[i];
				}
			}//在与右边bar列比较，当bar列比右源列高时，maxHeight为最高bar列高度
		} 
		else if (this.width == Control.SRC1_COLUMN) {//当在左源列时
			if (maxHeight < columnHeight(LEFT))
			{
				maxHeight = columnHeight(LEFT);
			}//maxHeight暂时为左源列高度
			for (int i = 0; i <= this.destWidth - Control.FIRST_BAR_COLUMN; i++) 
			{
				if (barHeights[i] > maxHeight) 
				{
					maxHeight = barHeights[i];
				}
			}//再与到目标宽度直接的bar列比较，当bar列比左源列高时，maxHeight为最高bar列高度
		} 
		else {//当在右源列时
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
			}//再与到目标宽度直接的bar列比较，当bar列比右源列高时，maxHeight为最高bar列高度
		}
		return maxHeight;
	}
	private int columnHeight(int dir) {// 计算左or右源列的总高度

		int totalHeight = 0;
		for (int i = dir;i< blockHeights.length; i+= 2) 
		{
			totalHeight += blockHeights[i];
		}
		return totalHeight;
	}
	private int mobilemethod1() {
		//根据number计数器查找目标源列,并更新robot需要移动到的目标宽度
		//用number计数器来使目标宽度为左源列或者右源列
		if (number % 2 == 0) {
			destWidth = Control.SRC1_COLUMN;//1
			return Control.SRC1_COLUMN;//1
		} else {
			destWidth = Control.SRC2_COLUMN;//10
			return Control.SRC2_COLUMN;//10
		}
	}
	private void NextBarColumn() {
		//找到robot需要放置块的bar列              
		//如果向右移动，则每次条形列将+1               
		//直到它到达最后一个bar列               
		//然后切换方向，将judge判断更改为false              
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
