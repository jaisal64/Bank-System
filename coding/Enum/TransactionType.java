package coding.Enum;

import java.util.Arrays;

/**
 * @Author: Jun Zhu
 * @Date: 12/1/2022 7:55 PM
 * @Description: TODO
 */
public enum TransactionType {
    DEPOSIT(1,"Deposit"),
    WITHDRAW(2,"Withdraw"),
    TRANSACTION(3,"Transaction"),
    TRANSFER(4,"Transfer"),
    CHARGE(5,"Charge"),
    BUY_STOCK(6,"BUY_STOCK"),
    SELL_STOCK(7,"SELL_STOCK"),
    BUY_CURRENCY(8,"BUY_CURRENCY"),
    SELL_CURRENCY(9,"SELL_CURRENCY"),
    CREATE_NEW_ACCOUNT(10,"CREATE_NEW_ACCOUNT"),
    SYSTEM_FEE(11,"SYSTEM_FEE"),
    TAKE_LOAN(12,"TAKE_LOAN"),
    RETURN_LOAN(13,"RETURN_LOAN"),
    NA(14,"N/A");
    int typeIndex;
    String typeName;

    TransactionType(int typeIndex, String typeName) {
        this.typeIndex = typeIndex;
        this.typeName = typeName;
    }

    public static int getIndexByName(String typeName){
        for (TransactionType accountType:TransactionType.values()){
            if (accountType.getTypeName().equals(typeName)){
                return accountType.getTypeIndex();
            }
        }
        return 0;
    }

    public static String getNameByIndex(int index){
        for (TransactionType accountType:TransactionType.values()){
            if (accountType.getTypeIndex()==index){
                return accountType.getTypeName();
            }
        }
        return "";
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public TransactionType getTransactionType(int transactionTypeIndex){
        return Arrays.stream(TransactionType.values()).filter(transactionType ->
                transactionType.typeIndex==transactionTypeIndex).findFirst().orElse(TransactionType.NA);
    }
}