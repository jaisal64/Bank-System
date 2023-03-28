package Utils;

import com.mysql.cj.util.StringUtils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Jun Zhu
 * @Date: 12/2/2022 6:17 PM
 * @Description: TODO
 */
public class CommonUtil {

    public static Long getRandomId(){
        Random random = new Random();
        String randomResult = "";
        for (int i=0;i<8;i++){
            randomResult+=random.nextInt(10);
        }
        return Long.parseLong(randomResult);
    }

    //Check input is positive number
    public static boolean isNumeric(String str){
        if (StringUtils.isNullOrEmpty(str)){
            return false;
        }
        final String number = "0123456789";
        for(int i = 0;i < str.length(); i ++) {
            if(number.indexOf(str.charAt(i)) == -1) {
                return false;
            }
        }
        return true;
    }

    //Checking input is positive double number
    public static boolean isDouble(String str){
        if (StringUtils.isNullOrEmpty(str)){
            return false;
        }
        Matcher mer = Pattern.compile("^[+]?[0-9.]+$").matcher(str);
        return mer.find();
    }

}
