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
    private String _Name;
    private DamageCategory _DamageCategory;
    private boolean _hasStructureDamage;
    private TabularFunctions.ISampleWithUncertainty _StructureDamageFunction;
    private boolean _hasContentDamage;
    private TabularFunctions.ISampleWithUncertainty _ContentDamageFunction;
    private boolean _hasCarDamage;
    private TabularFunctions.ISampleWithUncertainty _CarDamageFunction;
    private boolean _hasOtherDamage;
    private TabularFunctions.ISampleWithUncertainty _OtherDamageFunction;
    private Distributions.ContinuousDistribution _FoundationHeight;
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
