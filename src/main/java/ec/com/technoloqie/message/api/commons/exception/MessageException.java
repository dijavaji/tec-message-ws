package ec.com.technoloqie.message.api.commons.exception;

public class MessageException extends RuntimeException{
	
	public MessageException() {
        super();
    }
    
	public MessageException (String msg, Throwable nested) {
        super(msg, nested);
    }
    
	public MessageException (String message) {
        super(message);
    }
    
	public MessageException(Throwable nested) {
        super(nested);
	}
	
	private static final long serialVersionUID = 1L;
	
}
