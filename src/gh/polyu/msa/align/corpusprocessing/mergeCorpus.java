/**
* Copyright (C) 2013.
*
* Licensed by Dehong Gao, Polyu;
*
* @file: mergeCorpus.java
* @author: danny gao
* @date  : 2013-6-3 ÉÏÎç9:59:00
* @email : gaodehong_polyu@163.com
* @function: This class is used to merge the data corpus
* @input:  Two files to merge
* @output: one file
* @prerequisite: NULL
*
*/

/**
 * 
 */
package gh.polyu.msa.align.corpusprocessing;

import gh.polyu.msa.global.GLOBALPARAMETER;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author danny
 *
 */
public class mergeCorpus {
	
	public void ReadFile(String file)
	{
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader br = new BufferedReader(read);			
			
			String line = "";
			while((line = br.readLine()) != null)
			{
				System.out.println(line);
			}
			
			if(br != null)
			{
				br.close();
				br = null;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void Read2File(String file1, String file2)
	{
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file1), "utf-8");
			BufferedReader br1 = new BufferedReader(read);
			
			BufferedReader br2 = new BufferedReader(new FileReader(file2));
			
			String line1 = "";
			String line2 = "";
			while((line1 = br1.readLine()) != null)
			{
				System.out.println(line1 + "\n" + br2.readLine());
			}
			
			if(br1 != null)
			{
				br1.close();
				br1 = null;
			}
			if(br2 != null)
			{
				br2.close();
				br2 = null;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void DoMerge(String file1, String file2)
	{
		try {
			PrintWriter    pw1 = new PrintWriter(new FileWriter(file1, true), true);
			BufferedReader br2 = new BufferedReader(new FileReader(file2));
			
			String line = "";
			while((line = br2.readLine()) != null)
			{
				pw1.append(line + "\n");
			}
		
			if(pw1 != null)
			{
				pw1.close();
				pw1 = null;
			}
			if(br2 != null)
			{
				br2.close();
				br2 = null;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mergeCorpus mg = new mergeCorpus();
		mg.ReadFile(GLOBALPARAMETER.UNALIGNEDROOT + "en-zh/en-zh-MultiUN.tmx");
		//mg.Read2File(GLOBALPARAMETER.UNALIGNEDROOT + "en-zh/MultiUN.en-zh.zh", 
		//		GLOBALPARAMETER.UNALIGNEDROOT + "en-zh/MultiUN.en-zh.en");
		//mg.DoMerge(GLOBALPARAMETER.UNALIGNEDROOT + "Test/OpenSubtitles2011.en-zh.zh", 
		//		GLOBALPARAMETER.UNALIGNEDROOT + "Test/OpenSubtitles2011.en-zh.en");
	}

}
