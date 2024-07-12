package com.iglesia.market.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class Utileria {

	public static String guardarArchivo(MultipartFile multipart, String ruta) {
		// Obtenemos el nombre original del Archivo

		String nombreOriginal = multipart.getOriginalFilename();
		// Esto permite quitar los espacios de los nombres de las imagenes
		// nombreOriginal.replaceAll(" ", "-");
		nombreOriginal = nombreOriginal.replaceAll(" ", "-");
		String nombreFinal = randomAlphaNumeric(8) + nombreOriginal;
		try {
			// Formamos el nombre del archivo para guardarlo en el disco duro
			File imageFile = new File(ruta + nombreFinal);
			System.out.println("Archivo:" + imageFile.getAbsolutePath());
			// Guardamos fisicamente el archivo en HD
			multipart.transferTo(imageFile);
			return nombreFinal;

		} catch (IOException e) {
			System.out.println("Error" + e.getMessage());
			return null;
		}
	}

	public static String randomAlphaNumeric(int count) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));

		}
		return builder.toString();
	}

}
