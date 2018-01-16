package util;

public class FormatUtil {
    
    public String formatString(String string){
        string = string.replace(" ", "%20").replace("/", "%2F");
        return string;
    }
    
    public String formatDate(String string){
        string = string.substring(0, 10);
        return string;
    }
    
    public String formatDouble(double val){
        return String.format("%.2f", val);
    }
    
    public String formatInteger(int val){
        return val+"";
    }

}
