import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ReadCSV {
	   public static void main(String[] args) {
		   //A1: 5, A2: 7, A3: 9, B1: 3, B2: 8, B3: =4+5, C1: =5+A1, C2: =A2+B2, C3: =C2+B3
	        Scanner s = new Scanner(System.in);
	        String CSVInput = s.nextLine();
	         csvOutput(CSVInput);
	    }
	     //conforming if given cell satisfy requirement.
	    public static boolean isCellValid(String str) {
	        return Pattern.matches("^[A-Z]{1,2}[1-9]{1}[0-9]{0,4}$", str);
	    }
	      //verifying values as numbers.
	    public static boolean isNum(String str) {
	        return Pattern.matches("^[0-9]+$", str);
	    }
	           // to extract required data from cell with formula.
	    public static boolean isExpressionValid(String str) {
	        return Pattern.matches("^=[A-Z0-9]+[+*/-][A-Z0-9]+$", str);
	    }

	    // to do mathematical operations.
	    public static int add(int num1, int num2) {
	        return num1 + num2;
	    }
	    public static  int multiply (int num1, int num2){
	        return  num1 * num2;
	    }
	    public static  int div (int num1, int num2){
	        return  num1 / num2;
	    }
	    public static  int subtract (int num1, int num2){
	        return  num1 - num2;
	    }

	    public static void csvOutput(String CSVInput){


	        String[] splitArray = CSVInput.split(", ");

	        if (splitArray.length == 0) {

	            System.out.println("Invalid Input");

	            return;
	        }

	        Map<String, String> cellsMap = new LinkedHashMap<>();

	        try {
	            for (String str : splitArray) {

	                String[] cellVal = str.split(": ");

	                if (cellVal.length != 2) {

	                    System.out.println("Invalid Input");

	                    return;
	                }

	                String cell = cellVal[0];

	                String value = cellVal[1];

	                if (isCellValid(cell)) {


	                    if (isNum(value)) {

	                        cellsMap.put(cell, value);

	                    } else {

	                        if (isExpressionValid(value)) {

	                            String expression = value.substring(1);

	                            String[] expressionArray = expression.split("[+*/-]");

	                            boolean add = false;
	                            boolean subtract = false;
	                            boolean divide = false;
	                            boolean multi = false;

	                            if (expression.contains("+"))
	                                add = true;
	                           else if (expression.contains("*"))
	                                multi = true;
	                            else if (expression.contains("/"))
	                            	divide = true;
	                            else if (expression.contains("-"))
	                                subtract = true;

	                            Integer value1 = null;

	                            Integer value2 = null;

	                            if (isCellValid(expressionArray[0])) {

	                                if (cellsMap.containsKey(expressionArray[0])) {

	                                    value1 = Integer.parseInt(cellsMap.get(expressionArray[0]));

	                                } else {

	                                    System.out.println("Invalid Input");

	                                    return;
	                                }
	                            }
	                            if (isCellValid(expressionArray[1])) {

	                                if (cellsMap.containsKey(expressionArray[1])) {

	                                    value2 = Integer.parseInt(cellsMap.get(expressionArray[1]));

	                                } else {

	                                    System.out.println("Invalid Input");

	                                    return;
	                                }
	                            }

	                            if (isNum(expressionArray[0]) && isNum(expressionArray[1])) {

	                                Integer answer = null;

	                                Integer number1 = Integer.parseInt(expressionArray[0]);

	                                Integer number2 = Integer.parseInt(expressionArray[1]);

	                                if (add) {

	                                    answer = add(number1, number2);
	                                }

	                                cellsMap.put(cell, "" + answer);

	                            } else if (value1 != null && value2 != null) {

	                                Integer answer = null;

	                                if (add) {

	                                    answer = add(value1, value2);
	                                }

	                                cellsMap.put(cell, "" + answer);

	                            } else if (value1 != null & value2 == null) {

	                                Integer answer = null;

	                                Integer number2 = Integer.parseInt(expressionArray[1]);

	                                if (add) {

	                                    answer = add(value1, number2);
	                                }

	                                cellsMap.put(cell, "" + answer);

	                            } else if (value1 == null && value2 != null) {

	                                Integer answer = null;

	                                Integer number1 = Integer.parseInt(expressionArray[0]);

	                                Integer number2 = value2;

	                                if (add) {
	                                    answer = add(number1, number2);
	                                }

	                                cellsMap.put(cell, "" + answer);

	                            } else {

	                                System.out.println("Invalid Input form");

	                                return;
	                            }
	                        } else {

	                            System.out.println("Invalid Input from ");

	                            return;
	                        }
	                    }
	                } else {

	                    System.out.println("Invalid cell with=" + cell);

	                    return;
	                }
	            }
	        } catch (NumberFormatException e) {

	            System.out.println("Invalid Input form ");

	            return;
	        }
	        System.out.println(cellsMap);
	    }



}
