package optimo.lotterypointsprocessor.commons.enums;

/**
 * @author Irakli Iobashvili
 */
public enum TransactionTypes {
    ADD_POINTS("ADD_POINTS"),
    BUY_TICKET("BUY_TICKET");

    private String type;
    TransactionTypes(String type) {
        this.type=type;
    }
    public String getType(){
        return type;
    }
}
