/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ZipTestDemo;

/**
 *
 * @author 6302743
 */
public class ZipCode {
    public int Zip;
    
    public ZipCode(int zipCode) {
        if(zipCode > 99999) {
            System.out.println("Inputted zipcode has more than 5 digits");
        }
        this.Zip = zipCode;
    }

    public ZipCode(String barCode) {
        this.Zip = parseBarCode(barCode);
    }
    
    private int parseBarCode(String barCode) {
        if (barCode.length() != 27) {
            System.out.println("Not 25 character barcode");
            return 0;
        } else if (barCode.charAt(0) != '1' || barCode.charAt(26) != '1') {
            System.out.println("1st or last Char is not 1");
            return 0;
        }
        //char[] code = barCode.toCharArray();
        barCode = barCode.substring(1, barCode.length() - 1);
        String zip = "";
        for (int i = 0; i < 25; i = i + 5) {
            int counter = 0;
            int sum = 0;
            String sub = barCode.substring(i, i + 5);
            for (int j = 0; j < 5; j++) {
                if(sub.charAt(j) == '1') {
                    switch(j){
                        case 0 -> {
                            sum = sum + 7;
                            counter++;
                        }
                        case 1 -> {
                            sum = sum + 4;
                            counter++;
                        }
                        case 2 -> {
                            sum = sum + 2;
                            counter++;
                        }
                        case 3 -> {
                            sum = sum + 1;
                            counter++;
                        }
                        case 4 -> {
                            sum = sum + 0;
                            counter++;
                        }
                    }
                }
            }
            if (counter != 2){
                System.out.println("not exactly 2 ones in 5 digit sequence");
                return 0;
            }
            if (sum == 11) {
                zip = zip +'0';
            } else {
            zip = zip + sum;
            }
        }
        return Integer.parseInt(zip);
    }
    
    public String GetBarCode() {
        String barCode = "1";
        String str = Integer.toString(this.Zip);
        if(str.length() > 5) {
            System.out.println("Too many digits in barcode");
        }
        while (str.length() < 5) {
            str = '0' + str;
        }
        
        for (char digit : str.toCharArray()) {
            switch(digit) {
                case '1' -> barCode = barCode + "00011";
                case '2' -> barCode = barCode + "00101";
                case '3' -> barCode = barCode + "00110";
                case '4' -> barCode = barCode + "01001";
                case '5' -> barCode = barCode + "01010";
                case '6' -> barCode = barCode + "01100";
                case '7' -> barCode = barCode + "10001";
                case '8' -> barCode = barCode + "10010";
                case '9' -> barCode = barCode + "10100";
                case '0' -> barCode = barCode + "11000";
            }
        }
        
        return barCode + '1';
    }
    
}
