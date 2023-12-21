package optimo.lotterypointsprocessor.commons.enums;

/**
 * @author Irakli Iobashvili
 */
public enum AccountTypes {
    POINTS("POINTS");
    private String type;
    AccountTypes(String type) {
        this.type=type;
    }
    public String getType(){
        return type;
    }
}
