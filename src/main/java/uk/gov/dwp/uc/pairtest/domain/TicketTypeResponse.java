package uk.gov.dwp.uc.pairtest.domain;

public class TicketTypeResponse {
    int countAdult = 0;
    int countChild = 0;
    int countInfant = 0;
    double totalPrice = 0;

    public TicketTypeResponse() {
    }

    public TicketTypeResponse(int countAdult, int countChild, int countInfant, double totalPrice) {
        this.countAdult = countAdult;
        this.countChild = countChild;
        this.countInfant = countInfant;
        this.totalPrice = totalPrice;
    }

    public int getCountAdult() {
        return countAdult;
    }

    public void setCountAdult(int countAdult) {
        this.countAdult = countAdult;
    }

    public int getCountChild() {
        return countChild;
    }

    public void setCountChild(int countChild) {
        this.countChild = countChild;
    }

    public int getCountInfant() {
        return countInfant;
    }

    public void setCountInfant(int countInfant) {
        this.countInfant = countInfant;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
