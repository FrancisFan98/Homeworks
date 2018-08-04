#include <stdio.h>
#include "lcd.h"

tod_t tod;
/*
int set_tod_from_secs(int time_of_day_sec, tod_t *tod){
// Accepts time of day in seconds as an argument and modifies the
// struct pointed at by tod to fill in its hours, minutes,
// etc. fields.  If time_of_day_sec is invalid (negative or larger
// than the number of seconds in a day) does not tod and returns 1 to
// indicate an error. Otherwise returns 0 to indicate success.
	if(time_of_day_sec <0 || time_of_day_sec > 86400){
		return 1;
	}
	tod->hours = (time_of_day_sec/3600)%12;
	if (tod->hours == 0) {
		tod->hours = 12;
	}

	tod->minutes = time_of_day_sec%3600/60;
	tod->seconds = time_of_day_sec%60;
	tod->ispm = time_of_day_sec/43200;
	return 0;

}*/

int set_display_bits_from_tod(tod_t tod, int *display){
// Accepts a tod and alters the bits in the int pointed at by display
// to reflect how the LCD clock should appear. If any fields of tod
// are negative or too large (e.g. bigger than 12 for hours, bigger
// than 59 for min/sec), no change is made to display and 1 is
// returned to indicate an error. Otherwise returns 0 to indicate
// success.
//
// May make use of an array of bit masks corresponding to the pattern
// for each digit of the clock to make the task easier.
	if(tod.hours > 12 || tod.minutes > 59 || tod.seconds > 59){
		return 1;
	}
	int bitRep[10] = {0b0111111, 0b0000110, 0b1011011,
	0b1001111, 0b1100110, 0b1101101, 0b1111101,
	0b0000111, 0b1111111, 0b1101111};




	int min_ones = tod.minutes%10;
	int min_tens = tod.minutes/10;

	int hour_ones = tod.hours%10;
	int hour_tens = tod.hours/10;


	int state = 0b00;

	if(tod.ispm){
		state = 0b10;
	}
	else {
		state = 0b01;
	}

	if (hour_tens) {
		state = state << 7;
		state += bitRep[hour_tens];
	}
	else {
		state = state << 7;
	}
	state = state << 7;
	state += bitRep[hour_ones];
	state = state << 7;
	state += bitRep[min_tens];
	state = state << 7;
	state += bitRep[min_ones];
	*display = state;
	return 0;
}

int lcd_update(){
	int a = set_tod_from_secs(TIME_OF_DAY_SEC, &tod);
	int b = set_display_bits_from_tod(tod, &LCD_DISPLAY_PORT);

	if(a||b){
		LCD_DISPLAY_PORT = ~0b0;
		return 1;
	}
	return 0;
}
