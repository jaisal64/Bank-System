package coding.Enum;



/**
 * @Author: Jun Zhu
 * @Date: 12/2/2022 5:51 PM
 * @Description: TODO
 */
public enum AccountType {
    Saving_Type(1,"Saving"),
    Checking_Type(2,"Checking"),
    Security_Type(3,"Security");
    int typeIndex;
    String typeName;

    AccountType(int typeIndex, String typeName) {
        this.typeIndex = typeIndex;
        this.typeName = typeName;
    }

    public static int getIndexByName(String typeName){
        for (AccountType accountType:AccountType.values()){
            if (accountType.getTypeName().equals(typeName)){
                return accountType.getTypeIndex();
            }
        }
        return 0;
    }

    public static String getNameByIndex(int index){
        for (AccountType accountType:AccountType.values()){
            if (accountType.getTypeIndex()==index){
                return accountType.getTypeName();
            }
        }
        return "";
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }
}
