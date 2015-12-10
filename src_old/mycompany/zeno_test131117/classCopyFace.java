/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.zeno_test131117;
import java.util.Random;

/**
 *
 * @author Kris
 */
public class classCopyFace 
{
            /**
         * KC
         * RAND for "2D Array"
         * //since we have different dimensions between levels 
         * we'll need determine the number of slots based on level
         */
           public static Integer[] AU_RANDFill2DArray(int levelNum)
            {
            
            Random ranNum = new Random();
           
            if(levelNum <= 2)
            {
                //AU array
                //create an array with sequence 1,2,3,4,5,6,7
                Integer[] sequence1to7 = new Integer[] {1,2,3,4,5,6,7};   
                Integer[] randSeq = new Integer[7];
                int n = 7;
                int[] res = new int[7];
                 for (int i = 0; i < n; i++) 
                    {
                        int d = ranNum.nextInt(i+1);
                        res[i] = res[d];
                        res[d] = i;
                    }
                    for(int k=0;k<n;k++)
                    {
                        randSeq[k]=sequence1to7[res[k]];                    
                    }
                    Integer[] randSeqShort = new Integer[3];
                    for(int j = 0; j < 3; j++)
                    {
                        randSeqShort[j] = randSeq[j];
                   
                    }
                     return randSeqShort;
            }
            else
            {
                 //reps array
                //create an array with sequence 1,2,3,4,5,6,7
                Integer[] sequence1to26 = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26};   
                Integer[] randSeq = new Integer[26];
                int n = 26;
                int[] res = new int[26];
                 for (int i = 0; i < n; i++) 
                    {
                        int d = ranNum.nextInt(i+1);
                        res[i] = res[d];
                        res[d] = i;
                    }
                    for(int k=0;k<n;k++)
                    {
                        randSeq[k]=sequence1to26[res[k]];                    
                    }
                     Integer[] randSeqShort = new Integer[4];
                    for(int j = 0; j < 4; j++)
                    {
                        randSeqShort[j] = randSeq[j];
                    }
                     return randSeqShort;                      
            }
         
        }

           //pass the AU[] cuz it will determine the length of the outcoming Reps[]
              public static Integer[] Reps_RANDFill2DArray(int levelNum, Integer[] AU)
            {
            int AULength = AU.length;
            Random ranNum = new Random();
           if(AULength == 3)
           {
               if(levelNum == 1)
               {
                       Integer[] randSeqShort = new Integer[] {1,1,1};
                 
                     return randSeqShort;
               }
               if(levelNum == 2)
               {
                    //create an array with sequence 1,2,3,
                Integer[] sequence1to3 = new Integer[] {1,2,3};   
                Integer[] randSeq = new Integer[3];
                int n = 3;
                int[] res = new int[3];
                 for (int i = 0; i < n; i++) 
                    {
                        int d = ranNum.nextInt(i+1);
                        res[i] = res[d];
                        res[d] = i;
                    }
                    for(int k=0;k<n;k++)
                    {
                        randSeq[k]=sequence1to3[res[k]];                    
                    }
                     
                     return randSeq;
               }
               else
               {
                   return null;
               }
           }
           else if(AULength == 4)
           {
                   if(levelNum == 3 || levelNum == 4)
                {
           
                //create an array with sequence containing all 1's
               
                    Integer[] randSeqShort = new Integer[] {1,1,1,1};
                 
                     return randSeqShort;
                }
                   if(levelNum == 5)
            {
                
                //create an array with sequence 1,2,3,
                Integer[] sequence1to3 = new Integer[] {1,2,3};   
                Integer[] randSeq = new Integer[4];
                int n = 4;
                int[] res = new int[4];
                 for (int i = 0; i < n; i++) 
                    {
                        int d = ranNum.nextInt(i+1);
                        res[i] = res[d];
                        res[d] = i;
                    }
                    for(int k=0;k<n;k++)
                    {
                        randSeq[k]=sequence1to3[res[k]];                    
                    }
                     
                     return randSeq;                      
            }
                   else
                   {
                       return null;
                   }    
           }
           else
           {
               return null;
           }
        
       
         
        }
                 public static Integer[] CFAnswerGenerator(Integer[] AU, Integer[] Reps)
              {
                  int runningTotalCount = 1; //running total
                  int AUCounter; //AU[i]
                  int RepsCounter; //Reps[i]
                  Integer[] AU_Map_Array = new Integer[7];
                  //traverse throug hte AU[] and determine which slot on AU_Map_Array to go to
                  for(int i = 0; i < AU.length; i++)
                  {
                      AUCounter = AU[i];
                      RepsCounter = Reps[i];
                     // if(RepsCounter == 1) //if it is one, just added it to the slot
                     /* {
                          AU_Map_Array[AUCounter-1] = RepsCounter;
                          runningTotalCount = runningTotalCount + RepsCounter;
                      }*/
                     // else //append the numbers
                      {
                        //make a for loop
                      int kat=0;
                          for(int j = runningTotalCount; j < runningTotalCount+RepsCounter; j++)
                          {
                              
                              System.out.println("KAT"+kat);
                              if(AU_Map_Array[AUCounter-1]==null) 
                              {
                                    AU_Map_Array[AUCounter-1] = j;
                                    System.out.println(AUCounter-1+" "+j+" j-running"+(j-runningTotalCount+RepsCounter));
                              }
                                  
                              else     
                              {
                                AU_Map_Array[AUCounter-1] = (AU_Map_Array[AUCounter-1]*10+j);
                                System.out.println("After mult"+(AU_Map_Array[AUCounter-1]*10+j));
                                System.out.println(AUCounter-1+" "+j+" j-running"+ "KAT"+kat);
                              }
                          kat++;
                          }
                          runningTotalCount = runningTotalCount + RepsCounter;
                      }
                  }
                  
                  System.out.println("End of this");
                  return  AU_Map_Array;
              }

public static void CopyFace()
{
    //HARD CODED LEVEL VALUES
    int levelNum = 0;
    Integer[] AU = AU_RANDFill2DArray(levelNum);
    Integer[] Reps = Reps_RANDFill2DArray(levelNum, AU);
    Integer[] answer =  CFAnswerGenerator(AU, Reps);
}


    
}
