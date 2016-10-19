package controller;

import model.*;

import static controller.XMLTags.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

//JAXB, XMLStreams
public class ReadFile
{
    public static Bus readFile(File file)
    {
        Bus bus = new Bus();

        try 
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
            Document document = null;
            try {
                dBuilder = dbFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }
            try {
                if (dBuilder != null) {
                    document = dBuilder.parse(file);
                }
            } catch (SAXException | IOException e) {
                e.printStackTrace();
            }

            if (document != null) {
                document.getDocumentElement().normalize();
            }
            NodeList nList = null;
            if (document != null) {
                nList = document.getElementsByTagName(PLAYER_TAG);
            }

            assert nList != null;
            Element playerPositionElement = (Element) ((Element) nList.item(0)).getElementsByTagName(POSITION_TAG).item(0);
            Element playerPointElement = (Element) playerPositionElement.getElementsByTagName(POINT_TAG).item(0);
            bus.add(new Player(Integer.parseInt(playerPointElement.getAttribute(X_ATTRIB)),
                    Integer.parseInt(playerPointElement.getAttribute(Y_ATTRIB))));

            nList = document.getElementsByTagName(INSPECTOR_TAG);
            for (int inspectorIndex = 0; inspectorIndex < nList.getLength(); inspectorIndex++) {
                Element inspectorPositionElement =
                        (Element) ((Element) nList.item(inspectorIndex)).getElementsByTagName(POSITION_TAG).item(0);
                Element inspectorPointElement = (Element) inspectorPositionElement.getElementsByTagName(POINT_TAG).item(0);
                bus.add(new Inspector(Integer.parseInt(inspectorPointElement.getAttribute(X_ATTRIB)),
                        Integer.parseInt(inspectorPointElement.getAttribute(Y_ATTRIB))));
            }

            nList = document.getElementsByTagName(SEAT_TAG);
            for (int seatIndex = 0; seatIndex < nList.getLength(); seatIndex++) {
                Element seatPositionElement = (Element) ((Element) nList.item(seatIndex)).getElementsByTagName(POSITION_TAG).item(0);
                Element seatPointElement = (Element) seatPositionElement.getElementsByTagName(POINT_TAG).item(0);
                bus.add(new Seat(Integer.parseInt(seatPointElement.getAttribute(X_ATTRIB)),
                        Integer.parseInt(seatPointElement.getAttribute(Y_ATTRIB))));
            }

            nList = document.getElementsByTagName(DOOR_TAG);
            for (int doorIndex = 0; doorIndex < nList.getLength(); doorIndex++) {
                Element doorPositionElement = (Element) ((Element) nList.item(doorIndex)).getElementsByTagName(POSITION_TAG).item(0);
                Element doorPointElement = (Element) doorPositionElement.getElementsByTagName(POINT_TAG).item(0);
                bus.add(new Door(Integer.parseInt(doorPointElement.getAttribute(X_ATTRIB)),
                        Integer.parseInt(doorPointElement.getAttribute(Y_ATTRIB))));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return bus;
    }
}
