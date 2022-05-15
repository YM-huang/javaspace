package state;

public class DayState implements State {
    private static DayState singleton = new DayState();
    private DayState() {                                // ���캯���Ŀɼ�����private
    }
    public static State getInstance() {                 // ��ȡΨһʵ��
        return singleton;
    }
    public void doClock(Context context, int hour) {    // ����ʱ��
        if (hour < 9 || 17 <= hour) {
            context.changeState(NightState.getInstance());
        }
    }
    public void doUse(Context context) {                // ʹ�ý��
        context.recordLog("ʹ�ý��(����)");
    }
    public void doAlarm(Context context) {              // ���¾���
        context.callSecurityCenter("���¾���(����)");
    }
    public void doPhone(Context context) {              // ����ͨ��
        context.callSecurityCenter("����ͨ��(����)");
    }
    public String toString() {                          // ��ʾ��ʾ�������
        return "[����]";
    }
}