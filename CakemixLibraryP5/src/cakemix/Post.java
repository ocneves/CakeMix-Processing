// Class to make a Post from Processing
// Based on "PostData" library by Chris Allick, http://chrisallick.com/


package cakemix;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Post {
	String url;
	ArrayList<BasicNameValuePair> nameValuePairs;

	public Post(String url) {
		this.url = url;
		nameValuePairs = new ArrayList<BasicNameValuePair>();
	}

	public void addData(String key, String value) {
		BasicNameValuePair nvp = new BasicNameValuePair(key,value);
		nameValuePairs.add(nvp);
	}


	public void post() {

		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();

			HttpPost httpPost   = new HttpPost( url );

			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			System.out.println( "executing request: " + httpPost.getRequestLine() );
			HttpResponse response = httpClient.execute( httpPost );
			HttpEntity   entity   = response.getEntity();

			//System.out.println("----------------------------------------");
			System.out.println( response.getStatusLine() );
			//System.out.println("----------------------------------------");

			if( entity != null ) entity.writeTo( System.out );
			if( entity != null ) EntityUtils.consume(entity);

			httpClient.getConnectionManager().shutdown();
			
			// Clear it out for the next time
			nameValuePairs.clear();

		} catch( Exception e ) { 
			e.printStackTrace(); 
		}


	}
}
