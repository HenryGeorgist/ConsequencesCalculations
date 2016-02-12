/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComputableObjects;

import Utils.ConsequencesErrorReport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *This is a fully defined occupancy type, it has all of the uncertainty defined.  It can produce Immutable Occupancy Types based on a seed. It is used for editing validating and writing to file.
 * @author Will_and_Sara
 */
public class MutableOccupancyType implements Utils.ISerializeToXMLElement, Utils.IValidate{
//<editor-fold defaultstate="collapsed" desc="Private Variables">
    private String _Name;
    private String _Description;
    private DamageCategory _DamageCategory;
    private int _NumberOfStories;
    private double _NumberOfHouseholds;//continuous distribution?
    private boolean _hasStructureDamage;
    private TabularFunctions.ISampleWithUncertainty _StructureDamageFunction;
    private boolean _hasContentDamage;
    private TabularFunctions.ISampleWithUncertainty _ContentDamageFunction;
    private boolean _hasCarDamage;
    private TabularFunctions.ISampleWithUncertainty _CarDamageFunction;
    private boolean _hasOtherDamage;
    private TabularFunctions.ISampleWithUncertainty _OtherDamageFunction;
    private Distributions.ContinuousDistribution _FoundationHeight;
    private Distributions.ContinuousDistribution _StructureValueUncertainty;
    private Distributions.ContinuousDistribution _ContentValueUncertainty;
    private Distributions.ContinuousDistribution _CarValueUncertainty;
    private Distributions.ContinuousDistribution _OtherValueUncertainty;
    
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Property Access">
    public String getName(){return _Name;}
    public void Rename(String NewName){_Name = NewName;}
    public DamageCategory getDamageCategory(){return _DamageCategory;}
    public void setDamageCategory(DamageCategory DamCat){_DamageCategory = DamCat;}
    public boolean HasStructureDamage(){return _hasStructureDamage;}
    public void setHasStructureDamage(boolean value){_hasStructureDamage = value;}
    public TabularFunctions.ISampleWithUncertainty getStructureDamageFunction(){return _StructureDamageFunction;}
    public void setStructureDamageFunction(TabularFunctions.ISampleWithUncertainty NewDamageFunction){_StructureDamageFunction = NewDamageFunction;}
    public boolean HasContentDamage(){return _hasContentDamage;}
    public void setHasContentDamage(boolean value){_hasContentDamage = value;}
    public TabularFunctions.ISampleWithUncertainty getContentDamageFunction(){return _ContentDamageFunction;}
    public void setContentDamageFunction(TabularFunctions.ISampleWithUncertainty NewDamageFunction){_ContentDamageFunction = NewDamageFunction;}
    public boolean HasCarDamage(){return _hasCarDamage;}
    public void setHasCarDamage(boolean value){_hasCarDamage = value;}
    public TabularFunctions.ISampleWithUncertainty getCarDamageFunction(){return _CarDamageFunction;}
    public void setCarDamageFunction(TabularFunctions.ISampleWithUncertainty NewDamageFunction){_CarDamageFunction = NewDamageFunction;}
    public boolean HasOtherDamage(){return _hasOtherDamage;}
    public void setHasOtherDamage(boolean value){_hasOtherDamage = value;}
    public TabularFunctions.ISampleWithUncertainty getOtherDamageFunction(){return _OtherDamageFunction;}
    public void setOtherDamageFunction(TabularFunctions.ISampleWithUncertainty NewDamageFunction){_OtherDamageFunction = NewDamageFunction;}
//</editor-fold>
    public ImmutableOccupancyType GetOcctypeSample(long Seed){
        java.util.Random R = new java.util.Random(Seed);
        //sample all of the curves
        ImmutableOccupancyType Output = new ImmutableOccupancyType(_Name, _DamageCategory);
        if(_hasStructureDamage){
            Output.SetStructureDamageFunction(_StructureDamageFunction.CurveSample(R.nextDouble()));
        }else{
            R.nextDouble();//to ensure reproducability if users change number of curves between alternatives.
        }
        if(_hasContentDamage){
            Output.SetContentDamageFunction(_ContentDamageFunction.CurveSample(R.nextDouble()));
        }else{
            R.nextDouble();//to ensure reproducability if users change number of curves between alternatives.
        }
        if(_hasCarDamage){
            Output.SetCarDamageFunction(_CarDamageFunction.CurveSample(R.nextDouble()));
        }else{
            R.nextDouble();//to ensure reproducability if users change number of curves between alternatives.
        }
        if(_hasOtherDamage){
            Output.SetOtherDamageFunction(_OtherDamageFunction.CurveSample(R.nextDouble()));
        }else{
            R.nextDouble();//to ensure reproducability if users change number of curves between alternatives.
        }
        //sample the coefficants of variation.
        //foundation height
        //structure value
        //lethality thresholds?
        return Output;
    }
    @Override
    public void ReadFromXMLElement(Element ele) {
        //need to match LifeSim, FIA, and GeoFDA's spec.
        _Name = ele.getAttribute("Name");
        //what if the node list has no values?
        _Description = ele.getElementsByTagName("Description").item(0).getNodeValue();
        _DamageCategory.ReadFromXMLElement((Element)ele.getElementsByTagName("DamageCategory").item(0));//not sure this works.
        _NumberOfStories = Integer.parseInt(ele.getElementsByTagName("NumberOfStories").item(0).getNodeValue());
        _NumberOfHouseholds = Double.parseDouble(ele.getElementsByTagName("NumberOfHouseHolds").item(0).getNodeValue());
        
        _FoundationHeight.ReadFromXML((Element)ele.getElementsByTagName("FoundationHeightUncertainty").item(0).getChildNodes().item(0));
        _StructureValueUncertainty.ReadFromXML((Element)ele.getElementsByTagName("StructureUncertainty").item(0).getChildNodes().item(0));
        _ContentValueUncertainty.ReadFromXML((Element)ele.getElementsByTagName("ContentUncertainty").item(0).getChildNodes().item(0));
        _OtherValueUncertainty.ReadFromXML((Element)ele.getElementsByTagName("OtherUncertainty").item(0).getChildNodes().item(0));
        _CarValueUncertainty.ReadFromXML((Element)ele.getElementsByTagName("VehicleUncertainty").item(0).getChildNodes().item(0));
        
        //depth damage relationships and damage compute booleans.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Element WriteToXMLElement() {
        try {
            //need to match LifeSim, FIA, and GeoFDA's spec.
            DocumentBuilderFactory d = DocumentBuilderFactory.newInstance();
            DocumentBuilder Db;
            Db = d.newDocumentBuilder();
            Document doc = Db.newDocument();
            Element Occtype = doc.createElement("OccupancyType");
            Occtype.setAttribute("Name", _Name);
            
            Element description = doc.createElement("Description");
            description.setNodeValue(_Description);
            Occtype.appendChild(description);
            
            Occtype.appendChild(_DamageCategory.WriteToXMLElement());
            
            Element NS = doc.createElement("NumberOfStories");
            NS.setNodeValue(Integer.toString(_NumberOfStories));
            Occtype.appendChild(NS);
            
            Element NH = doc.createElement("NumberOfHouseholds");
            NH.setNodeValue(Double.toString(_NumberOfHouseholds));
            Occtype.appendChild(NH);
            
            Element FH = doc.createElement("FoundationHeightUncertainty");
            FH.appendChild(_FoundationHeight.WriteToXML());
            Occtype.appendChild(FH);
            
            Element SU = doc.createElement("StructureUncertainty");
            SU.appendChild(_StructureValueUncertainty.WriteToXML());
            Occtype.appendChild(SU);
            
            Element CU = doc.createElement("ContentUncertainty");
            CU.appendChild(_ContentValueUncertainty.WriteToXML());
            Occtype.appendChild(CU);
            
            Element OU = doc.createElement("OtherUncertainty");
            OU.appendChild(_OtherValueUncertainty.WriteToXML());
            Occtype.appendChild(OU);
            
            Element VU = doc.createElement("VehicleUncertainty");
            VU.appendChild(_CarValueUncertainty.WriteToXML());
            Occtype.appendChild(VU);
            
            Element StructDD = doc.createElement("StructureDD");
            StructDD.setAttribute("CalculateDamage", Boolean.toString(_hasStructureDamage));
            if(_hasStructureDamage){//should i save any data regardless?
                if(_StructureDamageFunction.GetYDistributions().size()>0){
                    //StructDD.appendChild(_StructureDamageFunction.WriteToXML());  
                }
            }
            Occtype.appendChild(StructDD);
            
            Element ContentDD = doc.createElement("ContentDD");
            ContentDD.setAttribute("CalculateDamage", Boolean.toString(_hasContentDamage));
            if(_hasContentDamage){//should i save any data regardless?
                if(_ContentDamageFunction.GetYDistributions().size()>0){
                    //ContentDD.appendChild(_ContentDamageFunction.WriteToXML());  
                }
            }
            Occtype.appendChild(ContentDD);
            
            Element OtherDD = doc.createElement("OtherDD");
            OtherDD.setAttribute("CalculateDamage", Boolean.toString(_hasOtherDamage));
            if(_hasOtherDamage){//should i save any data regardless?
                if(_OtherDamageFunction.GetYDistributions().size()>0){
                    //OtherDD.appendChild(_OtherDamageFunction.WriteToXML());  
                }
            }
            Occtype.appendChild(OtherDD);
            
            Element CarDD = doc.createElement("VehicleDD");
            CarDD.setAttribute("CalculateDamage", Boolean.toString(_hasCarDamage));
            if(_hasCarDamage){//should i save any data regardless?
                if(_CarDamageFunction.GetYDistributions().size()>0){
                    //CarDD.appendChild(_CarDamageFunction.WriteToXML());  
                }
            }
            Occtype.appendChild(CarDD);
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            //return Occtype;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MutableOccupancyType.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    @Override
    public ConsequencesErrorReport Validate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
