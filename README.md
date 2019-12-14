# Api Testi
Bu proje Rest-Assured kütüphanesi kullanılarak oluşturulmuştur.

Gereksinimler
* Java
* Maven

Testi iki yöntemle koşabilirsiniz.

## 1. Yöntem
AppTest.java classını açtığınızda Run butonuna tıklayarak.

## 2. Yöntem
Aşağıdaki komut satırını kullanarak. <br>
<code>
mvn clean test -DbaseURI="http://www.omdbapi.com/" -DapiKey="423f2562" -DsearchWord="harry potter" 
-DmovieTitle="Harry Potter and the Sorcerer's Stone"
</code>




