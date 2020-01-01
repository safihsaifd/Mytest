package robot;

//Caspar和Ross提供的机器人移动界面（机器人内部使用）
public interface RobotMovement
{
	/**
	 *
	 * 从柱的顶部拾取块（臂3必须在块的正上方）
	 */
	public abstract void pick();

	/**
	 * 
	 * 列中的放置块（臂3必须位于放置位置正上方）
	 */
	public abstract void drop();

	/**
	 * move arm 1 up (height)机械臂1
	 */
	public abstract void up();

	/**
	 * move arm 1 down (height)机械臂1
	 */
	public abstract void down();

	/**
	 * move arm2 left (width)机械臂2向左移动ARM2（宽度）
	 */
	public abstract void contract();

	/**
	 * move arm2 right (width)机械臂2向右移动ARM2（宽度）
	 */
	public abstract void extend();

	/**
	 * move arm3 down (depth)机械臂3向下移动ARM3（深度）
	 */
	public abstract void lower();

	/**
	 * move arm3 up (depth)机械臂3向上移动ARM3（深度）
	 */
	public abstract void raise();
}
