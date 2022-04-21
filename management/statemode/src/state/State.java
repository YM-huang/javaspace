package state;

public interface State {
	public abstract void doClock(Context context, int hour);    // ����ʱ��
    public abstract void doUse(Context context);                // ʹ�ý��
    public abstract void doAlarm(Context context);              // ���¾���
    public abstract void doPhone(Context context);              // ����ͨ��
}
