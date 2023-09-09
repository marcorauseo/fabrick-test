package com.fabrick.test.entity;

public class test {
    public static void main(String[] args){


        twoSum(new int[]{2,5,5,11},10);

    }
            public static int[] twoSum(int[] nums, int target) {

                  for (int i=0; i<nums.length; i++){
                      for(int j=1; j<nums.length; j++){
                          if(nums[i]+nums[j]==target && i!=j){
                              System.out.println("[" + String.valueOf(nums[i]) +","+ String.valueOf(nums[j]) + "]");
                              System.out.println("[" + String.valueOf(i) +","+ String.valueOf(j) + "]");
                              return new int[]{nums[i],nums[j]};

                          }

                      }

                  }
                throw new IllegalArgumentException("Nessuna coppia di numeri somma al target.");
            }
    }

