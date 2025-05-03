package CustomExceptions;

public class SceneManagerNotInitializedException extends Exception{
    public SceneManagerNotInitializedException(){
        super("Scene Manager nuk është inicializuar ende!");
    }
}
