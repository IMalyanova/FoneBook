import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.TreeMap;

public class PhoneBook {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String,String> phoneName = new TreeMap<>();
        int count = 0;

        for (; ; ) {

            System.out.println("Введите номер телефона, имя или команду: ");
            String command = reader.readLine().trim();
            String name;

            if(command.equals("exit")) {
                System.out.println("До новых встреч!");
                break;
            }if(command.equals("EXPORT")) {
                System.out.println("Введите путь папки, в которую сохранить проект: ");
                String path = "";
                PrintWriter pw = null;

                try {
                    path = reader.readLine().trim() + "/data.json";
                    pw = new PrintWriter(path);
                    System.out.println("Файл будет сохранен по адресу " + path + "\r\n");
                }catch (Exception e){
                    path = "/data.json";
                    pw = new PrintWriter(path);
                    System.out.println("Введеный путь не найден, файл будет сохранен по адресу " + path + "\r\n");
                }
                finally {
                    JSONObject object = new JSONObject();

                    for (String key : phoneName.keySet()){
                        object.put(key,phoneName.get(key));
                    }
                    pw.write(String.valueOf(object));
                    pw.flush();
                    pw.close();
                }


            }else if (command.equals("LIST")) {
                for (String element: phoneName.keySet()){
                    System.out.println(element + " " + phoneName.get(element));
                }
            }else if ((phoneName.get(command)) == (null)){
                boolean nameIsCommand = false;
                for (String element: phoneName.keySet()){
                    if(phoneName.get(element).equals(command)){
                        System.out.println(element + " " + command);
                        nameIsCommand = true;
                    }
                }
                if (!nameIsCommand){

                    System.out.println("Таких данных нет. Для сохранения новых данных" +
                            " введите сначала номер телефона и нажмите enter: ");
                    String phone = reader.readLine().trim();
                    if (phone.equals("")) {
                        phone = command;
                    }

                    System.out.println("Теперь введите имя: ");
                    name = reader.readLine().trim();
                    count++;
                    phoneName.put(phone, name);
                }
             }else {
                System.out.println(command + " " + phoneName.get(command));
            }
        }

    }
}
