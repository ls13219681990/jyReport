package com.common.deploy;

import com.common.BusinessException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BookingDeploy {
    private final static String src = "src";
    private final static String WebRoot = "WebRoot";
    private final static String delpoyContentFile = "deployContent.txt";
    private Set<String> undeployFileSets = new HashSet<String>();

    private List<ContentBean> parseDeployContent() throws IOException {
        FileHelper fileHelper = new FileHelper();
        String path = this.getClass().getResource("").getPath();
        String content = fileHelper.readTxt(path + delpoyContentFile, "UTF-8");
        String[] arr = content.split("\n");
        //排除不发布文件
        arr = unDeploy(arr);
        List<ContentBean> ContentBeans = new ArrayList<ContentBean>();
        for (String str : arr) {
            ContentBean con = new ContentBean();
            int indexSrc = str.indexOf(src);
            int indexWebRoot = str.indexOf(WebRoot);
            if (indexSrc != -1) {
                con.setRootPath(src);
                con.setFilePath(str.substring(indexSrc + src.length(), str.lastIndexOf("/") + 1));
                con.setFileName(str.substring(str.lastIndexOf("/") + 1));
                if (con.getFileName().lastIndexOf(".") != -1) {
                    con.setFileType(con.getFileName().substring(con.getFileName().lastIndexOf(".") + 1));
                } else {
                    continue;
                }
                ContentBeans.add(con);
            } else if (indexWebRoot != -1) {
                con.setRootPath(WebRoot);
                con.setFilePath(str.substring(indexWebRoot + WebRoot.length(), str.lastIndexOf("/") + 1));
                con.setFileName(str.substring(str.lastIndexOf("/") + 1));
                if (con.getFileName().lastIndexOf(".") != -1) {
                    con.setFileType(con.getFileName().substring(con.getFileName().lastIndexOf(".") + 1));
                } else {
                    continue;
                }
                ContentBeans.add(con);
            }
        }
        return ContentBeans;
    }

    /**
     * 排除xml配置文件
     * 排除properties文件
     * 排除common.deploy包，排除test包下内容
     *
     * @param arr
     * @return
     */
    private String[] unDeploy(String[] arr) {
        List<String> files = new ArrayList<String>();
        for (String str : arr) {
            files.add(str);
        }
        String[] undeployRoles = DelpoyConfig.getInstance().getValue("undeployRole").split(",");
        for (String filePath : files) {
            for (String role : undeployRoles) {
                if (filePath.matches(parseRole(role))) {
                    undeployFileSets.add(filePath);
                }
            }
        }
        files.removeAll(undeployFileSets);
        return files.toArray(new String[files.size()]);
    }

    private String parseRole(String role) {
        String regex = "";
        if (role.contains("*.")) {
            regex = ".*\\." + role.substring(role.indexOf("*.") + 2);
        } else if (role.contains("/")) {
            regex = ".*" + role + ".*";
        } else {
            throw new BusinessException("不支持的规则");
        }
        return regex;
    }

    private void copyFile(List<ContentBean> ContentBeans) {
        for (ContentBean cb : ContentBeans) {
            String resPath = DelpoyConfig.getInstance().getValue("appPath") + "\\"
                    + DelpoyConfig.getInstance().getValue("projectName");
            String deployPath = DelpoyConfig.getInstance().getValue("deployPath") + "\\"
                    + DelpoyConfig.getInstance().getValue("projectName");
            FileHelper fileHelper = new FileHelper();
            if (cb.getRootPath().equals(src)) {
                if ("java".equals(cb.getFileType())) {
                    String baseResDIR = resPath + "\\WEB-INF\\classes" + cb.getFilePath();
                    String baseDeployDIR = deployPath + "\\WEB-INF\\classes" + cb.getFilePath();
                    List<File> resultList = new ArrayList<File>();
                    resultList.add(new File(baseResDIR + cb.getFileName().replace("java", "class")));
                    FileSearcher.findFiles(baseResDIR, cb.getFileName().replace(".java", "$?.class"), resultList);
                    if (!resultList.isEmpty()) {
                        fileHelper.createFolder(baseDeployDIR);
                    }
                    for (File file : resultList) {
                        String oldPathFile = file.toString();
                        String newPathFile = baseDeployDIR + oldPathFile.substring(oldPathFile.lastIndexOf("\\") + 1);
                        fileHelper.copyFile(oldPathFile, newPathFile);
                    }

                } else {
                    resPath += "\\WEB-INF\\classes" + cb.getFilePath() + cb.getFileName();
                    deployPath += "\\WEB-INF\\classes" + cb.getFilePath() + cb.getFileName();
                    fileHelper.createFolder(deployPath.replace(cb.getFileName().replace("java", "class"), ""));
                    fileHelper.copyFile(resPath, deployPath);
                }
            } else if (cb.getRootPath().equals(WebRoot)) {
                resPath += cb.getFilePath() + cb.getFileName();
                deployPath += cb.getFilePath() + cb.getFileName();
                fileHelper.createFolder(deployPath.replace(cb.getFileName().replace("java", "class"), ""));
                fileHelper.copyFile(resPath, deployPath);
            }
        }
        System.out.println("发布完毕,数量：" + ContentBeans.size());
        if (!undeployFileSets.isEmpty()) {
            System.out.println("本次排除文件：");
            for (String str : undeployFileSets) {
                System.err.println(str);
            }
        }
    }


    public static void main(String[] args) throws IOException {
//		System.out.println("-1".matches("-?\\d+"));
//		System.out.println("fs.xml".matches(".*\\.xml$"));
//		System.out.println("M /branches/booking2.977/src/common/CommonMethod.java".matches(".*src/common.*"));
        BookingDeploy ms = new BookingDeploy();
        ms.parseDeployContent();
        ms.copyFile(ms.parseDeployContent());
    }

}
