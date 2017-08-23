package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFileReader {
		public  String  readProperty(String fileName,String propertyKey){
		Properties prop = new Properties();

		InputStream input = null;

	try {
		String filename = "./config/"+fileName+".properties";
		input = getClass().getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			System.out.println("Sorry, unable to find " + filename);
		}
		prop.load(input);
return prop.getProperty(propertyKey);

	}
catch (IOException ex) {
	ex.printStackTrace();
} finally {
	if (input != null) {
		try {
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	return null;

	}

}
