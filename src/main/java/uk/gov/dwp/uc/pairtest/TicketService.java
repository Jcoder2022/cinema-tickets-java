package uk.gov.dwp.uc.pairtest;

import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeResponse;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public interface TicketService {

    TicketTypeResponse purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests)
            throws InvalidPurchaseException;

}
