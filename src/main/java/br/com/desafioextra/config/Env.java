package br.com.desafioextra.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static String get(String key,String defaultValue){
        String value = dotenv.get(key);
        return value == null || value.isBlank() ? defaultValue : value;
    }



}
