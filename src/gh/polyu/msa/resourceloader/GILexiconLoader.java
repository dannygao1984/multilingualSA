/**
* Copyright (C) 2013.
*
* Licensed by Dehong Gao, Polyu;
*
* @file: GILexiconLoader.java
* @author: danny gao
* @date  : 2013-5-8 ÏÂÎç6:34:19
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
package gh.polyu.msa.resourceloader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author danny
 *
 */
public class GILexiconLoader {

	String gi_lexicon_file = null;
	
	public GILexiconLoader()
	{
		this.gi_lexicon_file = LEXICON_OPTION.GI_LEXICON;
	}
	
	public GILexiconLoader(String dir)
	{
		this.gi_lexicon_file = dir;
	}
	
	
	/* (non-Javadoc)
	 * @see gh.polyu.resourceloader.ILexiconLoader#GetSentimentWordFromGILexicon(java.util.Hashtable, java.util.Hashtable)
	 */	
	public boolean GetSentimentWordFromGILexicon(
			ArrayList<String> listPos, ArrayList<String> listNeg) 
	{
		// TODO Auto-generated method stub		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.gi_lexicon_file));
		
			String line = br.readLine(); //ignore first line
			while((line = br.readLine()) != null)
			{
				String[] tmp = line.toLowerCase().split("\\s+");
				String[] elems = tmp[0].split("#");
				String word = elems[0];
				
				if(tmp[2].compareTo("pos") == 0)
				{
					listPos.add(word);
				}else if(tmp[2].compareTo("neg") == 0)
				{
					listNeg.add(word);
				}
			}
			
			if(br != null)
			{
				br.close(); br = null;
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see gh.polyu.resourceloader.ILexiconLoader#GetSentimentWordFromGILexicon(java.util.Hashtable, java.util.Hashtable)
	 */	
	public boolean GetSentimentWordFromGILexicon(
			Hashtable<String, Integer> hasPos, Hashtable<String, Integer> hasNeg) {
		// TODO Auto-generated method stub
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.gi_lexicon_file));
		
			String line = br.readLine(); //ignore first line
			while((line = br.readLine()) != null)
			{
				String[] tmp = line.toLowerCase().split("\\s+");
				String[] elems = tmp[0].split("#");
				String word = elems[0];
				
				if(tmp[2].compareTo("pos") == 0)
				{
					hasPos.put(word, 1);
				}else if(tmp[2].compareTo("neg") == 0)
				{
					hasNeg.put(word, 1);
				}
			}
			
			if(br != null)
			{
				br.close(); br = null;
			}
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
