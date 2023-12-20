package com.epam.mjc.io;

import java.io.*;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String name = "";
        String email = "";
        int age = 0;
        long phone = 0;


        try (InputStream inputStream = new FileInputStream(file)) {
            try(BufferedReader reader = new BufferedReader((new InputStreamReader(inputStream)))){
                String line;
                while((line = reader.readLine()) != null){
                    String[] tempStr = line.split(" ");
                    if(tempStr[0].contains("Name:")){
                        name = tempStr[1];
                    } else if (tempStr[0].contains("Age:")) {
                        age = Integer.parseInt(tempStr[1]);
                    }else if (tempStr[0].contains("Email:")) {
                        email = tempStr[1];
                    }else if (tempStr[0].contains("Phone:")) {
                        phone = Long.parseLong(tempStr[1]);
                    }
                }
            }catch (IOException e){
                throw new IOException(e);
            }finally {
                inputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Profile(name, age, email, phone);
    }
}
