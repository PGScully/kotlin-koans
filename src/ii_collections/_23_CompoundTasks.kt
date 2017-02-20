package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product
    return this.customers.filter { it.orderedProducts.contains(product) }.toSet()
}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)
    return this.deliveredOrders().flatMap { it.products }.maxBy { it.price }
}

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
    return this.customers.flatMap { it.orders }.flatMap { it.products }.count { it == product }
}
