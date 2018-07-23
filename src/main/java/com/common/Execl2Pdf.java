package com.common;


public class Execl2Pdf {

    private static boolean getLicense() {
        /*boolean result = false;
        try {
            InputStream is = Execl2Pdf.class.getClassLoader().getResourceAsStream("license.xml"); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	public static File excel2pdf(String excelPath, File pdfPath) {
        if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生
            return null;
        }
        try {
            long old = System.currentTimeMillis();
            Workbook wb = new Workbook(excelPath);// 原始excel路径
            FileOutputStream fileOS = new FileOutputStream(pdfPath);
            wb.save(fileOS, com.aspose.cells.SaveFormat.PDF);
            fileOS.close();
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");  //转化用时
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdfPath;
    }*/
//	public static void main(String[] args) {
//		Execl2Pdf dd =new Execl2Pdf();
//		dd.excel2pdf("C:/Users/STKJ-12/Desktop/测试.xlsx", "C:/Users/STKJ-12/Desktop/测试.pdf");
//		
//	}
	
	/*public static boolean getLicense() {       
		boolean result = false;       
		try {           
		InputStream is = Test.class.getClassLoader().getResourceAsStream("xlsxlicense.xml"); //  license.xml应放在..\WebRoot\WEB-INF\classes路径下           
		License aposeLic = new License();           
		aposeLic.setLicense(is);           
		result = true;       
		}
		catch (Exception e) {                          
		e.printStackTrace();       
		}       
		return result;   
		}  
		public static void excel2pdf(String Address) {               
		if (!getLicense()) {          // 验证License 若不验证则转化出的pdf文档会有水印产生           
		return;       
		}       
		try {           
		File pdfFile = new File("C:/inetpub/wwwroot/web/file/pdf1.pdf");// 输出路径           
		Workbook wb = new Workbook(Address);// 原始excel路径                        FileOutputStream fileOS = new FileOutputStream(pdfFile);           
		wb.save(fileOS, SaveFormat.PDF);             
		fileOS.close();                 
		}
		catch (Exception e) {           
		e.printStackTrace();       
		}   
	}*/
        return false;
    }
}