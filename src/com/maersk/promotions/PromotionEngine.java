package com.maersk.promotions;

import java.util.ArrayList;
import java.util.List;

public class PromotionEngine {

    public PromotionRule getPromotion(String promotion){

        if(promotion.contains("&") && promotion.contains("for") )
        {
            /*
                combine scenario
             */

            String regex1=" & ";
            String[] arr1=promotion.split(regex1);

            if(arr1.length==2){
                String regex2=" for ";
                String[] arr2=arr1[1].split(regex2);
                if(arr2.length==2 && Double.valueOf(arr2[1])!=null ){
                    List<String> s=new ArrayList<>();
                    s.add(arr1[0]);
                    s.add(arr2[0]);
                    return new CombinedPromotionRule(s,Double.valueOf(arr2[1]));
                }
                else
                    return  null;
            }
            else
                return  null;

        }
        else if(promotion.contains("of") && promotion.contains("'s for") ){
            /* N items Scenario

             */
            String regex1=" of ";
            String[] arr1=promotion.split(regex1);

            if(arr1.length==2 && Integer.valueOf(arr1[0])!=null ){
                String regex2="'s for ";
                String[] arr2=arr1[1].split(regex2);
                if(arr2.length==2 && Double.valueOf(arr2[1])!=null ){

                    return new NItemPromotionRule(arr2[0], Integer.valueOf(arr1[0]), Double.valueOf(arr2[1]));
                }
                else
                    return  null;
            }
            else
                return  null;

        }
        else{
            /*
                    invalid Scenario
             */
            return  null;
        }

    }
}
