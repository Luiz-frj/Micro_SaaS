package br.edu.ifsp.MicroSaaS.model;

import java.security.MessageDigest;

public class Cliente {
	private int id;
	private String name;
	private String user;
	private String email;
	private String phone;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Cliente(String name, String user, String email, String phone, String password, Boolean newClient) {
		setName(name);
		setUser(user);
		setEmail(email);
		setPhone(phone);
		if (newClient) {
			setPassword(sha256(password));
		} else {
			setPassword(password);
		}
	}

	public Boolean verify(String password) {
		if (sha256(password).equals(this.password)) {
			return true;
		}
		return false;
	}
	
	private static String sha256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(password.getBytes());

            StringBuilder string_builder = new StringBuilder();
            for (byte b : bytes) {
                String h = Integer.toHexString(0xff & b);
                if (h.length() == 1) {
                	string_builder.append('0');
                }
                string_builder.append(h);
            }
            return string_builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", name=" + name + ", user=" + user + ", email=" + email + ", phone=" + phone
				+ "]";
	}
}