package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	static Properties prop;
	
	public ReadConfig(){
		
		 try {
				FileInputStream fis= new FileInputStream("src\\test\\java\\config\\config.properties");
				prop= new Properties();
				prop.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception is: "+ e.getMessage());
			}
	}
	
	
	public String getUsername() {
		String username=prop.getProperty("username");
		return username;
	}
	
	public String getPassword() {
		String password=prop.getProperty("password");
		return password;
	}
	
	public String getBaseUri() {
		String baseURI=prop.getProperty("baseUri");
		return baseURI ;
	}
	
	public String getEndpoint() {
		String endpoint=prop.getProperty("endpoint");
		return endpoint ;
	}
	
	public String getUserSkillsMapEndpoint() {
		String userSkillsMapEndpoint=prop.getProperty("userSkillsMapEndpoint");
		return userSkillsMapEndpoint ;
	}
	
	public int getOkCode() {
		int code=Integer.parseInt(prop.getProperty("okcode"));
		return code ;
	}
}
