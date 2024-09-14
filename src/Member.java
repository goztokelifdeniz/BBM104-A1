/**
 * Represents a Member (person) that is using the Gym Meal Machine.
 */
public class Member {
    public String memberName;
    private String moneyType, choiceName;
    private int choiceValue;
    private int money = 0;
    private boolean moneyValidity = true;


    /**
     * Constructs a new Member with the given name.
     * @param memberName The name of the member.
     */
    Member(String memberName){
        this.memberName = memberName;
    }

    /**
     * Checks if the money provided by the member is valid according to Gym Meal Machine.
     * @return true if the money is valid, false otherwise.
     */
    public boolean isMoneyValidity() {
        return moneyValidity;
    }


    /**
     * Sets the validity of the money provided by the member.
     * @param moneyValidity The validity status of the money.
     */
    public void setMoneyValidity(boolean moneyValidity) {
        this.moneyValidity = moneyValidity;
    }


    /**
     * Gets the type of money used by the member (e.g., cash).
     * @return The type of money used by the member.
     */
    public String getMoneyType() {
        return moneyType;
    }

    /**
     * Sets the type of money used by the member.
     * @param moneyType The type of money used by the member.
     */
    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    /**
     * Sets the name of the choice made by the member.
     * @param choiceName The name of the choice made by the member.
     */
    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

    /**
     * Gets the name of the choice made by the member (e.g., slot number or nutrition value).
     * @return The name of the choice made by the member.
     */
    public String getChoiceName() {
        return choiceName;
    }

    /**
     * Gets the value of the choice made by the member (e.g., slot number or nutrition value).
     * @return The value of the choice made by the member.
     */
    public int getChoiceValue() {
        return choiceValue;
    }

    /**
     * Sets the value of the choice made by the member.
     * @param choiceValue The value of the choice made by the member.
     */
    public void setChoiceValue(int choiceValue) {
        this.choiceValue = choiceValue;
    }

    /**
     * Gets the amount of money possessed by the member.
     * @return The amount of money possessed by the member.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the amount of money possessed by the member.
     * @param money The amount of money possessed by the member.
     */
    public void setMoney(int money) {
        this.money = money;
    }
}
