/**
* Copyright (C) 2013.
*
* Licensed by Dehong Gao, Polyu;
*
* @file: ILexiconLoader.java
* @author: danny gao
* @date  : 2013-5-8 обнГ5:30:35
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

import java.util.Hashtable;

/**
 * @author danny
 *
 */
public interface ILexiconLoader {

	void LoaderWordIndex(Hashtable<String, Integer> has, LOADTYPE type);
	void LoaderIndexWord(Hashtable<Integer, String> has, LOADTYPE type);
}
