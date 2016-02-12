/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComputableObjects;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Will_and_Sara
 */
public class DamageCategory implements Utils.ISerializeToXMLElement{
    private String _Name;
    private String _Description;
    private int _ReconstructionPeriod;//in days...
    private double _CostFactor;
    public String Name(){return _Name;}
    public int ReconstructionPeriod(){return _ReconstructionPeriod;}
    public DamageCategory(String DamCatName, int DaysToRebuild){
        _Name = DamCatName;
        _ReconstructionPeriod = DaysToRebuild;
    }
    public DamageCategory(String DamCatName){
        _Name = DamCatName;
        _ReconstructionPeriod = 365;//default to 1 year.
    }
    public DamageCategory(){
        //default constructor.
        _Name = "";
        _ReconstructionPeriod = 365;
    }
    @Override
    public void ReadFromXMLElement(Element ele) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Element WriteToXMLElement() {
        try {
            DocumentBuilderFactory d = DocumentBuilderFactory.newInstance();
            DocumentBuilder Db;
            Db = d.newDocumentBuilder();
            Document doc = Db.newDocument();
            Element DamCat = doc.createElement("DamageCategory");
            
            Element NameString = doc.createElement("Name");
            NameString.setNodeValue(_Name);
            DamCat.appendChild(NameString);
            
            Element description = doc.createElement("Description");
            description.setNodeValue(_Description);
            DamCat.appendChild(description);
            
            Element Rebuild = doc.createElement("Rebuild");
            Rebuild.setNodeValue(Integer.toString(_ReconstructionPeriod));
            DamCat.appendChild(Rebuild);
            
            Element CostFactor = doc.createElement("CostFactor");
            CostFactor.setNodeValue(Double.toString(_CostFactor));
            DamCat.appendChild(CostFactor);
            return DamCat;
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DamageCategory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
