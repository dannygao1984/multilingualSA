/**
* Copyright (C) 2013.
*
* Licensed by Dehong Gao, Polyu;
*
* @file: separateCorpus.java
* @author: danny gao
* @date  : 2013-6-3 ÏÂÎç2:22:45
* @email : gaodehong_polyu@163.com
* @function: 
* @input:
* @output:
* @prerequisite:
*
*/

/**
 * 
 */
package gh.polyu.msa.align.corpusprocessing;

import gh.polyu.msa.global.GLOBALPARAMETER;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author danny
 *
 */
public class separateCorpus {

	int fileIndex = 0;
	String raw_file = "";
	
	public int DoSeparate(String file)
	{
		raw_file = file;
		int iCnt = 0;
		ArrayList<String> listEngSentence = new ArrayList<String>();
		ArrayList<String> listChnSentence = new ArrayList<String>();
		
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), "utf-8");
			BufferedReader br = new BufferedReader(read);			
			
			String line = "";
			while((line = br.readLine()) != null)
			{
				if(line.indexOf("<tu>") != -1)
				{
					String lineEn = br.readLine();					
					// if error
					if(lineEn.indexOf("<tuv xml:lang=\"en\"><seg>") == -1)
					{
						System.err.println("Error in reading Eng sentence:" + lineEn);	
						continue;
					}else
					{
						lineEn = lineEn.replace("<tuv xml:lang=\"en\"><seg>", "");
						lineEn = lineEn.replace("</seg></tuv>", "");
					}
				
					String lineCn = br.readLine();
					// if error
					if(lineCn.indexOf("<tuv xml:lang=\"zh\"><seg>") == -1)
					{
						System.err.println("Error in reading Chn sentence:" + lineCn);	
						continue;
					}else
					{
						lineCn = lineCn.replace("<tuv xml:lang=\"zh\"><seg>", "");
						lineCn = lineCn.replace("</seg></tuv>", "");
					}
					
					listEngSentence.add(lineEn.trim());
					listChnSentence.add(lineCn.trim());
					iCnt++;
				} // end of if <tu>
				
				if(iCnt % 100000 == 0)
				{
					System.out.print(".");
				}
				
				if(listEngSentence.size() >= 1000000)
				{
					OutputToFile(listEngSentence, listChnSentence, fileIndex++);					
					if(listEngSentence.size() != listChnSentence.size())
					{
						System.err.println("Output file " + fileIndex-- + " Sentence number not consistent!!!!");
					}
					listEngSentence.clear();
					listChnSentence.clear();
				}
			}// while 
			
			OutputToFile(listEngSentence, listChnSentence, fileIndex++);
			
			
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
		return iCnt;
	}
	
	/**
	 * @param listEngSentence
	 * @param listChnSentence
	 * @param i
	 */
	private void OutputToFile(ArrayList<String> listEngSentence,
			ArrayList<String> listChnSentence, int i) {
		// TODO Auto-generated method stub
		System.out.println("\nOutput file " + i + " at " + new Date(System.currentTimeMillis()));
		String fileName = this.raw_file + ".";
		OutputToFile(listEngSentence, fileName + "en." + i);
		OutputToFile(listChnSentence, fileName + "zh." + i);
		
	}
	
	private void OutputToFile(ArrayList<String> listEngSentence, String file) {
		// TODO Auto-generated method stub
		try {
			BufferedWriter out = 
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),"UTF8"));
			
			for(String sent : listEngSentence)
			{
				out.append(sent + "\n");
			}
		
			if(out != null)
			{
				out.close();
				out = null;
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
		System.out.println("Begin .... at " + new Date(System.currentTimeMillis()));
		long beg = System.currentTimeMillis();
		
		separateCorpus se = new separateCorpus();
		int cnt = se.DoSeparate(GLOBALPARAMETER.UNALIGNEDROOT + "en-zh/en-zh-MultiUN.tmx");
		
		System.out.println("Totally " + cnt + " sentence pairs are processed!!");
		beg = System.currentTimeMillis() - beg;
		
		System.out.println("Totally time cosummed " + beg/1000/60 + " mins!");
	}

}
