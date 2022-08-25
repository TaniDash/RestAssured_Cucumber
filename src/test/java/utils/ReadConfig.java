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
		String baseURI=prop.getProperty("baseURI");
		return baseURI ;
	}
	
	public String getUserAPIEndpoint() {
		String endpoint=prop.getProperty("userAPIendpoint");
		return endpoint ;
	}
	
	public String getUserSkillsMapEndpointForUser() {
		String userSkillsMapEndpointForUser=prop.getProperty("userSkillsMapEndpointForUser");
		return userSkillsMapEndpointForUser ;
	}
	
	public String getUsersSkillsMapEndpointForSkillId() {
		String usersSkillsMapEndpoint=prop.getProperty("usersSkillsMap");
		return usersSkillsMapEndpoint ;
	}
	
	public String getSkillAPIEndpoint() {
		String skillAPI=prop.getProperty("skillAPI");
		return skillAPI ;
	}
	
	public String getUserSkillMapAPIEnd() {
		String userSkillMapAPI=prop.getProperty("userSkillMapAPI");
		return userSkillMapAPI ;
	}

}
