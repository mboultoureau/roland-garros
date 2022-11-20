package bzh.ineed.rolandgarros.model;


import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.net.URL;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.io.BufferedReader;

@Entity
@Table(name = "tournaments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id")
        })
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;

    public Tournament(){

    }
    public Tournament(Integer year) {
        this.year = year;
    }

    // Getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }





    // POST START
    public void POSTReq(Integer status, Integer round, Integer type, Integer courtId) throws IOException
    {
        // The message that is going to be sent to the server
        // using the POST request
        /*final String messageContent = "{\n" + "\"status\": 1, \r\n" +
                " \"round\": 0, \r\n" +
                " \"type\": \"0\", \r\n" +
                " \"courtId\": \"31\"" + "\n}";*/

        final String messageContent = "{\n" + "\"status\": "+ status +" , \r\n" +
                " \"round\": "+ round +", \r\n" +
                " \"type\": \""+ type +"\", \r\n" +
                " \"courtId\": \"" + courtId +"\"" + "\n}";

        // Printing the message
        System.out.println(messageContent);
        // URL of the API or Server
        String url = "http://localhost:8000/api/createDefaultMatchForTournament";
        URL urlObj = new URL(url);
        HttpURLConnection postCon = (HttpURLConnection) urlObj.openConnection();
        postCon.setRequestProperty("Authorization","Bearer "+"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5LGFkbWluIiwiaXNzIjoiQ29kZUphdmEiLCJpYXQiOjE2Njg5Mzg0OTYsImV4cCI6MTY2OTAyNDg5Nn0.8Kz7LkN-L1Dy5QIFox7BZWAADh-nyYgDRr4y-s33C9CWc8F6JRUzAWNx1KC-yEUIwcmeXFdcl3qRxu3iHses1w");
        postCon.setRequestMethod("POST");
        //postCon.setRequestProperty("userId", "abcdef");
        // Setting the message content type as JSON
        postCon.setRequestProperty("Content-Type", "application/json");
        postCon.setDoOutput(true);
        // for writing the message content to the server
        OutputStream osObj = postCon.getOutputStream();
        osObj.write(messageContent.getBytes());
        // closing the output stream
        osObj.flush();
        osObj.close();
        int respCode = postCon.getResponseCode();
        System.out.println("Response from the server is: \n");
        System.out.println("The POST Request Response Code :  " + respCode);
        System.out.println("The POST Request Response Message : " + postCon.getResponseMessage());
        if (respCode == HttpURLConnection.HTTP_CREATED)
        {
            // reaching here means the connection has been established
            // By default, InputStream is attached to a keyboard.
            // Therefore, we have to direct the InputStream explicitly
            // towards the response of the server
            InputStreamReader irObj = new InputStreamReader(postCon.getInputStream());
            BufferedReader br = new BufferedReader(irObj);
            String input = null;
            StringBuffer sb = new StringBuffer();
            while ((input = br .readLine()) != null)
            {
                sb.append(input);
            }
            br.close();
            postCon.disconnect();
            // printing the response
            System.out.println(sb.toString());
        }
        else
        {
            // connection was not successful
            System.out.println("POST Request did not work.");
        }
    }

    // POST START
    public void POSTAuthorization() throws IOException
    {
        // The message that is going to be sent to the server
        // using the POST request
        final String messageContent = "{\n" + "\"username\": admin, \r\n" +
                " \"password\": password, \r\n";
        // Printing the message
        System.out.println(messageContent);
        // URL of the API or Server
        String url = "http://localhost:8000/auth/login";
        URL urlObj = new URL(url);
        HttpURLConnection postCon = (HttpURLConnection) urlObj.openConnection();
        //postCon.setRequestProperty("Authorization","Bearer "+"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI2LGFkbWluIiwiaXNzIjoiQ29kZUphdmEiLCJpYXQiOjE2Njg0NTQyNDIsImV4cCI6MTY2ODU0MDY0Mn0.XwX1FeS3vhr5PIZ_W7N3oqLNKAkOrC2xHkef6f1XEcTKVsgwuZuWBjiYELtFu72PmsUpxw-j6w7uXAQnJgoHDA");
        postCon.setRequestMethod("POST");
        postCon.setRequestProperty("admin", "password");
        // Setting the message content type as JSON
        postCon.setRequestProperty("Content-Type", "application/json");
        postCon.setDoOutput(true);
        // for writing the message content to the server
        OutputStream osObj = postCon.getOutputStream();
        osObj.write(messageContent.getBytes());
        // closing the output stream
        osObj.flush();
        osObj.close();
        int respCode = postCon.getResponseCode();
        System.out.println("Response from the server is: \n");
        System.out.println("The POST Request Response Code :  " + respCode);
        System.out.println("The POST Request Response Message : " + postCon.getResponseMessage());
        if (respCode == HttpURLConnection.HTTP_CREATED)
        {
            // reaching here means the connection has been established
            // By default, InputStream is attached to a keyboard.
            // Therefore, we have to direct the InputStream explicitly
            // towards the response of the server
            InputStreamReader irObj = new InputStreamReader(postCon.getInputStream());
            BufferedReader br = new BufferedReader(irObj);
            String input = null;
            StringBuffer sb = new StringBuffer();
            while ((input = br .readLine()) != null)
            {
                sb.append(input);
            }
            br.close();
            postCon.disconnect();
            // printing the response
            System.out.println(sb.toString());
        }
        else
        {
            // connection was not successful
            System.out.println("POST Request did not work.");
        }
    }

    // CALL the post method
    //JavaPOSTExample obj = new JavaPOSTExample();
    // obj.POSTReq();

    // POST END
}
