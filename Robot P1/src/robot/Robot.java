package robot;

//Caspar和Ross提供的机器人接口（机器人内部使用）
public interface Robot extends RobotMovement
{
	/**
	 * @param barHeights
	 *            specifies the height of the bars from left to right (the array
	 *            is copied internally)
	 * @param blockHeights
	 *            specifies the height of each block which will be stacked in
	 *            the far right column
	 * @param height
	 *            starting height of arm1
	 * @param width
	 *            starting width of arm2
	 * @param depth
	 *            starting depth of arm3
	 */
	public abstract void init(int barHeights[], int blockHeights[], int height,
			int width, int depth);

	/**
	 * Slow down the Robot animation by specified amount. Robot speed range is
	 * from 1 - 10. The speed
	 * starts at 5. The actual animation speed halved each time the speed is
	 * adjusted. Calling
	 * slowDown(2) has the same effect as calling slowDown(1) twice.
	 * 
	 * @param amount
	 *            the number of times to adjust the speed.
	 */
	public abstract void slowDown(int amount);
	
	/**
	 * Speed up the Robot animation by specified amount. Robot speed range is
	 * from 1 - 10. The speed
	 * starts at 5. The actual animation speed doubles each time the speed is
	 * adjusted. Calling
	 * speedUp(2) has the same effect as calling speedUp(1) twice.
	 * 
	 * @param amount
	 *            the number of times to adjust the speed.
	 */
	public void speedUp(int amount);
}