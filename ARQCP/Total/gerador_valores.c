#include "arqcp.h"
#include <stdio.h>

void gerador_valores(){
    if (!initialize())	//initialization successful
    {
        int sec = 2;

        char tmp = pcg32_random_r() % 61 - 20;					//[0, 60] - 20 = [-20, 40] (ºC)
        unsigned char wind_vel = pcg32_random_r() % 51;			//[0, 50] (km/h)
        unsigned short wind_dir = pcg32_random_r() % 360;		//[0, 359] (º)
        unsigned char humd_atm = pcg32_random_r() % 101;		//[0, 100] (%)
        unsigned char humd_grnd = pcg32_random_r() % 101;		//[0, 100] (%)
        unsigned char rain = pcg32_random_r() % 26;				//[0,25] (mm)

        while(1)
        {
            char rand = (char) ((pcg32_random_r() % 14) - 7);
            tmp = sens_temp(tmp, rand);

            rand = (char) ((pcg32_random_r() % 51) - 25);
            wind_vel = sens_velc_vento(wind_vel, rand);

            rand = (short) pcg32_random_r();
            wind_dir = sens_dir_vento(wind_dir, rand);

            rand = (char) pcg32_random_r();
            humd_atm = sens_humd_atm(humd_atm, rain, rand);

            rand = (char) pcg32_random_r();
            humd_grnd = sens_humd_solo(humd_grnd, rain, rand);

            rand = (char) ((pcg32_random_r() % 14) - 7);
            rain = sens_pluvio(rain, tmp, rand);

            printf("\n----------------------------\n");
            printf("Temperatura: %dºC\n",tmp);		//%d because %c converts to ascii
            printf("Velocidade do vento: %ukm/h\n",wind_vel);	//%u for unsigned
            printf("Direção do vento: %huº\n",wind_dir);	//short
            printf("Humidade atmosférica: %u%%\n",humd_atm);
            printf("Humidade do solo: %u%%\n",humd_grnd);
            printf("Pluviosidade: %umm\n",rain);
            printf("----------------------------\n");

            sleep(sec);
        }

    }

    else
    {
        printf("An error occured initializing the random number generator.\n");
    }
}