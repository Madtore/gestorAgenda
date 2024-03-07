package gestorAgenda.agenda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Agenda {

	private List<Contacto> conctacList;
	private String ruta;

	public Agenda(String ruta) {
		this.ruta = ruta;
		conctacList = new ArrayList<Contacto>();
	}

	public void addContact(Contacto con) {
		conctacList.add(con);
	}

	public void saveContactToFile() {

		File fos = new File(ruta);
		File directory = fos.getParentFile();

		try {
			if (!directory.exists()) {
				if (directory.mkdirs()) {
					System.out.println("Directorio creado");
				} else {
					throw new IOException("Directorio no creado");
				}
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(fos))) {
				for (Contacto c : conctacList) {
					bw.append("[")
					.append("Name:")
					.append(c.getName())
					.append(",")
					.append("Phone:")
					.append(c.getPhone())
					.append(",")
					.append("Email:")
					.append(c.getEmail())
					.append("]")
					.append(",");
					bw.newLine();
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void loadContacts() {
		try (BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(ruta)))) {
			String line;
			while ((line = br.readLine()) != null) {
				String name = "";
				String phone = "";
				String email = "";
				String[] key = line.split(",");
				
				for(String keyValue : key) {
			        String[] attributeValue = keyValue.split(":");
			        String attribute = attributeValue[0];
			        String value = attributeValue[1];

			        if(attribute.contains("Name")) {
			        	name = value;
			        }
			        if(attribute.contains("Phone")) {
			        	phone = value;
			        }
			        if(attribute.contains("Email")) {
			        	email = value;
			        }
			    }
				this.addContact(new Contacto(name, phone, email));
				
			
				
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void listContacts() {
		System.out.println("Borrando");
		conctacList.removeAll(conctacList);
		if(conctacList.isEmpty()) {
			System.out.println("Cargando");
			loadContacts();
		}
		for (Contacto c : conctacList) {
			System.out.println(c.toString());
			
		}
	
	}
	
	public void filterGmail() {
		conctacList.stream()
		.filter(e-> e.getEmail().contains("@gmail.com"))
		.forEach(System.out::println);;
	}
}
