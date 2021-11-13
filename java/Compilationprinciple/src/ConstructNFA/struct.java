package ConstructNFA;

public class struct 
{ 
    public struct(int id, int nextId, char aChar) {
    	this.id = id;
        this.nextId = nextId;
        this.aChar = aChar;
	}
    public int id;//保存起始状态序号
    public int nextId;//保存终止状态序号 
    public char aChar;//保存输入信息
}
