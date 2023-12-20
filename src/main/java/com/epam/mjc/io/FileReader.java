package com.epam.mjc.io;

import java.io.*;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            return getProfile(inputStream);
        } catch (IOException e) {
            return  createDefaultProfileOnError();
        }
    }

    private Profile createDefaultProfileOnError() {
        return new Profile();
    }

    private static Profile getProfile(InputStream inputStream) throws IOException {
        String name = "";
        String email = "";
        int age = 0;
        long phone = 0;

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
            return new Profile(name, age, email, phone);
        }catch (IOException e){
            throw new IOException(e);
        }
    }
}
