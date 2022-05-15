package decodepack;

public abstract class encodeanddecodemain {
	
	ecanddcbehavior edbehavior;

	
	public encodeanddecodemain(){
		
	}

	
	public void performEncode(String file, String destFile) throws Exception 
	{
		edbehavior.encrypt(file,destFile);
	}
	
	public void performDecode(String file, String destFile) throws Exception 
	{
		edbehavior.decrypt(file,destFile);
	}


}
