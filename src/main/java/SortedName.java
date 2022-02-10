import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class SortedName {
    public void sortFile(){
        // The first is to define the filename of the file to be read and the filename of the file to be written afterwards
        String filePath = this.getClass().getResource("/").getPath() + "unsorted-names-list.txt";
        String newFilePath = this.getClass().getResource("/").getPath() + "sorted-names-list.txt";

        // Define file input stream and file output stream
        // A file input stream is used to read content from a file
        // The file output stream is used to write the sorted content to a new file
        File file = new File(filePath);
        InputStream is = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bw = null;
        try{
            // Define a list collection to store the content read from the file
            ArrayList<String> list = new ArrayList<>();
            is = new FileInputStream(file);
            reader = new InputStreamReader(is);
            bufferedReader = new BufferedReader(reader);
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                list.add(line);
            }

            // Use the sort method of the Collections class to sort the list collection
            // The sorted content is the content that needs to be written to the new file later
            Collections.sort(list, new Comparator<String>(){
                public int compare(String a, String b){
                    // Sorting Rules:
                    // First sort by lastName, lastName is the last string of each line so use lastIndexOf to locate that string
                    // If the lastName is not the same, the sorting is successful, if the lastName is the same, you need to use the firstName to sort
                    String lastName_a = a.substring(a.lastIndexOf(" "));
                    String lastName_b = b.substring(b.lastIndexOf(" "));

                    if(!lastName_a.equals(lastName_b)){
                        return lastName_a.compareTo(lastName_b);
                    }
                    // Sort by firstName
                    return a.compareTo(b);
                }
            });

            // Use a for loop to output the elements in the list to the screen
            // Elements in list are now ordered
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i));
            }

            // Write the sorted file contents to a new file using the file output stream
            bw = new BufferedWriter(new FileWriter(newFilePath));

            // Write line by line, and wrap each line at the same time
            Iterator<String> it = list.iterator();
            while(it.hasNext()){
                bw.write(it.next().toString());
                // newline operation
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
