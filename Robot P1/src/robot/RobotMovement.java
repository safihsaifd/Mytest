package robot;

//Caspar��Ross�ṩ�Ļ������ƶ����棨�������ڲ�ʹ�ã�
public interface RobotMovement
{
	/**
	 *
	 * �����Ķ���ʰȡ�飨��3�����ڿ�����Ϸ���
	 */
	public abstract void pick();

	/**
	 * 
	 * ���еķ��ÿ飨��3����λ�ڷ���λ�����Ϸ���
	 */
	public abstract void drop();

	/**
	 * move arm 1 up (height)��е��1
	 */
	public abstract void up();

	/**
	 * move arm 1 down (height)��е��1
	 */
	public abstract void down();

	/**
	 * move arm2 left (width)��е��2�����ƶ�ARM2����ȣ�
	 */
	public abstract void contract();

	/**
	 * move arm2 right (width)��е��2�����ƶ�ARM2����ȣ�
	 */
	public abstract void extend();

	/**
	 * move arm3 down (depth)��е��3�����ƶ�ARM3����ȣ�
	 */
	public abstract void lower();

	/**
	 * move arm3 up (depth)��е��3�����ƶ�ARM3����ȣ�
	 */
	public abstract void raise();
}
