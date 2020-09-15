package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import model.Apartman;

public class ApartmanDAO {

	private HashMap<UUID, Apartman> apartmani = new HashMap<UUID, Apartman>();
	private String path;
	
	public ApartmanDAO(String path) {
		this.path = path;
		this.loadApartmani("");
		
	}

	public HashMap<UUID, Apartman> getApartmani() {
		return apartmani;
	}

	public void setApartmani(HashMap<UUID, Apartman> apartmani) {
		this.apartmani = apartmani;
	}

	public Apartman findOneApartman(UUID id) {
		return this.apartmani.get(id);
		
	}
	
	public void saveApartmani(String contextPath) {
		File f = new File(this.path + "\\data\\apartman.txt");
		FileWriter fileWriter = null;
		try {
			//f.createNewFile();
			fileWriter = new FileWriter(f);

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			String sUsers = objectMapper.writeValueAsString(apartmani);
			fileWriter.write(sUsers);
			fileWriter.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void loadApartmani(String contextPath) {
		FileWriter fileWriter = null;
		BufferedReader in = null;
		File file = null;
		try {
			file = new File(this.path + "\\data\\apartman.txt");
			in = new BufferedReader(new FileReader(file));

			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setVisibilityChecker(
					VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
			TypeFactory factory = TypeFactory.defaultInstance();
			MapType type = factory.constructMapType(HashMap.class, UUID.class, Apartman.class);
			objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			apartmani = ((HashMap<UUID, Apartman>) objectMapper.readValue(file, type));
		} catch (FileNotFoundException fnfe) {
			try {
				file.createNewFile();
				fileWriter = new FileWriter(file);
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.getFactory().configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
				String stringApartman = objectMapper.writeValueAsString(apartmani);
				fileWriter.write(stringApartman);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fileWriter != null) {
					try {
						fileWriter.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
