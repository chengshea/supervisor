package com.cs.tool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TES {
 
	public static void main(String[] args) {
		 LocalDate today = LocalDate.now();
		 System.err.println(today);
		 //Current Date using LocalDate and LocalTime
		 LocalDateTime  toda = LocalDateTime.of(LocalDate.now(), LocalTime.now());
	        System.out.println("Current DateTime="+toda);
	}
}
