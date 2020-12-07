package exceptions;

public class NicknameLengthException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NicknameLengthException(){
		super("The nickname entered exceeds 10 characters, \n please enter a shorter one");
	}

}
