package com.pemda.ekinerjademo;

import com.pemda.ekinerjademo.service.impl.DataSynchronizerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EkinerjademoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx =
				SpringApplication.run(EkinerjademoApplication.class, args);

//		DataSynchronizerImpl dataSynchronizer
//				= (DataSynchronizerImpl) ctx.getBean("DataSynchronizer");
//		dataSynchronizer.start(); //test clone

	}
}
