/**
* Copyright (C) 2013.
*
* Licensed by Dehong Gao, Polyu;
*
* @file: ictclas4Token.java
* @author: danny gao
* @date  : 2013-6-2 下午10:14:13
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
package gh.polyu.msa.align;

import java.util.ArrayList;

import org.ictclas4j.bean.MidResult;
import org.ictclas4j.bean.SegResult;
import org.ictclas4j.segment.SegTag;

/**
 * @author danny
 *
 */
public class ictclas4Token {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("This is OneMain");

		SegTag st = new SegTag(1);
		SegResult sr = st.split("一块勤奋地漂亮的一块钱,/打造经济的航空母舰。ABCD.#$% Hello World!\n又一段文本123辆 ！3.0");
		
		System.out.println(sr.getFinalResult());
	}

}
