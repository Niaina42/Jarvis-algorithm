import java.util.List;

public class Main {

    public static String showPoint(int[] point) {
        String result = "";
        for (int i = 0; i < 2; i++) {
            if(i == 0)
                result += "["+point[i]+",";
            else
                result += point[i]+"]";
        }

        return result;
    }

    public static void main(String[] args) {
        Jarvis jv = new Jarvis(20);

        int[][] random_values = jv.pointParHasard();

        // Print the randoms array
        System.out.println("Les valeurs de [x,y] randoms: ");
        for (int i = 0; i < random_values.length; i++) {
            for (int j = 0; j < 2; j++) {
                if(j == 0)
                    System.out.print("p"+i+" = ["+random_values[i][j]+",");
                else
                    System.out.print(random_values[i][j]+"]");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println("Exo 2: ");
        System.out.println("p0="+showPoint(random_values[0])+" p1="+showPoint(random_values[1])+" p2="+showPoint(random_values[2]));
        System.out.println(jv.anglePolaireInferieur(random_values[0], random_values[1], random_values[2]));
        System.out.println(" ");
        System.out.println("Envelope convex (Algorithm de Jarvis):");

        List<int[]> envconv = jv.findEnvConv(jv.getData());
        List<int[]> dataList = jv.getListData();

        for (int[] p : envconv) {
            System.out.print("p"+dataList.indexOf(p)+": ");
            System.out.println("[" + p[0] + "," + p[1] + "]");
        }
    }
}