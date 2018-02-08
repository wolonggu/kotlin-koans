package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
    return customers.filter { it.orderedProducts.contains<Product>(product) }.toSet()
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)
    val (delivered, undelivered) = orders.partition { it.isDelivered;  };
    val products = delivered.flatMap { it.products }
    return products.maxBy { it.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
    val allOrders = customers.flatMap { it.orders }
    val allOrderedProducts = allOrders.flatMap { it.products }
    return allOrderedProducts.filter { it.name == product.name }.count()
}
