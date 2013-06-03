/**
* Copyright (C) 2013.
*
* Licensed by Dehong Gao, Polyu;
*
* @file: WordLoader.java
* @author: danny gao
* @date  : 2013-5-9 ÏÂÎç9:32:48
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
import java.util.Hashtable;

/**
 * @author danny
 *
 */
public class WordLoader implements ILexiconLoader {

	/* (non-Javadoc)
	 * @see gh.polyu.resourceloader.ILexiconLoader#LoaderIndexWord(java.util.Hashtable, gh.polyu.resourceloader.LOADTYPE)
	 */
	@Override
	public void LoaderIndexWord(Hashtable<Integer, String> has, LOADTYPE type) {
		// TODO Auto-generated method stub
		switch (type)
		{
		case ENG:
			LoaderIndexWord(has, LEXICON_OPTION.WORD_CACHE + "Eng_index_cache.txt");
			break;
		case CHN:
			LoaderIndexWord(has, LEXICON_OPTION.WORD_CACHE + "Chn_index_cache.txt");
			break;
		default:
			break;
		}
	}
	
	/**
	 * @param has
	 * @param string
	 */
	private void LoaderIndexWord(Hashtable<Integer, String> has, String string) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(string));
			
			String line = "";
			while((line = br.readLine()) != null)
			{
				String[] elems = line.split("\\s+");
				
				Integer index = Integer.valueOf(elems[1]);
				
				has.put(index, elems[0]);
			}
			
			if(br != null)
			{
				br.close(); br = null;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see gh.polyu.resourceloader.ILexiconLoader#LoaderWordIndex(java.util.Hashtable, gh.polyu.resourceloader.LOADTYPE)
	 */
	@Override
	public void LoaderWordIndex(Hashtable<String, Integer> has, LOADTYPE type) {
		// TODO Auto-generated method stub
		switch (type)
		{
		case ENG:
			LoaderWordIndex(has, LEXICON_OPTION.WORD_CACHE + "Eng_index_cache.txt");
			break;
		case CHN:
			LoaderWordIndex(has, LEXICON_OPTION.WORD_CACHE + "Chn_index_cache.txt");
			break;
		default:
			break;
		}
	}

	/**
	 * @param has
	 * @param string
	 */
	private void LoaderWordIndex(Hashtable<String, Integer> has, String string) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(string));
			
			String line = "";
			while((line = br.readLine()) != null)
			{
				String[] elems = line.split("\\s+");
				
				Integer index = Integer.valueOf(elems[1]);
				
				has.put(elems[0], index);
			}
			
			if(br != null)
			{
				br.close(); br = null;
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

	}

	

}
