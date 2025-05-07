package CustomExceptions;

public class OperationFailedException extends Exception{
    public OperationFailedException(String message){
        super(message);
    }
}
