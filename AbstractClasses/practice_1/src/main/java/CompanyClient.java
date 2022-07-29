

public class CompanyClient extends Client {
    double takeFeeCoef = 0.01;

    @Override
    public void take(double amount) {
        amount += takeFeeCoef * amount;
        super.take(amount);
    }

    @Override
    String getName() {
        return String.valueOf(this.getClass());
    }
}
