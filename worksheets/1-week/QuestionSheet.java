import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



public class QuestionSheet {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        list.add("abc");
        list.add("def");
        list.add("ghi");    
        list.add("jkl");
        int size =  list.size();
        
        // for( int i = 0; i < size; i++)
        // {
        //     System.out.println(i);
        //     list.add(list.get(i) + list.get(i));
        // }
        for(String s : list)
        {
            list2.add(s + s);
        }
        list.addAll(list2);
        
        System.out.println(list);
 

    }
}
