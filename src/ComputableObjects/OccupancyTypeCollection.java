/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComputableObjects;

import Utils.ConsequencesErrorReport;
import java.util.ArrayList;
import org.w3c.dom.Element;

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
        for(MutableOccupancyType OT : _Occtypes){
            result.add(OT.GetOcctypeSample(R.nextLong()));
        }
        return result;
    }
    @Override
    public void ReadFromXMLElement(Element ele) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
