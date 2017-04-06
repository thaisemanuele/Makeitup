package com.natura.teste.thaissantos.makeitup;

import android.graphics.Color;

/**
 * Created by thais.santos on 05/04/2017.
 */

public class CalculatorUtils {

    public static int medPixel(int[] pixels, int domain){
        int medred = 0;
        int medgreen = 0;
        int medblue = 0;
        int i=1;
        for(i=1; i<=domain;i++){
            int redValue = Color.red(pixels[i-1]);
            int blueValue = Color.blue(pixels[i-1]);
            int greenValue = Color.green(pixels[i-1]);
            medred += redValue;
            medgreen += greenValue;
            medblue += blueValue;
        }
        medred /= i;
        medgreen /=i;
        medblue /=i;

        return Color.argb(120, medred, medgreen, medblue);
    }
}
