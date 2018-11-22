package com.example.bee.form;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistForm {
	private String username;
	private String email;
	private Long age;
	private String location;
	private String password;
}
