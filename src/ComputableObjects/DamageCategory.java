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
import org.w3c.dom.Node;

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
    public DamageCategory(Element ele){
        ReadFromXMLElement(ele);
    }
    @Override
    public final void ReadFromXMLElement(Element ele) {
        if(ele.hasAttribute("Name")){_Name = ele.getAttribute("Name");}
        if(ele.hasAttribute("Description")){_Description = ele.getAttribute("Description");}
        if(ele.hasAttribute("Rebuild")){_ReconstructionPeriod = Integer.parseInt(ele.getAttribute("Rebuild"));}
        if(ele.hasAttribute("CostFactor")){_CostFactor = Double.parseDouble(ele.getAttribute("CostFactor"));}
    }
    @Override
    public Element WriteToXMLElement() {
        try {
            DocumentBuilderFactory d = DocumentBuilderFactory.newInstance();
            DocumentBuilder Db;
            Db = d.newDocumentBuilder();
            Document doc = Db.newDocument();
            Element DamCat = doc.createElement("DamageCategory");
            DamCat.setAttribute("Name", _Name);
            DamCat.setAttribute("Description", _Description);
            DamCat.setAttribute("Rebuild", Integer.toString(_ReconstructionPeriod));
            DamCat.setAttribute("CostFactor",Double.toString(_CostFactor));
            return DamCat;
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DamageCategory.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
