package com.example.techolution;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class will process the input file and finds out the maximum satisfactory
 * that person can with by spending totalTime on noOfItemsInMenu.
 */
public class SatisfactionProcessor {

    int[] arrTimeTaken = null;
    int[] arrSatisfaction = null;
    int totalTime = 0,noOfitemsInMenu = 0;
    String inputFileName;

    public SatisfactionProcessor(String inputFileName)
    {
        this.inputFileName = inputFileName;
        initValues();
    }

    /**
     * This will initializes the values
     */
    private void initValues()
    {
        Resource resource = new ClassPathResource(inputFileName);
        InputStream inputStream = null;
        try
        {
            inputStream =  resource.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line =null;
            line = bufferedReader.readLine();
            String[] arrLine = line.split(" ");
            totalTime = Integer.parseInt(arrLine[0]);
            noOfitemsInMenu = Integer.parseInt(arrLine[1]);

            arrTimeTaken = new int[noOfitemsInMenu];
            arrSatisfaction = new int[noOfitemsInMenu];
            for(int i =0 ; i < noOfitemsInMenu; i++)
            {
                    String strMenuItem = bufferedReader.readLine();
                    String arrMenuItem[] = strMenuItem.split(" ");
                    arrSatisfaction[i] = Integer.parseInt(arrMenuItem[0]);
                    arrTimeTaken[i] = Integer.parseInt(arrMenuItem[1]);

            }
            inputStream.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * We are using knapsack problem to get maximum profit for the given weights
     * @return
     */
    public int getMaxSatisfactioin()
    {
        int[][] arrKnapsack = new int[noOfitemsInMenu + 1][totalTime + 1];
        for (int i = 0; i <= noOfitemsInMenu; i++) {
            for (int j = 0; j <= totalTime; j++) {

                if(i == 0 || j == 0)
                    arrKnapsack[i][j]=0;
                else if(arrTimeTaken[i-1] <= j)
                    arrKnapsack[i][j]=max(arrSatisfaction[i-1] + arrKnapsack[i-1][j-arrTimeTaken[i-1]],arrKnapsack[i-1][j]);
                else
                    arrKnapsack[i][j]=arrKnapsack[i-1][j];
            }
        }
        printValues(arrKnapsack);
        //The maximum profit will be filled for the total time i.e; last row and column
        return arrKnapsack[noOfitemsInMenu][totalTime];
    }

    private void printValues(int[][] arrKnapsack)
    {
        System.out.println("TotalTime:" + totalTime);
        System.out.println("No.of Items:" + noOfitemsInMenu);
        for (int i = 0; i < arrKnapsack.length; i++) {
            for (int j = 0; j < arrKnapsack[i].length; j++) {
                System.out.print(arrKnapsack[i][j]+" ");
            }
            System.out.println();
        }
    }
    private int max(int m1,int m2)
    {
        return m1 > m2 ? m1 : m2;
    }
}
