import java.util.Scanner;

public class Lab4{
    
    public static int[] findDigits(String args){
        int nums[] = new int[args.length()];
        int pos = 0;
        for(int i = 0; i < args.length(); i++){
            if(Character.isDigit(args.charAt(i)))
              nums[pos++] = Character.getNumericValue(args.charAt(i));
        }

        return nums;
    }

    public static int addNumbers(int[] nums){
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            count += nums[i];
        }

        return count;
    }

    public static void main(String[] args){ 
        Scanner scan = new Scanner(System.in);
        int array[] = findDigits(scan.next());

        System.out.println(addNumbers(array));
        
        scan.close();
    }
}
