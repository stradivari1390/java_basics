public class IndividualEntrepreneurClient extends Client {

    double putFeeCoef = 0.01;

    @Override
    public void put(double amount) {
        if (amount < 1000) {
            amount -= amount * putFeeCoef;
        } else {
            amount -= amount * putFeeCoef / 2;
        }
        super.put(amount);
    }

    @Override
    String getName() {
        return String.valueOf(this.getClass());
    }
}
