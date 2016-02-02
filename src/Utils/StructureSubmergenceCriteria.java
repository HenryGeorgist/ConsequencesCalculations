/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Will_and_Sara
 */
public class StructureSubmergenceCriteria implements IValidate{
    private Distributions.ContinuousDistribution _ChanceZoneThreshold;
    private Distributions.ContinuousDistribution _CompromizedZoneThreshold;
    private Distributions.ContinuousDistribution _SafeZoneThreshold;

    @Override
    public ConsequencesErrorReport Validate() {
        //ensure that chance is higher than compromized zone is higher than safe zone
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
