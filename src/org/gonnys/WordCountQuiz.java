package org.gonnys;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class WordCountQuiz {

	//VO만드는 습관을 들이자 (중첩클래스)
	static class WordCount{
		
		private String content;
		private int count;
		
		WordCount(String content){
			this.content = content;
			this.count = 1;
		}
		
		public void increase() {
			this.count++;
		}
		
		public int getCount() {
			 return this.count;
		}

		@Override
		public String toString() {
			return "WordCount [content=" + content + ", count=" + count + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
		
		//파일을 읽는다.
		//방법 1) InputStream in = new FileInputStream("")
		//방법 2) Scanner
		Scanner scanner = new Scanner(new File("C:\\zzz\\test.txt"));
		
		TreeMap<String, WordCount> wordMap = new TreeMap<>();
		List<WordCount> list = new ArrayList<WordCount>();
		
		//System.out.println(scanner);
		
		//파일에서 라인 단위로 데이터를 읽어낸다.
		while(true) {
			try {
				String line = scanner.nextLine().toLowerCase();
				if (line.length() == 0) {
					continue;
				}
				
				//System.out.println(line);
				
				//라인에서 띄어쓰기를 기준으로 분해한다.
				String[] arr = line.split(" ");
				
				//System.out.println(Arrays.toString(arr));
				
				for (String aword : arr) {
					
					WordCount wc = wordMap.get(aword);
					
					if(wc != null) {
						
						wc.increase();
						continue;
					}
					
					WordCount obj = new WordCount(aword);
					wordMap.put(aword, obj);
					list.add(obj);
					
				}
			}catch(Exception e){
				break;
			}
			
		}
		System.out.println(wordMap);	
		
		//단어를 데이터 구조에 넣는다.
		
		//결과를 출력한다.
		
		//1. 빈도수가 많은 거 정렬
		Collections.sort(list, (v1, v2) -> v2.getCount() - v1.getCount());
		//2. 알파벳 순 정렬
		Collections.sort(list, (v1, v2) -> v1.content.compareTo(v2.content) );
		
		//결과 출력
		list.stream().forEach(w -> System.out.println(w));
		
		
		
	}
}