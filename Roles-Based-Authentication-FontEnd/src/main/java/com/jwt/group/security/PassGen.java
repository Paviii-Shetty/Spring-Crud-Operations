package com.jwt.group.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGen {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println(encoder.encode("suresh"));
		System.out.println(encoder.encode("ramesh"));
		System.out.println(encoder.encode("nilesh"));
	}
}
/*
 * $2a$10$crHPQ/4os/LgyUR.xaeT6.oC3Rw6Hi2p93GdSwzMFkHrwHtxByaUK
 * 
 * $2a$10$c/ldpoTG4e/oijAxKzEtj.0VkDgHylHwhdsBqaX7V6WobvcXMihNS
 */