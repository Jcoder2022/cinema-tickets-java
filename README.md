# cinema-tickets-java
Test

/**
     * Should only have private methods other than the one below.
     */
    /*
     * # Business Rules
     * - There are 3 types of tickets i.e. Infant, Child, and Adult.
     * - The ticket prices are based on the type of ticket (see table below).
     * - The ticket purchaser declares how many and what type of tickets they want
     * to buy.
     * - Multiple tickets can be purchased at any given time.
     * - Only a maximum of 25 tickets that can be purchased at a time.
     * - Infants do not pay for a ticket and are not allocated a seat. They will be
     * sitting on an Adult's lap.
     * - Child and Infant tickets cannot be purchased without purchasing an Adult
     * ticket.
     * | Ticket Type | Price |
     * | ---------------- | ----------- |
     * | INFANT | £0 |
     * | CHILD | £15 |
     * | ADULT | £25 |
     * - There is an existing `TicketPaymentService` responsible for taking
     * payments.
     * - There is an existing `SeatReservationService` responsible for reserving
     * seats.
     * 
     * 
     * ## Constraints
     * - The TicketService interface CANNOT be modified.
     * - The code in the thirdparty.* packages CANNOT be modified.
     * - The `TicketTypeRequest` SHOULD be an immutable object.
     * 
     * 
     * ## Your Task
     * Provide a working implementation of a `TicketService` that:
     * - Considers the above objective, business rules, constraints & assumptions.
     * - Calculates the correct amount for the requested tickets and makes a payment
     * request to the `TicketPaymentService`.
     * - Calculates the correct no of seats to reserve and makes a seat reservation
     * request to the `SeatReservationService`.
     * - Rejects any invalid ticket purchase requests. It is up to you to identify
     * what should be deemed as an invalid purchase request
     * 
     * 
     */
