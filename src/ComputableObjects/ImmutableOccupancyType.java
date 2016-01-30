/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComputableObjects;

/**
 *This is a trimmed down version of the occupancy type, it is used for computation.
 * @author Will_and_Sara
 */
public class ImmutableOccupancyType {
    private String _Name;
    private DamageCategory _DamageCategory;
    private TabularFunctions.ISampleDeterministically _StructureDamageFunction;
    private TabularFunctions.ISampleDeterministically _ContentDamageFunction;
    private TabularFunctions.ISampleDeterministically _CarDamageFunction;
    private TabularFunctions.ISampleDeterministically _OtherDamageFunction;
    private double _FoundationHeightCoV;//coefficent of variation.
    private double _StructureValueCoV;
    private double _ContentValueCoV;
    private double _CarValueCoV;
    private double _OtherValueCoV;
    public final double FoundationHeightCoV(){return _FoundationHeightCoV;}
    public final double StructureValueCoV(){return _StructureValueCoV;}
    public final double ContentValueCoV(){return _ContentValueCoV;}
    public final double CarValueCoV(){return _CarValueCoV;}
    public final double OtherValueCoV(){return _OtherValueCoV;}
    public double StructureDamagePercent(double DepthAboveFFE){
        return _StructureDamageFunction.GetYFromX(DepthAboveFFE);
    }
    public double ContentDamagePercent(double DepthAboveFFE){
        return _StructureDamageFunction.GetYFromX(DepthAboveFFE);
    }
    public double CarDamagePercent(double DepthAboveCarOffset){
        return _StructureDamageFunction.GetYFromX(DepthAboveCarOffset);
    }
    public double OtherDamagePercent(double DepthAboveFFE){
        return _StructureDamageFunction.GetYFromX(DepthAboveFFE);
    }
}
