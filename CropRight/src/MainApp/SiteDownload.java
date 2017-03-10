package MainApp;
import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;


// A Java Class that downloads the HTML File for given region,
// the file holds the forecast info for the next 10 days.

public class SiteDownload {
	
	final static int size=1024;
	AppPage ap;
	String region ="";

	SiteDownload(String getReg){
		
		region = getReg;
		
		
		// Check for region, the request is passed to method depending upon the region selected
		if (region.matches("Rawalpindi")){

			fileDownload("http://www.intellicast.com/Local/Weather.aspx?location=PKXX0006", "scrappedsite");

		}

		if (region.matches("Lahore")){
			fileDownload("http://www.intellicast.com/Local/Weather.aspx?location=PKXX0011", "scrappedsite");

		}

		if ( region.matches("Sialkot")){
			fileDownload("http://www.intellicast.com/Local/Weather.aspx?location=PKXX0019", "scrappedsite");
		}

		if ( region.matches("Multan")){
			fileDownload("http://www.intellicast.com/Local/Weather.aspx?location=PKXX0022", "scrappedsite");

		}
	}


	// A method, that sends request to given URL of weather forecast website and,
	// stores the content in HTML File.

	@SuppressWarnings("static-access")
	public void  fileUrl(String fAddress, String localFileName, String destinationDir) {
		OutputStream outStream = null;
		URLConnection  uCon = null;

		InputStream is = null;
		try {
			URL Url;
			byte[] buf;
			int ByteRead,ByteWritten=0;
			Url= new URL(fAddress);
			outStream = new BufferedOutputStream(new
					FileOutputStream(destinationDir+"\\"+"Downloaded"+".html"));


			uCon = Url.openConnection();
			is = uCon.getInputStream();
			buf = new byte[size];
			while ((ByteRead = is.read(buf)) != -1) {
				outStream.write(buf, 0, ByteRead);
				ByteWritten += ByteRead;
			}
			System.out.println("Downloaded Successfully.");

		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No Internet Connection!","Couldn't Connect" , 0);
			ap.ld.dispose();
			//System.exit(1);
		}
		finally {
			try {
				is.close();
				outStream.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public  void  fileDownload(String fAddress, String destinationDir)
	{    
		int slashIndex =fAddress.lastIndexOf('/');
		int periodIndex =fAddress.lastIndexOf('.');

		String fileName=fAddress.substring(slashIndex + 1);

		if (periodIndex >=1 &&  slashIndex >= 0 
				&& slashIndex < fAddress.length()-1)
		{
			fileUrl(fAddress,fileName,destinationDir);
		}
		else
		{
			System.err.println("path or file name.");
		}
	}


	public static void main(String[] args)
	{
		//new SiteDownload();
	}

}