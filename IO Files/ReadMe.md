# Information about sample I/O files
**Release Date: 21/03/2024**

## Sample I/O Pack Number 1
A simple example where machine is filled with no problems. Includes:

 - "INFO: Insufficient money, try again with more money."

 - "INFO: Number cannot be accepted. Please try again with another number."

 - "INFO: Product not found, your money will be returned."

 - "INFO: This slot is empty, your money will be returned."

 - And purchases with no problem.

## Sample I/O Pack Number 3
A sample with:

- An example where all the products in one slot are sold.
 (So you can see that a slot is not assigned to a particular product indefinitely.
 If products in a slot is sold out, the slot should be RESET and look like all the other empty slots.)

 - An example with 0 calorie input in a scenario where there are no products with 0 calorie.
(So you can see that while you are writing GMM to output file you are to write 0 for the calorie if the slot is empty,
but this does not mean that you can point to an empty slot if there are no products with 0 calorie.)

 -   And some other examples like pack number 1.