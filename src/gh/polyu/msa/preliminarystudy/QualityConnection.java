/**
* Copyright (C) 2013.
*
* Licensed by Dehong Gao, Polyu;
*
* @file: QualityConnection.java
* @author: danny gao
* @date  : 2013-5-9 ÏÂÎç8:51:53
* @email : gaodehong_polyu@163.com
* @function: investigate the quality of learned sentiment words and 
* 			 their connections
* @input:    The word and ID files, the relation of inter/intra-language
* 			 and learned words
* @output:
* @prerequisite:
*
*/

/**
 * 
 */
package gh.polyu.msa.preliminarystudy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

import gh.polyu.msa.resourceloader.LOADTYPE;
import gh.polyu.msa.resourceloader.WordLoader;

/**
 * @author danny
 *
 */
public class QualityConnection {

	private String learned_word_dir = "D:/MyWork/MyPhD Works/visual studio 2010/" +
			"Projects/ParralSentiLexicon/ParralSentiLexicon/output/" +
			"baselineBigraphPropagation/";
	
	private String word_relation_dir = "D:/MyWork/MyPhD Works/visual studio 2010/" +
			"Projects/ParralSentiLexicon/ParralSentiLexicon/cache/";
	
	private String learned_word_file_eng_pos_1 = "bipro_30_final_label_eng_id_pos.txt"; // with unlabeled English words
	private String learned_word_file_eng_neg_1 = "bipro_30_final_label_eng_id_neg.txt"; // with unlabeled English words
	private String learned_word_file_eng_pos_2 = "bipro_5_final_label_eng_id_pos.txt"; // without unlabeled English words
	private String learned_word_file_eng_neg_2 = "bipro_5_final_label_eng_id_neg.txt"; // without unlabeled English words
	
	class LearnedSentimentWord
	{
		String word;
		int index;
		float  score;		
	}
	
	public void QualityConnectionEvaluation()
	{
		Hashtable<Integer, String> hasIndexWord = new Hashtable<>();
		WordLoader wl = new WordLoader();
		wl.LoaderIndexWord(hasIndexWord, LOADTYPE.ENG);
		System.out.println("Load word number:" + hasIndexWord.size());
		
		ArrayList<LearnedSentimentWord> list = new ArrayList<>();
		LoadLearnedFile(list, learned_word_dir + this.learned_word_file_eng_pos_1);
		LoadLearnedFile(list, learned_word_dir + this.learned_word_file_eng_neg_1);
		Collections.sort(list, new Comparator<LearnedSentimentWord>(){    
	           public int compare(LearnedSentimentWord o1, LearnedSentimentWord o2) {    
	               if(o1.score > o2.score)
	               {
	            	   return -1;
	               }else if (o1.score < o2.score)
	               {
	            	   return 1;
	               }else 
	               {
	            	   return 0;
	               }
	        	      
	            }    
	        });
		System.out.println("Load sentiment words (sorted descend):" + list.size());
		
		Hashtable<Integer, Integer> hasIndexCnt = new Hashtable<>();
		LoadRelationToHashtable(hasIndexCnt, this.word_relation_dir + 
				"rel_freq.txt");
		LoadRelationToHashtable(hasIndexCnt, this.word_relation_dir + 
				"wn_rel_index.txt");
		
		String outfile = "./output/preliminaryStudy/learnedQualityConnection/qualityScore.txt";
		OutputIndexConnection(list, hasIndexCnt, outfile);
		
	}
	
	/**
	 * @param list
	 * @param hasIndexCnt
	 * @param outfile
	 */
	private void OutputIndexConnection(ArrayList<LearnedSentimentWord> list,
			Hashtable<Integer, Integer> hasIndexCnt, String outfile) {
		// TODO Auto-generated method stub
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(outfile, true), true);
		
			for(LearnedSentimentWord wrd : list)
			{
				if(hasIndexCnt.containsKey(wrd.index))
				{
					pw.append(wrd.index + " " + hasIndexCnt.get(wrd.index) 
							+ "\n");
				}
			}
			
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
	 * @param hasIndexCnt
	 * @param string
	 */
	private void LoadRelationToHashtable(
			Hashtable<Integer, Integer> hasIndexCnt, String string) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(string));
			
			String line = "";
			while((line = br.readLine()) != null)
			{
				String[] elems = line.split("\\s+");
				
				int from = Integer.parseInt(elems[0]);
				int to   = Integer.parseInt(elems[1]);
				int cnt  = Integer.parseInt(elems[2]);
				
				if(hasIndexCnt.containsKey(to))
				{
					hasIndexCnt.put(to, hasIndexCnt.get(to) + cnt);
				}else
				{
					hasIndexCnt.put(to, cnt);
				}
							
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
	 * @param list
	 * @param string
	 */
	private void LoadLearnedFile(ArrayList<LearnedSentimentWord> list,
			String string) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(string));
			
			String line = "";
			while((line = br.readLine()) != null)
			{
				String[] elems = line.split("\\s+");
				
				int index = Integer.parseInt(elems[0]);
				float score = Float.parseFloat(elems[1]);
				
				LearnedSentimentWord sentiWord = new LearnedSentimentWord();
				sentiWord.index = index;
				sentiWord.score = score;
				list.add(sentiWord);				
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
		QualityConnection qc = new QualityConnection();
		qc.QualityConnectionEvaluation();
	}

}
