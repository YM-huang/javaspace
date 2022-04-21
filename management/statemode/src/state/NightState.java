package state;

public class NightState implements State {
    private static NightState singleton = new NightState();
    private NightState() {                              // ���캯���Ŀɼ�����private
    }
    public static State getInstance() {                 // ��ȡΨһʵ��
        return singleton;
    }
    public void doClock(Context context, int hour) {    // ����ʱ��
        if (9 <= hour && hour < 17) {
            context.changeState(DayState.getInstance());
        }
    }
    public void doUse(Context context) {                // ʹ�ý��
        context.callSecurityCenter("����������ʹ�ý�⣡");
    }
    public void doAlarm(Context context) {              // ���¾���
        context.callSecurityCenter("���¾���(����)");
    }
    public void doPhone(Context context) {              // ����ͨ��
        context.recordLog("���ϵ�ͨ��¼��");
    }
    public String toString() {                          // ��ʾ��ʾ�������
        return "[����]";
    }
}