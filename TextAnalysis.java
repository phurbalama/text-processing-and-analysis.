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
        int[] count = new int[26]; // counts how many times the character is being used
        char[] letter = new char[26];// store the letter a to z
        String longestWord = ""; //stores the longest word
        String shortestWord = ""; // stores the shortest word
        int max = 0; // used to determine the maximum length of the word
        int min = 100; // used to determine the minimum length of the word
        ArrayList<String> result = new ArrayList<String>(); //will have all the words
        ArrayList<String> unigram = new ArrayList<String>();
        ArrayList<Integer> uniCountList = new ArrayList<Integer>();
        ArrayList<String> bigram = new ArrayList<String>();
        ArrayList<String> bigramresult = new ArrayList<String>();
        ArrayList<Integer> bigramCountlist = new ArrayList<Integer>();
       
        //this string of array shows how many times each letter is being used.
        String[] letterFrequency = new String[26];
        
       
        int a = 0;
        char c;
        //using this loop the array letter will store from letter a to z starting from index 0 till the 26
        for(c = 'a'; c <= 'z'; ++c) {
        	
            letter[a] = c;
            a++;
        }
        
       
        
          
        // Reading line by line from the  
        // file until a null is returned 
        while((line = reader.readLine()) != null) 
        { 
        	//calculates the paragraph in the file
            if(line.equals("")) 
            { 
                paragraphCount++; 
            } 
            //goes 
            if(!(line.equals(""))) 
            { 
                  
                characterCount += line.length(); //calculates the no. of character
                frequency = line;
                for(int i =0;i<line.length();i++)
                {
                	char ch = frequency.charAt(i); //grabs each character from the line
                	ch = Character.toLowerCase(ch); //lower cases that character
                	//checks if the character is from a to z
                	if(ch >= 'a' && ch <= 'z') {
                		
                			//takes the current character in the memory 
                			//if the character is 'a' it will deduct by 'a' making the count index 0
                		//where the count with 0 index will increment each time the character is a
                	          count[ch - 'a']++; 
                	           
                	        }
                }
                
                  
                // \\s+ is the space delimiter in java 
                String[] wordList = line.split("\\s+");//temporarily stores each word in wordlist
                
               
                // goes through the list of words and assigns longestWord to it
                for(int i =0;i<wordList.length;i++)
                {
                	result.add(wordList[i].toLowerCase()); //lowers all the word
                	//checks if the current word is greater than max 
                	if(wordList[i].length()>max)
                	{	
                		//max will now have current length of the longest word found
                		max = wordList[i].length();
                		//longest word will now have the longest word
                		longestWord = wordList[i];
                	}
                }
               
                //same thing for the minimum word
                for(int i =0;i<wordList.length;i++)
                {
                	
                	if(wordList[i].length()<min)
                	{	
                		min = wordList[i].length();
                		shortestWord = wordList[i];
                	}
                }
                  //counts the number of word
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
        System.out.println("-------------------------------Letter Frquency in Descending order----------------------------------");
        for(int i =0;i<count.length;i++)
        {
        	letterFrequency[i] = letter[i] + " " + count[i];
        	System.out.println(letterFrequency[i]);
        }
        // goes through all the result which are all the words in the text
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
        
     // sorting the unigram list in desc order
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
        
        
      
       
        for(int i = 0;i<result.size()-1;i++) {
        	bigramresult.add(result.get(i)+" "+result.get(i+1));
        	
        	
        }
       
     // goes through all the result which are all the bigram in the text
        for(int i =0;i<bigramresult.size();i++)
        {
        	int bigramCount = 0;  //how many words are same
        	for(int j = 0;j<bigramresult.size();j++)
        	{
        		
        		if(bigramresult.get(i).equalsIgnoreCase(bigramresult.get(j)))
        		{
        			bigramCount++;
        		}
        		
        	}
        	bigramCountlist.add(bigramCount);
        	//adds the words and the count
        	bigram.add(bigramresult.get(i) + " " + bigramCount);
        }
        for (int i = 0; i < bigramCountlist.size()-1; i++) 
            for (int j = 0; j < bigramCountlist.size()-i-1; j++) 
                if (bigramCountlist.get(j)< bigramCountlist.get(j+1)) 
                { 
                    // swap uniCountList[j+1] and uniCountList[i] 
                    int temp = bigramCountlist.get(j); 
                    bigramCountlist.set(j, bigramCountlist.get(j+1));
                    bigramCountlist.set(j+1, temp);
                   
                    
                    // swap the character as well
                    String tempWord = bigram.get(j); 
                    bigram.set(j, bigram.get(j+1));
                    bigram.set(j+1, tempWord);
                }
        
        System.out.println("-------------------------------Unigram in Descending Order----------------------------------");
        ArrayList li2 = new ArrayList(new LinkedHashSet(unigram));//removes the duplicate in the list
        
        for(int i =0;i<li2.size();i++)
        {
        	System.out.println(li2.get(i));
        }
        
        System.out.println("-------------------------------Top 20 Bigram----------------------------------");
        
        ArrayList li3 = new ArrayList(new LinkedHashSet(bigram));//removes the duplicate in the list
        for(int i = 0;i<20;i++)
        {
        	System.out.println(li3.get(i));
        }

	}

}
