package com.controller.common;

import com.common.BusinessException;
import com.common.CommonMethod;
import com.common.QueryAction;
import com.model.SignName;
import com.service.common.SignNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
public class SignNameAction extends QueryAction<SignName> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String strSignName = "";

    private String strUserId = "";

    @Autowired
    private SignNameService signNameService;

    /*@SuppressWarnings({ "rawtypes", "unused" })
    @Action(value = "/signNameAction", results = {
            @Result(name = QUERY, location = "/jsp/sys/sysCode.html") })*/
    public String jompSys() {
        return "/jsp/sys/sysCode";
    }

    public void uploadSignName() {
        try {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");

            if (CommonMethod.isNull(strUserId)) {
                throw new BusinessException("fail,参数strUserId不能为空！", "");
            }
			/*HttpServletRequest request = ServletActionContext.getRequest();
			
			MultiPartRequestWrapper multipartRequest = (MultiPartRequestWrapper) request;
			String[] fileNames = multipartRequest.getFileNames("file");
			File[] reportFiles = multipartRequest.getFiles("file");

		    for(int i=0;i<reportFiles.length;i++)*//*{
		    	File reportFile =  reportFiles[i];
		    	String fileName = fileNames[i];
		    	String[] names = fileName.split("\\.");
		    	
//		    	if(reportFile.length()>(1024 * 1024)){//文件大于1M
//					throw new BusinessException("模板文件太大，不能上传！","");
//				}
		    	boolean gs = false;
		    	String hz = "";
				if(names.length>1){
					hz = names[names.length-1];
					if(!"jpg".equalsIgnoreCase(hz) && !"png".equalsIgnoreCase(hz)){
						gs = true;
					}
				}else{
					gs = true;
				}
				if(gs){//签名必须为jpg、png格式
					throw new BusinessException("上传失败！格式错误！","");
				}
				FileInputStream fis = null;
				FileOutputStream out = null;
				try{
					fis = new FileInputStream(reportFile);

					if(CommonMethod.isNull(strUserId)){
						throw new BusinessException("用户ID为空，上传失败！","");
					}
					List ls = signNameService.findByProperty("userId", strUserId);

				    SignName singName = null;
				    if ((ls == null) || (ls.size() < 1)){
				    	singName = new SignName();
				    }else{
				    	singName = (SignName)ls.get(0);
				    }
				    boolean isSuccess =false;
				    String path = ServletActionContext.getServletContext().getRealPath("");
				    String saveFileName = strUserId+ "." +hz;
				    if (singName.getPath() != null){
				    	String filePath = path + "/" + singName.getPath();
//				    	String filePath_samll = path + "/" + singName.getPathSupply();
				    	File oldFile = new File(filePath,saveFileName);
//				    	File samll = new File(filePath_samll);
//				    	if ((oldFile.exists()) && (samll.exists()))
//				    		isSuccess = (oldFile.delete()) && (samll.delete());
				    	if (oldFile.exists())
				    		isSuccess = oldFile.delete();
				    	else
				    		isSuccess = true;
				    }
				    else {
				      isSuccess = true;
				    }
				    
				    if (isSuccess){
				    	String realAdd = "signName"+"/"+strUserId;
				    	String savePath = ServletActionContext.getServletContext().getRealPath("/"+realAdd);
						File newfile = new File(savePath.toString());
						if((!newfile.exists()) && (!newfile.isDirectory())){
							newfile.mkdirs(); 
						}

						reSize(new FileInputStream(reportFile), new FileOutputStream(new File(savePath, saveFileName)), 50, "png");

						singName.setUserId(strUserId);
						singName.setPath(realAdd);
						singName.setPathSupply(realAdd);
						singName.setSignatureName(saveFileName);
						if (singName.getId() == null) {
							singName.setId(CommonMethod.getNewKey());
							signNameService.save(singName);
				      } else {
				    	  	signNameService.update(singName);
				      }
					  jsonPrint("true");
				    }
				}catch (Exception e) {
					if (null != fis) {
						fis.close();
					}
					if ( null != out) {
						out.close();
					}
					e.printStackTrace();
				}finally {
					if (null != fis) {
						fis.close();
					}
					if ( null != out) {
						out.close();
					}
				}
		    }*/
            jsonPrint("fail:签名上传失败！");
        } catch (BusinessException e) {
            e.printStackTrace();
            jsonPrint("fail:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonPrint("error:" + e.getMessage());
        }
    }

    public static void reSize(InputStream is, OutputStream os, int size, String format)
            throws IOException {
        BufferedImage prevImage = ImageIO.read(is);
        double width = prevImage.getWidth();
        double height = prevImage.getHeight();
//	    double percent = size / width;
        double percent = 1d;
        int newWidth = (int) (width * percent);
        int newHeight = (int) (height * percent);
        BufferedImage image = new BufferedImage(newWidth, newHeight, 1);
        Graphics graphics = image.getGraphics();
        graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
        ImageIO.write(image, format, os);
        os.flush();
        is.close();
        os.close();
    }

    public void findSignNameByUserId() {
        if (CommonMethod.isNull(strUserId)) {
            jsonPrint("fail,用户ID不能为空！");
            return;
        }
        SignName signName = new SignName();
        List<SignName> snList = this.signNameService.findByProperty("userId", strUserId);
        if (snList != null && snList.size() > 0) {
            signName = snList.get(0);
        }
        jsonPrintOnlyProperty(signName);
    }


    public String getStrSignName() {
        return strSignName;
    }


    public void setStrSignName(String strSignName) {
        this.strSignName = strSignName;
    }

    public String getStrUserId() {
        return strUserId;
    }

    public void setStrUserId(String strUserId) {
        this.strUserId = strUserId;
    }
}
