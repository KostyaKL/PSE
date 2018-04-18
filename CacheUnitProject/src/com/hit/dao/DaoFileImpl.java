package com.hit.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.hit.dm.DataModel;

public class DaoFileImpl<T> extends Object implements IDao<Long,DataModel<T>> {
	
	String filePath;
	File file;

	public DaoFileImpl(String filePath) {
		this.filePath = filePath;
		file = new File(filePath);
	}

	@Override
	public void save(DataModel<T> entity) {
		String save = entity.toString();
		
		try {
			FileWriter fw = new FileWriter(file, true);
		    fw.write(save + "\n");
		    fw.close();
		}
		catch(Exception e) {
			System.out.println("file write error: " + e);
		}
	}

	@Override
	public void delete(DataModel<T> entity) {
		File tmp = new File("tmp.dat");
		FileWriter fw;
		Scanner scan;
		String line;
		String srchID = entity.getDataModelId().toString();
		
		try {		
			fw = new FileWriter(tmp, true);   
			scan = new Scanner(file);
			while(scan.hasNext()) {
				line = scan.nextLine().toString();
				if(!line.contains("ID: " + srchID)){
					fw.write(line);
				}
			}
			scan.close();
			fw.close();
			tmp.renameTo(file);
		}
		catch (Exception e) {
			System.out.println("file delete error: " + e);
		}
	}

	@Override
	public DataModel<T> find(Long id) {
		DataModel<T> ret = null;
		Scanner scan;
		String line;
		T content;
		
		try {
			scan = new Scanner(file);
			while(scan.hasNext()) {
				line = scan.nextLine().toString();
				if(line.contains("ID: " + id.toString())){
					content = (T)line;
					scan.close();
					ret = new DataModel<T>(id, content);
					return ret;
				}
			}
			scan.close();
		}
		catch(Exception e) {
			System.out.println("file search error: " + e);
		}
		
		return ret;
	}
	
}
