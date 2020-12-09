package exceptions;

public class WinException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WinException(){
		super("You Win!");
	}
}
