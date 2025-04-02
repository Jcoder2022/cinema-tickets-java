package uk.gov.dwp.uc.pairtest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;

//import org.aspectj.lang.annotation.Before;
//import org.assertj.core.api.Assert;
//import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
//import org.junit.runner.RunWith;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeResponse;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

//import org.mockito.junit.MockitoJUnitRunner;
//import org.junit.BeforeClass;

//@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

    @Mock
    private TicketService ticketService = new TicketServiceImpl();

    private static TicketTypeRequest firstTicketTypeRequest;
    private static TicketTypeRequest secTicketTypeRequest;
    private static TicketTypeRequest thirdTicketTypeRequest;
    private static TicketTypeRequest ticketTypeRequests[];
    private static TicketTypeResponse ticketTypeResponse;

    private static Long firstCustomerAccountId = 1l;
    private static Long secCustomerAccountId = 2l;
    private static Long thirdCustomerAccountId = 3l;
    private static Long fourthCustomerAccountId = 4l;

    @BeforeEach
    public void setUp() {

        firstTicketTypeRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 10);
        secTicketTypeRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10);
        thirdTicketTypeRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 3);

        ticketTypeRequests = new TicketTypeRequest[2];

        ticketTypeRequests[0] = firstTicketTypeRequest;
        ticketTypeRequests[1] = thirdTicketTypeRequest;

    }

    @Test
    public void purchase_Tickets_ADULTS_Type_Test() throws InvalidPurchaseException {

        TicketTypeResponse ticketTypeResponse = ticketService.purchaseTickets(secCustomerAccountId,
                thirdTicketTypeRequest);
        Assertions.assertEquals(thirdTicketTypeRequest.getNoOfTickets(),
                ticketTypeResponse.getCountAdult() + ticketTypeResponse.getCountChild());
        Assertions.assertEquals(thirdTicketTypeRequest.getNoOfTickets() * 25, ticketTypeResponse.getTotalPrice());
    }

    @Test
    public void purchase_Tickets_Type_As_Children_Age_Less_Than_10_Test() {

        InvalidPurchaseException exception = Assertions.assertThrows(InvalidPurchaseException.class, () -> {
            ticketService.purchaseTickets(firstCustomerAccountId, firstTicketTypeRequest);
        });
        Assertions.assertEquals("Children Must be accompanied by atlease one Adult", exception.getMessage());

    }

    @Test
    public void purchase_Tickets_Type_As_Children_And_Adult_Test() throws InvalidPurchaseException {

        TicketTypeResponse ticketTypeResponse = ticketService.purchaseTickets(thirdCustomerAccountId,
                ticketTypeRequests);
        Assertions.assertEquals(thirdTicketTypeRequest.getNoOfTickets(), ticketTypeResponse.getCountAdult());
        Assertions.assertEquals(225.0, ticketTypeResponse.getTotalPrice());

    }
}
