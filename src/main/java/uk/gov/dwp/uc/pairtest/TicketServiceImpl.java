package uk.gov.dwp.uc.pairtest;

import java.net.Authenticator.RequestorType;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeResponse;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
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

    private static TicketTypeResponse ticketTypeResponse;

    @Override
    public TicketTypeResponse purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests)
            throws InvalidPurchaseException {

        int countAdult = 0;
        int countChild = 0;
        int countInfant = 0;
        double totalPrice = 0;

        if (ticketTypeRequests.length > 0) {
            for (TicketTypeRequest ticketTypeRequest : ticketTypeRequests) {
                if (ticketTypeRequest.getTicketType() == TicketTypeRequest.Type.ADULT) {
                    countAdult = ticketTypeRequest.getNoOfTickets();
                    totalPrice = totalPrice + ticketTypeRequest.getNoOfTickets() * 25;

                    System.out.println("Adult");
                } else if (ticketTypeRequest.getTicketType() == TicketTypeRequest.Type.CHILD) {
                    System.out.println("Child");
                    countChild = ticketTypeRequest.getNoOfTickets();
                    totalPrice = totalPrice + ticketTypeRequest.getNoOfTickets() * 15;
                } else // (ticketTypeRequest.getTicketType() == TicketTypeRequest.Type.INFANT)
                {
                    countInfant = ticketTypeRequest.getNoOfTickets();
                }

            }

            if (countChild > 0 & countAdult == 0) {
                throw new InvalidPurchaseException("Children Must be accompanied by atlease one Adult");
            }
            if (countInfant > 0 & countAdult == 0) {
                throw new InvalidPurchaseException("Infant Must be accompanied by atlease one Adult");
            }
            if (countInfant > 0 && countInfant > countAdult) {
                throw new InvalidPurchaseException("Each Infant Must be accompanied by atlease one Adult");
            }

            ticketTypeResponse = new TicketTypeResponse(countAdult, countChild, countInfant, totalPrice);
        }
        if ((countAdult + countChild + countInfant) > 25)
            throw new InvalidPurchaseException("Total number of tickets bought at one occassion must be < than 25.");

        return ticketTypeResponse;
    }

}
