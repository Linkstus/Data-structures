public class lab2{

    public static void main(String[] args){
        int arr1[] = new int[6];
        
        arr1 = fillRandomArray(arr1);

        IncInsertion(arr1);

        DecInsertion(arr1);


    }

    public static int[] fillRandomArray(int[] num){
        for(int i = 0; i < num.length; i++){
            num[i] = (int)(Math.random() * 6);
        }

        System.out.println("The numbers in the array are: ");
        for(int i = 0; i < num.length; i++){
            System.out.printf("Num at %d = %d\t", i, num[i]);
        }

        return num;   
    }

    public static void IncInsertion(int[] num){
        int key, j;

        for(int i = 1; i < num.length; i++){
            key = num[i];
            j = i - 1;

            while(j >= 0 && num[j] > key){
                num[j + 1] = num[j];
                j = j - 1;
            }
            num[j + 1] = key;
        }

        System.out.println("\nThe number in increasing order are: ");
        for(int i = 0; i < num.length; i++){
            System.out.printf("Num at %d = %d\t", i, num[i]);
        }
    }

    public static void DecInsertion(int[] num){
        int key, j;

        for(int i = 1; i < num.length; i++){
            key = num[i];
            j = i - 1;

            while(j >= 0 && num[j] < key){
                num[j + 1] = num[j];
                j = j - 1;
            }
            num[j + 1] = key;
        }

        System.out.println("\nThe number in increasing order are: ");
        for(int i = 0; i < num.length; i++){
            System.out.printf("Num at %d = %d\t", i, num[i]);
        }
    }
}