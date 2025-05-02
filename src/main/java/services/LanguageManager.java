package services;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static LanguageManager instance;
    private Locale currentLocale;
    private static final String BASE_NAME="languages.messages";
    public LanguageManager(){
        this.currentLocale=new Locale("sq","Kosova");
    }
    public static LanguageManager getInstance(){
        if(instance==null){
            instance=new LanguageManager();
        }
        return instance;
    }
    public void setLocale(Locale locale){
        this.currentLocale=locale;
    }
    public ResourceBundle getResourceBundle(){
        return ResourceBundle.getBundle(BASE_NAME,this.currentLocale);
    }
}
