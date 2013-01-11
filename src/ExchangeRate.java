import java.io.IOException;

public interface ExchangeRate {
	public double getRate(String inputCurrency, String outputCurrency)
			throws IOException;
}
