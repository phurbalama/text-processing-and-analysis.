import java.io.*;
import java.util.*;
public class TextAnalysis {

	public static void main(String[] args) throws IOException {

		
		File file = new File("TheGoldBug1.txt"); 
        FileInputStream fileStream = new FileInputStream(file); 
        InputStreamReader input = new InputStreamReader(fileStream); 
        BufferedReader reader = new BufferedReader(input); 
          
        String line; 
        String frequency;
          
        // Initializing counters 
        int countWord = 0; 
        int sentenceCount = 0; 
        int characterCount = 0; 
        int paragraphCount = 1; 
        int whitespaceCount = 0;
        int[] count = new int[26];
        char[] letter = new char[26];
        String longestWord = "";
        String shortestWord = "";
        int max = 0;
        int min = 100;
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> unigram = new ArrayList<String>();
        ArrayList<Integer> uniCountList = new ArrayList<Integer>();
        
        
       
        int a = 0;
        char c;
        for(c = 'a'; c <= 'z'; ++c) {
        	
            letter[a] = c;
            a++;
        }
        
        String[] letterFrequency = new String[26];
        
          
        // Reading line by line from the  
        // file until a null is returned 
        while((line = reader.readLine()) != null) 
        { 
            if(line.equals("")) 
            { 
                paragraphCount++; 
            } 
            if(!(line.equals(""))) 
            { 
                  
                characterCount += line.length(); 
                frequency = line;
                for(int i =0;i<line.length();i++)
                {
                	char ch = frequency.charAt(i);
                	ch = Character.toLowerCase(ch);
                	if(ch >= 'a' && ch <= 'z') {
                		
                	          count[ch - 'a']++;
                	           
                	        }
                }
                
                  
                // \\s+ is the space delimiter in java 
                String[] wordList = line.split("\\s+");
                
               
                // goes through the list of words and assigns longestWord to it
                for(int i =0;i<wordList.length;i++)
                {
                	result.add(wordList[i]);
                	
                	if(wordList[i].length()>max)
                	{	
                		max = wordList[i].length();
                		longestWord = wordList[i];
                	}
                }
               
                
                for(int i =0;i<wordList.length;i++)
                {
                	
                	if(wordList[i].length()<min)
                	{	
                		min = wordList[i].length();
                		shortestWord = wordList[i];
                	}
                }
                  
                countWord += wordList.length; 
                whitespaceCount += countWord -1; 
                  
                // [!?.:]+ is the sentence delimiter in java 
                String[] sentenceList = line.split("[!?.:]+"); 
                  
                sentenceCount += sentenceList.length; 
            } 
        } 
          
        System.out.println("Total word count = " + countWord); 
        System.out.println("Total number of sentences = " + sentenceCount); 
        System.out.println("Total number of characters = " + characterCount); 
        System.out.println("Number of paragraphs = " + paragraphCount); 
        System.out.println("Total number of whitespaces = " + whitespaceCount); 
        System.out.println(("Longest Word = ") + longestWord);
        System.out.println(("Shortest Word = ") + shortestWord);
        System.out.println(result.size());
       
        
       //using bubble sort to sort the frequency of character
        
        for (int i = 0; i < count.length-1; i++) 
            for (int j = 0; j < count.length-i-1; j++) 
                if (count[j] < count[j+1]) 
                { 
                    // swap count[j+1] and count[i] 
                    int temp = count[j]; 
                    count[j] = count[j+1]; 
                    count[j+1] = temp; 
                    
                    // swap the character as well
                    char tempChar = letter[j];
                    letter[j] = letter[j+1];
                    letter[j+1] = tempChar;
                }
        for(int i =0;i<count.length;i++)
        {
        	letterFrequency[i] = letter[i] + " " + count[i];
        	System.out.println(letterFrequency[i]);
        }
    
        for(int i =0;i<result.size();i++)
        {
        	int uniCount = 0;  //how many words are same
        	for(int j = 0;j<result.size();j++)
        	{
        		
        		if(result.get(i).equalsIgnoreCase(result.get(j)))
        		{
        			uniCount++;
        		}
        		
        	}
        	uniCountList.add(uniCount);
        	//adds the words and the count
        	unigram.add(result.get(i) + " " + uniCount);
        }
        
       // System.out.println(unigram);
       
       // System.out.println(uniCountList);
        for (int i = 0; i < uniCountList.size()-1; i++) 
            for (int j = 0; j < uniCountList.size()-i-1; j++) 
                if (uniCountList.get(j)< uniCountList.get(j+1)) 
                { 
                    // swap uniCountList[j+1] and uniCountList[i] 
                    int temp = uniCountList.get(j); 
                    uniCountList.set(j, uniCountList.get(j+1));
                    uniCountList.set(j+1, temp);
                   
                    
                    // swap the character as well
                    String tempWord = unigram.get(j); 
                    unigram.set(j, unigram.get(j+1));
                    unigram.set(j+1, tempWord);
                }
        for(int i =0;i<unigram.size();i++)
        {
        	System.out.println(unigram.get(i));
        }
        //System.out.println(unigram);
        //System.out.println(uniCountList);

	}

}
