/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComputableObjects;

import org.w3c.dom.Element;

/**
 *
 * @author Will_and_Sara
 */
public class DamageCategory implements Utils.ISerializeToXMLElement{
    private String _Name;
    private int _ReconstructionPeriod;//in days...
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
