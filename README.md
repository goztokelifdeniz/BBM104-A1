# Gym Meal Machine (GMM)

## Overview
Gym Meal Machine (GMM) is a Java 8-based object-oriented programming (OOP) project designed to simulate a vending machine that allows gym members to select meals based on their nutritional needs. The system supports meal selection by protein, carbohydrate, fat, or calorie content, as well as direct slot-based selection.

## Features
### Meal Management
- **Loading Products:** Products are loaded row by row from `Product.txt`, with calculated calorie values.
- **Storage Rules:** GMM has **6 rows and 4 columns**, with a maximum of **10 items per slot**.
- **Calorie Calculation:** Calories are computed using the formula:
  
  ```
  calorie (kcal) = 4 × protein (g) + 4 × carbohydrate (g) + 9 × fat (g)
  ```

### Purchasing System
- Users can purchase meals by:
  - Selecting meals based on **protein, carbohydrate, fat, or calorie** values (with a ±5 tolerance range).
  - Choosing a product directly by **slot number**.
- Payments are made using **1, 5, 10, 20, 50, 100, or 200 TL**.
- Remaining change is returned after purchase.
- If an issue occurs (e.g., empty slot, insufficient funds), an informational message is displayed, and money is refunded.

### Input & Output
- **Input Files:**
  - `Product.txt`: Contains product details (name, price, protein, carbohydrate, fat, but no calorie value).
  - `Purchase.txt`: Lists transactions with payment details and selected nutrition type.
- **Output File:**
  - `GMMOutput.txt`: Stores machine contents before and after purchases, transaction logs, and informational messages.

## Compilation & Execution
1. Upload Java files to the **dev.cs.hacettepe.edu.tr** server.
2. Compile the code:
   ```bash
   javac8 Main.java
   ```
3. Run the program:
   ```bash
   java8 Main Product.txt Purchase.txt GMMOutput.txt
   ```
4. Check `GMMOutput.txt` for results.

## Project Structure
```
project_folder/
│── FileInput.java
│── FileOutput.java
│── Main.java
│── Member.java
│── Product.txt
│── Purchase.txt
│── Slot.java
│── GMMOutput.txt
```

## Notes
- The project follows **Object-Oriented Programming (OOP)** principles, utilizing **classes and objects**.
- **JavaDoc comments** are used for documentation.
- Code is tested and executed on the **Hacettepe University DEV server**.

## License
This project is for educational and non-commercial use.

