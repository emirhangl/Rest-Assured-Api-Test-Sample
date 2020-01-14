package com.apitest;

import org.junit.Test;

public class AppTest 
{
    ApiTestHelper apiTestHelper = new ApiTestHelper();


    String apiKey = System.getProperty("apiKey","423f2562");
    String searchWord = System.getProperty("searchWord","harry potter");
    String movieTitle = System.getProperty("movieTitle","Harry Potter and the Sorcerer's Stone");

    @Test
    public void harryPotterSearchAssertion(){
        // gerekli argümanları geçerek bulmak istediğimiz filmin ID'sini ayıklıyoruz.

        String id = apiTestHelper.getIdFromMovie(apiKey, searchWord, movieTitle);

        // Yakaladığımız ID ile film araması yapıyoruz.
        apiTestHelper.searchByID(apiKey, id);
    }


}

