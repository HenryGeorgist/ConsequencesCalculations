/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComputableObjects;

import Utils.ConsequencesErrorReport;

/**
 *
 * @author Will_and_Sara
 */
public class ConsequencesStructure implements Utils.IValidate {
    private String _Name;
    private ImmutableOccupancyType _Occtype;//ensure this is a pointer.
    private double _MeanStructureValue;
    private double _MeanContentValue;
    private double _MeanCarValue;
    private double _MeanOtherValue;
    private double _MeanFoundationHeight;
    private int _PopulationU65Day;
    private int _PopulationU65Night;
    private int _PopulationO65Day;
    private int _PopulationO65Night;
    private int _NumberOfStories;
    public final double ComputeStructureDamage(double DepthAboveGround){
        //need to add all sorts of logic, building stability, reconstruction etc.
        double fh = _MeanFoundationHeight + (_MeanFoundationHeight * _Occtype.FoundationHeightCoV());
        double structval = _MeanStructureValue + (_MeanStructureValue * _Occtype.StructureValueCoV());
        return structval * _Occtype.StructureDamagePercent(DepthAboveGround - fh);
    }
    @Override
    public ConsequencesErrorReport Validate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
