package com.khasang_incubator.clothesforecast.helpers;

import java.lang.Math;

public class CalcHelper {

    private double effTempHumidity;
    private double effTempWindSpeed;
    private double dewPoint; //На данном этапе отдельно переменная не нужна, но может пригодится в будущем для определения "духоты"

    private double convertCelToFar(double airTempInCel){
        return airTempInCel * (9 / 5) + 32;
    }

    private double convertFarToCel(double tempInFar){
        return (tempInFar - 32) / (9 / 5);
    }

    private double convertWindSpeed(double windSpeedInMperSec){
        return windSpeedInMperSec * 3.6;
    }

    private double calcEffTempHumidity (double airTempInCel, double dewPoint){
        double tempInFar = convertCelToFar(airTempInCel);
        dewPoint = convertCelToFar(dewPoint);
        double varE = 6.11 * Math.exp(5417.7530 * ((1/273.16) - (1/dewPoint)));
        double varH = (0.5555) * (varE - 10.0);
        effTempHumidity = tempInFar + varH;
        return convertFarToCel(effTempHumidity);
    }

//    Tew = 13.12 + 0.6215 * T - 11.37 * (V)^0.16 + 0.3965 * T * (V)^0.16

    private double calcEffTempWindSpeed (double airTempInCel, double windSpeed){
        windSpeed = (convertWindSpeed(windSpeed)) * 0.5;
        windSpeed = Math.pow(windSpeed, 0.16);
        return 13.12 + 0.6215 * airTempInCel - 11.37 * windSpeed + 0.3965 * airTempInCel * windSpeed;
    }

    private double calcDewPoint(double airTempInCel, double relHumidity){
        double funcTempAndHum = (Math.log(relHumidity/100) + (17.27 * airTempInCel / 237.3 + airTempInCel)) / 17.27;
        this.dewPoint = (237.3 * funcTempAndHum) / (1 - funcTempAndHum);
        return dewPoint;
    }

    public double getEffectiveTemperature(double airTempInCel, int relHumidity, double windSpeed, String sex){
        sex = sex.trim().toLowerCase();
        double sexModifer;
        if (sex.equals("male")){
            sexModifer = 1.04;
        }else if (sex.equals("female")){
            sexModifer = 1.0;
        }else {
            sexModifer = 1.02;
        }

        if (airTempInCel < 1){
            this.dewPoint = 1;
        } else {
            this.dewPoint = calcDewPoint(airTempInCel, (double) relHumidity);
        }
        this.effTempHumidity = calcEffTempHumidity(airTempInCel, dewPoint);
        this.effTempWindSpeed = calcEffTempWindSpeed(airTempInCel, windSpeed);
        return ((effTempHumidity + effTempWindSpeed) / 2) * sexModifer;
    }
}
