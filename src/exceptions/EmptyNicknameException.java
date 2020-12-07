package exceptions;

public class EmptyNicknameException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmptyNicknameException(){
		super("Please enter a nickname to be able to continue");
	}
}
