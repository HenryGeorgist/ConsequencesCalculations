/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComputableObjects;

import Utils.ConsequencesErrorReport;
import org.w3c.dom.Element;

/**
 *This is a fully defined occupancy type, it has all of the uncertainty defined.  It can produce Immutable Occupancy Types based on a seed. It is used for editing validating and writing to file.
 * @author Will_and_Sara
 */
public class MutableOccupancyType implements Utils.ISerializeToXMLElement, Utils.IValidate{
//<editor-fold defaultstate="collapsed" desc="Private Variables">
    private String _Name;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Element WriteToXMLElement() {
        //need to match LifeSim, FIA, and GeoFDA's spec.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public ConsequencesErrorReport Validate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
