package optimo.lotterypointsprocessor.commons.enums;

/**
 * @author Irakli Iobashvili
 */
public enum Actions {
    POINTS("POINTS");
    private String type;
    Actions(String type) {
        this.type=type;
    }
    public String getType(){
        return type;
    }
}
