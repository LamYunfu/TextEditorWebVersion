package javaExp10.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import javaExp10.Dao.LogDao;
import javaExp10.po.FileFolderEntity;

public class FileFolderOperation {
    static final String mainDirectory = "C:/j2EE/Exp10/";
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String LINE_SEPARATOR = System.lineSeparator();
    
   /**
    * 返回当前路径的Path对象
    * @param folder
    * @return 
    */
   private static Path getPath(String folder){
       String directory = mainDirectory + folder;
       directory = directory.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);       //路径转化成本地平台化
       Path path = Paths.get(directory);
       return path;
   }
   
	/**
     * 创建文件夹
     * @param userName
     * @return 
     */
    public static boolean createDirectory(String folder){
        Path path = getPath(folder);
        try {
            Files.createDirectories(path);
        } catch (IOException ex) {
            return false;
        }
      
        String userID = Tools.getUserID(folder);
        LogDao.insertLog(userID, "create directory " + mainDirectory + folder);
      
        return true;
    }
    
    /*
     * 读取当前目录下的所有文件
	 * 调用newDirectorySteam(Path)方法，可以列出一个目录下面的所有内容
	 * 该方法返回一个实现了DirectoryStream接口的对象，它同时也实现了Iterable，所以可以在
	 * 目录流上迭代，读取所有对象。
	 */
	public static ArrayList<FileFolderEntity> listInforMation(String args){
		Path dir = getPath(args);
		
		String size;
		String createDate;
		String modifiedDate;
		String name;
		int type;	
		
		ArrayList<FileFolderEntity> list = new ArrayList<FileFolderEntity>();
		
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
			for(Path file : stream){
				//获得文件的基本属性,批量操作，返回基本属性类对象
				BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
				name = file.getFileName().toString();
				createDate = attr.creationTime().toString();
				modifiedDate = attr.lastModifiedTime().toString();
				type = attr.isDirectory() ? 2 : 1;
				size = getSize(file.toFile())  + " b";
				/*
				System.out.println(stringAdd(file.getFileName().toString(), 10) + "   " + stringAdd(attr.creationTime().toString(), 27)
				+ "    " + stringAdd(attr.lastModifiedTime().toString(), 27) + "  " + (attr.isDirectory() ? "d" : "f"));
				*/
				FileFolderEntity f = new FileFolderEntity(size, createDate, modifiedDate, name, type);
				list.add(f);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return list;
	}
	
	/**
	 * 获取文件或者文件夹大小
	 */
    public static long getSize(File f) {
    	long size = 0;
    	File flist[] = f.listFiles();
    	
    	if(flist == null) {
    		return f.length();
    	}
    	
    	for(int i = 0;i < flist.length;i++) {
    		if(flist[i].isDirectory()) {
    			size = size + getSize(flist[i]);
    		}
    		else {
    			size = size + flist[i].length();
    		}
    	}
    	return size;
    }
    
    
    
    /**
     * 删除文件夹
     * @param folder
     * @return 
     */
    public static boolean deleteDirectory(String folder){
    	//记录日志数据库
    	
    	String userID = Tools.getUserID(folder);
        LogDao.insertLog(userID, "delete directory " + mainDirectory + folder);
        
        folder = mainDirectory + folder;
        folder = folder.replace("/", FILE_SEPARATOR).replace("\\", FILE_SEPARATOR);       //路径转化成本地平台化
        File file = new File(folder);
        return deleteDir(file);
    }
    
    /**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
    private static void doDeleteEmptyDir(String dir) {
        boolean success = (new File(dir)).delete();
        if (success) {
            System.out.println("Successfully deleted empty directory: " + dir);
        } else {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     *                 If a deletion fails, the method stops attempting to
     *                 delete and returns "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除子目录
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    
    public byte[] getFileContent(String path){
		String abPath = mainDirectory + path;
		File f = new File(abPath);
		Long length = f.length();
		byte[] filecontent = new byte[length.intValue()];  
		FileInputStream in;
		try {
			in = new FileInputStream(f);
			try {
				in.read(filecontent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filecontent;
	}
	
    public static void saveFile(String path, String content) throws IOException {
    	String userID = Tools.getUserID(path);
		LogDao.insertLog(userID, "edit file " + path);
		
    	path = mainDirectory + path;
    	File f = new File(path);
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(f);
			fos.write(content.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public boolean createFile(String filePath){
        Path path = getPath(filePath);
       // System.out.println(path.toString());
        try {
            Files.createFile(path);
	} catch (IOException e) {
	// TODO Auto-generated catch block
            e.printStackTrace();
            return false;
	}
        
        String userID = Tools.getUserID(filePath);
        LogDao.insertLog(userID, "create file " + mainDirectory + filePath);
        
        return true;
    }
}
