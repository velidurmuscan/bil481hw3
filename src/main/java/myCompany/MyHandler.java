package myCompany;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {

	private List<Entity> entityList = null;
	private Entity ent = null;
	private StringBuilder data = null;

	public List<Entity> getEntityList() {
		return entityList;
	}

	boolean bFirstName = false;
	boolean bLastName = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equals("ENTITY")) {
			String id_S = attributes.getValue("Id");
			ent = new Entity();
			ent.setId(Integer.parseInt(id_S));
			if (entityList == null)
				entityList = new ArrayList<>();
		} else if (qName.equalsIgnoreCase("FIRSTNAME")) {
			bFirstName = true;
		} else if (qName.equalsIgnoreCase("LASTNAME")) {
			bLastName = true;
		}

		data = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (bFirstName) {
			ent.setFirstName(data.toString());
			bFirstName = false;
		} else if (bLastName) {
			ent.setLastName(data.toString());
			bLastName = false;
		}		
		if (qName.equals("ENTITY")) {
			entityList.add(ent);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		data.append(new String(ch, start, length));
	}
}