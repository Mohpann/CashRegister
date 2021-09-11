import java.util.ArrayList;
import javax.swing.*;

public class CashRegister extends Currency
{
  ArrayList<Currency> Currencies = new ArrayList<Currency>();
  ArrayList<String> Change = new ArrayList<String>();
  private double totalDue;
  private double totalPaid;
  private double changeValue;

  JFrame frame;


  public CashRegister()
  {
    Currencies.add(new Currency("$100 bill", 100.00, "US"));
    Currencies.add(new Currency("$50 bill", 50.00, "US"));
    Currencies.add(new Currency("$20 bill", 20.00, "US"));
    Currencies.add(new Currency("$10 bill", 10.00, "US"));
    Currencies.add(new Currency("$1 bill", 1.00, "US"));
    Currencies.add(new Currency("Quarter", 0.25, "US"));
    Currencies.add(new Currency("Dime", 0.10, "US"));
    Currencies.add(new Currency("Nickel", 0.05, "US"));
    Currencies.add(new Currency("Penny", 0.01, "US"));
  }


  public void getAmountOwed()
  {
    String due = JOptionPane.showInputDialog(frame, "Input Amount Due:");

    if (isNumber(due))
    {
      totalDue = Double.parseDouble(due);
    }
    else
    {
      JOptionPane.showMessageDialog(frame, "Please put in a valid amount due");
      getAmountOwed();
    }
    
  } 

  public void getAmountPaid()
  {
    String paid = JOptionPane.showInputDialog(frame, "Input Amount Paid:");
    
    if (isNumber(paid))
    {
      totalPaid = Double.parseDouble(paid);
    }
    else
    {
      JOptionPane.showMessageDialog(frame, "Please put in a valid payment amount");
      getAmountPaid();
    }
  } 


/*
checks if input string has any non-number characters in it
currently counts decimal points as non-number characters
*/

/*
  public Boolean isNumber(String str)
  {
    char decimal = '.';
    int counter = 0;
    for (int i = 0; i < str.length(); i++)
    {

      if (str.length() == 0 || str == null)
        return false;

        for (char c : str.toCharArray()) 
        {
          if (!(Character.isDigit(c))) //check if char at index is a digit
          {
              if (c == decimal) //check if char at index is a decimal
                counter++;
                if(counter > 1)
                  return false;
              else if (!(c == decimal))
                return false;
          }
      }
    }
    return true;
  }

  */

  public Boolean isNumber(String str)
  {
    char decimal = '.';
    int counter = 0;
      for (int i = 0; i < str.length(); i++)
      {
        if (str.length() == 0 || str == null || str.charAt(0) == '.')
          return false;
        
        if (!(Character.isDigit(str.charAt(i))))
        {
          if (str.charAt(i) == decimal)
            counter++;
          else
            return false;
        }
        if(counter > 1)
          return false;
      }
    return true;
  }

  public void changeCalculator()
  {
    getAmountOwed();
    getAmountPaid();
    while (totalPaid < totalDue)
      getAmountPaid();
    changeValue = totalPaid - totalDue;
  }
//returns a string that has the quantities and denominations of the currencies returned
//ArrayList of Currencies is already sorted largest to smallest with index[0] being the largest
//find way to prevent case where customer underpays**

  public void getChange()
  {
    changeCalculator();
    int count = 0;

    for (int currCurrency = 0; currCurrency < Currencies.size(); currCurrency++)
    {
      while (changeValue >= Currencies.get(currCurrency).getValue()) //while change value is greater than or equal to current denomination value in AList Currencies
        count++;
        Change.add((String)(" " + count + " - " + Currencies.get(currCurrency).getDenomination()));
        if (changeValue > 0)
          changeValue -= Currencies.get(currCurrency).getValue();
    }
    JOptionPane.showMessageDialog(frame, changeAccess()); 
  }

  public String changeAccess()
  {
    String str = "";
    for (String item: Change)
      str += item;
    return str;
  }

}