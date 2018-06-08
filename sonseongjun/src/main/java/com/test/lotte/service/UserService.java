package com.test.lotte.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.lotte.model.UserModel;
@Service
@Transactional
/**
 * 
 * 
 * <pre>
 * com.test.lotte.service
 * UserService.java
 * </pre>
 * ----------------------
 * @Auth  : 손성준
 * @Since : 2018. 6. 7.
 * @Desc  : Service
 *
 */
public class UserService {
	@Autowired
	private UserMapper mapper;
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : search
		 * @Desc   : 유저정보 조회
		 * ---------------------------------- 
		 * @return : List<UserModel>
		 *
	 */
	public List<UserModel> search(){
		return mapper.search();
	}
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : insert
		 * @Desc   : 유저정보 삽입
		 * ----------------------------------- 
		 * @param userModel : void
		 *
	 */
	public void insert(UserModel userModel){
		mapper.insert(userModel);
	}
	
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : insertFile
		 * @Desc   : 파일 읽어서  각 줄 마다 유저 정보 뽑아서 리스트에 담아서 리턴
		 * FTP로 진행
		 * ----------------------------------- 
		 * @param request
		 * @param files
		 * @return
		 * @throws Exception : List<UserModel>
		 *
	 */
	public List<UserModel> insertFile(HttpServletRequest request,MultipartFile files) throws Exception {
/*		  	FTPClient ftpClient = new FTPClient();
	        try {
	 
	            ftpClient.connect("localhost", "8080");
	            ftpClient.login("root", "root");
	            ftpClient.enterLocalPassiveMode();
	 
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
	 
	            URL url = new URL(fileurl);
	            InputStream inputStream = new BufferedInputStream(url.openStream());
	            boolean done = ftpClient.storeFile(destimg, inputStream);
	            inputStream.close();
	            if (done) {
	                System.out.println("The first file is uploaded successfully.");
	            }
	            
	        } catch (FileNotFoundException e) {
	        	return "file not found";
	        	 //e.printStackTrace();
	        	 
	        } catch (IOException ex) {
	        	return "error : " + ex.getMessage();
	            //ex.printStackTrace();

	        } finally {
	            try {
	                if (ftpClient.isConnected()) {
	                    ftpClient.logout();
	                    ftpClient.disconnect();
	                }
	            } catch (IOException ex) {
	                //ex.printStackTrace();
	                return "error : " + ex.getMessage();
	            }
	            return "upload complete";
	        }*/

		List<UserModel> userList = new ArrayList<UserModel>();
		
		String fileName = files.getOriginalFilename();
		String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
		File destinationFile; 
	    String destinationFileName;
	    String fileUrl="c://WEB-INF/uploadFiles/";
	    do { 
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension; 
            destinationFile = new File(fileUrl + destinationFileName); 
        } while (destinationFile.exists()); 
        logger.info(fileUrl+destinationFileName);
        destinationFile.getParentFile().mkdirs(); 
        files.transferTo(destinationFile); 
        
		File file = new File(fileUrl+destinationFileName);
		FileReader fileReader = new FileReader(file);
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = "";
		while((line = bufferedReader.readLine())!= null){
			//한 줄 한 줄 유저 정보 세팅한 후 리스트에 담음
			userList.add(setUserInfo(line));
		}
		bufferedReader.close();
        return userList;
	}
	
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : setUserInfo
		 * @Desc   : 유저정보 세팅
		 * ----------------------------------- 
		 * @param userInfo
		 * @return : UserModel
		 *
	 */
	private UserModel setUserInfo(String userInfo){
		UserModel userModel = new UserModel();
		
		String[] info = userInfo.split("\\|");
	
		userModel.setId(info[0]);
		userModel.setStuNm(info[1]);
		userModel.setScore1(Integer.parseInt(info[2]));
		userModel.setScore2(Integer.parseInt(info[3]));
		userModel.setScore3(Integer.parseInt(info[4]));
		userModel.setScore4(Integer.parseInt(info[5]));
		userModel.setScore5(Integer.parseInt(info[6]));
		Float avg = (float)((Integer.parseInt(info[2])+Integer.parseInt(info[3])+Integer.parseInt(info[4])+Integer.parseInt(info[5])+Integer.parseInt(info[6]))/(float)5);
		userModel.setScoAvg(avg);
		
		return userModel;
	}
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : downFile
		 * @Desc   : 파일 다운로드
		 * -----------------------------------  : void
		 *
	 */
	public List<UserModel> downFile(){
		return mapper.fileDown();
	}
	/**
	 * 
		 *
		 * @Auth   : 손성준
		 * @Since  : 2018. 6. 7.
		 * @Method : writeFile
		 * @Desc   : 파일 쓰기
		 * ----------------------------------- 
		 * @param userList
		 * @throws IOException : void
		 *
	 */
	public void writeFile(List<UserModel> userList) throws IOException{
		try {
			OutputStreamWriter osw = new OutputStreamWriter(System.out);
			BufferedWriter bw = new BufferedWriter(osw, 1024);
			PrintWriter pw = new PrintWriter(bw);
			
			
			File f = new File("result.txt");
			FileWriter fw = new FileWriter(f);
			
			BufferedWriter bw1 = new BufferedWriter(fw, 1024);
			PrintWriter pw1 = new PrintWriter(bw1);
			
			String[] list = new String[userList.size()];
			
			for(int i=0; i<list.length; i++){
				UserModel user = userList.get(i);
				list[i]=user.getRankNum()+"\\|"
						+user.getId()    +"\\|"
						+user.getStuNm() +"\\|"
						+user.getScore1()+"\\|"
						+user.getScore2()+"\\|"
						+user.getScore3()+"\\|"
						+user.getScore4()+"\\|"
						+user.getScore5()+"\\|"
						+user.getScoAvg();		
				pw1.print(list[i]);
				pw1.println();
			}
			pw1.close();
			pw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
