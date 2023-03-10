package Ex2.Shop;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.util.stream.Stream;

public class ShopTest
{
    public static void main(String[] args) throws IOException
    {
        boolean count = true;
        int count1 =0;
        Shop shop = new Shop();
        Scanner input = new Scanner(System.in);
        while (true)
        {
            System.out.println("""
                    Выберите действие:
                        1. Добавить компьютер.            
                        2. Найти компьютер.
                        3. Удалить компьютер.
                        4. Остановить программу.
                    """);
            if (!input.hasNextInt())
            {
                input.nextLine();
                continue;
            }
            int number = input.nextShort();

            if (number == 1)
            {
                input.nextLine();
                System.out.println("Введите название компьютера:");
                String comp = input.nextLine();
                shop.AddPc(comp);
                try (FileWriter writer = new FileWriter("C:\\Users\\htrco\\Desktop\\shop.txt", true))
                {
                    String text = comp;
                    writer.write(text);
                    writer.append('\n');
                }
                catch (IOException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }

            if (number == 2)
            {
                input.nextLine();
                System.out.println("Введите название компьютера:");
                String comp = input.nextLine();
                System.out.println(shop.SearchPc(comp));

                String searchWord = comp;
                FileInputStream fis = new FileInputStream(new File("C:\\Users\\htrco\\Desktop\\shop.txt"));
                byte[] content = new byte[fis.available()];
                fis.read(content);
                fis.close();
                String[] lines = new String(content).split("\n");
                for (String line : lines)
                {
                    String[] words = line.split(" ");
                    for (String word : words)
                    {
                        if (word.equalsIgnoreCase(searchWord))
                        {
                            System.out.println("Найдено в базе - " + word);
                            count1=1;
                        }
                        if(!word.equalsIgnoreCase(searchWord))
                        {
                            count=false;
                        }
                    }
                }
                if(count == false && count1==0)
                {
                    System.out.println(comp + " - Не найдено в базе");
                }
                System.out.println("\n");
            }

            if (number == 3)
            {
                input.nextLine();
                System.out.println("Введите название компьютера:");
                String comp = input.nextLine();

                Path put = Paths.get("C:\\Users\\htrco\\Desktop\\shop.txt");
                Path temp = Files.createTempFile("shop", ".txt");
                Stream<String> lines = Files.lines(put);
                try (BufferedWriter writer = Files.newBufferedWriter(temp))
                {
                    lines
                            .filter(line -> !line.startsWith(comp))
                            .forEach(line ->
                            {
                                try
                                {
                                    writer.write(line);
                                    writer.newLine();
                                } catch (IOException e)
                                {
                                    throw new RuntimeException(e);
                                }
                            });
                    System.out.println("Удалено из базы - " + comp + "\n");
                }
                Files.move(temp, put, StandardCopyOption.REPLACE_EXISTING);
                shop.DeletePc(comp);
            }
            if (number == 4)
                break;
        }
    }
}