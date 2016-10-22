package com.example.android.pnpcab;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by HP on 22-10-2016.
 */
public class MyJsonParser {
    JSONObject obj;
    HttpURLConnection urlconn;
    public JSONObject getJson(String url)
    {
        try
        {
            URL u=new URL(url);
            StringBuilder builder=new StringBuilder();
            urlconn=(HttpURLConnection)u.openConnection();
            urlconn.connect();
            InputStream is=new BufferedInputStream(urlconn.getInputStream());
            BufferedReader reader=new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line=reader.readLine())!=null)
            {
                builder.append(line);
            }

            obj=new JSONObject(builder.toString());

        }
        catch(Exception e)
        {

            e.printStackTrace();

        }
        finally
        {

            urlconn.disconnect();
        }
        return obj;
    }
}
