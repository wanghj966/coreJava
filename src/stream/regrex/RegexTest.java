package stream.regrex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: wanghj
 * Date: 12-12-26
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */
public class RegexTest {
    public static void main(String[] args)
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter Pattern");
        String patternString=in.nextLine();
        Pattern pattern=null;
        try{
            pattern=Pattern.compile(patternString);
        }catch (PatternSyntaxException e){
            System.out.println("Pattern syntax error");
            System.exit(1);
        }
        while (true){
            System.out.println("Enter String to match:");
            String input=in.nextLine();
            if(input==null||input.equals("")) return;
            Matcher matcher=pattern.matcher(input);
            if(matcher.matches()){
                System.out.println("match");
                int g=matcher.groupCount();
                if(g>0){
                    for(int i=0;i<input.length();i++){
                        for (int j=1;j<=g;j++){
                            if(i==matcher.start(j)){
                                System.out.print("(");

                            }  System.out.print(input.charAt(i));
                            if(i+1==matcher.end(j))
                                System.out.println(")");
                        }

                    }
                    System.out.println();
                }
            }else  System.out.println("No Match");
        }
    }
}
