/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComputableObjects;

import java.util.ArrayList;

/**
 *
 * @author Will_and_Sara
 */
public class StructureInventory {
    private ArrayList<ConsequencesStructure> _Structures;
    private ComputableObjects.OccupancyTypeCollection _OccupancyTypes;
    //intialize from NSI
    //initialize from point shapefile path
    public void SetHydraulicEvents(){
        //for every structure determine the hydraulic inputs... only should happen once per compute
    }
    public double ReComputeSpecificIteration(int IterationNumber, long initialseed){
        java.util.Random SimulationRandom = new java.util.Random(initialseed);
        for(int i = 0; i<IterationNumber;i++){
            SimulationRandom.nextInt();
        }
        return ComputeIteration(SimulationRandom.nextInt());
    }
    public void ComputeSimulation(long initialseed, int miniterations, double confidenceinterval, double convergencetolerance){
        java.util.Random SimulationRandom = new java.util.Random(initialseed);
        MomentFunctions.BasicProductMomentsHistogram BPMH = new MomentFunctions.BasicProductMomentsHistogram(500, 0, 1000000000.0);//random max estimate...
        //set convergence criteria?
        BPMH.SetConvergenceTolerance(convergencetolerance);
        BPMH.SetMinValuesBeforeConvergenceTest(10);
        BPMH.SetZAlphaForConvergence(confidenceinterval);
        do{
            BPMH.AddObservation(ComputeIteration(SimulationRandom.nextLong()));
        }while(!BPMH.IsConverged());
    }
    private double ComputeIteration(long seed){
        java.util.Random R = new java.util.Random(seed);
        //sample all of the occupancy types
        ArrayList<ImmutableOccupancyType> OccTypes = _OccupancyTypes.getOccupancyTypeSample(R.nextLong());
        //reset the structure occupancytype pointers?
        //compute damage
        //record results?
        //tally results?
        
        return 0;
    }
    public void ComputeDeterministically(){
        //boring...
    }
}
