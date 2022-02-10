import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class SortedName {
    public void sortFile(){
        // 首先是定义要读取的文件的文件名以及之后要写入的文件的文件名
        String filePath = this.getClass().getResource("/").getPath() + "unsorted-names-list.txt";
        String newFilePath = this.getClass().getResource("/").getPath() + "sorted-names-list.txt";

        // 定义文件输入流和文件输出流
        // 文件输入流用于从文件中读取内容
        // 文件输出流用于将排序后的内容写入到新文件中
        File file = new File(filePath);
        InputStream is = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bw = null;
        try{
            // 定义一个list集合，用于存放从文件中读取的内容
            ArrayList<String> list = new ArrayList<>();
            is = new FileInputStream(file);
            reader = new InputStreamReader(is);
            bufferedReader = new BufferedReader(reader);
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                list.add(line);
            }

            // 使用Collections这个类的sort方法来对list这个集合排序
            // 排序后的内容就是之后需要被写入到新文件中的内容
            Collections.sort(list, new Comparator<String>(){
                public int compare(String a, String b){
                    // 排序规则：
                    // 首先用lastName进行排序，lastName是每一行的最后一个字符串所以使用lastIndexOf来定位到那个字符串
                    // 如果lastName不相同，则排序成功，如果lastName相同则需要用firstName进行排序
                    String lastName_a = a.substring(a.lastIndexOf(" "));
                    String lastName_b = b.substring(b.lastIndexOf(" "));

                    if(!lastName_a.equals(lastName_b)){
                        return lastName_a.compareTo(lastName_b);
                    }
                    // 使用firstName进行排序
                    return a.compareTo(b);
                }
            });

            // 使用for循环来将list里面的元素输出到屏幕中
            // list中的元素现在是有序的
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i));
            }

            // 使用文件输出流将排好序的文件内容写入到新文件中
            bw = new BufferedWriter(new FileWriter(newFilePath));

            // 一行一行进行写入，每写入一行内容同时换行
            Iterator<String> it = list.iterator();
            while(it.hasNext()){
                bw.write(it.next().toString());
                // 换行操作
                bw.newLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (reader != null) {
                    reader.close();
                }
                if (is != null) {
                    is.close();
                }
                if(bw != null){
                    bw.close();
                }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        SortedName sortedName = new SortedName();
        sortedName.sortFile();
    }
}
