package kyc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class KnowYourCustomer{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numberOfInputs = sc.nextInt();
        ArrayList<String> result=new ArrayList<>();// To Store output in ArrayList.
        for (int i = 0; i < numberOfInputs; i++)
        {
            String SignupDate = sc.next();
            String CurrentDate = sc.next();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            //Converting the given string into DateTime format.
            LocalDate SignupLocalDate = LocalDate.parse(SignupDate, formatter);
            LocalDate CurrentLocalDate = LocalDate.parse(CurrentDate, formatter);
            CalculateDateRange(CurrentLocalDate,SignupLocalDate,result,formatter);


        }
        //Displaying the output at once in the end.
        for(String s:result)
        {
            System.out.println(s);
        }
    }
    private static void CalculateDateRange(LocalDate CurrentLocalDate,LocalDate SignupLocalDate,ArrayList<String> result,DateTimeFormatter formatter)
    {

        LocalDate AnniversaryDate = LocalDate.of(CurrentLocalDate.getYear(), SignupLocalDate.getMonth(), SignupLocalDate.getDayOfMonth());
        LocalDate StartingRange = AnniversaryDate.minusDays(30);
        LocalDate EndingRange = AnniversaryDate.plusDays(30);
        if (CurrentLocalDate.getYear() <= SignupLocalDate.getYear()) {
            result.add("NO Range");

        }

        else if (CurrentLocalDate.isAfter(StartingRange)) {
            if (CurrentLocalDate.isBefore(EndingRange))
                result.add(StartingRange.format(formatter) + " " + CurrentLocalDate.format(formatter));

            else
                result.add(StartingRange.format(formatter) + " " + EndingRange.format(formatter));

        }
        else
        {
            AnniversaryDate = LocalDate.of(CurrentLocalDate.getYear()-1, SignupLocalDate.getMonth(), SignupLocalDate.getDayOfMonth());
            StartingRange = AnniversaryDate.minusDays(30);
            EndingRange = AnniversaryDate.plusDays(30);
            if (CurrentLocalDate.isAfter(StartingRange)) {
                if (CurrentLocalDate.isBefore(EndingRange))
                    result.add(StartingRange.format(formatter) + " " + CurrentLocalDate.format(formatter));

                else
                    result.add(StartingRange.format(formatter) + " " + EndingRange.format(formatter));


            }
        }
    }
}

/* Testcase:
2
04-05-2017 04-04-2017
30-12-2010 01-01-2018
   Output:
No Range
30-11-2017 01-01-2018
 */

