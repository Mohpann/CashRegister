public class Currency
{
  private String denomination;
  private String type;
  private double value;

    public Currency()
    {}

    public Currency(String d, double v, String t)
    {
        denomination = d;

        value = v;

        type = t;
        
    }

    public String getDenomination()
    {
      return denomination;
    }

    public double getValue()
    {
      return value;
    }

    public String getType()
    {
      return type;
    }

}