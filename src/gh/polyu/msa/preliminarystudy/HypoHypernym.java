/**
* Copyright (C) 2013.
*
* Licensed by Dehong Gao, Polyu;
*
* @file: HypoHypernym.java
* @author: danny gao
* @date  : 2013-5-8 ÏÂÎç4:46:01
* @email : gaodehong_polyu@163.com
* @function: To investigate the polarity consistency of 
* 			 the hypernyms and hyponyms with the original word.
* @input:
* @output:
* @prerequisite: null
*
*/

/**
 * 
 */
package gh.polyu.msa.preliminarystudy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import gh.polyu.digital.digitalutility;
import gh.polyu.msa.resourceloader.GILexiconLoader;
import gh.polyu.msa.resourceloader.ILexiconLoader;
import gh.polyu.wordnet.wordnetHandleRiTaWN;

/**
 * @author danny
 *
 */
public class HypoHypernym {

	public void WordHypoHypernyms(String outputDir)
	{
		// Load GI words
		ArrayList<String> listPos = new ArrayList<String>();
		ArrayList<String> listNeg = new ArrayList<String>();
		GILexiconLoader gi = new GILexiconLoader();
		gi.GetSentimentWordFromGILexicon(listPos, listNeg);
		System.out.println("Pos words: " + listPos.size() + 
					", Neg words:" + listNeg.size());
		
		// Randomly selected words
		ArrayList<String> listRandomPos = new ArrayList<>();
		ArrayList<String> listRandomNeg = new ArrayList<>();
		GetRandomFromList(listRandomPos, listPos, 200);
		GetRandomFromList(listRandomNeg, listNeg, 200);
		System.out.println("Random Pos words: " + listRandomPos.size() + 
				", Neg words: " + listRandomNeg.size());
		
		// Hyponym and hypernym
		Hashtable<String, String[]> hasPosHyponym = new Hashtable<String, String[]>();
		Hashtable<String, String[]> hasNegHyponym = new Hashtable<String, String[]>();
		Hashtable<String, String[]> hasPosHypernym = new Hashtable<String, String[]>();
		Hashtable<String, String[]> hasNegHypernym = new Hashtable<String, String[]>();
		wordnetHandleRiTaWN wn = new wordnetHandleRiTaWN();
		for(String wrd : listRandomPos)
		{
			String[] hypernym = wn.getAllHypernyms(wrd, wn.getBestPos(wrd));
			if(hypernym != null)
			{
				hasPosHypernym.put(wrd, hypernym);
			}else
			{
				//System.out.println("hypernym of Pos word not existing in WN:" + wrd);
			}
			String[] hyponym = wn.getAllHyponyms(wrd, wn.getBestPos(wrd));
			if(hyponym != null)
			{	
				hasPosHyponym.put(wrd, hyponym);		
			}else
			{
				//System.out.println("hyponym of Neg word not existing in WN:" + wrd);
			}
		}
		
		for(String wrd : listRandomNeg)
		{
			String[] hypernym = wn.getAllHypernyms(wrd, wn.getBestPos(wrd));
			if(hypernym != null)
			{
				hasNegHypernym.put(wrd, hypernym);
			}else
			{
				//System.out.println("hypernym of Pos word not existing in WN:" + wrd);
			}
			
			String[] hyponym = wn.getAllHyponyms(wrd, wn.getBestPos(wrd));
			if(hyponym != null)
			{
				hasNegHyponym.put(wrd, hyponym);
			}else
			{
				//System.out.println("hyponym of Neg word not existing in WN:" + wrd);
			}
			
		}
		System.out.println("Hypernym/hyponym Pos words: " + hasPosHypernym.size() + 
				" and " + hasNegHypernym.size() + 
				", Neg words: " + hasPosHyponym.size() +
				" and " + hasNegHyponym.size());
		
		// Output all the hypernym and hyponym words 
		String hypernymFile = outputDir + "hypernym.txt";
		Output(hasPosHypernym, hypernymFile);
		Output(hasNegHypernym, hypernymFile);
		String hyponymFile = outputDir + "hyponym.txt";
		Output(hasPosHyponym, hyponymFile);
		Output(hasNegHyponym, hyponymFile);
	}
	
	/**
	 * @param hasPosHyponym
	 * @param hyponymFile
	 */
	private void Output(Hashtable<String, String[]> hasNym,
			String file) {
		// TODO Auto-generated method stub
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(file, true), true);
		
			for(String wrd : hasNym.keySet())
			{
				String[] nyms = hasNym.get(wrd);
				String line = wrd + "\t";
				for(String nym : nyms)
				{
					line = line + nym + "\t";
				}
				pw.append(line + "\n");
			}
			
			pw.append("=========================\n");
			
			if(pw != null)
			{
				pw.close();
				pw = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @param listRandomPos
	 * @param listPos
	 */
	private void GetRandomFromList(ArrayList<String> listRandom,
			ArrayList<String> list, int TOP) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < TOP; i++)
		{
			int random = digitalutility.RandomArray(list.size());
			listRandom.add(list.get(random));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HypoHypernym app = new HypoHypernym();
		app.WordHypoHypernyms("./output/preliminaryStudy/hypohypernym/");
	}

}
