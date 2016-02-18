/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComputableObjects;

import Utils.ConsequencesErrorReport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Will_and_Sara
 */
public class OccupancyTypeCollection implements Utils.ISerializeToXMLElement, Utils.IValidate{
    private ArrayList<MutableOccupancyType> _Occtypes;
    private String _FilePath;
    public ArrayList<MutableOccupancyType> getOccupancyTypes(){return _Occtypes;}
    public ArrayList<ImmutableOccupancyType> getOccupancyTypeSample(long seed){
        ArrayList<ImmutableOccupancyType> result = new ArrayList<>();
        java.util.Random R = new java.util.Random(seed);
        _Occtypes.stream().forEach((OT) -> {
            result.add(OT.GetOcctypeSample(R.nextLong()));
        });
        return result;
    }
    public OccupancyTypeCollection(String Path){
        try {
            _FilePath = Path;
            java.io.File fXmlFile = new java.io.File(_FilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            _Occtypes = new ArrayList<>();
            ReadFromXMLElement((Element)doc.getElementsByTagName("OccupancyTypes").item(0));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(OccupancyTypeCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void ReadFromXMLElement(Element ele) {
        //loop through all elements and create occupancy types and add them to the list.
    }

    @Override
    public Element WriteToXMLElement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ConsequencesErrorReport Validate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
