import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        // Reading product and purchase content from files.
        String[] productContent = FileInput.readFile(args[0], false, false);
        String[] purchaseContent = FileInput.readFile(args[1], false, false);

        // Initializing arrays for products, slots, members, and output content.
        ArrayList<Product> productsArray = initializeProducts(productContent != null ? productContent : new String[0]);
        ArrayList<Slot> slotsArray = initializeSlots();
        ArrayList<Member> membersArray = initializeMembers(purchaseContent != null ? purchaseContent : new String[0]);
        ArrayList<String> outputContent = new ArrayList<>();

        // Filling the slots with products.
        outerLoop:
        for (Product currentProduct : productsArray) {
            for (Slot currentSlot : slotsArray) {
                int fillSituation = fill(currentSlot, currentProduct, slotsArray, outputContent);
                if (fillSituation == -1){
                    break outerLoop;
                } else if (fillSituation == 1){
                    break;
                }
            }
        }
        // Printing machine status after filling.
        printGMM(slotsArray, outputContent);

        // Processing purchases for members.
        for (Member currentMember : membersArray){
            int purchaseSituation = purchase(currentMember, membersArray, outputContent, purchaseContent, slotsArray);
        }
        // Printing machine status after purchases.
        printGMM(slotsArray, outputContent);

        // Writing output content to file.
        FileOutput.writeToFile(args[2], "", false, false);
        for (String line : outputContent) {
            FileOutput.writeToFile(args[2], line, true, true);
        }
    }


    /**
     * Fills a slot with a product if possible, updating slot information and generating output messages.
     * @param currentSlot The slot to fill with the product.
     * @param currentProduct The product to place in the slot.
     * @param slotsArray The array of all slots in the machine.
     * @param outputContent The list to which output messages are added.
     * @return An integer indicating the result of the fill operation:
     *         1 - Success: The product is successfully placed in the slot.
     *         0 - No action taken: The slot is neither full nor empty.
     *        -1 - Failure: The slot is full and no space is available, or the machine is full.
     */
    public static int fill(Slot currentSlot, Product currentProduct, ArrayList<Slot> slotsArray, ArrayList<String> outputContent){
        // slot completely full
        if (currentSlot.getProductAmount() == 10) {
            if (slotsArray.indexOf(currentSlot) == 23) {
                outputContent.add("INFO: There is no available place to put " + currentProduct.getProductName());
                if (checkIfTheMachineIsFull(slotsArray)){
                    outputContent.add("INFO: The machine is full!");
                    return -1;
                }
                return 1;
            }

        // slot completely empty
        } else if (currentSlot.getProductAmount() == 0) {
            currentSlot.setProductAmount(currentSlot.getProductAmount() + 1);
            currentSlot.fillTheSlotWithProduct(currentProduct);
            return 1;

        // slot has product inside but has place to put another product
        } else {
            if (currentSlot.getProductName().equals(currentProduct.getProductName())) {
                currentSlot.setProductAmount(currentSlot.getProductAmount() + 1);
                return 1;
            }
            else {
                if (slotsArray.indexOf(currentSlot) == 23){
                    outputContent.add("INFO: There is no available place to put " + currentProduct.getProductName());
                    if (checkIfTheMachineIsFull(slotsArray)){
                        outputContent.add("INFO: The machine is full!");
                        return -1;
                    }
                    return 1;
                }
            }
        }
        return 0;
    }


    /**
     * Processes a purchase for a member, updating slot and member information and generating output messages.
     * @param currentMember The member making the purchase.
     * @param membersArray The array of all members.
     * @param outputContent The list to which output messages are added.
     * @param purchaseContent The content of purchase information.
     * @param slotsArray The array of all slots in the machine.
     * @return An integer indicating the result of the purchase operation:
     *         1 - Success: The purchase is successful.
     *        -1 - Failure: The purchase cannot be completed due to insufficient funds or other reasons.
     */
    public static int purchase(Member currentMember, ArrayList<Member> membersArray,
                               ArrayList<String> outputContent, String[] purchaseContent, ArrayList<Slot> slotsArray){

        outputContent.add("INPUT: " + purchaseContent[membersArray.indexOf(currentMember)]);
        if (!currentMember.isMoneyValidity()){
            outputContent.add("INFO: GMM only accepts 1, 5, 10, 20, 50, 100 and 200 TL as money, your money will be returned.");
            returnAllMoney(currentMember, outputContent);
            return -1;
        } else if (!currentMember.getMoneyType().equals("CASH")){
            outputContent.add("INFO: GMM accepts cash only, your money will be returned.");
            returnAllMoney(currentMember, outputContent);
            return -1;
        } else{
            if (currentMember.getChoiceName().equals("NUMBER")){
                if (!((0 <= currentMember.getChoiceValue()) && (currentMember.getChoiceValue() <= 23))){
                    outputContent.add("INFO: Number cannot be accepted. Please try again with another number.");
                    returnAllMoney(currentMember, outputContent);
                    return -1;
                } else {
                    Slot currentSlot = slotsArray.get(currentMember.getChoiceValue());
                    if (currentSlot.getProductName().equals("___")) {
                        outputContent.add("INFO: This slot is empty, your money will be returned.");
                        returnAllMoney(currentMember, outputContent);
                        return -1;
                    }
                    else if (currentMember.getMoney() < currentSlot.getPrice()){
                        outputContent.add("INFO: Insufficient money, try again with more money.");
                        returnAllMoney(currentMember, outputContent);
                        return -1;
                    } else{
                        outputContent.add("PURCHASE: You have bought one " + currentSlot.getProductName());
                        outputContent.add("RETURN: Returning your change: " + (currentMember.getMoney() - currentSlot.getPrice())+ " TL");
                        currentSlot.setProductAmount(currentSlot.getProductAmount() - 1);
                        if (currentSlot.getProductAmount() == 0){
                            currentSlot.resetSlot();
                        }
                        return 1;
                    }
                }

            } else{ // nutrition value is chosen
                for (Slot currentSlot : slotsArray) {
                    if (!currentSlot.getProductName().equals("___")){
                        double chosenNutritionValueOfCurrentSlot = 0;

                        switch (currentMember.getChoiceName()) {
                            case "PROTEIN":
                                chosenNutritionValueOfCurrentSlot = currentSlot.getProtein();
                                break;
                            case "CARB":
                                chosenNutritionValueOfCurrentSlot = currentSlot.getCarb();
                                break;
                            case "FAT":
                                chosenNutritionValueOfCurrentSlot = currentSlot.getFat();
                                break;
                            case "CALORIE":
                                chosenNutritionValueOfCurrentSlot = currentSlot.getCalorie();
                                break;
                        }

                        if ((currentMember.getChoiceValue() - 5 <= chosenNutritionValueOfCurrentSlot) && (chosenNutritionValueOfCurrentSlot <= currentMember.getChoiceValue() + 5)) {
                            if (currentMember.getMoney() >= currentSlot.getPrice()) {

                                currentSlot.setProductAmount(currentSlot.getProductAmount() - 1);
                                outputContent.add("PURCHASE: You have bought one " + currentSlot.getProductName());
                                outputContent.add("RETURN: Returning your change: " + (currentMember.getMoney() - currentSlot.getPrice()) + " TL");
                                if (currentSlot.getProductAmount() == 0){
                                    currentSlot.resetSlot();
                                }
                                return 1;
                            } else {
                                outputContent.add("INFO: Insufficient money, try again with more money.");
                                returnAllMoney(currentMember, outputContent);
                                return -1;
                            }
                        }
                    }
                }
                outputContent.add("INFO: Product not found, your money will be returned.");
                returnAllMoney(currentMember, outputContent);
                return -1;
            }
        }
    }

    /**
     * Initializes an array list of products based on the provided product content.
     * @param productContent The content of products to initialize from.
     * @return An array list containing initialized products.
     */
    public static ArrayList<Product> initializeProducts(String[] productContent){
        ArrayList<Product> productsArray = new ArrayList<>();
        for (int index = 0; index < productContent.length; index++) {
            Product product = new Product(String.valueOf(index));
            productsArray.add(product);

            Product currentProduct = productsArray.get(index);

            String[] productInfoArray = productContent[index].split("\t");
            currentProduct.setProductName(productInfoArray[0]);
            currentProduct.setPrice(Integer.parseInt(productInfoArray[1]));

            String nutritionValues = productInfoArray[2];

            String[] nutritionValuesArray = nutritionValues.split(" ");
            currentProduct.setProtein(Double.parseDouble(nutritionValuesArray[0]));
            currentProduct.setCarb(Double.parseDouble(nutritionValuesArray[1]));
            currentProduct.setFat(Double.parseDouble(nutritionValuesArray[2]));

            currentProduct.findAndSetCalorie();
        }
        return productsArray;
    }

    /**
     * Initializes an array list of slots.
     * @return An array list containing initialized slots.
     */
    public static ArrayList<Slot> initializeSlots(){
        ArrayList<Slot> slotsArray = new ArrayList<>();
        for (int index = 0; index < 24; index++) {
            Slot slot = new Slot(String.valueOf(index));
            slotsArray.add(slot);
        }
        return slotsArray;
    }

    /**
     * Initializes an array list of members based on the provided purchase content.
     * @param purchaseContent The content of purchases to initialize from.
     * @return An array list containing initialized members.
     */
    public static ArrayList<Member> initializeMembers(String[] purchaseContent){
        ArrayList<Member> membersArray = new ArrayList<>();
        for (int index = 0; index < purchaseContent.length; index++) {
            Member member = new Member(String.valueOf(index));
            membersArray.add(member);

            Member currentMember = membersArray.get(index);

            String[] purchaseInfoArray = purchaseContent[index].split("\t");
            currentMember.setMoneyType(purchaseInfoArray[0]);
            currentMember.setChoiceName(purchaseInfoArray[2]);
            currentMember.setChoiceValue(Integer.parseInt(purchaseInfoArray[3]));

            String[] moneyStringsArray = purchaseInfoArray[1].split(" ");

            ArrayList<String> validMoneyBillsList = new ArrayList<>();
            validMoneyBillsList.add("1");
            validMoneyBillsList.add("5");
            validMoneyBillsList.add("10");
            validMoneyBillsList.add("20");
            validMoneyBillsList.add("50");
            validMoneyBillsList.add("100");
            validMoneyBillsList.add("200");

            for(String moneyString : moneyStringsArray) {
                if (!validMoneyBillsList.contains(moneyString)) {
                    currentMember.setMoneyValidity(false);
                }
                currentMember.setMoney(currentMember.getMoney() + Integer.parseInt(moneyString));
            }
        }
        return membersArray;
    }

    /**
     * Prints the current status of the Gym Meal Machine, including slot information.
     * @param slotsArray The array of all slots in the machine.
     * @param outputContent The list to which output messages are added.
     */
    public static void printGMM(ArrayList<Slot> slotsArray, ArrayList<String> outputContent){
       StringBuilder  toBeAddedString = new StringBuilder();
        toBeAddedString.append("-----Gym Meal Machine-----\n");

        for (int index = 0; index < slotsArray.size(); index++) {
            Slot currentSlot = slotsArray.get(index);
            toBeAddedString.append(String.format("%s(%d, %d)___",
                    currentSlot.getProductName(), currentSlot.getCalorie(), currentSlot.getProductAmount()));
            if ((index+1) % 4 == 0){
                toBeAddedString.append("\n");
            }
        }
        toBeAddedString.append("----------");
        outputContent.add(toBeAddedString.toString());
    }

    /**
     * Checks if the machine is full by examining all slots.
     * @param slotsArray The array of all slots in the machine.
     * @return True if the machine is full, false otherwise.
     */
    public static boolean checkIfTheMachineIsFull(ArrayList<Slot> slotsArray) {
        boolean machineIsFull = true;
        for (Slot slot : slotsArray) {
            if (slot.getProductAmount() != 10) {
                machineIsFull = false;
                break;
            }
        }
        return machineIsFull;
    }

    /**
     * Returns all money back to the member.
     * @param currentMember The member to return money to.
     * @param outputContent The list to which output messages are added.
     */
    public static void returnAllMoney(Member currentMember, ArrayList<String> outputContent){
        outputContent.add("RETURN: Returning your change: " + currentMember.getMoney() + " TL");
    }
}