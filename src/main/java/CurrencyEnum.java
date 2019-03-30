public enum CurrencyEnum {
  USD,
  RUB,
  EUR;

  public static String getCurrency(String currency) {
    switch (currency) {
      case "Доллар": return CurrencyEnum.USD.toString();
      case "Рубль": return  CurrencyEnum.RUB.toString();
      case "Евро": return  CurrencyEnum.EUR.toString();
      default: return null;
    }
  }
}
