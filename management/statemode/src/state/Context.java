package state;

public interface Context { 
    public abstract void setClock(int hour);                // ����ʱ��
    public abstract void changeState(State state);          // �ı�״̬
    public abstract void callSecurityCenter(String msg);    // ��ϵ��������
    public abstract void recordLog(String msg);             // �ھ����������¼�¼
}
