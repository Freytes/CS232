So from what I noticed, your cart items class seems to redundent when i look at you product class. 

in general I would suggest your main.java be incharge of only initiating the project, creating a store class that uses a instance of inventory and shopping cart.
the shoppingcart should be incharge of adding and removing items from the datatastructure you choose. the poduct properties should only come from the 
the productclass and the product class should only be incharge of modyfing its own properties. the shopping cart should only be able to store and retrieve these objects, 
and the store class should handle the user imput and priority setting and the eventual purchase. this way you can seperate your concerns and it
will make you code cleaner and easier to handle and eventually store into a db. 

